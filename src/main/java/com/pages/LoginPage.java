package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By EMAIL = By.name("email");
    private static final By PASSWORD = By.id("password_input");

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
}