package com.instabot;

import com.instabot.service.InstaService;
import com.instabot.util.Utils;
import com.instabot.webdriver.InstaPathFinder;
import com.instabot.webdriver.SeleniumLoader;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
@RunWith(SpringRunner.class)
@SpringBootTest
class InstabotApplicationTests {
	private  WebDriver driver;
	@Autowired
	private SeleniumLoader loader;
	@Autowired
	private InstaPathFinder ipf;
	@Test
	void testttt()  {
		loader.setProfile();
		driver = loader.getDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.manage().window().fullscreen();
		ipf.login(driver,"boban.lezin","josiftito");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("https://www.instagram.com/lezalekss/");
		for(WebElement wd : driver.findElements(By.className("v1Nh3"))){
			wd.click();
			driver.findElement(By.className("fr66n")).click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		}

		loader.tearDown();

	}

}
