package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class WriteaReview extends BasePage {

    public WriteaReview(WebDriver driver) {
        super(driver);
    }


    public void validateReviewPage(){
        locateElement(By.xpath("//*[@id=\"bv-fieldset-label-rating\"]/span/span[1]")).isDisplayed();


}

}