package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.ForgetpasswordPageAction;
import com.actions.LoginPageAction;
import com.driver.DriverClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Forgetpassword {

    ForgetpasswordPageAction fpa = new ForgetpasswordPageAction();
    LoginPageAction lpa = new LoginPageAction();

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

    // ✅ VALID EMAIL SCENARIO
    @When("the user enters the valid email")
    public void the_user_enters_the_valid_email() {
        fpa.enterEmail("validEmail");
        fpa.clickContinueButton();
    }

    @Then("the user should be able to receive a message stating reset link sent to email")
    public void success_message_validation() {

        String actual = fpa.getSuccessMessage();
        String expected = fpa.expectedSuccessMessage();

        try {
            Assert.assertEquals(actual, expected);
            log.info("Reset link sent successfully");
        } catch (AssertionError e) {
            log.error("Success message mismatch: " + e.getMessage());
            throw e;
        }
    }

    // ❌ INVALID EMAIL SCENARIO
    @When("the user enters the invalid email")
    public void the_user_enters_the_invalid_email() {
        fpa.enterEmail("InvalidEmail");
        fpa.clickContinueButton();
    }

    @Then("the user should receive an error message")
    public void error_message_validation() {

        String actual = fpa.getWarningMessage();
        String expected = fpa.expectedErrorMessage();

        try {
            Assert.assertEquals(actual, expected);
            log.info("Error message displayed correctly");
        } catch (AssertionError e) {
            log.error("Warning message mismatch: " + e.getMessage());
            throw e;
        }
    }
}