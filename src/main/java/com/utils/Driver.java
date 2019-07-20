package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

import static com.variables.PropertiesKey.BROWSER_NAME;
import static com.variables.TestConstants.UI_PROPERTIES;

public class Driver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String testName;
    private String testArea;

    public Driver(String testName, String testArea) throws Exception {
        this.testName = testName;
        this.testArea = testArea;
        UI_PROPERTIES = new PropertiesLoader().loadUIProperties();
        createDriver();
    }

    /**
     * Create local {@link WebDriver} instance.
     */
    private void createDriver() {
        String browserName = System.getenv("BROWSER_NAME");
        if (browserName == null) {
         browserName = UI_PROPERTIES.getProperty(BROWSER_NAME);;
        }
        switch (browserName.toUpperCase()) {
            case "CHROME":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--start-maximized");
//                chromeOptions.addArguments("--window-size=1325x744");
//                chromeOptions.setHeadless(false);
                WebDriverManager.chromedriver().version("75.0.3770.8").setup();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                FirefoxProfile geoDisabled = new FirefoxProfile();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                geoDisabled.setPreference("geo.enabled", false);
                geoDisabled.setPreference("geo.provider.use_corelocation", false);
                geoDisabled.setPreference("geo.prompt.testing", false);
                geoDisabled.setPreference("geo.prompt.testing.allow", true);
                firefoxOptions.setProfile(geoDisabled);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "SAFARI":
                driver.set(new SafariDriver());
                break;
            case "MOBILE-CHROME":
                chromeOptions = chromeMobileEmulationOptions("iPhone X");
//                chromeOptions.setHeadless(true);
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "IPAD":
                chromeOptions = chromeMobileEmulationOptions("iPad");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Unsupported browser with name '%s'. ", browserName));
        }
    }

    public ChromeOptions chromeMobileEmulationOptions(String deviceName) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return chromeOptions;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestArea() {
        return testArea;
    }

    public void setTestArea(String testArea) {
        this.testArea = testArea;
    }
}
