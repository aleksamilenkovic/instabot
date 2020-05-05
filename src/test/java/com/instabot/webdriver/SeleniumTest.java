package com.instabot.webdriver;

import com.instabot.InstabotApplicationTests;
import com.instabot.webdriver.SeleniumLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lezalekss
 */
public class SeleniumTest extends InstabotApplicationTests {
    @Autowired
    private SeleniumLoader loader;

    private WebDriver driver;

    @Before
    @Test
    public void setUpLoaderTest(){
        driver = loader.setUp();
        // Checking if driver is loaded
        assertNotNull(driver);
    }

    @Test(expected = NoSuchSessionException.class)
    public void errorWhileUsingIfTearDown(){
        driverTearUp();
        driver.get("www.google.com");
    }

    @After
    @Test
    public void driverTearUp(){
        loader.tearDown(driver);
    }

}
