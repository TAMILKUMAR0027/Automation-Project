package com.actions;

import java.time.Duration;
import java.util.Properties;

import com.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ForgetPasswordPage;
import com.pages.LoginPage;

public class ForgetpasswordPageAction extends BaseAction{
	WebDriver driver = DriverClass.getDriver();
	LoginPage lp = new LoginPage(driver);
	ForgetPasswordPage fp=new ForgetPasswordPage(driver);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	Properties prop = ConfigReader.getProperties();

	public void ForgetPasswordPageAction(WebDriver driver) {
		this.driver=driver;
	}
	public void launchWebUrl() {
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}


	public void clickMyAccountLink(){
		wait.until(ExpectedConditions.visibilityOf(lp.myAccLink)).click();
	}
	 public void clickForgotPassword() {
         click(fp.forgetpassword);
	}

	 // Reusable method for valid/invalid emails
	    public void enterEmail(String emailKey) {

	        wait.until(ExpectedConditions.visibilityOf(fp.email)).clear();

	        fp.email.sendKeys(prop.getProperty(emailKey));
	    }
	   
	    public void clickContinueButton() {
            click(fp.button);
	    }
	    public String Successmsg() {
	    	 return getText(fp.message);
	    }
	    public String Warningmsg() {
	    	return getText(fp.warningmsg);
	    }
	    public String expectedSuccessmsg() {

	        return prop.getProperty("message");
	    }

	    public String expectedErrormsg() {

	        return prop.getProperty("error_message");
	    }
	}
		
	

