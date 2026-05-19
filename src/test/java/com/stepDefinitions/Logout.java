package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.actions.LoginPageAction;
import com.actions.LogoutPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout {
	WebDriver driver;
	LogoutPageAction lga=new LogoutPageAction();
	LoginPageAction lpa=new LoginPageAction();
	private static final Logger log = LogManager.getLogger(Logout.class);
	
	@Given("the user is logged into the application with valid {string} and {string}")
	public void the_user_is_logged_into_the_application_with_valid_and(String email, String password) {
		lpa.launchWebUrl();
		lpa.clickMyAccountLink();
		lpa.enterEmailAndPass(email, password);
		lpa.clickLoginButton();
		

	}

	@Given("the user is on the My Account page")
	public void the_user_is_on_the_my_account_page() {
		String actual = lpa.LoginSuccessMsg();
		String expected = "My Account";
		try {
			Assert.assertEquals(actual, expected);
			log.info("Login Successful");
		} catch (AssertionError e) {
			log.error("Login is failing Due to Wrong credentials,Error: " + e.getMessage());
			throw e;
		}
	}

	@When("the user clicks on Logout link")
	public void the_user_clicks_on_logout_link() {
		lga.Clickaccount();
		lga.clickLogout();
	    
	}

	@Then("the user should receive an intimation {string} regarding logout")
	public void the_user_should_receive_an_intimation_regarding_logout(String message) {
		 String actual=lga.Message();
		try {
			Assert.assertEquals(actual, message);
			log.info("Logut successful");
		}
		catch(AssertionError e) {
			log.error("Logout failed"+e.getMessage());
		}
	}

	@Given("the user is not logged into the application with invalid {string} and {string}")
	public void the_user_is_not_logged_into_the_application_with_invalid_and(String iemail, String ipassword) {
		lpa.launchWebUrl();
		lpa.clickMyAccountLink();
		lpa.enterEmailAndPass(iemail, ipassword);
		lpa.clickLoginButton();
	}


	@When("the user navigates to the My Account page")
	public void the_user_navigates_to_the_my_account_page() {
		lga.Clickaccount();
	}

	@Then("the logout link should not be displayed and the login link should be displayed")
	public void the_logout_link_should_not_be_displayed_and_the_login_link_should_be_displayed() {
	  
		boolean status=lga.LoginDisplayed();
		try {
			Assert.assertTrue(status);
			log.info("Invalid logout successful");
		}
		catch(AssertionError e) {
			log.error("Invalid logout failed"+e.getMessage());
		}
	}



}
