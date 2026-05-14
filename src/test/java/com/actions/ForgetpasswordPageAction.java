package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ForgetPasswordPage;
import com.pages.LoginPage;

public class ForgetpasswordPageAction {
	WebDriver driver = DriverClass.getDriver();
	LoginPage lp = new LoginPage(driver);
	ForgetPasswordPage fp=new ForgetPasswordPage(driver);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
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

	    public void enterEmail(String email) {
           wait.until(ExpectedConditions.visibilityOf(fp.email)).sendKeys(email);
	    }
	  

	    public void clickContinueButton() {
            fp.button.click();
	    }
	    public String Successmsg() {
	    	 return wait.until(ExpectedConditions.visibilityOf(fp.message)).getText();
	    }
	    public String Warningmsg() {
	    	return wait.until(ExpectedConditions.visibilityOf(fp.warningmsg))  .getText();
	    }
		
	}

