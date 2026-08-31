package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReturnsPage extends BasePage{
	
	public ReturnsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[contains(text,Returns)]")
	public WebElement productReturnsHeading;
	
	@FindBy(xpath="(//tbody/child::tr/td/a)[3]")
	public WebElement productReturnFirstViewBtn;
	
	@FindBy(xpath="//li[text()='Return Information']")
	public WebElement productInfoText;
}
