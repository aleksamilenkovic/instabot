package com.instabot.webdriver;

import com.instabot.InstabotApplicationTests;
import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import static org.junit.Assert.*;
/**
 * @author lezalekss
 */
public class InstaParserTest extends InstabotApplicationTests {
    @Autowired
    private InstaParser instaParser;
    @Autowired
    private SeleniumLoader loader;
    @Value("${profile-username}")
    private String username;
    @Value("${profile-password}")
    private String password;
    private WebDriver driver;

    @Test
    public void instaLoginTest(){
        driver = loader.setUp();
        instaParser.login(driver, username, password);
        String tittle = driver.getTitle();
        loader.tearDown(driver);
        // if login is sucessful it will go to homepage instagram.com not login page
        assertEquals("Instagram", tittle);
    }

    @Test
    public void instaLoginFail(){
        driver = loader.setUp();
        instaParser.login(driver, "wrongname", "wrongpassword");
        String tittle = driver.getTitle();
        loader.tearDown(driver);
        // if login is sucessful it will go to homepage instagram.com not login page
        assertEquals("Login • Instagram", tittle);
    }

    @Test
    public void profileParse(){
        driver = loader.setUp();
        instaParser.login(driver, username, password);
        InstaProfile profile = new InstaProfile();
        profile.setUsername("lezalekss");
        ProfileStats stats = instaParser.collectStats(driver, profile);
        assertTrue(stats.getAverageLikes()>0);
        assertTrue(stats.getPosts()>0);
        assertNotNull(stats.getPostsStats());
        assertEquals("lezalekss", stats.getProfile().getUsername());
    }

    @After
    public void tearDownDriver(){loader.tearDown(driver);}
}
