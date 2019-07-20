package com.tests.mobile;

import com.pages.ProfilePage;
import com.pages.mobile.*;
import com.reports.ReportListener;
import com.test.page.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@Listeners(ReportListener.class)
public class MobileCheckoutTestScripts extends BaseTest {

    @Test
    public void guestCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        mobileHomePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.enterGuestUserDetails();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void createAccountAndCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void loginAndCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        mobileHomePage = mobileLoginPage.loginToAccount("qa10react123@yopmail.com", "Loyalty01");
        mobileHomePage.refreshPage();
        Assert.assertEquals("welcome", mobileHomePage.getProfileWelcomeText());
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void validateAddress() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddressToGetUseAddressPopup();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void validateFindInStore() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.checkFindInStore("H3C 1V8");
    }

    @Test
    public void createAccountDynamiteGarageCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        mobileProductPage.intentionalWait(5000);
        mobileHomePage = mobileProductPage.navigateToMobileGarageHomePage();
        String currentUrl = getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(currentUrl.contains("garage"), String.format("Current url page : %s", currentUrl));
        mobileHomePage.closeDialogPopupIfPresent();
        mobileHomePage.clickOnMenuIcon();
        mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void createAccountAndSort() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        mobileCategoryPage.sortLowToHigh();
        List<Double> expectedPriceList = mobileCategoryPage.getProductPriceList().stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(mobileCategoryPage.getProductPriceList(), expectedPriceList);
    }

    @Test
    public void frenchCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.navigateToFrenchDynamite();
        mobileHomePage.closeDialogPopupIfPresent();
        mobileHomePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToMobileCategoryPage("VÊTEMENTS");
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
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void searchWithItemAndCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.searchWithItemInMobile("Tops");
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        mobileCategoryPage = mobileProductPage.searchWithItemInMobile("Jeans");
        mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCartPage.allProductsList(), 2);
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }

    @Test
    public void writeReviewForProduct() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.writeReview();
        Assert.assertEquals("Your review was submitted!", mobileProductPage.getSuccessReviewMessage());
        mobileProductPage.closeReviewPopup();
        Assert.assertTrue(mobileProductPage.getReviewHeader().contains(mobileProductPage.getReviewTitle()));
    }

    @Test
    public void createAccountSortAndCheckout() {
        MobileHomePage mobileHomePage = new MobileHomePage(getDriver());
        mobileHomePage.closeDialogPopupIfPresent();
        MobileLoginPage mobileLoginPage = mobileHomePage.navigateToMobileLoginPage();
        ProfilePage profilePage = mobileLoginPage.createAccount();
        profilePage.fillPersonalDetails();
        profilePage.clickOnMenuIcon();
        MobileCategoryPage mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileHomePage.closeDialogPopupIfPresent();
        MobileProductPage mobileProductPage = mobileCategoryPage.navigateToFirstProductPage();
        mobileProductPage.getProductStyleNumber();
        mobileProductPage.clickOnMenuIcon();
        mobileCategoryPage = mobileHomePage.navigateToCategoryPage();
        mobileCategoryPage.sortLowToHigh();
        mobileCategoryPage.sortHighToLow();
        mobileCategoryPage.sortNewArrivals();
        Assert.assertEquals(mobileCategoryPage.getStyleNumberOfFirstProduct(), mobileProductPage.getStyleNumber());
        mobileProductPage = mobileCategoryPage.navigateToProductPage();
        mobileProductPage.addToBag();
        MobileCartPage mobileCartPage = mobileProductPage.navigateToMobileCartPage();
        mobileCartPage.refreshPage();
        Assert.assertEquals(mobileCategoryPage.getProductName(), mobileCartPage.getAddedProductName());
        Assert.assertEquals(mobileProductPage.getProductSize(), mobileCartPage.getAddedSize());
        MobileCheckoutPage mobileCheckoutPage = mobileCartPage.navigateToMobileCheckoutPage();
        mobileCheckoutPage.selectShipToMe();
        mobileCheckoutPage.enterAddress();
        mobileCheckoutPage.enterCreditCardDetails();
//        mobileCheckoutPage.clickOnPlaceYourOrder();
//        System.out.println(mobileCheckoutPage.getOrderNumber());
    }
}
