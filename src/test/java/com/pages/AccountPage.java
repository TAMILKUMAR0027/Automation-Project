package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	public AccountPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(linkText="Register")
	public WebElement registerLink;

	@FindBy(xpath="//a[normalize-space()='Edit your account information']")
	public WebElement editAccInfo;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	public WebElement telephoneEdit;
	
	@FindBy(xpath="//input[@value='Continue']")
	public WebElement eContinueButton;
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	public WebElement editSuccess;
	
	@FindBy(xpath = "//i[@class='fas fa-2x mb-1 fa-bullhorn']")
	public WebElement affilateAccountLink;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	public WebElement accountCreatedMessage;
	
	@FindBy (xpath="//div[@class='form-group row required']/child::label/following-sibling::div/child::input[@id='input-email']")
	public WebElement fnameEI;
	
	@FindBy (xpath="//div[@class='form-group row required']/child::label/following-sibling::div/child::input[@id='input-lastname']")
	public WebElement lnameEI;
	
	@FindBy(xpath="//div[@class='form-group row required']/child::label/following-sibling::div/child::input[@id='input-email']")
	public WebElement emailEI;
	
	@FindBy (xpath="//div[@class='col-sm-10']/child::input/following-sibling::div")
	public WebElement errorMsgEI;
	
	
	

}
