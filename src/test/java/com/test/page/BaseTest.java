package com.test.page;

import com.utils.Driver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static com.variables.PropertiesKey.*;
import static com.variables.TestConstants.UI_PROPERTIES;

public class BaseTest {

    protected Driver d;
    protected String applicationUrl;

    /**
     * Initializes a remote or local driver based on the flags.
     */
    @BeforeMethod(alwaysRun = true)
    public void initializeDriver(ITestContext context, Method method) throws Exception {
        this.d = new Driver(method.getName(), context.getName());
        d.getDriver().manage().deleteAllCookies();
        d.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String browser = UI_PROPERTIES.getProperty(BROWSER_NAME);
        if (browser.equalsIgnoreCase("mobile-chrome")) {
            applicationUrl = UI_PROPERTIES.getProperty(MOBILE_APPLICATION_URL);
            d.getDriver().get(applicationUrl);
            d.getDriver().navigate().refresh();
        } else {
            applicationUrl = UI_PROPERTIES.getProperty(APPLICATION_URL);
            d.getDriver().get(applicationUrl);
        }
//        d.getDriver().manage().deleteAllCookies();
//        Cookie reactTwo = new Cookie("dy_disable_tests","true");
//        Cookie react = new Cookie("rover_stage","v10-1-2-prod");
//        getDriver().manage().addCookie(reactTwo);
//        getDriver().manage().addCookie(react);
//        d.getDriver().navigate().refresh();
    }

    /**
     * Sets results, and then quits the driver.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        System.out.println("React rover_stage dynamite cookie values + " + getCookieValue("rover_stage"));
        System.out.println("React dy_disable_tests dynamite cookie values + " + getCookieValue("dy_disable_tests"));
        d.getDriver().quit();
    }

    /**
     * Return {@link WebDriver} instance.
     */
    public WebDriver getDriver() {
        return d.getDriver();
    }

    private String getCookieValue(String cookieName) {
        String cookieValue = "";
        try {
            cookieValue = d.getDriver().manage().getCookieNamed(cookieName).getValue();
        } catch (Exception e) {
            System.out.println(String.format("Unable to get the %s cookie value", cookieName));
        }
        return cookieValue;
    }
}
