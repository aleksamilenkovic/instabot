package com.instabot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instabot.service.InstaScrapperService;
import com.instabot.webdriver.SeleniumLoader;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

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
    public void getLikesAndCommentsViaHover() {
        scrapperService.addProfile("lezalekss", true);
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