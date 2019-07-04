package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StudioPage extends BasePage {

    private static final By OFFERS_TAB = By.xpath("//div[@id = 'offerstab']/a");
    private static final By STUDIO_PAGE_TITLE = By.id("studiotitle");

    public StudioPage(WebDriver driver) {
        super(driver);
    }

    public void validateStudioPage() {
        Assert.assertEquals(waitForElement(STUDIO_PAGE_TITLE).getText().trim(), "MY STUDIO");
    }

    public void clickOnOfferTab() {
        clickUsingJS(locateElement(OFFERS_TAB));
        intentionalWait(5000);
    }

    public void validateOffersPage() {
        Assert.assertTrue(driver.getPageSource().contains("OFFERS"));
    }
}
