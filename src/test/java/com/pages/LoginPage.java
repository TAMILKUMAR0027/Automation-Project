package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//a[@role='button']//span[@class='title'][normalize-space()='My account']")
	WebElement myAccLink;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement LoginEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement LoginPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement LoginButton;
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement LoginSuccessMessage;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement LoginFailedMessage;
	
	public void clickMyAccountLink()
	{
		wait.until(ExpectedConditions.visibilityOf(myAccLink)).click();
	}
	
	public void enterEmailAndPass(String username,String password)
	{
		wait.until(ExpectedConditions.visibilityOf(LoginEmail)).sendKeys(username);
		LoginPassword.sendKeys(password);
	}
	public void clickLoginButton()
	{
		LoginButton.click();
	}
	
	public String LoginSuccessMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(LoginSuccessMessage)).getText();
	}
	
	public String LoginFailedMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(LoginFailedMessage)).getText();
	}
}
