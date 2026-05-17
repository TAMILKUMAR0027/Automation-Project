package com.stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import com.actions.FilterPageAction;
import com.actions.LaunchPageAction;
import com.actions.productPageAction;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;
import com.utils.ExcelUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productPageDefinition {

    LaunchPages lp = new LaunchPages(DriverClass.getDriver());
    LaunchPageAction lpa = new LaunchPageAction();
    productPageAction ppa = new productPageAction();
    FilterPageAction fpa = new FilterPageAction();

    private static Map<String, String> testData = ExcelUtils.getQuestionData();

    @Given("User launches the ecommerce application")
    public void user_launches_the_ecommerce_application() {

        String url = ConfigReader.getProperties().getProperty("url");
        lp.launchApplication(url);
    }

    @Given("user opens HP product details page")
    public void user_opens_hp_product_details_page() {

        lpa.clickHpProduct();
    }

    @Given("user opens Canon product details page")
    public void user_opens_canon_product_details_page() {

        lpa.clickCanonES5Product();
    }

    @Then("Product title should be displayed")
    public void product_title_should_be_displayed() {

        Assert.assertEquals("Canon EOS 5D", ppa.getProductTitle());
    }

    @Then("Product price should be displayed")
    public void product_price_should_be_displayed() {

        Assert.assertEquals("$134.00", ppa.getPrice());
    }

    @Then("Product availability status should be displayed")
    public void product_availability_status_should_be_displayed() {

        Assert.assertEquals("In Stock", ppa.getInstockAvailability());
    }

    @When("user updates product quantity to {string}")
    public void user_updates_product_quantity_to(String string) {

        ppa.setQuantity(string);
    }

    @When("click the cart")
    public void click_the_cart() {

        ppa.clickAddToCart();
        ppa.clickCart();
    }

    @Then("product quantity should be updated successfully in the cart {string}")
    public void product_quantity_should_be_updated_successfully_in_the_cart(String string) {

        Assert.assertEquals(string, ppa.getQuantityInCart());
    }

    @When("user clicks on Ask Question option")
    public void user_clicks_on_ask_question_option() {

        ppa.clickAskQuestion();
    }

    @When("user fills enquiry form")
    public void user_fills_enquiry_form() {

        String name = testData.get("Name");
        String email = testData.get("Email");
        String subject = testData.get("subject");
        String message = testData.get("Message");

        ppa.setName(name);
        ppa.setEmail(email);
        ppa.setSubject(subject);
        ppa.setMessage(message);
        ppa.clickSendMessage();
    }

    @Then("enquiry should be sent successfully")
    public void enquiry_should_be_sent_successfully() {

        Assert.assertEquals(
                "Your enquiry has been successfully sent to the store owner!",
                ppa.getAlertMessage());
    }

    @When("user clicks Add to Cart button")
    public void user_clicks_add_to_cart_button() {

        ppa.clickAddToCart();
    }

    @When("user clicks Wishlist button")
    public void user_clicks_wishlist_button() {

        ppa.clickWishListBtn();
    }

    @Then("both actions should be triggered successfully")
    public void both_actions_should_be_triggered_successfully() {

        Assert.assertEquals("Size required!", ppa.getAddTocartConfirmation());
        Assert.assertEquals("wish list", ppa.getWishListConfirmation());
    }

    @When("user clicks on breadcrumb category link Software")
    public void user_clicks_on_breadcrumb_category_link_software() {

        ppa.clickSoftwareBreadcrumb();
    }

    @Then("user should be navigated to product category page successfully")
    public void user_should_be_navigated_to_product_category_page_successfully() {

        Assert.assertEquals("Software", fpa.getSoftwareTitle());
    }

    @When("user submits empty enquiry form")
    public void user_submits_empty_enquiry_form() {

        ppa.clickSendMessage();
    }

    @Then("validation message should be displayed for enquiry form")
    public void validation_message_should_be_displayed_for_enquiry_form() {

        Assert.assertEquals(
                "Name must be between 3 and 32 characters!",
                ppa.getMadatoryFieldsMessage());
    }

    @When("user clicks minus button {int} times")
    public void user_clicks_minus_button_times(Integer int1) {

        for (int i = 0; i < int1; i++) {

            ppa.clickMinus();
        }
    }

    @Then("product quantity should not be less than {int}")
    public void product_quantity_should_not_be_less_than(Integer int1) {

        Assert.assertEquals("1", ppa.getQuantity());
    }
}