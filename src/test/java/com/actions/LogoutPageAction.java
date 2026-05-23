package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LoginPage;
import com.pages.LogoutPage;

public class LogoutPageAction extends BaseAction{
	WebDriver driver=DriverClass.getDriver();
	LogoutPage lout=new LogoutPage(driver);
    LoginPage lp = new LoginPage(driver);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
	Actions act = new Actions(driver);

	public void launchWebUrl() {
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}

	public void clickMyAccountLink()
	{
		waitForVisibility(lp.myAccLink);
		click(lp.myAccLink);
	}
	
	public void enterEmailAndPass(String username,String password)
	{
		sendKeys(lp.LoginEmail,username);
		sendKeys(lp.LoginPassword,password);
	}
	
	public void clickLoginButton()
	{
		click(lp.LoginButton);
	}
	
	public String LoginSuccessMsg()
	{
		return getText(lp.LoginSuccessMessage);
	}
	
	public void Clickaccount() {
		
		act.moveToElement(lp.myAccLink).perform();

	}
	public void clickLogout() {

	    waitForVisibility(lout.logoutBtn);
	    click(lout.logoutBtn);
	}
	public String Message() {
		return getText(lout.Logoutmsg);
	}
	
	
	
}
