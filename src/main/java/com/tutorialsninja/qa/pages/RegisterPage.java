package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	@FindBy (id="input-firstname")
	private WebElement firstName;
	@FindBy (id="input-lastname")
	private WebElement lastName;
	@FindBy (id="input-email")
	private WebElement email;
	@FindBy (id="input-telephone")
	private WebElement telephone;
	@FindBy (id="input-password")
	private WebElement passwordField;
	@FindBy (id="input-confirm")
	private WebElement passwordConfirmField;
	@FindBy (name="agree")
	private WebElement agreeField;
	@FindBy (xpath="//input[@value='Continue']")
	private WebElement continueField;
	@FindBy (xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy (className="alert-dismissible")
	private WebElement duplicateEmailAdressWarning;
	
	@FindBy (xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy (xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy (xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy (xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement EmailWarning;
	
	@FindBy (xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy (xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}
	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}
	public void enterEmail(String EmailText) {
		email.sendKeys(EmailText);
	}
	public void enterTelephoneNo(String TelephoneText) {
		telephone.sendKeys(TelephoneText);
	}
	public void enterPassword(String PasswordText) {
		passwordField.sendKeys(PasswordText);
	}
	public void enterConfirmPassword(String ConfirmPasswordText) {
		passwordConfirmField.sendKeys(ConfirmPasswordText);
	}
	public void agreeField() {
		agreeField.click();
	}
	public AccountSuccessPage continueField() {
		continueField.click();
		return new AccountSuccessPage(driver);
	}
	public void selectNewsLatterField() {
		yesNewsLetterOption.click();
	}
	public String retrieveDuplicateErrorMessage() {
		String duplicateEmailWarningText = duplicateEmailAdressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePriavcyPolicyMessage() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	public String retrieveFirstNameErrorMessage() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText ;
	}
	public String retrieveLastNameErrorMessage() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveEmailErrorMessage() {
		String EmailWarningText = EmailWarning.getText();
		return EmailWarningText ;
	}
	public String retrieveTelephoneErrorMessage() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	public String retrievepasswordErrorMessage() {
		String passwordeWarningText = passwordWarning.getText();
		return passwordeWarningText;
	}
	public AccountSuccessPage registerWithMendatoryField(String firstNameText,String lastNameText,String EmailText,String TelephoneText,String PasswordText) {
		firstName.sendKeys(firstNameText);
		lastName.sendKeys(lastNameText);
		email.sendKeys(EmailText);
		telephone.sendKeys(TelephoneText);
		passwordField.sendKeys(PasswordText);
		passwordConfirmField.sendKeys(PasswordText);
		agreeField.click();
		continueField.click();
		return new AccountSuccessPage(driver);
	}
	public AccountSuccessPage registerWithAllField(String firstNameText,String lastNameText,String EmailText,String TelephoneText,String PasswordText) {
		firstName.sendKeys(firstNameText);
		lastName.sendKeys(lastNameText);
		email.sendKeys(EmailText);
		telephone.sendKeys(TelephoneText);
		passwordField.sendKeys(PasswordText);
		passwordConfirmField.sendKeys(PasswordText);
		yesNewsLetterOption.click();
		agreeField.click();
		continueField.click();
		return new AccountSuccessPage(driver);		
	}
	public boolean displayStatusOfWarningMessages(String expectedprivacyPolicyWarning, String expectedfirstNameWarning, String expectedlastNameWarning, 
			String expectedemailWarning, String expectedtelephoneWarning, String expectedpasswordWarning) {
		boolean privacyPolicyWarningStatus=privacyPolicyWarning.getText().contains(expectedprivacyPolicyWarning);
		boolean FirstNameWarningTextStatus=firstNameWarning.getText().equals(expectedfirstNameWarning);
		boolean LastNameWarningTextStatus=lastNameWarning.getText().equals(expectedlastNameWarning);
		boolean emailWarningTextStatus = EmailWarning.getText().equals(expectedemailWarning);
		boolean telephoneWarningStatus=telephoneWarning.getText().equals(expectedtelephoneWarning);
		boolean passwordeWarningStaus=passwordWarning.getText().equals(expectedpasswordWarning);
		return privacyPolicyWarningStatus && FirstNameWarningTextStatus && LastNameWarningTextStatus && emailWarningTextStatus && telephoneWarningStatus && passwordeWarningStaus;
	}
}
