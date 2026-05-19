package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.actions.ForgetpasswordPageAction;
import com.actions.LoginPageAction;
import com.driver.DriverClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forgetpassword {
	WebDriver driver = DriverClass.getDriver();
	ForgetpasswordPageAction fpa=new ForgetpasswordPageAction();
	LoginPageAction lpa=new LoginPageAction();
	private static final Logger log = LogManager.getLogger(Forgetpassword.class);
	
	@Given("the user is on the home page of the application")
	public void the_user_is_on_the_home_page_of_the_application() {
		 lpa.launchWebUrl();
	}

	@When("the user clicks login link")
	public void the_user_clicks_login_link() {
		 lpa.clickMyAccountLink();
	}

	@When("clicks on Forgotten Password")
	public void clicks_on_forgotten_password() {
		fpa.clickForgotPassword();
	}

	@When("the user enters the valid {string}")
	public void the_user_enters_the_valid(String email) {
		fpa.enterEmail(email);
		fpa.clickContinueButton();
	}

	@Then("the user should be able to receive a message stating reset link sent to email.")
	public void the_user_should_be_able_to_receive_a_message_stating_reset_link_sent_to_email() {
		String actual=fpa.Successmsg();
		String expected=fpa.expectedSuccessmsg();
	   try{
		   
		 Assert.assertEquals(actual,expected);
		 log.info("Reset link sent to email");
	    }
	   catch(AssertionError e){
		   log.error("Failed to sent email"+e.getMessage());
		   throw e;
	   }
		   
	   
	}

	@When("the user enters the invalid {string}")
	public void the_user_enters_the_invalid(String invalidemail){
		fpa.enterEmail(invalidemail);
		fpa.clickContinueButton();
	}

	@Then("the user should receive an error message")
	public void the_user_should_receive_an_error_message() {
		String actual=fpa.Warningmsg();
		String expected=fpa.expectedErrormsg();
		   try{
			   
			 Assert.assertEquals(actual,expected);
			 log.info("Failed to send reset link to invalid email");
		    }
		   catch(AssertionError e ){
			   log.error("Failed to display warning message"+e.getMessage());
			   throw e;
		   }
			   
	}



}
