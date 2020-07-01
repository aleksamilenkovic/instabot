package com.instabot.service.impl;

import com.instabot.domain.InstaProfile;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.service.InstaScrapperService;
import com.instabot.webdriver.InstaParser;
import com.instabot.webdriver.SeleniumLoader;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lezalekss
 *
 * Class that represents instagram scrapper service.
 * So practicly it calls parser to scrapp, call repository and returns data
 *
 */
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
    /**
     * *SeleniumLoader that loads web driver
     */
    @Autowired
    private SeleniumLoader loader;
    /**
     * InstaParser for parsing instagram informations
     */
    @Autowired
    private InstaParser instaParser;
    /**
     * Database connection for Instagram profile
     */
    @Autowired
    private InstaProfileRepository profileRepository;
    /**
     * Database connection for Instagram profile statistic
     */
    @Autowired
    private ProfileStatsRepository profileStatsRepository;

    /**
     *
     * Liking profiles that instagram bot follows
     *
     */
    @Override
    public void startLikes() {
        WebDriver driver = loader.setUp();
        instaParser.login(driver, username, password);
        getProfiles().forEach(profile-> instaParser.likePosts(driver, profile.getUsername()));
        loader.tearDown(driver);
    }
    /**
     *
     * Collecting statistic for profiles
     * that instagram bot follows
     *
     */
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
