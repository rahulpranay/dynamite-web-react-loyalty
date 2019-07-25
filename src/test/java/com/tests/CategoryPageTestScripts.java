package com.tests;

import com.pages.*;
import com.test.page.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryPageTestScripts extends BaseTest {

    @Test
    public void sortingLowToHigh() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        CategoryPage categoryPage = homePage.navigateToCategoryPage("NEW");
        categoryPage.sortPriceLowToHigh();
        List<Integer> expectedPriceList = categoryPage.getProductPriceList().stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(categoryPage.getProductPriceList(), expectedPriceList);
    }

    @Test
    public void validateRewardPage() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        homePage.validateGetRewardPage();
    }

    @Test
    public void validateStoreLocatorPage() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        homePage.validateStoreLocatorPage();
    }

    @Test
    public void validateJoinOurNewsLetter() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        homePage.validateJoinOurNewsLetter();
    }

    @Test
    public void validateCompleteLaterAfterCreatingAccount() {
        HomePage homePage = new HomePage(d.getDriver());
        homePage.closeDialogPopupIfPresent();
        LoginPage loginPage = homePage.navigateToLoginPage();
        CreateAccountPage createAccountPage = loginPage.clickCreateAccountButton();
        ProfilePage profilePage = createAccountPage.enterAccountDetails();
        profilePage.skipPersonalDetails();
    }
}
