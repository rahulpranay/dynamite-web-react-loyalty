package com.pages.mobile;

import com.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileHomePage extends HomePage {

    private static final By CREATE_ACCOUNT = By.xpath("//li[@id = 'loginLink']/a");
    private static final By WELCOME_TEXT = By.xpath("//div[contains(@class, 'welcome-text')]");

    public MobileHomePage(WebDriver driver) {
        super(driver);
    }

    public MobileLoginPage navigateToMobileLoginPage() {
        clickOnMenuIcon();
        intentionalWait(3000);
        click(CREATE_ACCOUNT);
        return new MobileLoginPage(driver);
    }

    public String getProfileWelcomeText() {
        clickOnMenuIcon();
        return waitForElement(WELCOME_TEXT).getText().split("\\n")[0].trim().toLowerCase();
    }

    public MobileCategoryPage navigateToCategoryPage() {
        clickOnLink("NEW");
        return new MobileCategoryPage(driver);
    }

    public MobileCategoryPage navigateToMobileCategoryPage(String categoryName) {
        clickOnLink(categoryName);
        return new MobileCategoryPage(driver);
    }
}
