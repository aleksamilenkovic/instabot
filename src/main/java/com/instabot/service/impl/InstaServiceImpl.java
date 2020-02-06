package com.instabot.service.impl;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.service.InstaService;
import com.instabot.webdriver.InstaParser;
import com.instabot.webdriver.SeleniumLoader;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lezalekss
 */

@Service
public class InstaServiceImpl implements InstaService {
    @Value("${profiles-json}")
    private String profilesJsonFile;
    @Value("${profile-username}")
    private String username;
    @Value("${profile-password}")
    private String password;
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
    public void collectStats() {
        WebDriver driver = loader.setUp();
        instaParser.login(driver, username, password);
        getProfiles().forEach(profile -> {
            ProfileStats stats = instaParser.collectStats(driver, profile);
            profileStatsRepository.save(stats);
        });
        loader.tearDown(driver);
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
