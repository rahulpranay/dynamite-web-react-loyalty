package com.pages;

import com.utils.ExcelUtilityClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class CheckoutPage extends BasePage {

    private static final String EMAIL_ADDRESS = "//input[@id = 'guestEmail']";
    private static final String CONTINUE_AS_GUEST = "//a[@id = 'continueAsGuestBtn']//div[@class = 'buttonText']";
    private static final String SHIP_TO_ME = "//input[@id = 'shipToMeRadio']";
    private static final String CHECKOUT_TEXT_BOX = "//label[normalize-space() = '%s']/following-sibling::input";
    private static final String TAX_COMPONENT = "//p[text() = '%s']/span";
    private static final By FIRSTNAME = By.id("firstName");
    private static final By LASTNAME = By.id("lastName");
    private static final By ADDRESS = By.id("address1");
    private static final By CITY = By.id("city");
    private static final By POSTAL_CODE = By.id("postalCode");
    private static final By STATE = By.id("state");
    private static final By PHONE_NUMBER = By.id("phoneNumber");
    private static final By PLACE_TO_ORDER = By.id("applyPaymentMethodBtn");
    private String state;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest() {
        waitForElement(By.xpath(EMAIL_ADDRESS)).sendKeys(generateEmail());
        click(By.xpath(CONTINUE_AS_GUEST));
        intentionalWait(5000);
    }

    public void enterAddressToGetAddressValidatorPopup(String stateName, HashMap<String, String> addressData) {
        click(By.xpath(SHIP_TO_ME));
        clearAndEnterText(FIRSTNAME, "Dynamite");
        clearAndEnterText(LASTNAME, "Test");
        clearAndEnterText(ADDRESS, addressData.get("Address"));
        clearAndEnterText(CITY, addressData.get("City"));
        clearAndEnterText(POSTAL_CODE, addressData.get("PostalCode"));
        new Select(locateElement(STATE)).selectByVisibleText(stateName);
        clearAndEnterText(PHONE_NUMBER, "(416) 629-0479");
        state = new Select(locateElement(By.id("state"))).getFirstSelectedOption().getText().trim();
    }

    public void enterAddressToGetAddressValidatorPopup() {
        click(By.xpath(SHIP_TO_ME));
        waitForElement(FIRSTNAME);
        clearAndEnterText(FIRSTNAME, "Dynamite");
        clearAndEnterText(LASTNAME, "Test");
        clearAndEnterText(ADDRESS, "1140 Wellington street apt 1507");
        clearAndEnterText(CITY, "Montreal");
        clearAndEnterText(POSTAL_CODE, "H3C 1V8");
        new Select(locateElement(STATE)).selectByValue("QC");
        clearAndEnterText(PHONE_NUMBER, "(416) 629-0479");
        state = new Select(locateElement(STATE)).getFirstSelectedOption().getText().trim();
    }

    public void enterAddress() {
        click(By.xpath(SHIP_TO_ME));
        clearAndEnterText(FIRSTNAME, "Dynamite");
        clearAndEnterText(LASTNAME, "Test");
        clearAndEnterText(ADDRESS, "1140 Wellington Street Apt 1507");
        clearAndEnterText(CITY, "Montreal");
        clearAndEnterText(POSTAL_CODE, "H3C 1V8");
        new Select(locateElement(STATE)).selectByValue("QC");
        clearAndEnterText(PHONE_NUMBER, "(416) 629-0479");
        state = new Select(locateElement(STATE)).getFirstSelectedOption().getText().trim();
    }

    public void clickOnConfirmShipment() {
        locateElement(By.id("confirmShippingAddressBtn")).click();
        intentionalWait(5000);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'shipMethod']")));
        List<WebElement> shippingMethods = listOfVisibleElements(By.xpath("//input[@name = 'shipMethod']"));
        shippingMethods.get(1).click();
        intentionalWait(8000);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'shipMethod']")));
        shippingMethods = listOfVisibleElements(By.xpath("//input[@name = 'shipMethod']"));
        shippingMethods.get(0).click();
        intentionalWait(8000);
    }

    public void useCorrectedAddress() {
        locateElement(By.id("confirmShippingAddressBtn")).click();
        intentionalWait(2000);
        locateElement(By.id("useCorrectedAddress")).click();
        intentionalWait(5000);
    }

    public void clickOnNextStep() {
        waitForElementToClickable(By.id("saveShipMethodBtn")).click();
        intentionalWait(5000);
    }

    public void clickOnConfirmShipmentWithoutSelectingShipmentMethod() {
        locateElement(By.id("confirmShippingAddressBtn")).click();
        intentionalWait(8000);
    }

    public String getTaxFromPage(String state) throws Exception {
        float tax = 0;
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        String[] allTaxes = ExcelUtilityClient.readTestScriptData("Tax").get(state).get("TaxApplicable").trim().split("&");
        for (String taxComponent : allTaxes) {
            if (isElementPresent(By.xpath(String.format(TAX_COMPONENT, taxComponent.trim())))) {
                tax = tax + Float.parseFloat(locateElement(By.xpath(String.format(TAX_COMPONENT, taxComponent.trim()))).getText().trim().replace("$", ""));
            }
        }

        return decimalFormat.format(tax);
    }

    public String calculateTax(String productPrice) throws Exception {
        String shippingText = locateElement(By.xpath("//p[contains(text(), 'Shipping to me')]/span")).getText();
        Float shippingCharge = 0.0f;
        if (!shippingText.equalsIgnoreCase("free")) {
            shippingText = shippingText.trim().replace("$", "");
            shippingCharge = Float.parseFloat(shippingText);
        }
        Float taxPercentage = Float.parseFloat(ExcelUtilityClient.readTestScriptData("Tax").get(state).get("TotalRate"));
        Float price = Float.parseFloat(productPrice.trim().replace("$", "")) + shippingCharge;
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        return decimalFormat.format(price * taxPercentage/100);
    }

    public void selectPaymentMethodUsingId(String id) {
        click(By.id(id));
    }

    public void clickOnPlaceOrder() {
        click(PLACE_TO_ORDER);
        intentionalWait(2000);
    }

    public void placeOrderUsingInterac() {
        click(By.xpath("//input[@value = 'Continue']"));
    }

    public String getOrderNumber() {
        WebElement order = waitForElement(By.xpath("//div[@class = 'orderConfirmationTxtContainer']//div[@class = 'innerpadding']/h3"));
        return order.getText().split(":")[1].trim();
    }
}
