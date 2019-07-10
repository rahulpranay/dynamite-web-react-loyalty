package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CartPage extends BasePage {

    private final By PRODUCT_NAME = By.xpath("//table[@class = 'MyCartTable']//tr[2]/td[3]//a");
    private final By ALL_PRODUCTS = By.xpath("//table[@class = 'MyCartTable']//tr/td[3]//a");
    private final By CHECKOUT_BTN = By.id("cartCheckOutBtn");
    private final By EDIT_LINK = By.xpath("//a[@class = 'pdl_popUp blkLink']");
    private final By PRODUCT_SIZES =
            By.xpath(
                    "//span[@stockavailability = 'AVAILABLE'][contains(@class, 'size')][not(contains(@class, 'selected'))]");
    private final By UPDATE_BTN = By.id("qvUpdateCart");
    private final By SELECTED_SIZE_TEXT = By.xpath("//td[contains(@id, 'Size')]/p[2]");
    private String productSize;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage navigateToCheckoutPage() {
        click(CHECKOUT_BTN);
        intentionalWait(5000);
        return new CheckoutPage(driver);
    }

    public String getProductName() {
        return waitForElement(PRODUCT_NAME).getText().trim();
    }

    public int getProductCount() {
        waitForElement(ALL_PRODUCTS);
        return listOfVisibleElements(ALL_PRODUCTS).size();
    }

    public void updateProductSize() {
        click(EDIT_LINK);
        intentionalWait(3000);
        WebElement size = waitForElement(PRODUCT_SIZES);
        productSize = size.getText();
        size.click();
        click(UPDATE_BTN);
        intentionalWait(5000);
    }

    public void validateUpdatedSize() {
        String actualSize = waitForElement(SELECTED_SIZE_TEXT).getText().trim().split(" ")[1].trim();
        Assert.assertEquals(productSize, actualSize);
    }
}
