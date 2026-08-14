package com.stepDefinitions;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.AccountPageAction;
import com.actions.AddressBookAction;
import com.actions.EditAddressPageAction;
import com.actions.GiftCertificateAction;
import com.actions.LaunchPageAction;
import com.actions.LoginPageAction;
import com.utils.ConfigReader;
import com.utils.ExcelUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountSD {

    AccountPageAction apa = new AccountPageAction();
    LoginPageAction lpa = new LoginPageAction();
    LaunchPageAction lp = new LaunchPageAction();
    AddressBookAction adpa = new AddressBookAction();
    EditAddressPageAction eapa = new EditAddressPageAction();
    GiftCertificateAction gca = new GiftCertificateAction();

    private static final Logger log = LogManager.getLogger(AccountSD.class);

    private static Map<String, String> giftData =
            ExcelUtils.getGiftData();

    // =========================================================
    // HOME PAGE
    // =========================================================

    @Given("The user is in HomePage of Ecommerce Lambda TestWebsite")
    public void the_user_is_in_home_page_of_ecommerce_lambda_test_website() {
        lpa.launchWebUrl();
    }

    // =========================================================
    // LOGIN
    // =========================================================

    @When("The user Enters valid email and valid passwords")
    public void the_user_enters_valid_email_and_valid_passwords() {

        String email = ConfigReader
                .getRegisterDataProperties()
                .getProperty("vemail");

        String pass = ConfigReader
                .getRegisterDataProperties()
                .getProperty("vpass");

        apa.setVemail(email);
        apa.setVpass(pass);

        log.info("Entered valid login credentials");
    }

    // =========================================================
    // EDIT ACCOUNT
    // =========================================================

    @When("The User clicks on edit account information link on Account page")
    public void the_user_clicks_on_edit_account_information_link_on_account_page() {

        apa.clickEditAccInfo();

        log.info("Clicked edit account link");
    }

    @When("The user clear and enter new telephone number in input field")
    public void the_user_clear_and_enter_new_telephone_number_in_input_field(
            DataTable dataTable) {

        apa.updateDetails(dataTable);

        log.info("Updated telephone number");
    }

    @When("The user clicks on continue Button in Edit Information Page")
    public void the_user_clicks_on_continue_button_in_edit_information_page() {

        apa.clickEContinueBtn();

        log.info("Clicked continue button");
    }

    @Then("The user should see a sucess Message Your account has updated successfully")
    public void the_user_should_see_a_success_message_your_account_has_updated_successfully() {

        String actual = apa.successMsgE();

        String expected =
                "Success: Your account has been successfully updated.";

        Assert.assertTrue(actual.contains(expected));

        log.info("Account update success message verified");
    }

    // =========================================================
    // INVALID ACCOUNT UPDATE
    // =========================================================

    @When("The user Clear all Existing Information in input field and Update Details Except one Input Field")
    public void the_user_clear_all_existing_information_in_input_field_and_update_details_except_one_input_field(
            DataTable dataTable) {

        apa.ivUpdateDetails(dataTable);
        apa.clickEContinueBtn();
    }

    @Then("The user Should be thrown with a Warning message")
    public void the_user_should_be_thrown_with_a_warning_message() {

        try {

            String actual = apa.AccountEditMsg();

            String expected =
                    "Telephone must be between 3 and 32 characters!";

            Assert.assertTrue(actual.contains(expected));

            log.info("Error Message has Thrown as Expected");

        } catch (AssertionError e) {

            log.error("Error message Not Thrown " + e.getMessage());

            throw e;
        }
    }

    // =========================================================
    // NEWSLETTER
    // =========================================================

    @When("The User clicks on Subscribe newsletter Link in Account Page")
    public void the_user_clicks_on_subscribe_newsletter_link_in_account_page() {

        apa.clickSubscribeNewsLetter();
    }

    @When("check the radio Button as yes")
    public void check_the_radio_button_as_yes() {

        apa.clickNlRadioButton();
    }

    @When("Click The  Continue Button")
    public void click_the_continue_button() {

        apa.clickNlContinueBtn();
    }

    @Then("The user Should be Successfully Subscribed and a Success Message should be Displayed")
    public void the_user_should_be_successfully_subscribed_and_a_success_message_should_be_displayed() {

        try {

            String actual = apa.successMsgNLSubscribe();

            String expected =
                    "Success: Your newsletter subscription has been successfully updated!";

            Assert.assertTrue(actual.contains(expected));

            log.info("Success Message Displayed");

        } catch (AssertionError e) {

            log.error("Success message Not Displayed "
                    + e.getMessage());

            throw e;
        }
    }

    // =========================================================
    // ADDRESS BOOK
    // =========================================================

    @When("click the address book and click new address")
    public void click_the_address_book_and_click_new_address() {

        apa.clickAddressBook();
        apa.clickNewAddress();
    }

    @When("enter the valid details")
    public void enter_the_valid_details() {

        adpa.enterAddressDetails();
    }

    @Then("user can the see the successfully address added message")
    public void user_can_the_see_the_successfully_address_added_message() {

        Assert.assertTrue(
                adpa.getSuccessMessage()
                        .contains("Your address has been successfully added")
        );
    }

    @When("click Account continue Button")
    public void click_account_continue_button() {

        adpa.clickAddressCtnBtn();
    }

    @When("The User Clicks on address Book Link")
    public void the_user_clicks_on_address_book_link() {

        apa.clickAddressBook();
    }

    @When("Clicks Delete Button on Address")
    public void clicks_delete_button_on_address() {

        apa.clickDeleteAddress();
    }

    @Then("the user should see a Delete Success Message")
    public void the_user_should_see_a_delete_success_message() {

        Assert.assertTrue(
                apa.DelteSuccessMsg()
                        .contains("Your address has been successfully deleted")
        );
    }

    @When("Clicks Edit Button on Address")
    public void clicks_edit_button_on_address() {

        adpa.clickEditAddressBtn();
    }

    @When("The User Change The Change the Address Details")
    public void the_user_change_the_change_the_address_details() {

        String fname = ConfigReader
                .getEditAddressProperties()
                .getProperty("edit1");

        String lname = ConfigReader
                .getEditAddressProperties()
                .getProperty("edit2");

        String address = ConfigReader
                .getEditAddressProperties()
                .getProperty("edit3");

        String city = ConfigReader
                .getEditAddressProperties()
                .getProperty("edit4");

        eapa.enterAddressChange(
                fname,
                lname,
                address,
                city
        );
    }

    @When("The User Clicks on Continue Button")
    public void the_user_clicks_on_continue_button() {

        eapa.clickEditSubmit();
    }

    @Then("the user should see a AddressEdit Success Message")
    public void the_user_should_see_a_address_edit_success_message() {

        Assert.assertTrue(
                eapa.getEditAddressSuccessMsg()
                        .contains("Your address has been successfully updated")
        );
    }

    // =========================================================
    // REWARD POINTS
    // =========================================================

    @When("The User Clicks on your Reward points in Account Page")
    public void the_user_clicks_on_your_reward_points_in_account_page() {

        apa.clickRewardPointsLink();
    }

    @Then("The User Should Redirected to Reward Poitns Page")
    public void the_user_should_redirected_to_reward_poitns_page() {

        Assert.assertTrue(
                apa.rewardPointRedirection()
                        .contains("Your Reward Points")
        );
    }

    // =========================================================
    // MY VOUCHER
    // =========================================================

    @When("The user move to myAccount link in navBar")
    public void the_user_move_to_my_account_link_in_nav_bar() {

        apa.moveToElementOfMyAccount();
    }

    @When("click My voucher navbar")
    public void click_my_voucher_navbar() {

        apa.clickMyVoucher();
    }

    // =========================================================
    // VALID GIFT CERTIFICATE
    // =========================================================

    @When("fill all the valid details for purchase gift certificate")
    public void fill_all_the_valid_details_for_purchase_gift_certificate() {

        gca.enterToName(giftData.get("Name"));

        gca.enterToEmail(giftData.get("Email"));

        gca.selectGiftCertificateTheme();

        log.info("Entered valid gift certificate details");
    }

    @When("I understand that gift certificates are non-refundable and click continue button")
    public void i_understand_that_gift_certificates_are_non_refundable_and_click_continue_button() {

        gca.checkAgreeCheckbox();

        gca.clickContinueButton();

        log.info("Accepted gift certificate terms and clicked Continue");
    }

    @Then("you can see the message Thank you for purchasing a gift certificate!")
    public void you_can_see_the_message_thank_you_for_purchasing_a_gift_certificate() {

        String actual = apa.getVoucherSuccessMsg();

        String expected = giftData.get("Expected");

        Assert.assertTrue(
                actual.contains(expected),
                "Expected: " + expected + " but Actual: " + actual
        );

        log.info("Gift certificate success message verified");
    }

    // =========================================================
    // INVALID GIFT CERTIFICATE EMAIL
    // =========================================================

    @When("fill the gift certificate details with invalid recipient email")
    public void fill_the_gift_certificate_details_with_invalid_recipient_email() {

        gca.enterToName(giftData.get("Name"));


        // Invalid email intentionally entered
        gca.enterToEmail("invalidemail");

        gca.selectGiftCertificateTheme();

        log.info("Entered invalid recipient email");
    }

  
    @Then("The user should see an error message for invalid recipient email")
    public void the_user_should_see_an_error_message_for_invalid_recipient_email() {

        String actual = apa.getInvalidEmailMessage();

        String expected =
                "E-Mail Address does not appear to be valid!";

        Assert.assertTrue(
                actual.contains(expected),
                "Expected error message: " + expected
                        + " but Actual: " + actual
        );

        log.info("Invalid recipient email error message verified");

        gca.enterToEmail(giftData.get("Name"));

        gca.selectGiftCertificateTheme();
    }

    @Then("The user should see an error message for invalid recipient email")
    public void the_user_should_see_an_error_message_for_invalid_recipient_email() {

        Assert.assertEquals(
                "E-Mail Address does not appear to be valid!",
                apa.getInvalidEmailMessage()
        );
    }

    // =========================================================
    // ORDER HISTORY
    // =========================================================

    @When("Clicks on Order History Link")
    public void clicks_on_order_history_link() {

        apa.clickOrderHistory();
    }
}