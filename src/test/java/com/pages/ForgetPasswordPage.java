package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordPage extends BasePage {
	public ForgetPasswordPage(WebDriver driver) {
          super(driver);
    }

	@FindBy(xpath="//label[@for='input-password']/following-sibling::a")
	public WebElement forgetpassword;
	
	@FindBy(xpath="//div[@class='col-sm-10']/child::input")
	public WebElement email;
	
	@FindBy(xpath="//div[@class='float-right']/child::button")
	public WebElement button;
	
	@FindBy(xpath="//div[@class='row']/preceding-sibling::div")
	public WebElement message;

	@FindBy(xpath="//div[@id='account-forgotten']/child::div[1]")
	public WebElement warningmsg;
}
