package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.variables.PropertiesKey.APPLICATION_URL;
import static com.variables.TestConstants.UI_PROPERTIES;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void closeDialogPopupIfPresent() {
        selectCountryFromPopup();
        closeLoyaltyOnBoarding();
        closeJoinNowPopup();
        intentionalWait(2000);
    }

    public GetrewardedPage navigateToGetrewardedPage() {
        clickOnLink("GET REWARDED");
        return new GetrewardedPage(driver);
    }

    public StoreLocatorPage navigateToStoreLocatorPage() {
        click(By.xpath("//*[@id=\"topnav\"]/div[3]/ul/li[4]/a/img"));
        return new StoreLocatorPage(driver);
    }

    public Garage navigateToGaragePage() {
        click(By.xpath("//*[@id=\"topnav\"]/div[1]/span[2]/a/img"));
        return new Garage(driver);
    }

    public LoginPage navigateToLoginPage() {
        click(By.xpath("//div[contains(@class, 'profile')]//a | //li[@id='accountLink']//a/img"));
        intentionalWait(5000);
        return new LoginPage(driver);
    }

    public HomePage navigateToFrenchDynamite() {
        String applicationUrl = UI_PROPERTIES.getProperty(APPLICATION_URL);
        driver.get(applicationUrl.replace("/ca", "/fr-ca"));
        return new HomePage(driver);
    }

    public void validateAllFooterLinks() {
        validateEmailUsFooterLink();
//        validateFaqFooterLink();
        validateAboutDynamiteFooterLink();
        validateFindAStoreFooterLink();
        validateCareersFooterLink();
        validatePurchaseFooterLink();
        validateCheckBalanceFooterLink();
        validateShippingDeliveryFooterLink();
        validateReturnPolicyFooterLink();
        validateSizeGuideFooterLink();
        validateOrderTrackingFooterLink();
        validatePaymentInfoFooterLink();
        validateJoinTheCommunityFooterLink();
        validateJoinNowFooterLink();
    }
    
    private String getPageSource() {
        return driver.getPageSource().toUpperCase();
    }

    public void validateEmailUsFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Email us']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("BY PHONE:"), errorMessage);
        driver.navigate().back();
        intentionalWait(1000);
    }

    public void validateFaqFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'FAQ']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("E-COMMERCE FAQ"), errorMessage);
        driver.navigate().back();
        intentionalWait(1000);
    }

    public void validateAboutDynamiteFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'About Dynamite']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(!getPageSource().contains("404"), errorMessage);
        driver.navigate().back();
        intentionalWait(1000);
    }

    public void validateFindAStoreFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Find a Store']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("BROWSE DYNAMITE LOCATIONS"), errorMessage);
        driver.navigate().back();
        intentionalWait(1000);
    }

    public void validateCareersFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Careers']"));
        String parentWindow = driver.getWindowHandle();
        webElement.click();
        intentionalWait(1000);
        switchToWindow(parentWindow);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("AVAILABLE POSITIONS"), errorMessage);
        driver.switchTo().window(parentWindow);
        intentionalWait(1000);
    }

    public void validatePurchaseFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Purchase']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("Already Have a Gift Card?".toUpperCase()), errorMessage);
        driver.navigate().back();;
        intentionalWait(1000);
    }

    public void validateCheckBalanceFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Check Balance']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(isElementPresent(By.xpath("//h1[contains(@class, 'headerTitle')]")), errorMessage);
        driver.navigate().back();;
        intentionalWait(1000);
    }

    public void validateShippingDeliveryFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Shipping & Delivery']"));
        String parentWindow = driver.getWindowHandle();
        webElement.click();
        intentionalWait(1000);
        switchToWindowUsingTitle("Shipping and Delivery | DYNAMITE CLOTHING");
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("SHIPPING"), errorMessage);
        driver.switchTo().window(parentWindow);
        intentionalWait(1000);
    }

    public void validateReturnPolicyFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Return Policy']"));
        String parentWindow = driver.getWindowHandle();
        webElement.click();
        intentionalWait(1000);
        switchToWindowUsingTitle("Return Policy | DYNAMITE CLOTHING");
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("RETURN POLICY"), errorMessage);
        driver.switchTo().window(parentWindow);
        intentionalWait(1000);
    }

    public void validateSizeGuideFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Size Guide']"));
        webElement.click();
        intentionalWait(1000);
        driver.switchTo().frame(waitForElement(By.xpath("//iframe[@class = 'cboxIframe']")));
        String title = driver.getTitle().toLowerCase();
        String errorMessage = "Unable to find size chart";
        Assert.assertTrue(isElementPresent(By.xpath("//div[@class = 'sizeChartDisplay show']")), errorMessage);
        driver.switchTo().defaultContent();
        driver.navigate().back();
        intentionalWait(1000);
    }

    public void validateOrderTrackingFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Order Tracking']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("Order history".toUpperCase()), errorMessage);
        driver.navigate().back();;
        intentionalWait(1000);
    }

    public void validatePaymentInfoFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Payment Info']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("PAYMENT INFO"), errorMessage);
        driver.navigate().back();;
        intentionalWait(1000);
    }

    public void validateJoinTheCommunityFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'Join the community']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("DYNAMITE INSIDERS RECRUITMENT"), errorMessage);
        driver.navigate().back();;
        intentionalWait(1000);
    }

    public void validateJoinNowFooterLink() {
        WebElement webElement = waitForElement(By.xpath("//div[@class = 'top-row']//li[@class = 'all-li']/a[normalize-space(text()) = 'JOIN NOW']"));
        webElement.click();
        intentionalWait(1000);
        String title = driver.getTitle().toLowerCase();
        String errorMessage = String.format("Current page title is '%s' : ", title);
        Assert.assertTrue(getPageSource().contains("A DYNAMITE AFFILIATE"), errorMessage);
        driver.navigate().back();;
        intentionalWait(1000);
    }
}

