package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AffilateAccountPage extends BasePage{

	public AffilateAccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}
	@FindBy(xpath = "//input[@id='input-company']")
	public WebElement company;
	@FindBy(xpath = "//input[@id='input-website']")
	public WebElement website;
	@FindBy(xpath = "//input[@id='input-tax']")
	public WebElement taxID;
	@FindBy(xpath = "//input[@id='input-cheque']")
	public WebElement chequeName;
	@FindBy(xpath = "//input[@name='agree']")
	public WebElement agreeCheckBox;
	@FindBy(xpath = "//input[@value='Continue']")
	public WebElement continueButton;
}
