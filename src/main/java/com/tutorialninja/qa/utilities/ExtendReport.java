package com.tutorialninja.qa.utilities;

import java.io.*;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReport {
  public static ExtentReports generateExtendReport() {
	  ExtentReports extendReport = new ExtentReports();
	  File extendReportfile= new File(System.getProperty("user.dir")+"\\test-output\\ExtendReports\\extendReport.html");
	  ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extendReportfile);
	  sparkReporter.config().setTheme(Theme.DARK);
	  sparkReporter.config().setReportName("TutorialNinja Test Automation");
	  sparkReporter.config().setDocumentTitle("Automation report");
	  sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	  extendReport.attachReporter(sparkReporter);
	  
	  Properties prop = new Properties();
	  File propfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninga\\qa\\config\\config.properties");
	  try {
	  FileInputStream fis = new FileInputStream(propfile);
	  prop.load(fis);
	  }catch(Throwable e) {
		  e.printStackTrace();
	  }
	  extendReport.setSystemInfo("Application URL", prop.getProperty("url"));
	  extendReport.setSystemInfo("Browser Name",prop.getProperty("browserName") );
	  extendReport.setSystemInfo("Email",prop.getProperty("ValidEmail"));
	  extendReport.setSystemInfo("Password",prop.getProperty("ValidPassword"));
	  extendReport.setSystemInfo("operating system",System.getProperty("os.name"));
	  extendReport.setSystemInfo("operating system",System.getProperty("user.name"));
	  extendReport.setSystemInfo("operating system",System.getProperty("java.version"));
	
		return extendReport;
  }
}
