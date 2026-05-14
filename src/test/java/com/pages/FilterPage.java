package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPage extends BasePage{
	
	public FilterPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//label[@for='mz-fm-0-8']")
	public WebElement Applebtn;
	@FindBy(xpath = "//div[@class='carousel-item active']//img[@title='iPod Touch']")
	public WebElement iPodTouchProduct;

}
