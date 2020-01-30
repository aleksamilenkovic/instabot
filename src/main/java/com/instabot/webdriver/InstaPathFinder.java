package com.instabot.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

/**
 * @author lezalekss
 */
@Component
public class InstaPathFinder {

    public void login(WebDriver driver, String username, String password){
        driver.get("https://www.instagram.com/accounts/login/");
        WebElement usernameInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.ENTER);
    }



}
