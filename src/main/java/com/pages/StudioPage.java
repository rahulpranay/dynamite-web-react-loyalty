package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StudioPage extends BasePage {

  private static final By OFFERS_TAB = By.id("offers");

  public StudioPage(WebDriver driver) {
    super(driver);
  }

  public void validateStudioPage() {
    Assert.assertTrue(driver.getPageSource().contains("STUDIO REWARDS AND BENEFITS"));
  }

  public void clickOnOfferTab() {
    clickUsingJS(locateElement(OFFERS_TAB));
    intentionalWait(2000);
  }

  public void validateOffersPage() {
    Assert.assertTrue(driver.getPageSource().contains("Offers"));
  }
}
