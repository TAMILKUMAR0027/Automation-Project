package com.stepDefinitions;

import org.testng.Assert;

import com.actions.AffilateAction;
import com.actions.LoginPageAction;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AffilateaccountDefinition {

    LoginPageAction lpa = new LoginPageAction();
    AffilateAction aa = new AffilateAction();

    @Given("the user on home page")
    public void the_user_on_home_page() {
        lpa.launchWebUrl();
    }

    @Given("click My account link")
    public void click_my_account_link() {
        lpa.clickMyAccountLink();
    }

    @When("The affiliate user enters valid email and valid password")
    public void the_affiliate_user_enters_valid_email_and_valid_password(DataTable dataTable) {
        lpa.loginValid(dataTable);
    }

    @When("click Affilate account register link")
    public void click_affilate_account_register_link() {
        aa.clickAffilateAccountRegister();
    }

    @When("enter the valid details for registering via Test data")
    public void enter_the_valid_details_for_registering_via_test_data() {
        aa.enterAffilateDetails();
    }

    @When("click continue Button")
    public void click_privacy_and_policy_check_and_click_continue_button() {
        aa.clickContinue();
    }

    @Then("user can the message of successfully your account had been updated")
    public void user_can_the_message_of_successfully_your_account_had_been_updated() {

        Assert.assertEquals(
                aa.getAccountCreationMessage(),
                "Success: Your account has been successfully updated."
        );
    }
}