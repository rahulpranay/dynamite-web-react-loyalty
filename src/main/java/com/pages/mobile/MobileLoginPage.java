package com.pages.mobile;

import com.pages.BasePage;
import com.pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileLoginPage extends BasePage {

    private static final By CREATE_ACCOUNT = By.id("create-account-button");
    private static final By EMAIL = By.id("signup_email_input");
    private static final By PASSWORD = By.id("signup_password_input");
    private static final By TERMS_N_CONDITIONS = By.xpath("//label[@for = 'terms-checkbox']");
    private static final By JOIN_NOW = By.id("signup-button");
    private static final By POPUP_CLOSE = By.id("cboxClose");
    private static final By SIGN_IN_EMAIL = By.id("email_input");
    private static final By SIGN_IN_PASSWORD = By.id("password_input");
    private static final By LOGIN_BTN = By.id("login-button");

    public MobileLoginPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage createAccount() {
        click(CREATE_ACCOUNT);
        enterText(EMAIL, generateEmail());
        enterText(PASSWORD, "Loyalty01");
        click(TERMS_N_CONDITIONS);
        click(JOIN_NOW);
        intentionalWait(5000);
        return new ProfilePage(driver);
    }

    public MobileHomePage loginToAccount(String email, String password) {
        enterText(SIGN_IN_EMAIL, email);
        enterText(SIGN_IN_PASSWORD, password);
        click(LOGIN_BTN);
        intentionalWait(5000);
        return new MobileHomePage(driver);
    }
}
