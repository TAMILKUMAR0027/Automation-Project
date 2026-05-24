package com.actions;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ForgetPasswordPage;
import com.pages.LoginPage;
import com.utils.TestdataReader;

public class ForgetpasswordPageAction {
	WebDriver driver = DriverClass.getDriver();
	LoginPage lp = new LoginPage(driver);
	ForgetPasswordPage fp=new ForgetPasswordPage(driver);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	Properties prop = TestdataReader.getProperties();

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
          fp.forgetpassword.click();
	}

	 // Reusable method for valid/invalid emails
	    public void enterEmail(String emailKey) {

	        wait.until(ExpectedConditions.visibilityOf(fp.email)).clear();

	        fp.email.sendKeys(prop.getProperty(emailKey));
	    }
	   
	    public void clickContinueButton() {
            fp.button.click();
	    }
	    public String Successmsg() {
	    	 return wait.until(ExpectedConditions.visibilityOf(fp.message)).getText();
	    }
	    public String Warningmsg() {
	    	return wait.until(ExpectedConditions.visibilityOf(fp.warningmsg)).getText();
	    }
	    public String expectedSuccessmsg() {

	        return prop.getProperty("message");
	    }

	    public String expectedErrormsg() {

	        return prop.getProperty("error_message");
	    }
	}
		
	

