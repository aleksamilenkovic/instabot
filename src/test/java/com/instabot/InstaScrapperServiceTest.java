package com.instabot;

import com.instabot.service.InstaService;
import com.instabot.webdriver.SeleniumLoader;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;


/**
 * @author lezalekss
 */

public class InstaServiceTest extends InstabotApplicationTests{
    @Autowired
    private InstaService instaService;
    @Autowired
    private SeleniumLoader loader;

    @Test
    public void collectStats(){
        instaService.collectStats();
    }

    @Test
    public void instaServiceTest(){
        instaService.startLikes();

    }

    @Test
    public void getLikesAndCommentsViaHover(){

    }

    @Test
    public void test() throws InterruptedException {
//        String path = System.getProperty("user.dir");
//        System.setProperty("webdriver.chrome.driver", path+"\\src\\test\\resources\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = loader.setUp();
        driver.get("https://www.google.com/");

        Thread.sleep(2000);
        driver.quit();
    }
}
