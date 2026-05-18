package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage{
	
	public LogoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[@role='button']//span[@class='title'][normalize-space()='My account']")
    public WebElement account;

	@FindBy(xpath="//li[@class='nav-item dropdown dropdown-hoverable show']//li[1]//a[1]")
    public	WebElement logoutBtn;
	
	@FindBy(xpath="//h1[@class='page-title my-3']")
	public WebElement Logoutmsg;
	
	@FindBy(xpath="//a[text()='Login']")
     public WebElement login;

}
