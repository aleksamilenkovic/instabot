package com.instabot;

import com.instabot.service.InstaService;
import com.instabot.util.Utils;
import com.instabot.webdriver.InstaParser;
import com.instabot.webdriver.SeleniumLoader;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
class InstabotApplicationTests {
	@Autowired
	private InstaService instaService;

	@Test
	void instaServiceTest(){
		instaService.startLikes();

	}

}
