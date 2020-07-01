package com.instabot.webdriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.instabot.util.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lezalekss
 * Class that loads and tears down selenium chrome WebDriver.
 *
 */
@Component
public class SeleniumLoader {
    /**
     * path to chrome driver
     */
    @Value("${chrome-driver}")
    private String CHROMEDRIVER_EXE;
    /**
     * path to user profile
     */
    @Value("${user-profile}")
    private String USER_PROFILE;

    /**
     * loading driver with user profile
     *
     * @return loaded driver as WebDriver
     */
    public WebDriver setProfile(){
        WebDriver driver;
//        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_EXE);
        ChromeDriverService chSvc = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(CHROMEDRIVER_EXE)).usingAnyFreePort().build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir =" + USER_PROFILE);
        driver = new ChromeDriver(chSvc, options);
        System.out.println("Chrome Driver loaded, using local profile.");
        return driver;
    }

    /**
     * Loading selenium WebDriver
     *
     * @throws org.openqa.selenium.SessionNotCreatedException when driver is not suitable
     * @return loaded driver as WebDriver
     */
    public WebDriver setUp() {
        WebDriver driver;
        String driverFile = Utils.findFile(CHROMEDRIVER_EXE);
//        System.setProperty("webdriver.chrome.driver", driverFile);
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
        driver = new ChromeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }


    /**
     *  tear down the driver
     * @param driver
     */
    public void tearDown(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
