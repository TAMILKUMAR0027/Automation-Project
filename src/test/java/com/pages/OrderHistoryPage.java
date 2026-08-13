package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHistoryPage extends BasePage {

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="(//tbody/tr/td)[5]")
	public WebElement orderIdHistory;
	
	@FindBy(xpath="(//tbody/tr/td)[11]/child::a")
	public WebElement viewBtn;
	
	@FindBy(xpath="(//td)[6]")
	public WebElement orderIdInfo;

}
