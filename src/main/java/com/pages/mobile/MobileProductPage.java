package com.pages.mobile;

import com.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class MobileProductPage extends BasePage {

    private String productSize;
    private String productPrice;

    private static By SIZE = By.xpath("//div[contains(@class, 'selectorsstyle__SizeTileStyle')][not(contains(@class, 'unavailable'))]");
    private static By ADD_TO_BAG = By.id("pdp_addtobag_btn");
    private static By SUCCESS_MESSAGE = By.id("addToBagSuccess");
    private static By PRICE_XPATH = By.xpath("//div[contains(@class, 'pdpstyle__PdpPriceMobile')]/span[last()]");
    private static final By FIND_IN_STORE = By.xpath("//button[@id = 'pdp_fis_btn']");
    private static final By FIND_IN_STORE_TITLE = By.xpath("//div[contains(@class, 'FindInStore__Title')]");
    private static final By FIND_IN_STORE_ZIP_CODE_BTN = By.id("pdp_fis_use_zip");
    private static final By FIND_IN_STORE_ZIP_CODE = By.id("pdp_fis_zip");
    private static final By FIND_IN_STORE_SUBMIT = By.id("pdp_fis_zip_submit");
    private static final By FIND_IN_STORE_RESULTS = By.id("pdp_fis_result");
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
    private static final By REVIEW_HEADER = By.xpath("//h4[@class = 'bv-content-title']");
    private static final By REVIEWER_NAME = By.id("bv-text-field-usernickname");
    private static final By REVIEWER_EMAIL = By.id("bv-email-field-hostedauthentication_authenticationemail");
    private String reviewTitle;
    private String styleNumber;

    public MobileProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSize() {
        WebElement size = waitForElement(SIZE);
        productSize = size.getText().trim();
        productPrice = waitForElement(PRICE_XPATH).getText().trim();
        size.click();
    }

    public void addToBag() {
        selectSize();
        click(ADD_TO_BAG);
        intentionalWait(5000);
    }

    public String getAddToBagSuccessMessage() {
        return waitForElement(SUCCESS_MESSAGE).getText().trim();
    }

    public String getProductSize() {
        return productSize;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void checkFindInStore(String zipCode) {
        WebElement size = waitForElement(SIZE);
        size.click();
        click(FIND_IN_STORE);
        Assert.assertEquals(waitForElement(FIND_IN_STORE_TITLE).getText().trim(), "Find in store");
        clickUsingJS(waitForElement(FIND_IN_STORE_ZIP_CODE_BTN));
        enterText(FIND_IN_STORE_ZIP_CODE, zipCode);
        click(FIND_IN_STORE_SUBMIT);
        intentionalWait(2000);
        Assert.assertTrue(isElementPresent(FIND_IN_STORE_RESULTS));
    }

    public void writeReview() {
        click(WRITE_REVIEW);
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
        WebElement postReview = waitForElement(BTN_POST_REVIEW);
        new Actions(driver).moveToElement(postReview).pause(1000).click().perform();
    }

    public String getSuccessReviewMessage() {
        return waitForElement(REVIEW_SUCCESS_MESSAGE).getText().trim();
    }

    public void closeReviewPopup() {
        click(CLOSE_ICON);
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

    public String getStyleNumber() {
        return styleNumber;
    }
}
