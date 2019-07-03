package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class SignUp extends BasePage {

    public SignUp(WebDriver driver) {
        super(driver);
    }

    public void enterCredentials() {
        waitForElement(By.id("loginEmail_loyaltyPopupLoginBtn")).sendKeys(generateEmail());
        waitForElement(By.id("password_loyaltyPopupLoginBtn")).sendKeys("Loyalty05");
        waitForElement(By.id("acceptTerms")).click();
        waitForElement(By.id("loyaltyPopupLoginBtn")).click();
        intentionalWait(5000);
        waitForElement(By.id("loyaltyContentContainer")).isDisplayed();
    }

    public void completeprofile() {
        waitForElement(By.xpath("//*[@id=\"loyaltyContentContainer\"]/div[2]/div[4]/a")).click();
        intentionalWait(3000);

    }

    public void enterdetails() {
        waitForElement(By.name("firstName")).sendKeys("ABC");
        waitForElement(By.name("lastName")).sendKeys("xyz");
        waitForElement(By.id("address1")).sendKeys("1140 wellington street apt 1507");
        waitForElement(By.id("city")).sendKeys("Montreal");
        waitForElement(By.id("postalZipCode")).sendKeys("h3c 1v8");
        new Select(waitForElement(By.name("state"))).selectByVisibleText("Quebec");
        clearAndEnterText(By.name("phoneNumber"), "(416) 629-0479");
        new Select(waitForElement(By.name("dobMonth"))).selectByVisibleText("01-January");
        new Select(waitForElement(By.name("dobDay"))).selectByVisibleText("01");
        new Select(waitForElement(By.name("dobYear"))).selectByVisibleText("1980");
        waitForElement(By.id("completeProfileForm")).click();
        intentionalWait(3000);
        waitForElement(By.id("cboxClose")).click();
        waitForElement(By.xpath("//*[@id=\"mainnav\"]/div[1]/a[1]/img"));
        intentionalWait(3000);
    }
}