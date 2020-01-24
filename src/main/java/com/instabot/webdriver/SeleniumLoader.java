package com.instabot.webdriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

/**
 * @author lezalekss
 */
@Component
public class SeleniumLoader {
    private final String CHROMEDRIVER_EXE = "../../../src/main/resources/chromedriver.exe";
    protected WebDriver driver;

    public void setProfile(){
        String exePath = "D:\\Repositories\\instabot\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\aleksa.milenkovic\\AppData\\Local\\Google\\Chrome\\User Data");
        this.driver = new ChromeDriver(options);
        System.out.println("Chrome Driver loaded, using local profile.");

    }

    public WebDriver getDriver(){
        return driver;
    }

    public WebDriver setUp() {
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
        return this.driver;
    }

    private String findFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(CHROMEDRIVER_EXE);
        System.out.println(url.getPath());
        return url.getFile();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
