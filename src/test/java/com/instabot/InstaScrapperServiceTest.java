package com.instabot;

import com.instabot.service.InstaScrapperService;
import com.instabot.webdriver.SeleniumLoader;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class InstaScrapperServiceTest extends InstabotApplicationTests{
    @Autowired
    private InstaScrapperService scrapperService;
    @Autowired
    private SeleniumLoader loader;

    @Test
    public void collectStats(){
        scrapperService.collectStats();
    }

    @Test
    public void instaServiceTest(){
        scrapperService.startLikes();

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