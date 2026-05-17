package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//td[@class='text-left']//a[contains(text(),'HP LP3065')]")
	public WebElement productName;

	@FindBy(xpath = "//input[contains(@name,'quantity')]")
	public WebElement quantityCount;

	@FindBy(xpath = "//button[@type='submit' and contains(@class,'btn-primary')]")
	public WebElement updateButton;

	@FindBy(css = ".alert.alert-success.alert-dismissible")
	public WebElement quantityUpdatedMsg;

}
