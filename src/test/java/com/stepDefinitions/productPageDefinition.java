package com.stepDefinitions;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.FilterPageAction;
import com.actions.LaunchPageAction;
import com.actions.productPageAction;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;
import com.utils.ExcelUtils;
import com.utils.ProductInformationCSVReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class productPageDefinition {

	private static final Logger log = LogManager.getLogger(productPageDefinition.class);
	LaunchPages launchPage = new LaunchPages(DriverClass.getDriver());
	LaunchPageAction launchAction = new LaunchPageAction();
	productPageAction productAction = new productPageAction();
	FilterPageAction filterAction = new FilterPageAction();
	Map<String, String> data = ProductInformationCSVReader
			.getFirstRow("src/test/resources/ProductInformationAssertion.csv", null);
	private static Map<String, String> enquiryData = ExcelUtils.getQuestionData();

	@Given("User launches the ecommerce application")
	public void user_launches_the_ecommerce_application() {

		String url = ConfigReader.getProperties().getProperty("url");
		launchPage.launchApplication(url);
		log.info("Application launched successfully");
	}

	@Given("user opens HP product details page")
	public void user_opens_hp_product_details_page() {

		launchAction.clickHpProduct();
		log.info("HP product page opened");
	}

	@Given("user opens Canon product details page")
	public void user_opens_canon_product_details_page() {

		launchAction.clickCanonES5Product();
		log.info("Canon product page opened");
	}

	@Then("Product title should be displayed")
	public void product_title_should_be_displayed() {

		String actualTitle = productAction.getProductTitle();
		String expectedTitle = data.get("title");
		try {
			Assert.assertEquals(actualTitle, expectedTitle);
			log.info("Product title verified successfully");
		} catch (AssertionError e) {
			log.error("Product title verification failed : " + e.getMessage());
			throw e;
		}
	}

	@Then("Product price should be displayed")
	public void product_price_should_be_displayed() {

		String actualPrice = productAction.getPrice();
		String expectedPrice = data.get("price");
		try {
			Assert.assertEquals(actualPrice, expectedPrice);
			log.info("Product price verified successfully");
		} catch (AssertionError e) {
			log.error("Product price verification failed : " + e.getMessage());
			throw e;
		}
	}

	@Then("Product availability status should be displayed")
	public void product_availability_status_should_be_displayed() {

		String actualStatus = productAction.getInstockAvailability();
		String expectedStatus = data.get("availability");
		try {
			Assert.assertEquals(actualStatus, expectedStatus);
			log.info("Availability status verified successfully");
		} catch (AssertionError e) {
			log.error("Availability verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("user updates product quantity to {string}")
	public void user_updates_product_quantity_to(String quantity) {

		productAction.setQuantity(quantity);
		log.info("Product quantity updated to " + quantity);
	}

	@When("click the cart")
	public void click_the_cart() {

		productAction.clickAddToCart();
		productAction.clickAddToCartbutton();
		log.info("Cart opened successfully");
	}

	@Then("product quantity should be updated successfully in the cart {string}")
	public void product_quantity_should_be_updated_successfully_in_the_cart(String expectedQuantity) {

		String actualQuantity = productAction.getQuantityInCart();
		try {
			Assert.assertEquals(actualQuantity, expectedQuantity);
			log.info("Product quantity updated successfully");
		} catch (AssertionError e) {
			log.error("Quantity verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("user clicks on Ask Question option")
	public void user_clicks_on_ask_question_option() {

		productAction.clickAskQuestion();
		log.info("Ask Question option clicked");
	}

	@When("user fills enquiry form")
	public void user_fills_enquiry_form() {

		productAction.setName(enquiryData.get("Name"));
		productAction.setEmail(enquiryData.get("Email"));
		productAction.setSubject(enquiryData.get("subject"));
		productAction.setMessage(enquiryData.get("Message"));
		productAction.clickSendMessage();
		log.info("Enquiry form submitted");
	}

	@Then("enquiry should be sent successfully")
	public void enquiry_should_be_sent_successfully() {

		String actualMessage = productAction.getAlertMessage();
		String expectedMessage = "Your enquiry has been successfully sent to the store owner!";
		try {
			Assert.assertEquals(actualMessage, expectedMessage);
			log.info("Enquiry sent successfully");
		} catch (AssertionError e) {
			log.error("Enquiry verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("user clicks Add to Cart button")
	public void user_clicks_add_to_cart_button() {

		productAction.clickAddToCart();
		log.info("Add To Cart button clicked");
	}

	@When("user clicks Wishlist button")
	public void user_clicks_wishlist_button() {

		productAction.clickWishListBtn();
		log.info("Wishlist button clicked");
	}

	@Then("both actions should be triggered successfully")
	public void both_actions_should_be_triggered_successfully() {

		try {
			Assert.assertEquals("Size required!", productAction.getAddTocartConfirmation());
			Assert.assertEquals("wish list", productAction.getWishListConfirmation());
			log.info("Wishlist and cart actions verified");
		} catch (AssertionError e) {
			log.error("Action verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("user clicks on breadcrumb category link Software")
	public void user_clicks_on_breadcrumb_category_link_software() {

		productAction.clickSoftwareBreadcrumb();
		log.info("Software breadcrumb clicked");
	}

	@Then("user should be navigated to product category page successfully")
	public void user_should_be_navigated_to_product_category_page_successfully() {

		String actualTitle = filterAction.getSoftwareTitle();
		String expectedTitle = "Software";
		try {
			Assert.assertEquals(actualTitle, expectedTitle);
			log.info("Successfully navigated to Software page");
		} catch (AssertionError e) {
			log.error("Navigation verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("user submits empty enquiry form")
	public void user_submits_empty_enquiry_form() {

		productAction.clickSendMessage();
		log.info("Empty enquiry form submitted");
	}

	@Then("validation message should be displayed for enquiry form")
	public void validation_message_should_be_displayed_for_enquiry_form() {

		String actualMessage = productAction.getMadatoryFieldsMessage();
		String expectedMessage = "Name must be between 3 and 32 characters!";
		try {
			Assert.assertEquals(actualMessage, expectedMessage);
			log.info("Validation message verified successfully");
		} catch (AssertionError e) {
			log.error("Validation message verification failed : " + e.getMessage());
			throw e;
		}
	}

	@When("user clicks minus button {int} times")
	public void user_clicks_minus_button_times(Integer count) {

		for (int i = 0; i < count; i++) {
			productAction.clickMinus();
		}
		log.info("Minus button clicked " + count + " times");
	}

	@Then("product quantity should not be less than {int}")
	public void product_quantity_should_not_be_less_than(Integer quantity) {

		String actualQuantity = productAction.getQuantity();
		String expectedQuantity = "1";
		try {
			Assert.assertEquals(actualQuantity, expectedQuantity);
			log.info("Minimum quantity verified successfully");
		} catch (AssertionError e) {
			log.error("Minimum quantity verification failed : " + e.getMessage());
			throw e;
		}
	}
}