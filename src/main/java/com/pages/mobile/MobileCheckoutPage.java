package com.pages.mobile;

import com.pages.BasePage;
import com.utils.ExcelUtilityClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;
import java.util.HashMap;

public class MobileCheckoutPage extends BasePage {

    private static By SHIP_TO_ME = By.xpath("//label[@for = 'shipToMeOption']");
    private static By FIRST_NAME_ID = By.id("firstName");
    private static By LAST_NAME_ID = By.id("lastName");
    private static By ADDRESS_ONE_ID = By.id("address1");
    private static By CITY_ID = By.id("city");
    private static By STATE_ID = By.id("state");
    private static By POSTAL_CODE_ID = By.id("postalCode");
    private static By PHONE_NUMBER_ID = By.id("phoneNumber");
    private static By CONFIRM_SHIPPING_ADDRESS = By.cssSelector("#confirmShippingAddressBtn");
    private static By GO_TO_PAYMENT = By.id("checkoutStep_btn");
    private static By CREDIT_CARD_ACCORDION = By.id("ccAccordionTab");
    private static By CARD_NUMBER_ID = By.id("creditCardNo");
    private static By EXPIRY_MONTH_ID = By.id("expirationMonth");
    private static By EXPIRY_YEAR_ID = By.id("expirationYear");
    private static By CVV_ID = By.id("ccCVV");
    private static By PACKING_SLIP_MESSAGE = By.id("customerMessage");
    private static By GUEST_CHECKOUT_MAIL = By.id("guestEmail");
    private static By BTN_CONTINUE_AS_GUEST = By.id("continueAsGuestBtn");
    private static By USE_CORRECTED_ADDRESS = By.id("useCorrectedAddress");
    private static By SAME_AS_SHIPPING_ADDRESS = By.xpath("//label[@for = 'sameAsShippingAddress']");
    private static By PAYMENT_DETAILS = By.id("orderDetails");
    private static final String TAX_COMPONENT = "//td[contains(text(), '%s')]/following-sibling::td";

    private String state;

    public MobileCheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectShipToMe() {
        click(SHIP_TO_ME);
        intentionalWait(3000);
    }

    public void enterAddress() {
        enterText(FIRST_NAME_ID, "Dynamite");
        enterText(LAST_NAME_ID, "Test");
        enterText(ADDRESS_ONE_ID, "1140 Wellington Street Apt 1507");
        enterText(CITY_ID, "Montreal");
        new Select(waitForElement(STATE_ID)).selectByValue("QC");
        enterText(POSTAL_CODE_ID, "H3C 1V8");
        enterText(PHONE_NUMBER_ID, "(416) 629-0479");
        enterText(PACKING_SLIP_MESSAGE, "Test Automation Message");
        intentionalWait(3000);
        clickUsingJS(locateElement(CONFIRM_SHIPPING_ADDRESS));
        intentionalWait(3000);
        clickUsingJS(waitForElement(GO_TO_PAYMENT));
        intentionalWait(5000);
    }

    public void enterAddressToGetUseAddressPopup() {
        enterText(FIRST_NAME_ID, "Dynamite");
        enterText(LAST_NAME_ID, "Test");
        enterText(ADDRESS_ONE_ID, "1140 Wellington Street apt 1507");
        enterText(CITY_ID, "Montreal");
        selectByVisibleText(STATE_ID, "Quebec");
        enterText(POSTAL_CODE_ID, "H3C 1V8");
        enterText(PHONE_NUMBER_ID, "(416) 629-0479");
        enterText(PACKING_SLIP_MESSAGE, "Test Automation Message");
        intentionalWait(3000);
        clickUsingJS(locateElement(CONFIRM_SHIPPING_ADDRESS));
        intentionalWait(3000);
        useCorrectedAddress();
//        clickUsingJS(waitForElement(GO_TO_PAYMENT));
        intentionalWait(3000);
        clickUsingJS(waitForElement(GO_TO_PAYMENT));
    }

    public void useCorrectedAddress() {
        clickUsingJS(waitForElement(USE_CORRECTED_ADDRESS));
    }

    public void enterAddressFromExcel(String stateName, HashMap<String, String> addressData) {
        clearAndEnterText(FIRST_NAME_ID, "Dynamite");
        clearAndEnterText(LAST_NAME_ID, "Test");
        clearAndEnterText(ADDRESS_ONE_ID, addressData.get("Address"));
        clearAndEnterText(CITY_ID, addressData.get("City"));
        clearAndEnterText(POSTAL_CODE_ID, addressData.get("PostalCode"));
        new Select(locateElement(STATE_ID)).selectByVisibleText(stateName);
        clearAndEnterText(PHONE_NUMBER_ID, "(416) 629-0479");
        state = new Select(locateElement(By.id("state"))).getFirstSelectedOption().getText().trim();
        enterText(PACKING_SLIP_MESSAGE, "Test Automation Message");
    }

    public void navigateToCheckoutPageStepTwo() {
        intentionalWait(3000);
        clickUsingJS(locateElement(CONFIRM_SHIPPING_ADDRESS));
        intentionalWait(3000);
        click(GO_TO_PAYMENT);
        intentionalWait(2000);
        click(GO_TO_PAYMENT);
        intentionalWait(5000);
    }

    public void enterCreditCardDetails() {
        clickUsingJS(waitForElement(CREDIT_CARD_ACCORDION));
        intentionalWait(3000);
        enterText(CARD_NUMBER_ID, "4539010000012345");
        selectByValue(EXPIRY_MONTH_ID, "7");
        selectByValue(EXPIRY_YEAR_ID, "21");
        enterText(CVV_ID, "777");
    }

    public void enterGuestUserDetails() {
        enterText(GUEST_CHECKOUT_MAIL, generateEmail());
        clickUsingJS(locateElement(BTN_CONTINUE_AS_GUEST));
    }

    public void clickOnPlaceYourOrder() {
        clickUsingJS(locateElement(SAME_AS_SHIPPING_ADDRESS));
        intentionalWait(1000);
        click(GO_TO_PAYMENT);
        intentionalWait(5000);
    }

    public void openPaymentDetails() {
        clickUsingJS(waitForElement(PAYMENT_DETAILS));
        intentionalWait(3000);
    }

    public String calculateTax(String productPrice) throws Exception {
        String shippingText = locateElement(By.xpath("//td[contains(text(), 'Shipping to me')]/following-sibling::td")).getAttribute("innerHTML");
        System.out.println("Shipping Text " + shippingText);
        Float shippingCharge = 0.0f;
        if (!shippingText.equalsIgnoreCase("free")) {
            shippingText = shippingText.trim().replace("$", "");
            shippingCharge = Float.parseFloat(shippingText);
        }
        Float taxPercentage = Float.parseFloat(ExcelUtilityClient.readTestScriptData("Tax").get(state).get("TotalRate"));
        Float price = Float.parseFloat(productPrice.trim().replace("$", "")) + shippingCharge;
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        String tax = decimalFormat.format(price * taxPercentage/100);
        System.out.println("calculate tax" + tax);
        return tax;
    }

    public String getTaxFromPage(String state) throws Exception {
        float tax = 0;
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        String[] allTaxes = ExcelUtilityClient.readTestScriptData("Tax").get(state).get("TaxApplicable").trim().split("&");
        for (String taxComponent : allTaxes) {
            if (isElementPresent(By.xpath(String.format(TAX_COMPONENT, taxComponent.trim())))) {
                tax = tax + Float.parseFloat(locateElement(By.xpath(String.format(TAX_COMPONENT, taxComponent.trim()))).getAttribute("innerHTML").trim().replace("$", ""));
            }
        }

        System.out.println("getTaxFromPage - " + tax);

        return decimalFormat.format(tax);
    }

    public String getOrderNumber() {
        WebElement order = waitForElement(By.xpath("//p[@class = 'orderConfMessage']/strong"));
        return order.getText().trim();
    }
}
