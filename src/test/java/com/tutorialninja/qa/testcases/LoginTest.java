package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialninja.qa.utilities.Utilities;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

import Base.Base;

public class LoginTest extends Base{
	public LoginTest() {
		super();
	}
	public WebDriver driver;
	LoginPage loginpage;
	@BeforeMethod
	public void setup() {
		  driver = intializeBrowserAndPassURL(prop.getProperty("browserName"));
		  HomePage homepage = new HomePage(driver);
		  loginpage = homepage.navigateTOLoginPage();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test (priority=1,dataProvider="supplierMethod")
  public void verifyLoginWithValidCredentials(String email, String password) {	
		AccountPage accountpage=loginpage.login(email,password);
	  Assert.assertTrue(accountpage.getDisplayStatus(),"Edit your account information is not displayed");
  }
	@DataProvider (name="supplierMethod")
	public Object[][] supplier() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	@Test (priority=2)
	public void verifyLoginWithInValidCredentials() {
		  loginpage.login(Utilities.generateEmailByTimestamp(),dataProp.getProperty("invalidPassword"));
		  Assert.assertEquals(loginpage.retrieveWarning(),dataProp.getProperty("emailPasswordNoMatchWarning"));
		  Assert.assertTrue(loginpage.retrieveWarning().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected warninng msg is not displayed");	  
	  }
	@Test (priority=3)
	public void verifyLoginWithInValidEmailandValidPassword() {
		loginpage.login(Utilities.generateEmailByTimestamp(),dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginpage.retrieveWarning().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected warninng msg is not displayed");
	  }
	@Test (priority=4)
	public void verifyLoginWithValidEmailandInValidPassword() {
		loginpage.login(prop.getProperty("ValidEmail"),dataProp.getProperty("invalidPassword"));
		String expectedmsg=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginpage.retrieveWarning().contains(expectedmsg),"Expected warninng msg is not displayed");
	  }
	@Test (priority=5)
	public void verifyLoginWithoutCredentials() {
		loginpage.clickonLoginButton(); 
		String expectedmsg=dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginpage.retrieveWarning().contains(expectedmsg),"Expected warninng msg is not displayed");
	  }	
}
