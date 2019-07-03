package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryPage extends BasePage {

    private static final By PRODUCT_NAME = By.xpath("//a[contains(@class, 'contentfulTile__TileTitleLink')] | //div[@class = 'pdl_details']/p/a");
    private static final String ADD_TO_BAG = "addToCartHover_btn_0_";
    private static final By PRODUCT_SIZE = By.xpath("//div[contains(@class, 'available overlaycomponent__Size')][not(contains(@class, 'not-available'))]");
    private static final By ADD_TO_BAG_BUTTON = By.xpath("//button[contains(@id, 'addToCart_btn')]");
    private static final By PRODUCT_IMAGE = By.xpath("//a[contains(@class, 'overlaycomponent__HoverContainerLink')] | //div[@class = 'prodListingImg']//img");
    private static final By SORTING_ARROW = By.id("sorting-btn");
    private static final By LOW_TO_HIGH = By.id("price_asc");
    private static final By HIGH_TO_LOW = By.id("price_desc");
    private static final By NEW_ARRIVALS_FILTER = By.id("new_arrivals");
    private static final By PRICE = By.xpath("//div[contains(@class, 'contentfulTile__PriceDiv')]");
    private static final By FIRST_PRODUCT = By.xpath("//a[contains(@class, 'contentfulTile__TileTitleLink')]");

    private String productName;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage navigateToProductPage() {
        List<WebElement> list = listOfVisibleElements(PRODUCT_NAME);
        Random random = new Random();
        clickUsingJS(list.get(random.nextInt(list.size() - 1)));
        intentionalWait(2000);
        closeLoyaltyOnBoarding();
        intentionalWait(2000);
        return new ProductPage(driver);
    }

    public ProductPage navigateToFirstProductPage() {
        List<WebElement> list = listOfVisibleElements(PRODUCT_NAME);
        Random random = new Random();
        clickUsingJS(list.get(0));
        intentionalWait(2000);
        return new ProductPage(driver);
    }

    public void addToBag() {
        waitForElement(PRODUCT_NAME);
        List<WebElement> products = listOfVisibleElements(PRODUCT_NAME);
        List<WebElement> images = listOfVisibleElements(PRODUCT_IMAGE);
        int productIndex = 1;
        productName = products.get(productIndex).getText().trim();
        hoverOnElement(images.get(productIndex));
        intentionalWait(1000);
        clickUsingJS(waitForElement(By.id(ADD_TO_BAG.concat(String.valueOf(productIndex)))));
        click(PRODUCT_SIZE);
        click(ADD_TO_BAG_BUTTON);
    }

    public CartPage clickOnCheckOut() {
        click(By.xpath("//a[@id = 'cartCheckOutBtn']"));
        intentionalWait(5000);
        return new CartPage(driver);
    }

    public String getProductName() {
        return productName;
    }

    public void sortPriceLowToHigh() {
        click(SORTING_ARROW);
        intentionalWait(2000);
        click(LOW_TO_HIGH);
        intentionalWait(5000);
    }

    public void sortPriceHighToLow() {
        click(SORTING_ARROW);
        intentionalWait(2000);
        click(HIGH_TO_LOW);
        intentionalWait(5000);
    }

    public void sortNewArrivals() {
        click(SORTING_ARROW);
        intentionalWait(2000);
        click(NEW_ARRIVALS_FILTER);
        intentionalWait(5000);
    }

    public List<Integer> getProductPriceList() {
        List<WebElement> priceListElements = listOfVisibleElements(PRICE);
        List<Integer> priceList = new ArrayList<>();
        for (WebElement priceElement : priceListElements) {
            String price = priceElement.getText().split(" ")[1].replace("$", "");
            priceList.add(Integer.parseInt(price));
        }
        return priceList;
    }

    public String getFirstProductStyleNumber() {
        return waitForElement(FIRST_PRODUCT).getAttribute("href");
    }
}
