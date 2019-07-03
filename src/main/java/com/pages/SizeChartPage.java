package com.pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SizeChartPage extends BasePage {

  private static final By CATEGORIES_TABS =
      By.xpath("//div[contains(@class, 'sizeChartstyle__TabContainer')]//div/a[@role = 'tab']");

  public SizeChartPage(WebDriver driver) {
    super(driver);
  }

  public void validateCategoriesSizeTabNames() {
    waitForElement(CATEGORIES_TABS);
    List<String> actualCategories =
        listOfVisibleElements(CATEGORIES_TABS).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    List<String> expectedCategories =
        Arrays.asList("TOPS", "BOTTOMS", "DRESSES", "DENIM", "SWIMWEAR", "ACCESSORIES", "SHOES");
    Assert.assertEquals(actualCategories, expectedCategories);
  }
}
