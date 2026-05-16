package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordPage extends BasePage {
	public ForgetPasswordPage(WebDriver driver) {
          super(driver);
    }

	@FindBy(xpath="//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
	public WebElement forgetpassword;
	
	@FindBy(xpath="//input[@id='input-email']")
	public WebElement email;
	
	@FindBy(xpath="//button[normalize-space()='Continue']")
	public WebElement button;
	
	@FindBy(xpath="//div[contains(@class,'alert-success')]")
	public WebElement message;

	  @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	public WebElement warningmsg;
}
