package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	public WebElement fname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	public WebElement lname;
	
	@FindBy(xpath="//input[@id='input-email']")
	public WebElement email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	public WebElement telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	public WebElement pass;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	public WebElement cpass;
	
	@FindBy(xpath="//label[@for='input-agree']")
	public WebElement privacyPolicyCheckBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	public WebElement continueButton;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	public WebElement rSuccess;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	public WebElement errMsgPP;
	
	@FindBy(css=".text-danger")
	public WebElement emptyFieldMsg;
	

}
