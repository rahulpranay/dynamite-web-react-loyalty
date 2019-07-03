package com.tests;

import com.pages.CategoryPage;
import com.pages.HomePage;
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
}
