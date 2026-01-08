package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy (linkText="My Account")
	private WebElement myAccountDropMenu;
	@FindBy(linkText="Login")
	private WebElement login;
	@FindBy(linkText="Register")
	private WebElement register;
	@FindBy(name="search")
	private WebElement searchBoxField;
	@FindBy(className="input-group-btn")
	private WebElement searchButton;
    public HomePage(WebDriver driver) {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
    public void clickonMyAccount() {
    	myAccountDropMenu.click();
    }
    public LoginPage selectLoginOption() {
     login.click();
     return new LoginPage(driver);
    }
    public LoginPage navigateTOLoginPage() {
    	myAccountDropMenu.click();
    	login.click();
        return new LoginPage(driver);
    }
    public RegisterPage selectRegisterOption() {
    	register.click();
    	return new RegisterPage(driver);
    }
    public RegisterPage naviagateToRegisterPage() {
    	myAccountDropMenu.click();
    	register.click();
    	return new RegisterPage(driver);
    }
    public void enterProductText(String productText) {
    	searchBoxField.sendKeys(productText);
    }
    public SearchPage clickOnSearchButton() {
    	searchButton.click();
    	return new SearchPage(driver);
    }
    public SearchPage searchForProduct(String productText) {
    	searchBoxField.sendKeys(productText);
    	searchButton.click();
    	return new SearchPage(driver);
    }
}
