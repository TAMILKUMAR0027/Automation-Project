package com.stepDefinitions;

import org.testng.Assert;

import com.actions.AddOnPageAction;
import com.actions.LaunchPageAction;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactFormDefinition {
	LaunchPages lp = new LaunchPages(DriverClass.getDriver());
	LaunchPageAction lpa = new LaunchPageAction();
	AddOnPageAction aopa = new AddOnPageAction();

	@Given("The user is on the home page")
	public void the_user_is_on_the_home_page() {
		// Write code here that turns the phrase above into concrete actions
		String url = ConfigReader.getProperties().getProperty("url");
		lp.launchApplication(url);
	}

	@Given("The user clicks the AddsOn link")
	public void the_user_clicks_the_adds_on_link() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickAddonButton();
	}

	@Given("The user clicks the Widgets button")
	public void the_user_clicks_the_widgets_button() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickWidgets();
	}

	@When("The user enters {string} in the Name field")
	public void the_user_enters_in_the_name_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		aopa.sendName(string);
	}

	@When("The user enters {string} in the Email field")
	public void the_user_enters_in_the_email_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		aopa.sendEmail(string);
	}

	@When("The user enters {string} in the Subject field")
	public void the_user_enters_in_the_subject_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		aopa.sendSubject(string);
	}

	@When("The user enters {string} in the Message field")
	public void the_user_enters_in_the_message_field(String string) {
		// Write code here that turns the phrase above into concrete actions
		aopa.sendMessage(string);
	}

	@When("The user clicks the Submit button")
	public void the_user_clicks_the_submit_button() {
		// Write code here that turns the phrase above into concrete actions
		aopa.submitForm();
	}

	@Then("The success message should be displayed")
	public void the_success_message_should_be_displayed() {
		String actualMessage = aopa.getConfirmationMessage();

		Assert.assertTrue(actualMessage.contains("Your enquiry has been successfully sent to the store owner!"));
	}

}
