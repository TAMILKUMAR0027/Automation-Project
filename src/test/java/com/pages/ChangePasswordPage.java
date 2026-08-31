package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {
	
	public ChangePasswordPage(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy (css="#input-password")
	    public WebElement passwordField;
	    
	    @FindBy (css="#input-confirm")
	    public WebElement newPasswordField;
	    
	    @FindBy(xpath="//input[@type='submit']")
	    public WebElement passwordContinueBtn;
	    
	    @FindBy(xpath="//div[text()=' Success: Your password has been successfully updated.']")
	    public WebElement passwordChangeSuccessMsg;
	    
	    @FindBy(xpath="//div[text()='Password confirmation does not match password!']")
	    public WebElement emptyPasswordConfirmMsg;
	    
	    @FindBy(xpath="//div[text()='Password must be between 4 and 20 characters!']")
	    public WebElement emptyPasswordMsg;
	    
	    
	    

}
