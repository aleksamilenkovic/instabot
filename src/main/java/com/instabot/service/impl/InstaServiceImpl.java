package com.instabot.service.impl;

import com.instabot.service.InstaService;
import com.instabot.util.Utils;
import com.instabot.webdriver.SeleniumLoader;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lezalekss
 */

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
    private WebDriver driver;

    @Override
    public void start() {
        loader.setUp();
        driver = loader.getDriver();
        this.profiles = getProfiles();

    }

    @Override
    public List<String> getProfiles() {
        List<String> profiles = new ArrayList<>();
        try {
            profiles = Utils.readJsonStringArray(profilesJsonFile, "usernames");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return profiles;
    }


}
