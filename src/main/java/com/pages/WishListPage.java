package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends BasePage {

    private final By PRODUCT_NAME = By.xpath("//table[@class = 'MyWishlistTable']//tr[normalize-space(@class) = 'dynamiteRow']/td[3]//a");
    private final By MOVE_TO_BAG = By.xpath("//td[@id = 'moveToMyBag']//a");
    private final By CLOSE_BTN = By.xpath("//button[@id = 'cboxClose']");

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return locateElement(PRODUCT_NAME).getText().toUpperCase().trim();
    }

    public void addProductToBag() {
        waitForElement(MOVE_TO_BAG).click();
        intentionalWait(2000);
        clickUsingJS(waitForElement(CLOSE_BTN));
    }
}
