package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.AccountPageAction;
import com.actions.LaunchPageAction;
import com.actions.LoginPageAction;
import com.utils.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSD {

    AccountPageAction apa = new AccountPageAction();
    LoginPageAction lpa = new LoginPageAction();
    LaunchPageAction lp = new LaunchPageAction();

    private static final Logger log = LogManager.getLogger(AccountSD.class);
    
    @Given("The user is in HomePage of Ecommerce Lambda TestWebsite")
    public void the_user_is_in_home_page_of_ecommerce_lambda_test_website() {
    	lpa.launchWebUrl();
    }
   
    // =========================
    // LOGIN STEP
    // =========================
    @When("The user Enters valid email and valid passwords")
    public void the_user_enters_valid_email_and_valid_passwords() {

        String email =
                ConfigReader.getRegisterDataProperties()
                .getProperty("vemail");

        String pass =
                ConfigReader.getRegisterDataProperties()
                .getProperty("vpass");

        apa.setVemail(email);
        apa.setVpass(pass);

        log.info("Entered valid login credentials");
    }

    // =========================
    // EDIT ACCOUNT LINK
    // =========================
    @When("The User clicks on edit account information link on Account page")
    public void the_user_clicks_on_edit_account_information_link_on_account_page() {

        apa.clickEditAccInfo();

        log.info("Clicked edit account link");
    }

    // =========================
    // UPDATE TELEPHONE (DATATABLE ONLY)
    // =========================
    @When("The user clear and enter new telephone number in input field")
    public void the_user_clear_and_enter_new_telephone_number_in_input_field(DataTable dataTable) {

        apa.updateDetails(dataTable);

        log.info("Updated telephone number");
    }

    // =========================
    // CONTINUE BUTTON
    // =========================
    @When("The user clicks on continue Button in Edit Information Page")
    public void the_user_clicks_on_continue_button_in_edit_information_page() {

        apa.clickEContinueBtn();

        log.info("Clicked continue button");
    }

    // =========================
    // ASSERTION
    // =========================
    @Then("The user should see a sucess Message Your account has updated successfully")
    public void the_user_should_see_a_success_message_your_account_has_updated_successfully() {

        String actual = apa.successMsgE();
        String expected =
                "Success: Your account has been successfully updated.";

        Assert.assertTrue(actual.contains(expected));

        log.info("Account update success message verified");
    }
}