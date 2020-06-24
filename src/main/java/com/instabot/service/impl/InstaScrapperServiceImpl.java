package com.instabot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.parser.InstaParser;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.rest.dto.request.ProfileConfig;
import com.instabot.service.InstaCollectorService;
import com.instabot.service.InstaScrapperService;
import com.instabot.parser.InstaParserSelenium;
import com.instabot.webdriver.SeleniumLoader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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
    @Autowired
    private SeleniumLoader loader;
    @Autowired
    private InstaParserSelenium instaParserSelenium;
    @Autowired
    private InstaProfileRepository profileRepository;
    @Autowired
    private ProfileStatsRepository profileStatsRepository;
    @Autowired
    private InstaParser instaParser;
    @Autowired
    private InstaCollectorService collectorService;

    @Override
    public void startLikes() {
        WebDriver driver = loader.setUp();
        try {
            instaParserSelenium.login(driver, username, password);
            getProfiles().stream().filter(InstaProfile::isToLike).forEach(profile -> instaParserSelenium.likePosts(driver, profile.getUsername()));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            loader.tearDown(driver);
        }
    }

    @Override
    @Transactional
    public void collectStats() {
        WebDriver driver = loader.setUp();
        try {
            instaParserSelenium.login(driver, username, password);
            getProfiles().stream().map(profile -> instaParserSelenium.collectStats(driver, profile)).forEach(stats -> {
                collectorService.saveProfile(stats.getProfile());
                profileStatsRepository.save(stats);
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            loader.tearDown(driver);
        }
    }

    @Override
    public ProfileStats scrapNewProfile(ProfileConfig profileConfig) {
        ProfileStats profileStats = instaParser.scrapNewProfile(profileConfig);
        if(profileStats!=null)
            saveProfileStatsAsync(profileStats, profileConfig.isToFollow());
        return profileStats;
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

    @Async
    void saveProfileStatsAsync(ProfileStats stats, boolean toFollow){
        if(toFollow)
            collectorService.saveProfile(stats.getProfile());
        if(stats.getPostsStats()!=null)
            profileStatsRepository.save(stats);
    }

}
