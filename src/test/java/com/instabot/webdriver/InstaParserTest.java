package com.instabot.webdriver;

import com.instabot.InstabotApplicationTests;
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
        String url = driver.getCurrentUrl();
        loader.tearDown(driver);
        // if login is sucessful it will go to homepage instagram.com not login page
        assertEquals("https://www.instagram.com/", url);
    }

    @Test
    public void instaLoginFail(){
        driver = loader.setUp();
        instaParser.login(driver, "wrongname", "wrongpassword");
        String url = driver.getCurrentUrl();
        loader.tearDown(driver);
        // if login is sucessful it will go to homepage instagram.com not login page
        assertEquals("https://www.instagram.com/accounts/login/", url);

    }
    @After
    public void tearDownDriver(){loader.tearDown(driver);}
}
