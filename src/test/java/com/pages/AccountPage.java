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
	@FindBy(xpath = "//i[@class='fas fa-2x mb-1 fa-bullhorn']")
	public WebElement affilateAccountLink;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	public WebElement accountCreatedMessage;

}
