package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

import Base.Base;

public class SearchTest extends Base {
	public WebDriver driver;
	HomePage homepage;
	SearchPage searchpage;
	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndPassURL(prop.getProperty("browserName"));
		homepage = new HomePage(driver);
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test (priority=1)
   public void verifySearchWithValidProduct() {
		searchpage = homepage.searchForProduct(dataProp.getProperty("validProduct"));
        Assert.assertTrue(homepage.clickOnSearchButton().displayStatusOfHPProduct(),"Product not visible");
   }
	@Test (priority=2)
	public void verifySearchWithInValidProduct() {
		searchpage= homepage.searchForProduct(dataProp.getProperty("invalidProduct"));
		 Assert.assertEquals(searchpage.retrieveNoProductMessageText(),"abc","No product error message not dispalyed");
	   }
	@Test (priority=3, dependsOnMethods= {"verifySearchWithInValidProduct","verifySearchWithValidProduct"})
	   public void verifySearchWithoutProduct() {
		searchpage =homepage.clickOnSearchButton();
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextSearch"),"No product error message not dispalyed");

	   }
}
