package com.stepDefinitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.CartPageActions;
import com.actions.LaunchPageAction;
import com.actions.LoginPageAction;
import com.actions.productPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSD {
	LoginPageAction lpa = new LoginPageAction();
	LaunchPageAction lp = new LaunchPageAction();
	productPageAction ppa = new productPageAction();
	CartPageActions cpa = new CartPageActions();
	List<String> allproduct;
	
	private static final Logger log = LogManager.getLogger(CartSD.class);

	@Given("The user is in Home Page of Ecommerce lambda test application")
	public void the_user_is_in_home_page_of_ecommerce_lambda_test_application() {
		lpa.launchWebUrl();
	}

	@When("The user clicks on a product in Top Collection menu")
	public void the_user_clicks_on_a_product_in_top_collection_menu() {
		lp.clickProductInTopCollection();
	}

	@When("The user clicks on add to cart button in Product details page")
	public void the_user_clicks_on_add_to_cart_button_in_product_details_page() {
		ppa.clickAddToCartbutton();
	}

	@When("The user clicks view-cart button in popup displayed")
	public void the_user_clicks_view_cart_button_in_popup_displayed() {
		ppa.viewCartPP();
	}

	@Then("The added product should be in cart Page")
	public void the_added_product_should_be_in_cart_page() {
		String actual = cpa.getProductName();
		String expected = "HP LP3065";
		try {
			Assert.assertEquals(actual, expected);
			log.info("Product is Added in cart Succesfully");
		} catch (AssertionError e) {
			log.error("Product added in cart,Error: " + e.getMessage());
			throw e;
		}
	}

	@When("The user enter the quantity as {string} based on needs in quantity input box")
	public void the_user_enter_the_quantity_as_based_on_needs_in_quantity_input_box(String string) {
		cpa.sendQuantity(string);
	}

	@When("The user clicks quantity update button")
	public void the_user_clicks_quantity_update_button() {
		cpa.clickQUpdateButton();
	}

	@Then("The user should see a success message quantity updated successfully")
	public void the_user_should_see_a_success_message_quantity_updated_successfully() {
		String actual = cpa.getQuantitySuccessMsg().trim();
		String expected = "Success: You have modified your shopping cart!";
		try {
			Assert.assertTrue(actual.contains(expected));
			log.info("Quantity is updated in cart product Successfully");
		} catch (AssertionError e) {
			log.error("Quantity doesn't added!, Error: " + e.getMessage());
			throw e;
		}
	}
	
	@When("The user clicks product Remove button")
	public void the_user_clicks_product_remove_button() {
		cpa.clickRemoveButton();
	  
	}

	@Then("The product should be removed from the cart and display no products in cart")
	public void the_product_should_be_removed_from_the_cart_and_display_no_products_in_cart() {
	   String actual=cpa.getCartEmptyMsg();
	   String expected="Your shopping cart is empty!";
	   try {
			Assert.assertTrue(actual.contains(expected));
			log.info("The product is removed from cart Successfully");
		} catch (AssertionError e) {
			log.error("product doesn't removed!, Error: " + e.getMessage());
			throw e;
		}
	   
	}
	
	@When("The user clicks on Estimate Shipping and Tax Link")
	public void the_user_clicks_on_estimate_shipping_and_tax_link() {
	   cpa.clickESTLink();
	}

	@When("select options from country and state dropdown")
	public void select_options_from_country_and_state_dropdown(io.cucumber.datatable.DataTable dataTable) {
	   cpa.selectCountryAndState(dataTable);
	}
	@When("The user clicks Quotes Button")
	public void the_user_clicks_quotes_button() {
	  cpa.clickgetQuotesButton();
	}

	@When("check the radio button in Flash Shipping popup")
	public void check_the_radio_button_in_flash_shipping_popup() {
	    cpa.checkRadioButton();
	}

	@When("clicks on Apply Shipping Button")
	public void clicks_on_apply_shipping_button() {
	    cpa.clickApplyShippingButton();
	}

	@Then("The user should see a Success Message")
	public void the_user_should_see_a_success_message() {
	    String actual=cpa.getSuccessETMsg();
	    String expected="Success: Your shipping estimate has been applied!";
	    try {
			Assert.assertTrue(actual.contains(expected));
			log.info("Shipping estimation applied successfully");
		} catch (AssertionError e) {
			log.error("Shipping estimation doesn't applied, Error: " + e.getMessage());
			throw e;
		}
	    
	}
	
	@When("The user clicks on AddtoCart Button on Varios Product")
	public void the_user_clicks_on_addto_cart_button_on_varios_product() {
	   lp.addMultipleProduct();
	}
	
	@Then("All products Added in cart should be displayed in cart")
	public void all_products_added_in_cart_should_be_displayed_in_cart() {
		allproduct = cpa.storeAllProduct();
	    System.out.println(allproduct);
	    List<String> expectedProduct = new ArrayList<>();
	    expectedProduct.add("HTC Touch HD");
	    expectedProduct.add("iPod Nano");
	    expectedProduct.add("HP LP3065");
	    Collections.sort(allproduct);
	    Collections.sort(expectedProduct);
	    try {
	        Assert.assertEquals(allproduct, expectedProduct);
	        log.info("All products are added to cart");
	    } catch(AssertionError e) {
	        log.error("Not all products are added, Error: " + e.getMessage());
	        throw e;
		
		
		
	}



	}
}
