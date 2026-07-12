package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftCertificatePage extends BasePage {

	public GiftCertificatePage() {
		super(driver);
	}
	@FindBy(xpath = "//input[@id='input-to-name']")
	public WebElement toNameInput;

	@FindBy(xpath = "//input[@id='input-to-email']")
	public WebElement toEmailInput;

	@FindBy(xpath = "//input[@value='7']")
	public WebElement giftCertificateThemeRadioButton;

	@FindBy(xpath = "//input[@name='agree']")
	public WebElement agreeCheckBox;

	@FindBy(xpath = "//input[@value='Continue']")
	public WebElement continueButton;
	
}
