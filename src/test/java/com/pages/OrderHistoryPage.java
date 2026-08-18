package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHistoryPage extends BasePage {

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[1]")
	public WebElement orderIdHistory;
	
	@FindBy(xpath="//table[@class='table table-bordered table-hover']/tbody/tr[1]/td[7]/child::a")
	public WebElement viewBtn;
	
	@FindBy(xpath="(//td)[6]")
	public WebElement orderIdInfo;
	
	@FindBy(xpath="//a[@class='btn btn-danger']")
	public WebElement returnBtn;
	
	@FindBy(xpath="//label[text()=' Order Error']/child::input")
	public WebElement returnReason;
	
	@FindBy(xpath="//input[@type='submit']")
	public WebElement returnDeatilsSubmitBtn;
	
	@FindBy(xpath="//h1[text()=' Product Returns']")
	public WebElement returnSuccessMsg;


}
