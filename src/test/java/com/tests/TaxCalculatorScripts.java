package com.tests;

import com.pages.*;
import com.test.page.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.utils.ExcelUtilityClient.readTestScriptData;

public class TaxCalculatorScripts extends BaseTest {

    @Test
    public void britishColumbia() throws Exception {
        taxCalculator("British Columbia");
    }

    @Test
    public void novaScotia() throws Exception {
        taxCalculator("Nova Scotia");
    }

    @Test
    public void ontario() throws Exception {
        taxCalculator("Ontario");
    }

    @Test
    public void quebec() throws Exception {
        taxCalculator("Quebec");
    }

    @Test
    public void saskatchewan() throws Exception {
        taxCalculator("Saskatchewan");
    }

    private void taxCalculator(String province) throws Exception {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        homePage.closeDialogPopupIfPresent();
        checkoutPage.checkoutAsGuest();
        Map<String, HashMap<String, String>> testData = readTestScriptData("Address");
        checkoutPage.enterAddressToGetAddressValidatorPopup(province, testData.get(province));
        checkoutPage.clickOnConfirmShipment();
        checkoutPage.clickOnNextStep();
        Assert.assertEquals(checkoutPage.getTaxFromPage(province), checkoutPage.calculateTax(productPage.getProductPrice()));
    }
}
