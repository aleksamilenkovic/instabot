package com.instabot.parser;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.PostStats;
import com.instabot.domain.ProfileStats;
import com.instabot.util.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author lezalekss
 */
@Component
@Slf4j
public class InstaParserSelenium {
	private final String urlTemplate = "https://www.instagram.com/%s";
	private final String loginUrl = "https://www.instagram.com/accounts/login/";
	private final String profileInfoSuffix = "/?__a=1";

	public void login(WebDriver driver, String username, String password) {
		driver.navigate().to(loginUrl);
		WebElement usernameInput = driver.findElement(By.name("username"));
		WebElement passwordInput = driver.findElement(By.name("password"));
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		passwordInput.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public InstaProfile parseInstaProfile(WebDriver driver, String profileUsername, Boolean toFollowProfile){
		log.info("Starting to parse insta profile: " + profileUsername);
		String profileUrl = String.format(urlTemplate, profileUsername);
		driver.get(profileUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return null;
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
		int followers=0, following=0, posts=0; // driver will load first 12 posts or less
		String profileUrl = String.format(urlTemplate, profile.getUsername());
		log.info(profileUrl);
		driver.get(profileUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> profileInfos = driver.findElements(By.className("g47SY"));
		posts = Utils.parseInt(profileInfos.get(0).getText());
		followers = Utils.parseInt(profileInfos.get(1).getAttribute("tittle"));
		following = Utils.parseInt(profileInfos.get(2).getText());
		log.info(posts+"///"+followers+"///"+following);
		String imgUrl = driver.findElement(By.className("_6q-tv")).getAttribute("src");
		profile.update(followers, following, posts, imgUrl, profile.isToLike(), profile.isPrivate());
		List<WebElement> postElements = driver.findElements(By.className("v1Nh3"));
		ProfileStats stats =
				ProfileStats.builder().posts(posts).followers(followers).following(following).time(Utils.fetchCurrentDate()).profile(profile).build();
		setPostsStats(postElements, driver, stats);
		return stats;
	}

	public void setPostsStats(List<WebElement> postElements, WebDriver driver, ProfileStats stats){
		Actions action = new Actions(driver);
		Consumer< WebElement > hover = element -> {
			action.moveToElement(element).build()
					.perform();
		};
		int likes = 0, postsNumber=postElements.size();
		List<PostStats> postsStats = new ArrayList<>();
		for(WebElement post : postElements){
			try {
				post.click();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				String[] text = driver.findElement(By.className("Nm9Fw")).getText().split(" ");
				int postLikes= (text[text.length - 2].equals("likes")) ?
						Utils.parseInt(text[0])
						: Utils.parseInt(text[text.length - 2]) + 1;
				likes +=postLikes;
				String dateTime = driver.findElement(By.className("_1o9PC")).getAttribute("datetime");
				String postUrl = driver.getCurrentUrl();
				String img = post.findElement(By.className("FFVAD")).getAttribute("src");
				PostStats postStats = PostStats.builder().likes(postLikes).time(Utils.getDateFromString(dateTime)).url(postUrl).imgUrl(img).build();
				postsStats.add(postStats);
				log.info(postStats.toString());
			}catch (NoSuchElementException ex){
				log.info("Video skipping...");
				postsNumber--;
			}
			driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

		}
		/*for(int i=1;i<5;i++)
			for(int j=1;j<4;j++){
				System.out.println(i+"/"+j);
				hover.accept(driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div[3]/article/div[1]/div/div["+i+"]/div["+j+"]")));
				WebElement post = driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div[3]/article/div[1]/div/div["+i+"]/div["+j+"]"));
				System.out.println(post.getAttribute("innerHTML"));
			}*/
		stats.setPostsStats(postsStats);
		stats.setAverageLikes((double)likes/postsNumber);
	}

	private boolean isPostLiked(WebElement likeSpan) {
		String svg = likeSpan.findElement(By.className("_8-yf5")).getAttribute("aria-label");
		return !svg.equals("Like");
	}
}
