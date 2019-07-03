package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends BasePage {

    private final By PRODUCT_NAME = By.xpath("//table[@class = 'MyWishlistTable']//tr[normalize-space(@class) = 'dynamiteRow']/td[3]//a");

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return locateElement(PRODUCT_NAME).getText().toUpperCase().trim();
    }

    public void addProductToBag() {
        clickOnLink("Move to Bag");
    }
}
