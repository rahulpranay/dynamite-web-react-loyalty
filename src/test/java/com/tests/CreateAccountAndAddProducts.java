package com.tests;

import com.pages.*;
import com.test.page.BaseTest;
import com.utils.ExcelUtilityClient;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateAccountAndAddProducts extends BaseTest {

    @Test(dataProvider = "testdata")
    public void createAccountAndCheckoutUsingInterac(String sNo, String sku) throws Exception {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        String [] skus = sku.split(",");
        ProductPage productPage = profilePage.searchWithSku(skus[0]);
        productPage.addToBag();
        homePage = productPage.navigateToHomePage();
        ProductPage productPage1 = homePage.searchWithSku(skus[1]);
        productPage1.addToBag();
        CartPage cartPage = productPage1.navigateToCartPage();
        Assert.assertEquals(cartPage.getProductCount(), 2);
        ExcelUtilityClient.writeToExcel("LoginTestData", "LoginTestData", sNo, 2, createAccountPage.getEmailId());
        ExcelUtilityClient.writeToExcel("LoginTestData", "LoginTestData", sNo, 3, createAccountPage.getPassword());
        cartPage.logOut();
    }


    @DataProvider(name = "testdata")
    public Object[][] getSnoAndSkusData() throws Exception {
        return ExcelUtilityClient.readSnoAndSkusFromExcel("LoginTestData", "LoginTestData");
    }
}
