package com.tutorailsning.qa.listner;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utilities.ExtendReport;
import com.tutorialninja.qa.utilities.Utilities;

import org.openqa.selenium.TakesScreenshot;

public class MyListener implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		 extentReport = ExtendReport.generateExtendReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		extentTest= extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+"started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName()+" executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String destinationPath = Utilities.catureScreenshot(driver,result.getName());
		extentTest.addScreenCaptureFromPath(destinationPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got skipped");	
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtendReport = System.getProperty("user.dir")+"\\test-output\\ExtendReports\\extendReport.html";
		File extent= new File(pathOfExtendReport);
		try {
			Desktop.getDesktop().browse(extent.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
