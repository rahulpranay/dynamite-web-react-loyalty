package com.pages;

import com.pages.mobile.MobileCartPage;
import com.pages.mobile.MobileCategoryPage;
import com.pages.mobile.MobileHomePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class BasePage {

    private final int ELEMENT_WAIT_TIME = 30;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitForLoad();
    }

    /**
     * Waits for element and located element.
     */
    public WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIME);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementToClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIME);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Clear input field and enter data.
     */
    public void clearAndEnterText(By by, String inputText) {
        WebElement webElement = waitForElement(by);
        webElement.clear();
        webElement.sendKeys(inputText);
    }

    public String generateEmail() {
        Random random = new Random();
        return "dynamite_uat_" + random.nextInt(32000) + random.nextInt(999) +
                "@yopmail.com";
    }

    public boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public void click(By by) {
        waitForElement(by).click();
    }

    public WebElement locateElement(By by) {
        return driver.findElement(by);
    }

    public void clickOnLink(String linkName) {
        String xpath = String.format("//a[normalize-space() = '%s']", linkName);
        clickUsingJS(waitForElement(By.xpath(xpath)));
    }

    public List<WebElement> listOfVisibleElements(By by) {
        return driver.findElements(by).stream().filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
    }

    public void clickUsingJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void intentionalWait(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition =
                driver -> ((JavascriptExecutor) Objects.requireNonNull(driver))
                        .executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public CategoryPage navigateToCategoryPage(String categoryName) {
        waitForElement(By.xpath("(//span[@class = 'categoryMenuItemSpan']/a)[2] | (//div[contains(@id, 'top-category-link')])[2]")).click();
        intentionalWait(2000);
        closeLoyaltyOnBoarding();
        intentionalWait(3000);
        return new CategoryPage(driver);
    }

    public CategoryPage navigateToCategoryPage(int categoryIndex) {
        String xpath = "//span[@class = 'categoryMenuItemSpan']/a | //div[contains(@id, 'top-category-link')]";
        waitForElement(By.xpath(xpath));
        List<WebElement> categories = listOfVisibleElements(By.xpath(xpath));
        categories.get(categoryIndex - 1).click();
        intentionalWait(2000);
        closeLoyaltyOnBoarding();
        intentionalWait(3000);
        return new CategoryPage(driver);
    }

    public void closePopup() {
        String id = "cboxClose";
        click(By.id(id));
        intentionalWait(3000);
    }

    public WishListPage navigateToWishListPage() {
        WebElement webElement = waitForElement(By.id("profilePicture"));
        new Actions(driver).moveToElement(webElement).pause(3000).perform();
        clickOnLink("Wishlist");
        waitForElement(By.xpath("//span[text() = 'My Wish List'][@class = 'headerTitle3']"));
        return new WishListPage(driver);
    }

    public void logOut() {
        WebElement webElement = waitForElement(By.id("profilePicture"));
        new Actions(driver).moveToElement(webElement).pause(3000).perform();
        clickOnLink("Logout");
        intentionalWait(5000);
    }

    public HomePage navigateToHomePage() {
        waitForElement(By.xpath(
                "//div[normalize-space(@class) = 'logo']/a[2] | //a[contains(normalize-space(@class), 'DynamiteLogo__StyledLink')]"))
                .click();
        intentionalWait(2000);
        return new HomePage(driver);
    }

    public void hoverOnElement(By by) {
        new Actions(driver).moveToElement(waitForElement(by)).pause(3000).perform();
    }

    public void hoverOnElement(WebElement w) {
        new Actions(driver).moveToElement(w).pause(3000).perform();
    }

    public void switchToWindow(String parentWindow) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public void switchToWindowUsingTitle(String pageTitle) {
        String currentPageTitle = driver.getTitle();
        if (!currentPageTitle.equals(pageTitle)) {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().equals(pageTitle)) {
                    break;
                }
            }
        }
    }

    public void enterText(String fieldLabel, String inputValue) {
        String fieldXpath = String.format(
                "//div[normalize-space(text() = '%s')]/following-sibling::div[1]//input[@type = 'text']",
                fieldLabel);
        WebElement inputField = driver.findElement(By.xpath(fieldXpath));
        inputField.clear();
        inputField.sendKeys(inputValue);
    }

    public void clickButton(String buttonLabel) {
        String buttonXpath = String.format("//button[normalize-space() = '%s']", buttonLabel);
        WebElement button = driver.findElement(By.xpath(buttonXpath));
        button.click();
    }

    public void enterText(By by, String inputText) {
        WebElement w = waitForElement(by);
        w.clear();
        w.sendKeys(inputText);
    }

    public MobileCartPage navigateToMobileCartPage() {
        click(By.id("ShoppingBag"));
        refreshPage();
        return new MobileCartPage(driver);
    }

    public void selectByVisibleText(By by, String visibleText) {
        new Select(waitForElement(by)).selectByVisibleText(visibleText);
    }

    public void selectByValue(By by, String value) {
        new Select(waitForElement(by)).selectByValue(value);
    }

    public String generateEmail(String parentString) {
        Random random = new Random();
        return parentString + random.nextInt(32000) + random.nextInt(999) +
                "@yopmail.com";
    }

    public CartPage navigateToCartPage() {
        String cartIcon = "ShoppingBag";
        try {
            clickUsingJS(waitForElement(By.id(cartIcon)));
        } catch (Exception e) {
            clickUsingJS(waitForElement(By.id(cartIcon)));
        }
        return new CartPage(driver);
    }

    public HomePage navigateToGarage() {
        click(By.xpath("//div[@id = 'cornerLogo']//img[contains(@class, 'GarageLogo')]"));
        intentionalWait(10000);
        return new HomePage(driver);
    }

    public CategoryPage searchWithCategory(String categoryName) {
        waitForElement(By.id("searchText")).sendKeys(categoryName);
        intentionalWait(2000);
        clickUsingJS(waitForElement(By.xpath("//input[@class = 'mainSearchButton']")));
        return new CategoryPage(driver);
    }

    public ProductPage searchWithSku(String sku) {
        WebElement webElement = waitForElement(By.id("searchText"));
        intentionalWait(2000);
        new Actions(driver).sendKeys(webElement, sku).pause(5000).sendKeys(Keys.DOWN).pause(500)
                .sendKeys(Keys.ENTER).perform();
        return new ProductPage(driver);
    }

    public String generateAlphabets(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateToFrench() {
        driver.navigate().to("https://qa10.dynamiteclothing.com/fr-ca/");
    }

    public MobileHomePage navigateToMobileGarageHomePage() {
//        click(By.xpath("//button[@id = 'hamburgerMenu_icon'] | //a[@id = 'burger']"));
//        intentionalWait(1000);
//        clickUsingJS(waitForElement(By.xpath("//a[contains(@class, 'leftMenuSisterBrandLogo')] | //a[@aria-label = 'Garage']")));
        driver.navigate().to("https://uat3.garageclothing.com/ca");
        intentionalWait(5000);
        return new MobileHomePage(driver);
    }

    public MobileCategoryPage searchWithItemInMobile(String searchKeyword) {
        click(By.id("search"));
        enterText(By.id("searchTextNoResults"), searchKeyword);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        intentionalWait(5000);
        return new MobileCategoryPage(driver);
    }

    public void clickOnMenuIcon() {
        click(By.xpath("//button[@id = 'hamburgerMenu_icon'] | //a[@id = 'burger']"));
    }

    public MobileHomePage navigateToMobileHomePage() {
        waitForElement(By.xpath("//a[contains(@class, 'DynamiteLogo')]")).click();
        intentionalWait(3000);
        return new MobileHomePage(driver);
    }

    public void closeJoinNowPopup() {
        String checkBoxId = "cboxClose";
        if (isElementPresent(By.id(checkBoxId))) {
            click(By.id(checkBoxId));
        }
    }

    public void selectCountryFromPopup() {
        By canadaButton = By
                .xpath("//div[normalize-space() = 'Canada'][@class = 'countryPickerActionBtn']");
        if (isElementPresent(canadaButton)) {
            click(canadaButton);
        }
    }

    public void closeLoyaltyOnBoarding() {
        By by = By.id("closeLoyaltyOnBoarding");
        if (isElementPresent(by)) {
            click(by);
        }
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void navigateBrowserBack() {
        driver.navigate().back();
        waitForPageLoad();
    }
}
