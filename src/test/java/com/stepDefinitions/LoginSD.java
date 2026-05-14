package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.LoginPageAction;
import com.sun.tools.javac.util.Log;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSD {
    private static final Logger log = LogManager.getLogger(LoginSD.class);
	LoginPageAction lpa=new LoginPageAction();
	@Given("The user is in HomePage of Ecommerce LambadaTest Application")
	public void the_user_is_in_home_page_of_ecommerce_lambada_test_application() {
	    lpa.launchWebUrl();
	}

	@When("The user clicks on My account link")
	public void the_user_clicks_on_my_account_link() {
	    lpa.clickMyAccountLink();
	}

	@When("The user Enters valid email as {string} and valid password as {string}")
	public void the_user_enters_valid_email_as_and_valid_password_as(String e, String p) {
	    lpa.enterEmailAndPass(e, p);
	}

	@When("Clicks on Login Button")
	public void clicks_on_login_button() {
	    lpa.clickLoginButton();
	}

	@Then("The user should be successfully Logged in.")
	public void the_user_should_be_successfully_logged_in() {
	    String actual=lpa.LoginSuccessMsg();
	    String expected="My Account";
	    try
	    {
	    	Assert.assertEquals(actual, expected);
	    	log.info("Login Successful");
	    }
	    catch(AssertionError e)
	    {
	    	log.error("Login is failing Due to Wrong credentials,Error: "+e.getMessage());
	    	throw e;
	    }
	}

	@When("The user Enters invalid email as {string} and invalid password as {string}")
	public void the_user_enters_invalid_email_as_and_invalid_password_as(String ie, String ip) {
	   lpa.enterEmailAndPass(ie, ip);
	}

	@Then("The user should see an Error message Warning: No match for E-Mail Address and\\/or Password.")
	public void the_user_should_see_an_error_message_warning_no_match_for_e_mail_address_and_or_password() {
		 String actual=lpa.LoginFailedMsg();
		    String expected="Warning: No match for E-Mail Address and/or Password.";
		    try
		    {
		    	Assert.assertEquals(actual, expected);
		    	log.info("Login Unsuccessful as expected");
		    }
		    catch(AssertionError e)
		    {
		    	log.error("invalidLogin is failed Due to Wrong credentials,Error: "+e.getMessage());
		    	throw e;
		    }
	}



}
