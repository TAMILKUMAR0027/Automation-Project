package com.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.LoginPageAction;
import com.actions.LogoutPageAction;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout {

    LoginPageAction lpa = new LoginPageAction();
    LogoutPageAction lga = new LogoutPageAction();

    private static final Logger log = LogManager.getLogger(Logout.class);

    @Given("the user is logged into the application with valid email and password")
    public void the_user_is_logged_into_the_application_with_valid_email_and_password(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String email = data.get(0).get("email");
        String password = data.get(0).get("password");

        try {

            log.info("Entering valid login credentials");

            lpa.clickMyAccountLink();
            lpa.enterEmailAndPass(email, password);
            lpa.clickLoginButton();

            log.info("Login button clicked successfully");

        } catch (Exception e) {

            log.error("Unable to login into application: " + e.getMessage());
            throw e;
        }
    }

    @Given("the user is on the My Account page")
    public void the_user_is_on_the_my_account_page() {

        String actual = lpa.LoginSuccessMsg();
        String expected = "My Account";

        try {

            Assert.assertEquals(actual, expected);

            log.info("User successfully navigated to My Account page");

        } catch (AssertionError e) {

            log.error("My Account page validation failed: " + e.getMessage());
            throw e;
        }
    }

    @When("the user clicks on Logout link")
    public void the_user_clicks_on_logout_link() {

        try {

            // Before
//            lga.Clickaccount();
//            lga.clickLogout();

            // After - for @E2E

            lga.clickLogout();

            log.info("Logout link clicked successfully");

        } catch (Exception e) {

            log.error("Unable to click logout link: " + e.getMessage());
            throw e;
        }
    }

    @Then("the user should receive an intimation message regarding logout")
    public void the_user_should_receive_an_intimation_message_regarding_logout() {

        String actual = lga.Message();
        String expected = "Account Logout";

        try {

            Assert.assertEquals(actual, expected);

            log.info("Logout validation successful");

        } catch (AssertionError e) {

            log.error("Logout validation failed: " + e.getMessage());
            throw e;
        }
    }
}