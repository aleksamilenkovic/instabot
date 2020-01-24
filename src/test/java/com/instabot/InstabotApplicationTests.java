package com.instabot;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
@RunWith(SpringRunner.class)
@SpringBootTest
class InstabotApplicationTests {
	private static final String CHROMEDRIVER_EXE = "chromedriver.exe";
	protected WebDriver driver;


	public void setUp() {
		String driverFile = findFile();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(driverFile))
				.build();
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications",2);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
		options.addArguments("--headless");
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--start-fullscreen");
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.merge(capabilities);
		this.driver = new ChromeDriver(service, options);
	}

	private String findFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(CHROMEDRIVER_EXE);
		return url.getFile();
	}


	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void testttt() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.navigate().to("https://mozzartbet.com");
//		driver.manage().window().fullscreen();
		
	}

}
