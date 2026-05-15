package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

	public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = "//ul[@class='list-unstyled m-0']//a[contains(text(),'Apple')]")
	public WebElement BrandName;
	@FindBy(xpath = "//span[@class='badge badge-success']")
	
	public WebElement availability;
}
