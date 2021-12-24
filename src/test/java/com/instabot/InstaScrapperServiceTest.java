package com.instabot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instabot.parser.InstaParser;
import com.instabot.rest.dto.request.ProfileConfig;
import com.instabot.service.InstaScrapperService;
import com.instabot.webdriver.SeleniumLoader;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class InstaScrapperServiceTest extends InstabotApplicationTests{
    @Autowired
    private InstaScrapperService scrapperService;
    @Autowired
    private SeleniumLoader loader;
    @Autowired
    private InstaParser parser;
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
//        scrapperService.addProfile("lezalekss", true);
        parser.scrapNewProfile(new ProfileConfig("therock", true,true));
    }

    @Test
    public void test() throws InterruptedException {
        WebDriver driver = loader.setUp();
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        driver.quit();
    }
}