package com.stepDefinitions;

import java.util.Map;

import com.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.LoginPageAction;
import com.actions.RegisterPageAction;
import com.utils.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSD {
	private static final Logger log = LogManager.getLogger(RegisterSD.class);
	RegisterPageAction rpa = new RegisterPageAction();
	LoginPageAction lpa = new LoginPageAction();
	private static Map<String, String> registerData = ExcelUtils.getRegisterData();

	@Given("The user is in HomePage of EcommerceLambdaTestWebsite")
	public void the_user_is_in_home_page_of_ecommerce_lambda_test_website() {
		lpa.launchWebUrl();
	}

	@When("The user clicks on myAccount link in navBar")
	public void the_user_clicks_on_my_account_link_in_nav_bar() {
		lpa.clickMyAccountLink();
	}

	@When("clicks on Register link in Account page")
	public void clicks_on_register_link_in_account_page() {
		rpa.registerLinkClick();
	}

	@When("Enter your personal details")
	public void enter_your_personal_details() {
		rpa.setFname(registerData.get("fname"));
		rpa.setLname(registerData.get("lname"));
		rpa.setEmail(registerData.get("email"));
		rpa.setTelephone(registerData.get("telephone"));
		rpa.setPassword(registerData.get("password"));
		rpa.setConfirmPassword(registerData.get("confirmpassword"));
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
		rpa.registerSuccess();
	}

	@Then("The user should see a Warning message  Warning: You must agree to the Privacy Policy!")
	public void the_user_should_see_a_warning_message_warning_you_must_agree_to_the_privacy_policy() {
		rpa.uncheckPPMsg();
	}

	@When("Enter your personal details except firstname")
	public void enter_your_personal_details_except_firstname() {
	    String lname= ConfigReader.getRegisterDataProperties().getProperty("lname");
	    String email=ConfigReader.getRegisterDataProperties().getProperty("email");
	    String telephone=ConfigReader.getRegisterDataProperties().getProperty("telephone");
	    String pass=ConfigReader.getRegisterDataProperties().getProperty("pass");
	    String cpass=ConfigReader.getRegisterDataProperties().getProperty("cpass");
	    rpa.setLname(lname);
	    rpa.setEmail(email);
	    rpa.setTelephone(telephone);
	    rpa.setPassword(pass);
	    rpa.setConfirmPassword(cpass);
	    rpa.clickPrivacyPolicy();
	    rpa.continueButton();
	}

	@Then("the user should see a warning message : First Name must be between one and thirtyTwo characters!")
	public void the_user_should_see_a_warning_message_first_name_must_be_between_one_and_thirty_two_characters() {
	   String actual=rpa.fieldEmptyWmsg();
	   String exp="First Name must be between 1 and 32 characters!";
	   
	   try {
		   Assert.assertTrue(actual.contains(exp));
		   log.info("Error message Thrown Successfully");
	   }catch(AssertionError e)
	   {
		   log.error("Error: "+e.getMessage());
		   throw e;
	   }
		
		
	}



}
