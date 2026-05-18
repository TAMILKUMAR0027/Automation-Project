package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LoginPage;
import com.pages.LogoutPage;

public class LogoutPageAction {
	WebDriver driver=DriverClass.getDriver();
	LogoutPage lout=new LogoutPage(driver);
    LoginPage lp = new LoginPage(driver);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));

	public void launchWebUrl() {
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	public void clickMyAccountLink()
	{
		wait.until(ExpectedConditions.visibilityOf(lp.myAccLink)).click();
	}
	
	public void enterEmailAndPass(String username,String password)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.LoginEmail)).sendKeys(username);
		lp.LoginPassword.sendKeys(password);
	}
	
	public void enterPass(String password)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.LoginPassword)).sendKeys(password);
	}
	
	
	public void clickLoginButton()
	{
		lp.LoginButton.click();
	}
	
	public String LoginSuccessMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginSuccessMessage)).getText();
	}
	
	public void Clickaccount() {
		lout.account.click();
	}
	public void clickLogout() {
		wait.until(ExpectedConditions.visibilityOf(lout.logoutBtn)).click();
	}
	public String Message() {
		return lout.Logoutmsg.getText();
	}
	
	public String LoginFailedMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginFailedMessage)).getText();
	}
	public boolean LoginDisplayed() {
	    return lout.login.isDisplayed();
	}
	

}
