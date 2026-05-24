package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.LaunchPageAction;
import com.actions.ProductCompareAction;
import com.actions.productPageAction;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductCompareDefition {

	private static final Logger log = LogManager.getLogger(ProductCompareDefition.class);
	LaunchPages launchPage = new LaunchPages(DriverClass.getDriver());
	LaunchPageAction launchAction = new LaunchPageAction();
	ProductCompareAction compareAction = new ProductCompareAction();
	productPageAction productAction = new productPageAction();

	@Given("the user is on the homepage")
	public void the_user_is_on_the_homepage() {

		String url = ConfigReader.getProperties().getProperty("url");
		launchPage.launchApplication(url);
		log.info("Application launched successfully");
	}

	@When("the user clicks product compare without adding product")
	public void the_user_clicks_product_compare() {

		launchAction.clickProductCompare();
		log.info("Product compare button clicked");
	}

	@Then("the user should see no products to compare error {string}")
	public void the_user_should_see_no_products_to_compare_error(String expectedMessage) {

		String actualMessage = compareAction.getNoProductMessage();
		try {
			Assert.assertEquals(actualMessage, expectedMessage);
			log.info("No product compare message verified successfully");
		} catch (AssertionError e) {
			log.error("No product compare message verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("the user selects any product from the home page")
	public void the_user_selects_any_product_from_the_home_page() {

		launchAction.clickCanonES5Product();
		log.info("Product selected successfully");
	}

	@When("clicks on Compare this Product on the product details page")
	public void clicks_on_compare_this_product_on_the_product_details_page() {

		productAction.clickCompareBtn();
		log.info("Compare this Product button clicked");
	}

	@Then("the compare confirmation message should be displayed")
	public void the_compare_confirmation_message_should_be_displayed() {

		String actualMessage = productAction.getConfirmationMessage();
		try {
			Assert.assertTrue(actualMessage.contains("Success: You have added"));
			log.info("Compare confirmation message verified successfully");
		} catch (AssertionError e) {
			log.error("Compare confirmation verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("the user selects first product from the home page and click compare button")
	public void the_user_selects_two_products_from_the_home_page() {

		launchAction.clickCanonES5Product();
		productAction.clickCompareBtn();
		DriverClass.getDriver().navigate().back();
		log.info("First product added for comparison");
	}

	@When("the user selects second product from the home page and click compare button")
	public void clicks_compare_this_product_on_both_product_detail_pages() {

		launchAction.clickiMac();
		productAction.clickCompareBtn();
		launchAction.clickProductCompare();
		log.info("Second product added for comparison");
	}

	@Then("the user should see both products in the comparison page")
	public void the_user_should_see_both_products_in_the_comparison_page() {

		String actualiMacTitle = compareAction.getiMacTitle();
		String expectediMacTitle = "iMac";
		String actualCanonTitle = compareAction.getCanonTitle();
		String expectedCanonTitle = "Canon EOS 5D";
		try {
			Assert.assertEquals(actualiMacTitle, expectediMacTitle);
			Assert.assertEquals(actualCanonTitle, expectedCanonTitle);
			log.info("Both compared products verified successfully");
		} catch (AssertionError e) {
			log.error("Compared products verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("click compare Button")
	public void click_compare_button() {

		productAction.clickViewCompare();
		log.info("View compare button clicked");
	}

	@When("remove the product from compare")
	public void remove_the_product_from_compare() {

		compareAction.clickRemoveCompare();
		log.info("Product removed from compare successfully");
	}

	@Then("the user get an message {string}")
	public void the_user_get_an_message(String expectedMessage) {

		String actualMessage = compareAction.getConfirmationRemoved();
		try {
			Assert.assertEquals(actualMessage, expectedMessage);
			log.info("Product removal message verified successfully");
		} catch (AssertionError e) {
			log.error("Product removal message verification failed : " + e.getMessage());
			throw e;
		}
	}
}