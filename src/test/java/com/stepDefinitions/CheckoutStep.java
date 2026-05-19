package com.stepDefinitions;

import com.actions.CheckoutAction;
import com.actions.FilterPageAction;
import com.actions.SearchAction;
import com.actions.productPageAction;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class CheckoutStep {

    CheckoutAction checkoutAction = new CheckoutAction();
    SearchAction searchAction = new SearchAction();
    FilterPageAction filterPageAction = new FilterPageAction();
    productPageAction productPageAction = new productPageAction();

    @Given("the user should be an registered users")
    public void the_user_should_be_an_registered_users() {
        checkoutAction.loginAsRegisteredUser();
    }

    @When("the user enters the {string} in the search bar")
    public void the_user_enters_the_in_the_search_bar(String keyword) {
        searchAction.clickSearchBar();
        searchAction.enterKeywordAndPressEnter(keyword);
    }

    @When("the user clicks the {string} and click the product")
    public void the_user_clicks_the_and_click_the_product(String product) {
        checkoutAction.clickHpProduct();
    }

    @Then("the application should be redirect to the current product page")
    public void the_application_should_be_redirect_to_the_current_product_page() {
        Assert.assertTrue(
                productPageAction.getProductTitle().length() > 0,
                "Product title is not displayed, likely not on product page"
        );
    }

    @Then("the product Availability should be {string}")
    public void the_product_availability_should_be(String status) {
        Assert.assertEquals(productPageAction.getInstockAvailability().trim(), status);
    }

    @Then("the user clicks the add to cart button")
    public void the_user_clicks_the_add_to_cart_button() {
        productPageAction.clickAddToCartbutton();
    }

    @And("the user clicks the shopping cart link from the popup")
    public void the_user_clicks_the_shopping_cart_link_from_the_popup() {
        checkoutAction.clickShoppingCartFromPopup();
    }

    @And("the user clicks the checkout button from the cart page")
    public void the_user_clicks_the_checkout_button_from_the_cart_page() throws InterruptedException {
        Thread.sleep(5000);
        checkoutAction.clickCheckoutFromCartPage();
    }

    @When("the user clicks the cart button in the top of the navbar")
    public void the_user_clicks_the_cart_button_in_the_top_of_the_navbar() {
        checkoutAction.clickNavbarCart();
    }

    @And("the application opens and slidebar and user clicks the checkout")
    public void the_application_opens_and_slidebar_and_user_clicks_the_checkout() {
        checkoutAction.clickSidebarCheckout();
    }

    @Then("the application redirect to the chechkout")
    public void the_application_redirect_to_the_chechkout() {
        Assert.assertTrue(
                checkoutAction.isCheckoutOrLoginPageDisplayed(),
                "User is not redirected to the checkout or login page"
        );
    }

    @And("the user clicks the {string}")
    public void the_user_clicks_the(String buttonText) {
        if (buttonText.equals("I want to use a new address")) {
            checkoutAction.selectNewAddress();
        }
    }

    @And("the user enters the firstname, lastname, company, address1, city, postcode, country, Regionstate")
    public void the_user_enters_the_firstname_lastname_company_address1_city_postcode_country_regionstate() {
        checkoutAction.enterBillingDetails(
                "Test", "User", "Company", "Address", "City", "123456", "India", "Tamil Nadu"
        );
    }

    @And("the user clicks same billing address and cash on delivery and flat rate button")
    public void the_user_clicks_same_billing_address_and_cash_on_delivery_and_flat_rate_button() {
        checkoutAction.clickSameBillingAddress();
        checkoutAction.selectFlatRate();
        checkoutAction.selectCashOnDelivery();
    }

    @And("the user clicks the terms & conditions button and continue the checkout")
    public void the_user_clicks_the_terms_conditions_button_and_continue_the_checkout() {
        checkoutAction.clickTermsAndConditions();
        checkoutAction.continueCheckout();
    }

    @Then("the order should be successfully placed and application redirect to the order confirmation page")
    public void the_order_should_be_successfully_placed_and_application_redirect_to_the_order_confirmation_page() {
        Assert.assertTrue(
                checkoutAction.isOrderPlacedSuccessfully(),
                "Order was not successfully placed"
        );
    }

    @And("the user selects {string} option")
    public void the_user_selects_option(String option) {
        if (option.equals("Register Account")) {
            checkoutAction.selectRegisterAccount();
        }
    }

    @And("the user enters registration details")
    public void the_user_enters_registration_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> u = data.get(0);

        checkoutAction.enterRegistrationDetails(
                u.get("firstName"),
                u.get("lastName"),
                u.get("email"),
                u.get("telephone"),
                u.get("password"),
                u.get("confirmPassword"),
                u.get("company"),
                u.get("address1"),
                u.get("address2"),
                u.get("city"),
                u.get("postCode"),
                u.get("country"),
                u.get("region")
        );
    }

    @And("the user agrees to the privacy policy")
    public void the_user_agrees_to_the_privacy_policy() {

        checkoutAction.clickTermsAndConditions();
        checkoutAction.agreeToPrivacyPolicy();
    }

    @And("the user clicks on Continue button")
    public void the_user_clicks_on_continue_button() {
        checkoutAction.continueCheckout();
    }

    @Then("the application shows the shopping Cart is empty!")
    public void the_application_shows_the_shopping_cart_is_empty() {
        Assert.assertTrue(
                checkoutAction.isEmptyCartMessageDisplayed(),
                "Empty cart message is not displayed"
        );
    }
}
