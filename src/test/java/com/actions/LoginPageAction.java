package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LoginPage;

public class LoginPageAction {
	WebDriver driver = DriverClass.getDriver();
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
	public void clickLoginButton()
	{
		lp.LoginButton.click();
	}
	
	public String LoginSuccessMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginSuccessMessage)).getText();
	}
	
	public String LoginFailedMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(lp.LoginFailedMessage)).getText();
	}

}
