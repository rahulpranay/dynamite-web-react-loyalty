package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends BasePage {

    private static By EMAIL_INPUT = By.id("signup_email_input");
    private static By PASSWORD_INPUT = By.id("signup_password_input");
    private static By TERMS_AND_CONDITIONS = By.xpath("//label[@for = 'terms-checkbox']");
    private static By JOIN_NOW_BTN = By.id("signup-button");
    private String emailId;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage enterAccountDetails() {
        emailId = generateEmail("dynamite_react");
        enterText(EMAIL_INPUT, emailId);
        enterText(PASSWORD_INPUT, getPassword());
        click(TERMS_AND_CONDITIONS);
        click(JOIN_NOW_BTN);
        intentionalWait(5000);
        return new ProfilePage(driver);
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return "Loyalty01";
    }
}
