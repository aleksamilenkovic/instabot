package com.instabot.service.impl;

import com.instabot.service.InstaService;
import com.instabot.util.Utils;
import com.instabot.webdriver.InstaParser;
import com.instabot.webdriver.SeleniumLoader;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private List<String> profiles;
    @Autowired
    private SeleniumLoader loader;
    @Autowired
    private InstaParser instaParser;
    private WebDriver driver;

    @Override
    public void startLikes() {
        loader.setUp();
        driver = loader.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        profiles = getProfiles();
        instaParser.login(driver, username, password);
        profiles.forEach(profile-> instaParser.likePosts(driver, profile));
        loader.tearDown();
    }

    private List<String> getProfiles() {
        List<String> profiles = new ArrayList<>();
        try {
            profiles = Utils.readJsonStringArray(profilesJsonFile, "usernames");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return profiles;
    }


}
