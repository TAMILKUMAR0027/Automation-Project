package com.stepDefinitions;

import org.testng.Assert;

import com.actions.AffilateAction;
import com.actions.LoginPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AffilateaccountDefinition {
	LoginPageAction lpa = new LoginPageAction();
	AffilateAction aa = new AffilateAction();

	@Given("the user on home page")
	public void the_user_on_home_page() {
		// Write code here that turns the phrase above into concrete actions
		lpa.launchWebUrl();
	}

	@Given("click My account link")
	public void click_my_account_link() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickMyAccountLink();
	}

	@When("The user Enters valid email and valid password")
	public void the_user_enters_valid_email_and_valid_password(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		lpa.loginValid(dataTable);
	}

	@When("click Affilate account register link")
	public void click_affilate_account_register_link() {
		// Write code here that turns the phrase above into concrete actions
		aa.clickAffilateAccountRegister();
	}

	@When("enter the valid details for registering via Test data")
	public void enter_the_valid_details_for_registering_via_test_data() {
		aa.enterAffilateDetails();
	}

	@When("click continue Button")
	public void click_privacy_and_policy_check_and_click_continue_button() {
		// Write code here that turns the phrase above into concrete actions

		aa.clickContinue();
	}

	@Then("user can the message of successfully your account had been updated")
	public void user_can_the_message_of_successfully_your_account_had_been_updated() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("Success: Your account has been successfully updated.",aa.getAccountCreationMessage());
	}

}
