package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LoginPage;

public class LoginPageAction {
	
	WebDriver driver=DriverClass.getDriver();
	LoginPage lp = new LoginPage(driver);
	BaseAction ba = new BaseAction();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));

	public void launchWebUrl() {
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	public void clickMyAccountLink()
	{
		try
		{
			lp.myAccLink.isDisplayed();
			ba.click(lp.myAccLink);
		}
		catch(Exception e)
		{
			wait.until(ExpectedConditions.visibilityOf(lp.myAccLink));
			ba.click(lp.myAccLink);

		}
	}
	
	public void enterEmailAndPass(String username,String password)
	{	
		wait.until(ExpectedConditions.visibilityOf(lp.LoginEmail));
		ba.sendKeys(lp.LoginEmail, username);
		ba.sendKeys(lp.LoginPassword, password);
	}
	
	public void enterPass(String password)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.LoginPassword));
		ba.sendKeys(lp.LoginPassword, password);
	}
	
	
	public void clickLoginButton()
	{
		ba.click(lp.LoginButton);
	}
	
	public String LoginSuccessMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginSuccessMessage)).getText();
	}
	
	public String LoginFailedMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginFailedMessage)).getText();
	}
	
	public String LoginFailedMsgone()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginFailedMessageone)).getText();
	}
	

}
