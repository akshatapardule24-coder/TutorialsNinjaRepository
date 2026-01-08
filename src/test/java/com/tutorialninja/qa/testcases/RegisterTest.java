package com.tutorialninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utilities.Utilities;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

import Base.Base;
public class RegisterTest extends Base {
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	  RegisterPage registerPage;
	  AccountSuccessPage accountsuccesspage;
	      @BeforeMethod
	      public void setup() {
	      driver = intializeBrowserAndPassURL(prop.getProperty("browserName"));
	      HomePage homepage = new HomePage(driver);
	      registerPage= homepage.naviagateToRegisterPage();
	  }
	  @AfterMethod
	  public void tearDown() {
		  driver.quit();
	  }
	  @Test (priority=1)
	  public void registerWithMendatoryFields() {
		  accountsuccesspage= registerPage.registerWithMendatoryField(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailByTimestamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("ValidPassword"));
	      String actualText = accountsuccesspage.retrieveAccountSuccessPageHeadingText();
	      Assert.assertEquals(actualText, dataProp.getProperty("accountSuccessHeading"));
	  
	  }
	  @Test (priority=2)
	  public void registerWithAllFields() {
		  
		  accountsuccesspage= registerPage.registerWithAllField(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailByTimestamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("ValidPassword"));
	      Assert.assertEquals(accountsuccesspage.retrieveAccountSuccessPageHeadingText(), dataProp.getProperty("accountSuccessHeading"));

		  }
	  @Test (priority =3)
	  public void registerWithExistingEmail() {
		  accountsuccesspage= registerPage.registerWithAllField(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("ValidEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("ValidPassword"));
		  Assert.assertEquals(registerPage.retrieveDuplicateErrorMessage(), dataProp.getProperty("dublicateEmailWarning"),"warning message not dsplayed");
		  Assert.assertTrue(registerPage.retrieveDuplicateErrorMessage().contains(dataProp.getProperty("dublicateEmailWarning")),"warning message not dsplayed");
		  }
	  @Test  (priority =4)
	  public void registerWithNoFields() {
		  registerPage.continueField();
		  Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"),dataProp.getProperty("firstNameWarning"),
		   dataProp.getProperty("lastNameWarning"),dataProp.getProperty("emailWarning"),dataProp.getProperty("telephoneWarning"),dataProp.getProperty("passwordWarning")),"warning messages are not displayed");
	  }
}













