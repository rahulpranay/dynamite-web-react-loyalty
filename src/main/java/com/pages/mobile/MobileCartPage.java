package com.pages.mobile;

import com.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MobileCartPage extends BasePage {

    private static By PRODUCT_NAME = By.xpath("//div[@class = 'itemTitle']/a");
    private static By CHECKOUT_BUTTON = By.id("checkout_btn");

    public MobileCartPage(WebDriver driver) {
        super(driver);
    }

    public String getAddedProductName() {
        String productName = waitForElement(PRODUCT_NAME).getText().trim();
        return productName.split("\\|")[0].trim().toLowerCase();
    }

    public String getAddedSize() {
        String productName = waitForElement(PRODUCT_NAME).getText().trim();
        return productName.split("\\|")[1].trim();
    }

    public MobileCheckoutPage navigateToMobileCheckoutPage() {
        click(CHECKOUT_BUTTON);
        return new MobileCheckoutPage(driver);
    }

    public int allProductsList() {
        waitForElement(PRODUCT_NAME);
        return listOfVisibleElements(PRODUCT_NAME).size();
    }
}
