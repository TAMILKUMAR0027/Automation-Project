package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.AccountPageAction;
import com.actions.LaunchPageAction;
import com.actions.LoginPageAction;
import com.utils.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSD {

    AccountPageAction apa = new AccountPageAction();
    LoginPageAction lpa = new LoginPageAction();
    LaunchPageAction lp = new LaunchPageAction();

    private static final Logger log = LogManager.getLogger(AccountSD.class);

    @When("The user Enters valid email and valid password")
    public void the_user_enters_valid_email_and_valid_password() {

        String email = ConfigReader.getRegisterDataProperties().getProperty("vemail");
        String pass = ConfigReader.getRegisterDataProperties().getProperty("vpass");

        apa.setVemail(email);
        apa.setVpass(pass);
    }

    @When("The User clicks on edit account information link on Account page")
    public void the_user_clicks_on_edit_account_information_link_on_account_page() {

        apa.clickEditAccInfo();
    }

    @When("The user clear and enter new telephone number as {string} in input field")
    public void the_user_clear_and_enter_new_telephone_number_as_in_input_field(
            String telephone,
            DataTable dataTable) {

        apa.updateDetails(dataTable);
    }

    @When("The user clicks on continue Button in Edit Information Page")
    public void the_user_clicks_on_continue_button_in_edit_information_page() {

        apa.clickEContinueBtn();
    }

    @Then("The user should see a sucess Message Your account has updated successfully")
    public void the_user_should_see_a_sucess_message_your_account_has_updated_successfully() {

        String actual = apa.successMsgE();
        String exp = "Success: Your account has been successfully updated.";

        try {

            Assert.assertTrue(actual.contains(exp));
            log.info("Success message displayed successfully");

        } catch (AssertionError e) {

            log.error("Error : " + e.getMessage());
            throw e;
        }
    }
}