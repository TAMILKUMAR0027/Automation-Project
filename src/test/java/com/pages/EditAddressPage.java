package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditAddressPage extends BasePage{
	
	public EditAddressPage(WebDriver driver) {
		super(driver);
	}
	
    @FindBy(css = "#input-firstname")
    public WebElement firstName;
    
    @FindBy(css="#input-lastname")
    public WebElement lastName;
    
    @FindBy(css="#input-address-1")
    public WebElement AddressOne;
    
    @FindBy(css="#input-city")
    public WebElement city;
    
    @FindBy(xpath="//input[@type='submit']")
    public WebElement EditAddressContinueBtn;
    
    @FindBy(xpath="//div[contains(@class,'alert-success')]")
    public WebElement EditAddressSuccessMsg;
    
    

}
