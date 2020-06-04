package com.instabot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instabot.domain.InstaProfile;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.service.InstaScrapperService;
import com.instabot.webdriver.InstaParser;
import com.instabot.webdriver.SeleniumLoader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lezalekss
 */
@Slf4j
@Service
public class InstaScrapperServiceImpl implements InstaScrapperService {
    @Value("${profiles-json}")
    private String profilesJsonFile;
    /**
     * Instagram bot profile username. It's configured in application.properties
     */
    @Value("${profile-username}")
    private String username;
    /**
     * Instagram bot profile password. It's configured in application.properties
     */
    @Value("${profile-password}")
    private String password;
    private final String profileInfoApi = "https://www.instagram.com/%s/?__a=1";
    @Autowired
    private SeleniumLoader loader;
    @Autowired
    private InstaParser instaParser;
    @Autowired
    private InstaProfileRepository profileRepository;
    @Autowired
    private ProfileStatsRepository profileStatsRepository;

    @Override
    public void startLikes() {
        WebDriver driver = loader.setUp();
        instaParser.login(driver, username, password);
        getProfiles().forEach(profile-> instaParser.likePosts(driver, profile.getUsername()));
        loader.tearDown(driver);
    }

    @Override
    @Transactional
    public void collectStats() {
        WebDriver driver = loader.setUp();
        instaParser.login(driver, username, password);
        getProfiles().stream().map(profile -> instaParser.collectStats(driver, profile)).forEach(stats -> {
            profileRepository.save(stats.getProfile());
            profileStatsRepository.save(stats);
        });
        loader.tearDown(driver);
    }

    @Override
    public InstaProfile addProfile(String username, boolean toLike) {
        RestTemplate template = new RestTemplate();
        InstaProfile profile = null;
        String profileInfoUrl = String.format(profileInfoApi, username);
        try {
            ResponseEntity<String> response = template.getForEntity(profileInfoUrl, String.class);
            JsonNode user = (new ObjectMapper()).readTree(Objects.requireNonNull(response.getBody())).get("graphql").get("user");
            boolean isPrivate = user.get("is_private").booleanValue();
            String imgUrl = user.get("profile_pic_url_hd").toString();
            int followers = user.get("edge_followed_by").get("count").intValue();
            int following = user.get("edge_follow").get("count").intValue();
            int posts = user.get("edge_owner_to_timeline_media").get("count").intValue();
            profile = InstaProfile.builder().
                    username(username).isPrivate(isPrivate).toLike(toLike).imgUrl(imgUrl).followers(followers).following(following).posts(posts).build();
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error with parsing json from instagram api for user: " +username);
        }
        profileRepository.save(profile);
        log.info(profile.toString());
        return profile;
    }


    private List<InstaProfile> getProfiles() {
        List<InstaProfile> profiles = new ArrayList<>();
       /* try {
            profiles = Utils.readJsonStringArray(profilesJsonFile, "usernames");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }*/
        profiles = profileRepository.findAll();
        return profiles;
    }
}
