package com.instabot.webdriver;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.util.Utils;
import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void likePosts(WebDriver driver, String profileUsername){
        int i =0;
        log.info("Starting likes for profile: " + profileUsername);
        String profileUrl = String.format(urlTemplate, profileUsername);
        driver.get(profileUrl);
        for(WebElement we : driver.findElements(By.className("v1Nh3"))){
            log.info("Liking post: " + ++i);
            we.click();
            WebElement likeSpan = driver.findElement(By.className("fr66n"));
            if(!isPostLiked(likeSpan))
                likeSpan.click();
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        }
    }


	public ProfileStats collectStats(WebDriver driver, InstaProfile profile) {
		int postsNumber = 0, likes=0, followers=0, following=0, posts=0; // driver will load first 12 posts or less
		String profileUrl = String.format(urlTemplate, profile.getUsername());
		driver.get(profileUrl);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> profileInfos =  driver.findElements(By.className("g47SY"));
		posts = Utils.parseInt(profileInfos.get(0).getText());
		followers = Utils.parseInt(profileInfos.get(1).getText());
		following = Utils.parseInt(profileInfos.get(2).getText());
		List<WebElement> postElements = driver.findElements(By.className("v1Nh3"));
		postsNumber = postElements.size();
		for(WebElement post : postElements){
			try {
				post.click();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				String[] text = driver.findElement(By.className("Nm9Fw")).getText().split(" ");
				likes += (text[text.length - 2].equals("likes")) ?
						Utils.parseInt(text[0])
				    	: Utils.parseInt(text[text.length - 2]) + 1;
			}catch (NoSuchElementException ex){
				log.info("Video skipping...");
				postsNumber--;
			}
			driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		}
		ProfileStats stats =
				ProfileStats.builder().posts(posts).followers(followers).following(following).averageLikes((double)likes/postsNumber).time(LocalDate.now()).profile(profile).build();
		return stats;
	}

	private boolean isPostLiked(WebElement likeSpan) {
		String svg = likeSpan.findElement(By.className("_8-yf5")).getAttribute("aria-label");
		return !svg.equals("Like");
	}
}
