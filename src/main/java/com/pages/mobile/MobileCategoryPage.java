package com.pages.mobile;

import com.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MobileCategoryPage extends BasePage {

    private static final By SORTING_DOWN_ARROW = By.id("sorting-btn");
    private static final By LOW_TO_HIGH = By.id("price_asc");
    private static final By HIGH_TO_LOW = By.id("price_desc");
    private static final By SORT_NEW_ARRIVALS = By.id("new_arrivals");
    private static final By PRICE = By.xpath("//div[contains(@class, 'PriceDiv')]");
    private static By PRODUCT_NAME = By.xpath("//a[contains(@class, 'contentfulTile__TileTitleLink')] | //p[@class = 'prodname']");
    private String productName;

    public MobileCategoryPage(WebDriver driver) {
        super(driver);
    }

    public MobileProductPage navigateToProductPage() {
        WebElement product = waitForElement(PRODUCT_NAME);
        List<WebElement> listOfProducts = listOfVisibleElements(PRODUCT_NAME);
        productName = listOfProducts.get(4).getText().trim();
        listOfProducts.get(4).click();
        intentionalWait(4000);
        return new MobileProductPage(driver);
    }

    public MobileProductPage navigateToFirstProductPage() {
        waitForElement(PRODUCT_NAME).click();
        intentionalWait(4000);
        return new MobileProductPage(driver);
    }

    public String getProductName() {
        return productName.toLowerCase();
    }

    public void sortLowToHigh() {
        clickUsingJS(waitForElement(SORTING_DOWN_ARROW));
        clickUsingJS(waitForElement(LOW_TO_HIGH));
        intentionalWait(5000);
    }

    public void sortHighToLow() {
        click(SORTING_DOWN_ARROW);
        click(HIGH_TO_LOW);
        intentionalWait(5000);
    }

    public void sortNewArrivals() {
        click(SORTING_DOWN_ARROW);
        click(SORT_NEW_ARRIVALS);
        intentionalWait(5000);
    }

    public String getStyleNumberOfFirstProduct() {
        String[] styleNumber = waitForElement(PRODUCT_NAME).getAttribute("href").split("/");
        return styleNumber[styleNumber.length - 1];
    }

    public List<Double> getProductPriceList() {
        List<WebElement> priceListElements = listOfVisibleElements(PRICE);
        List<Double> priceList = new ArrayList<>();
        for (WebElement priceElement : priceListElements) {
            String[] prices = priceElement.getText().split(" ");
            String price = prices[prices.length - 1].replace("$", "");
            priceList.add(Double.parseDouble(price));
        }
        return priceList;
    }
}
