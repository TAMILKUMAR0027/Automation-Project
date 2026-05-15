package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.LoginPageAction;
import com.actions.RegisterPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSD {
	private static final Logger log = LogManager.getLogger(LoginSD.class);
	RegisterPageAction rpa = new RegisterPageAction();
	LoginPageAction lpa=new LoginPageAction();
	@Given("The user is in HomePage of EcommerceLambdaTestWebsite")
	public void the_user_is_in_home_page_of_ecommerce_lambda_test_website() {
	    lpa.launchWebUrl();
	}

	@When("The user clicks on myAccount link in navBar")
	public void the_user_clicks_on_my_account_link_in_nav_bar() {
	    rpa.clickMyAccount();
	}

	@When("clicks on Register link in Account page")
	public void clicks_on_register_link_in_account_page() {
	    rpa.registerLinkClick();
	}

	@When("Enter your personal details")
	public void enter_your_personal_details(io.cucumber.datatable.DataTable dataTable) {
	   rpa.enterPersonalDetails(dataTable);
	}

	@When("check the privacy policy checbox")
	public void check_the_privacy_policy_checbox() {
	    rpa.clickPrivacyPolicy();
	}

	@When("clicks on continue Button")
	public void clicks_on_continue_button() {
	   rpa.continueButton();
	}

	@Then("the user should see a page with confirmation text")
	public void the_user_should_see_a_page_with_confirmation_text() {
	   String actual=rpa.registerSuccess();
	   String expected="Your Account Has Been Created!";
	   try
	   {
		   Assert.assertEquals(actual, expected);
		   log.info("Registeration Successful");
	   }
	   catch(AssertionError e)
	   {
		   log.error("Register unsuccessful,Error: "+e.getMessage());
		   throw e;
	   }
	}


}
