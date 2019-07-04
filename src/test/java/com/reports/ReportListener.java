package com.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.test.page.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ReportListener implements ITestListener {

  private String filePath =
      "." + File.separator + "reports" + File.separator + "screenshots" + File.separator;

  public void onStart(ITestContext context) {
    System.out.println("*** Test Suite " + context.getName() + " started ***");
  }

  public void onFinish(ITestContext context) {
    System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
    ExtentTestManager.endTest();
    ExtentManager.getInstance().flush();
  }

  public void onTestStart(ITestResult result) {
    System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
    ExtentTestManager.startTest(result.getMethod().getMethodName());
  }

  public void onTestSuccess(ITestResult result) {
    String methodName = result.getMethod().getMethodName();
    System.out.println("*** Test execution " + methodName + " test successfully...");
    ExtentTest extentTest = ExtentTestManager.getTest();
    MediaEntityModelProvider mediaEntityModelProvider = attachScreenshot(takeScreenShot(result));
    extentTest.pass("Test Passed ...", mediaEntityModelProvider);
  }

  public void onTestFailure(ITestResult result) {
    String methodName = result.getMethod().getMethodName();
    System.out.println("*** Test execution " + methodName + " failed...");
    ExtentTest extentTest = ExtentTestManager.getTest();
    MediaEntityModelProvider mediaEntityModelProvider = attachScreenshot(takeScreenShot(result));
    extentTest.fail(result.getThrowable(), mediaEntityModelProvider);
  }

  public void onTestSkipped(ITestResult result) {
    System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
    ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    System.out
        .println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
  }

  private MediaEntityModelProvider attachScreenshot(String base64String) {
    MediaEntityModelProvider mediaEntityModelProvider = null;
    try {
      mediaEntityModelProvider = MediaEntityBuilder
          .createScreenCaptureFromBase64String(base64String)
          .build();
    } catch (IOException e) {
      System.out.println("Unable to attach screenshot " + e.getLocalizedMessage());
    }

    return mediaEntityModelProvider;
  }

  private String takeScreenShot(ITestResult result) {
    return ((TakesScreenshot) ((BaseTest) result.getInstance()).getDriver())
        .getScreenshotAs(OutputType.BASE64);
  }
}
