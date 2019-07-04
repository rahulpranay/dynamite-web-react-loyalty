package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static String fileSeparator = System.getProperty("file.separator");
    private static String reportFileName = "React-QA-Web-Automation-Report";
    private static String reportFilepath = System.getProperty("user.dir") + fileSeparator + "reports";
    private static String reportFileLocation = reportFilepath + fileSeparator + reportFileName + ".html";


    static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    //Create an extent report instance
    private static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setJS("$(document).ready(function() { var a = document.getElementsByClassName('brand-logo')[0]; a.innerHTML=''; a.href = '';});");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        return extent;
    }

    //Create the report path
    private static String getReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!");
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }

}
