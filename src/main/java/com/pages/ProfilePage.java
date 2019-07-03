package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {

    private static By FIRST_NAME = By.name("firstName");
    private static By LAST_NAME = By.name("lastName");
    private static By COUNTRY = By.name("country");
    private static By POSTAL_CODE = By.name("zipcode");
    private static By BIRTH_MONTH = By.name("birthMonth");
    private static By BIRTH_DAY = By.name("birthDay");
    private static By BIRTH_YEAR = By.name("birthYear");
    private static By GET_MY_REWARD = By.id("next");
    private static By REWARD_MESSAGE = By.xpath("//div[contains(@class, 'ToastNotification__Message')]//span");
    private static By HERE_LINK = By.xpath("//div[@class = 'title']/a[text() = 'here']");
    private static By ADDRESS_ONE = By.name("address1");
    private static By CITY = By.name("city");
    private static By PHONE_NUMBER = By.name("phoneNumber");
    private static By STATE = By.name("state");
    private static By BTN_CLOSE = By.xpath("//div[@class = 'loyaltyActionBtns']/div");
    private static By PROFILE_ICON = By.xpath("//li[@id = 'accountLink'][@class = 'welcomeLink']");
    private static By STUDIO_LINK = By.id("dashboard");
    private static By TOAST_MESSAGE_BANNER = By.xpath("//div[contains(@class, 'ToastNotification__Message')]//span");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalDetails() {
        intentionalWait(5000);
        enterText(FIRST_NAME, "Dynamite");
        enterText(LAST_NAME, "Test");
        enterText(POSTAL_CODE, "H3C 1V8");
        selectByValue(COUNTRY, "CA");
        selectByValue(BIRTH_MONTH, "4");
        selectByVisibleText(BIRTH_DAY, "11");
        selectByVisibleText(BIRTH_YEAR, "1991");
        click(GET_MY_REWARD);
        waitForElement(TOAST_MESSAGE_BANNER);
    }

    public void clickOnCompleteLater() {
        By by = By.xpath("//a[normalize-space(text()) = 'COMPLETE LATER']");
        if (isElementPresent(by)) {
            click(by);
        }
    }

    public String getRewardMessage() {
        return waitForElement(REWARD_MESSAGE).getText().trim();
    }

    public StudioPage navigateToStudioPage() {
        hoverOnElement(PROFILE_ICON);
        intentionalWait(2000);
        clickUsingJS(waitForElement(STUDIO_LINK));
        return new StudioPage(driver);
    }
}
