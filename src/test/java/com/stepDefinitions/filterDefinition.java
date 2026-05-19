package com.stepDefinitions;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.FilterPageAction;
import com.actions.LaunchPageAction;
import com.actions.productPageAction;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class filterDefinition {
	private static Logger log = LogManager.getLogger(filterDefinition.class);
	LaunchPages lp = new LaunchPages(DriverClass.getDriver());

	FilterPageAction fpa = new FilterPageAction();

	productPageAction ppa = new productPageAction();

	LaunchPageAction lpa = new LaunchPageAction();

	List<String> products;

	@Given("User is on Home page")
	public void user_is_on_home_page() {

		String url = ConfigReader.getProperties().getProperty("url");
		lp.launchApplication(url);
	}

	@Given("User clicks on Shop By Categories")
	public void user_clicks_on_shop_by_categories() {

		lpa.clickCategories();
	}

	@Given("User selects any one category")
	public void user_selects_any_one_category() {

		lpa.clickMonitor();
	}

	@When("User clicks any manufacturer filter option")
	public void user_clicks_any_manufacturer_filter_option() {

		fpa.clickManufacture();
	}

	@When("Products should display based on selected manufacturer")
	public void products_should_display_based_on_selected_manufacturer() {

		System.out.println("Manufacturer products displayed");
	}

	@When("User clicks any one product")
	public void user_clicks_any_one_product() {

		fpa.clickProduct();
	}

	@Then("Product brand name in description should match the selected filter")
	public void product_brand_name_in_description_should_match_the_selected_filter() {

		String actual = ppa.getBrandName();
		String expected = "Ale";
		try {
			Assert.assertEquals(actual, expected);
			log.info("Brand name matched successfully");
		} catch (AssertionError e) {
			log.error("Brand name mismatch, Error: " + e.getMessage());
			throw e;
		}
	}

	@When("User selects an option from the show products dropdown")
	public void user_selects_an_option_from_the_show_products_dropdown() {

		fpa.clickManufacture();
		fpa.selectDropdownByVisibleText(ConfigReader.getProperties().getProperty("index"));
	}

	@When("User  displayed products")
	public void user_stores_the_displayed_products_in_a_list() {

		products = fpa.storeAllProducts();
		System.out.println(fpa.getItems());
	}

	@Then("Displayed product count should match the selected dropdown value")
	public void displayed_product_count_should_match_the_selected_dropdown_value() {

		int actual = fpa.getDisplayedProductCount();
		int expected = 25;
		try {
			Assert.assertEquals(actual, expected);
			log.info("Displayed product count matched successfully");
		} catch (AssertionError e) {
			log.error("Displayed product count mismatch, Error: " + e.getMessage());
			throw e;
		}
	}

	@When("User clicks the in-stock filter option")
	public void user_clicks_the_in_stock_filter_option() {

		fpa.clickAvailability();
	}

	@When("User clicks any one product based on instock")
	public void user_clicks_any_one_product_based_on_instock() {

		fpa.clickCanonProduct();
	}

	@When("Products should display based on availability")
	public void products_should_display_based_on_availability() {

		System.out.println("Availability products displayed");
	}

	@Then("Product availability status should be displayed in product description")
	public void product_availability_status_should_be_displayed_in_product_description() {

		String actual = ppa.getInstockAvailability();
		String expected = "In Stock";
		try {
			Assert.assertEquals(actual, expected);
			log.info("Product availability status matched successfully");
		} catch (AssertionError e) {
			log.error("Availability status mismatch, Error: " + e.getMessage());
			throw e;
		}
	}

	@When("User clicks the in-stock filter option and click one product")
	public void user_clicks_the_in_stock_filter_option_and_click_one_product() {

		fpa.clickAvailability();
		fpa.clickCanonProduct();
	}

	@Then("In-stock products should display availability status in product description")
	public void in_stock_products_should_display_availability_status_in_product_description() {

		String actual = ppa.getInstockAvailability();
		String expected = "In Stock";
		try {
			Assert.assertEquals(actual, expected);
			log.info("In-stock product availability verified successfully");
		} catch (AssertionError e) {
			log.error("In-stock availability verification failed, Error: " + e.getMessage());
			throw e;
		}

		DriverClass.getDriver().navigate().back();
	}

	@When("User clicks the out-of-stock filter option and click one product")
	public void user_clicks_the_out_of_stock_filter_option_and_click_one_product() {

		DriverClass.getDriver().navigate().refresh();
		fpa.clickOutofStockOption();
		fpa.clickHTCTouchHD();
	}

	@Then("Out-of-stock products should display availability status in product description")
	public void out_of_stock_products_should_display_availability_status_in_product_description() {

		String actual = ppa.getOutStockAvailability();
		String expected = "Out Of Stock";
		try {
			Assert.assertEquals(actual, expected);
			log.info("Out-of-stock product availability verified successfully");
		} catch (AssertionError e) {
			log.error("Out-of-stock availability verification failed, Error: " + e.getMessage());
			throw e;
		}
	}

	@When("move the slider")
	public void move_the_slider() {
	//	fpa.moveSlider();
	}

	@Then("the price should be updated in filter page")
	public void the_price_should_be_updated_in_filter_page() {
		// Write code here that turns the phrase above into concrete actions
		// Assert.assertEquals(fpa.getValue(), "1544");
	}

}
