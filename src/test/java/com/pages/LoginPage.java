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

	@FindBy(xpath = "//a[@role='button']//span[@class='title'][normalize-space()='My account']")

	public WebElement myAccLink;

	@FindBy(xpath = "//input[@id='input-email']")
	public WebElement LoginEmail;

	@FindBy(xpath = "//input[@id='input-password']")
	public WebElement LoginPassword;

	@FindBy(xpath = "//input[@value='Login']")
	public WebElement LoginButton;

	@FindBy(xpath = "//h2[text()='My Account']")
	public WebElement LoginSuccessMessage;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	public WebElement LoginFailedMessage;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	public WebElement LoginFailedMessageone;

}
