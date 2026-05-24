package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;

public class LoginPageAction {
	
	LoginPage lp = new LoginPage(DriverClass.getDriver());
	BaseAction ba = new BaseAction();
	WebDriverWait wait=new WebDriverWait(DriverClass.getDriver(),Duration.ofSeconds(15));

	public void launchWebUrl() {
		DriverClass.getDriver().get("https://ecommerce-playground.lambdatest.io/");
	}

	public void clickMyAccountLink()
	{
		try
		{
			ba.isDisplayed(lp.myAccLink);
			ba.click(lp.myAccLink);
		}
		catch(Exception e)
		{
			ba.waitForVisibility(lp.myAccLink);
			ba.click(lp.myAccLink);

		}
	}
	
	public void enterEmailAndPass(String username,String password)
	{	
		ba.waitForVisibility(lp.LoginEmail);
		ba.sendKeys(lp.LoginEmail, username);
		ba.sendKeys(lp.LoginPassword, password);
	}
	
	public void enterPass(String password)
	{
		ba.waitForVisibility(lp.LoginPassword);
		ba.sendKeys(lp.LoginPassword, password);
	}
	
	
	public void clickLoginButton()
	{
		ba.click(lp.LoginButton);
	}
	
	public String LoginSuccessMsg()
	{
		ba.waitForVisibility(lp.LoginSuccessMessage);
		return ba.getText(lp.LoginSuccessMessage);
	}
	
	public String LoginFailedMsg()
	{
		ba.waitForVisibility(lp.LoginFailedMessage);
		return ba.getText(lp.LoginFailedMessage);
	}
	
	public String LoginFailedMsgone()
	{
		ba.waitForVisibility(lp.LoginFailedMessageone);
		return ba.getText(lp.LoginFailedMessageone);
	}
	
	public void loginValid(DataTable db)
	{
		ba.waitForVisibility(lp.LoginEmail);
		List<Map<String, String>> data = db.asMaps(String.class, String.class);
		ba.sendKeys(lp.LoginEmail, data.get(0).get("email"));
		ba.sendKeys(lp.LoginPassword, data.get(0).get("password"));
	}
	

}
