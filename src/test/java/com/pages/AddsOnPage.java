package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddsOnPage extends BasePage {

	
	public AddsOnPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}
	@FindBy(xpath = "//input[@placeholder='Your name']")
	public WebElement formName;
	@FindBy(xpath = "//input[@placeholder='Your email']")
	public WebElement formEmail;
	@FindBy(xpath = "//input[@placeholder='Subject']")
	public WebElement formSubject;
	@FindBy(xpath = "//textarea[@placeholder='Message']")
	public WebElement formMessage;
	@FindBy(xpath = "//button[normalize-space()='Send message']")
	public WebElement clickSubmit;
	@FindBy(xpath = "//div[@class='alert alert-success alert-notification w-50 alert-dismissible']")
	public WebElement confirmMessage;
	@FindBy(xpath = "//div[@class='error text-danger']")
	public WebElement emailErrorMessage;
}
