package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By EMAIL = By.name("email");
    private static final By PASSWORD = By.id("password_input");
    private static final By CONTINUE_WITH_FACEBOOK = By.id("facebook-button");
    private static final By FACEBOOK_EMAIL = By.id("email");
    private static final By FACEBOOK_PASSWORD = By.id("pass");
    private static final By FACEBOOK_LOGIN = By.xpath("//input[@name = 'login']");
    private static final By FACEBOOK_CONFIRM = By.xpath("//button[@name = '__CONFIRM__']");
    private static final By COMPLETE_LATER = By.id("skip");
    private static final By OFFERS_TAB = By.id("offerstab");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage loginToUsingExistingAccount(String email, String password) {
        clearAndEnterText(EMAIL, email);
        clearAndEnterText(PASSWORD, password);
        click(By.id("login-button"));
        intentionalWait(3000);
        return new ProfilePage(driver);
    }

    public CreateAccountPage clickCreateAccountButton() {
        click(By.xpath(
            "//div[contains(@class, 'create-button-grid')]//a "
                + "| //div[contains(@class, 'CreateAccountSection__StyledCreateAccountColumn')]/a "
                + "| //div[@id = 'loginSignup']//a"));
        return new CreateAccountPage(driver);
    }

    public ProfilePage loginUsingFacebook(String username, String password) {
        click(CONTINUE_WITH_FACEBOOK);
        enterText(FACEBOOK_EMAIL, username);
        enterText(FACEBOOK_PASSWORD, password);
        click(FACEBOOK_LOGIN);
        click(FACEBOOK_CONFIRM);
        intentionalWait(5000);
        waitForPageLoad();
        waitForElement(COMPLETE_LATER).click();
        waitForElement(OFFERS_TAB);
        return new ProfilePage(driver);
    }
}