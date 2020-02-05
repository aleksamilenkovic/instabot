package com.instabot.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lezalekss
 */
@Component
@Slf4j
public class InstaParser {
	private final String urlTemplate = "https://www.instagram.com/%s";
	private final String loginUrl = "https://www.instagram.com/accounts/login/";

	public void login(WebDriver driver, String username, String password) {
		driver.get(loginUrl);
		WebElement usernameInput = driver.findElement(By.name("username"));
		WebElement passwordInput = driver.findElement(By.name("password"));
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		passwordInput.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void likePosts(WebDriver driver, String profileUsername) {
		log.info("Starting likes for profile: " + profileUsername);
		String profileUrl = String.format(urlTemplate, profileUsername);
		driver.get(profileUrl);
		for (WebElement we : driver.findElements(By.className("v1Nh3"))) {
			we.click();
			WebElement likeSpan = driver.findElement(By.className("fr66n"));
			if (!isPostLiked(likeSpan))
				likeSpan.click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		}
	}

	private boolean isPostLiked(WebElement likeSpan) {
		String svg = likeSpan.findElement(By.className("_8-yf5")).getAttribute("aria-label");
		return !svg.equals("Like");
	}

}
