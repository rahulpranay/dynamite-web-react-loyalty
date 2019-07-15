package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BasePage {

    public static final String PRODUCT_NAME = "//*[contains(@class, 'pdpstyle__PdpTitle')]";
    private static final String PRODUCT_PRICE = "//div[contains(@class, 'pdpstyle__PdpPrice')]//span[last()]";
    private static final String PRODUCT_SIZE = "//*[contains(@class, 'available selectorsstyle__SizeTileStyle')][not(contains(@class, 'unavailable'))]/button";
    private static final By WRITE_REVIEW = By.xpath("//a[contains(@class, 'bv-write-review-label')] | //button[contains(@class, 'bv-write-review')]");
    private static final By OVERALL_RATING = By.cssSelector("#bv-radio-rating-5");
    private static final By REVIEW_TITLE = By.id("bv-text-field-title");
    private static final By REVIEW_CONTENT = By.id("bv-textarea-field-reviewtext");
    private static final By QUALITY_OF_PRODUCT = By.cssSelector("#bv-radio-rating_Qualityofproduc-5-label");
    private static final By FIT_REVIEW = By.cssSelector("#bv-radio-rating_Fit_22-5-label");
    private static final By RECOMMEND_TO_FRIENDS = By.cssSelector("#bv-radio-rating_NPS_1-5");
    private static final By SHOPING_EXPERIENCE = By.cssSelector("#bv-text-field-additionalfield_Why_1Why");
    private static final By TERMS_AND_CONDITIONS = By.cssSelector("#bv-checkbox-reviews-termsAndConditions");
    private static final By BTN_POST_REVIEW = By.xpath("//button[@aria-describedby = 'bv-casltext-review']");
    private static final By REVIEW_SUCCESS_MESSAGE = By.xpath("//span[@id = 'bv-mbox-label-text']");
    private static final By CLOSE_ICON = By.xpath("//button[@name= 'Cancel']");
    private static final By REVIEW_HEADER = By.xpath("//ol[contains(@class, 'bv-content-list-reviews')]//h4[@class = 'bv-content-title']");
    private static final By REVIEWER_NAME = By.id("bv-text-field-usernickname");
    private static final By REVIEWER_EMAIL = By.id("bv-email-field-hostedauthentication_authenticationemail");
    private static final By FIND_IN_STORE = By.xpath("//button[@id = 'pdp_fis_btn']");
    private static final By FIND_IN_STORE_TITLE = By.xpath("//div[contains(@class, 'FindInStore__Title')]");
    private static final By FIND_IN_STORE_ZIP_CODE_BTN = By.id("pdp_fis_use_zip");
    private static final By FIND_IN_STORE_ZIP_CODE = By.id("pdp_fis_zip");
    private static final By FIND_IN_STORE_SUBMIT = By.id("pdp_fis_zip_submit");
    private static final By FIND_IN_STORE_RESULTS = By.id("pdp_fis_result");
    private static final By SIZE_CHART = By.id("pdp_sizechart");
    private static final By STYLE_NUMBER = By.xpath("//div[contains(@class, 'productInfostyle__PdpStyleNumber')]");
    private String productName;
    private String productPrice;
    private String reviewTitle;
    private String styleNumber;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToBag() {
        productName = waitForElement(By.xpath(PRODUCT_NAME)).getText().trim();
        String [] allPrices = locateElement(By.xpath(PRODUCT_PRICE)).getText().trim().replace("$", "").split(" ");
        productPrice = allPrices[allPrices.length - 1];
        click(By.xpath(PRODUCT_SIZE));
        clickButton("add to bag");
    }

    public void selectSize() {
        click(By.xpath(PRODUCT_SIZE));
    }

    public void addToWishList() {
        productName = waitForElement(By.xpath(PRODUCT_NAME)).getText().trim();
        click(By.xpath(PRODUCT_SIZE));
        clickOnLink("Save for later");
        intentionalWait(3000);
    }

    public CartPage clickOnCheckOut() {
        clickOnLink("Checkout");
        intentionalWait(5000);
        return new CartPage(driver);
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }


    public WriteaReview navigateTowriteaReview() {
        click(By.xpath("//*[@id=\"BVRRSummaryContainer\"]/div/div/div/div/div/div/div/button"));
        return new WriteaReview(driver);
    }

    public void checkFindInStore(String zipCode) {
        WebElement size = waitForElement(By.xpath(PRODUCT_SIZE));
        size.click();
        click(FIND_IN_STORE);
        Assert.assertEquals(waitForElement(FIND_IN_STORE_TITLE).getText().trim(), "Find in store");
        clickUsingJS(waitForElement(FIND_IN_STORE_ZIP_CODE_BTN));
        enterText(FIND_IN_STORE_ZIP_CODE, zipCode);
        click(FIND_IN_STORE_SUBMIT);
        intentionalWait(2000);
        Assert.assertTrue(isElementPresent(FIND_IN_STORE_RESULTS));
    }

    public void closeCheckOutPopUp() {
        locateElement(By.xpath("//*[@id=\"cboxClose\"]")).click();
        intentionalWait(3000);
    }

    public void coupons10() {
        locateElement(By.xpath("//*[@id=\"availableOfferContent\"]/div/div[2]/div/div[1]")).getText();
    }

    public void navigateTocartpage() {
        locateElement(By.xpath("//*[@id=\"topnav\"]/div[3]/ul/li[3]/a/span")).click();
        intentionalWait(3000);
    }

    public void coupounApply() {
        locateElement(By.id("LOY17PROFIL10_btn")).click();
        intentionalWait(3000);
    }

    public void writeReview() {
        click(WRITE_REVIEW);
        intentionalWait(2000);
        click(OVERALL_RATING);
        reviewTitle = generateAlphabets(15);
        clearAndEnterText(REVIEW_TITLE, reviewTitle);
        clearAndEnterText(REVIEW_CONTENT, generateAlphabets(52));
        click(QUALITY_OF_PRODUCT);
        click(FIT_REVIEW);
        click(RECOMMEND_TO_FRIENDS);
        clearAndEnterText(SHOPING_EXPERIENCE, generateAlphabets(25));
        if (isElementPresent(REVIEWER_NAME)) {
            clearAndEnterText(REVIEWER_NAME, generateAlphabets(6));
            clearAndEnterText(REVIEWER_EMAIL, generateEmail());
        }
        click(TERMS_AND_CONDITIONS);
        click(BTN_POST_REVIEW);
    }

    public String getSuccessReviewMessage() {
        return waitForElement(REVIEW_SUCCESS_MESSAGE).getText().trim();
    }

    public void closeReviewPopup() {
        intentionalWait(3000);
        click(CLOSE_ICON);
        intentionalWait(3000);
    }

    public String getReviewHeader() {
        return waitForElement(REVIEW_HEADER).getText().trim();
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getProductStyleNumber() {
        String[] url = driver.getCurrentUrl().split("/");
        styleNumber = url[url.length - 1];
        return styleNumber;
    }

    public String storeStyleNumber() {
        styleNumber = waitForElement(STYLE_NUMBER).getText().split("#")[1].trim();
        return styleNumber;
    }

    public String getStyleNumber() {
        return styleNumber;
    }

    public SizeChartPage navigateToSizeChartPage() {
        waitForElement(SIZE_CHART).click();
        return new SizeChartPage(driver);
    }
}