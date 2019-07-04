package com.tests;

import com.pages.*;
import com.test.page.BaseTest;
import com.variables.TestConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTestScripts extends BaseTest {

    @Test
    public void createAccountAndCheckoutUsingInterac() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        CategoryPage categoryPage = profilePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void loginToAccountAndCheckoutUsingInterac() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        ProfilePage profilePage =
                loginPage.loginToUsingExistingAccount("qa10react123@yopmail.com", "Loyalty01");
        profilePage.clickOnCompleteLater();
        CategoryPage categoryPage = profilePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void validateAddress() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        CategoryPage categoryPage = profilePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
    }

    @Test
    public void quickAddToBagCheckout() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NEW");
        categoryPage.addToBag();
        CartPage cartPage = categoryPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.checkoutAsGuest();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void validateFindInStore() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.selectSize();
        productPage.checkFindInStore("H3C 1V8");
    }

    @Test
    public void dynamiteAndGarageCheckout() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        homePage = productPage.navigateToGarage();
        homePage.closeDialogPopupIfPresent();
        categoryPage = homePage.navigateToCategoryPage("NEW");
        homePage.closeDialogPopupIfPresent();
        productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.checkoutAsGuest();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void frenchCheckout() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        homePage = homePage.navigateToFrenchDynamite();
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NOUVEAUTÉS");
        categoryPage.addToBag();
        CartPage cartPage = categoryPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.checkoutAsGuest();
        checkoutPage.enterAddress();
        checkoutPage.clickOnConfirmShipmentWithoutSelectingShipmentMethod();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void validateFooterLinks() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        homePage = homePage.navigateToHomePage();
        homePage.validateAllFooterLinks();
    }

    @Test
    public void createAccountSearchAndCheckoutUsingInterac() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        homePage = homePage.navigateToHomePage();
        CategoryPage categoryPage = homePage.searchWithCategory("TOPS");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void productReview() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        CategoryPage categoryPage = profilePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.writeReview();
        Assert.assertEquals("Your review was submitted!", productPage.getSuccessReviewMessage());
        productPage.closeReviewPopup();
        Assert.assertEquals(productPage.getReviewHeader(), productPage.getReviewTitle());
    }

    @Test
    public void createAccountSortAndCheckoutUsingInterac() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        CategoryPage categoryPage = profilePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToFirstProductPage();
        productPage.getProductStyleNumber();
        homePage = productPage.navigateToHomePage();
        categoryPage = homePage.navigateToCategoryPage("NEW");
        categoryPage.sortPriceLowToHigh();
        categoryPage.sortPriceHighToLow();
        categoryPage.sortNewArrivals();
        String firstProductStyleNumber = categoryPage.getFirstProductStyleNumber();
        Assert.assertTrue(
                firstProductStyleNumber.contains(productPage.getStyleNumber()),
                String.format("First product style number is %s", firstProductStyleNumber));
        productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //        checkoutPage.clickOnPlaceOrder();
        //        checkoutPage.placeOrderUsingInterac();
        //        System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void addAndUpdateProductAndCheckout() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.fillPersonalDetails();
        CategoryPage categoryPage = profilePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        productPage.addToBag();
        CartPage cartPage = productPage.navigateToCartPage();
        cartPage.updateProductSize();
        cartPage.validateUpdatedSize();
        CheckoutPage checkoutPage = cartPage.navigateToCheckoutPage();
        checkoutPage.enterAddressToGetAddressValidatorPopup();
        checkoutPage.useCorrectedAddress();
        checkoutPage.clickOnNextStep();
        checkoutPage.selectPaymentMethodUsingId(TestConstants.INTERAC);
        //    checkoutPage.clickOnPlaceOrder();
        //    checkoutPage.placeOrderUsingInterac();
        //    System.out.println(checkoutPage.getOrderNumber());
    }

    @Test
    public void validateSizeChartPage() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NEW");
        ProductPage productPage = categoryPage.navigateToProductPage();
        SizeChartPage sizeChartPage = productPage.navigateToSizeChartPage();
        sizeChartPage.validateCategoriesSizeTabNames();
    }

    @Test
    public void validateStudioAndOfferPages() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        ProfilePage profilePage =
                loginPage.loginToUsingExistingAccount("arshamirpeta1@dynamite.ca", "Loyalty01");
        StudioPage studioPage = profilePage.navigateToStudioPage();
        studioPage.validateStudioPage();
        studioPage.clickOnOfferTab();
        studioPage.validateOffersPage();
    }
}
