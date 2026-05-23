package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage{
	
	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
    public WebElement account;

	@FindBy(linkText="Logout")
	public WebElement logoutBtn;
	
	@FindBy(xpath="//h1[@class='page-title my-3']")
	public WebElement Logoutmsg;
	
	
}
