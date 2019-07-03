package com.tests.mobile;

import com.pages.mobile.*;
import com.test.page.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.utils.ExcelUtilityClient.readTestScriptData;

public class MobileTaxCalculatorScripts extends BaseTest {

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
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        mobileHomePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.enterGuestUserDetails();
        mobileCheckoutPage.selectShipToMe();
        Map<String, HashMap<String, String>> testData = readTestScriptData("Address");
        mobileCheckoutPage.enterAddressFromExcel(province, testData.get(province));
        mobileCheckoutPage.navigateToCheckoutPageStepTwo();
        mobileCheckoutPage.openPaymentDetails();
        Assert.assertEquals(mobileCheckoutPage.getTaxFromPage(province), mobileCheckoutPage.calculateTax(mobileProductPage.getProductPrice()));
    }
}
