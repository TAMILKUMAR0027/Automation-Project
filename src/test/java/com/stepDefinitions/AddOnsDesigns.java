package com.stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.testng.Assert;

import com.actions.AddOnPageAction;
import com.actions.AddOnsDesignPageAction;
import com.driver.DriverClass;
import com.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddOnsDesigns {
	 private static final Logger log = LogManager.getLogger(Forgetpassword.class);
	AddOnsDesignPageAction adpa=new AddOnsDesignPageAction();
	AddOnPageAction aopa=new AddOnPageAction();
	private List<String> actualList;
	@Given("the user clicks on AddOns and selects designs")
	public void the_user_clicks_on_add_ons_and_selects_designs() {
	    adpa.clickAddOns();
	    adpa.clickDesigns();
	    log.info("Clicking on Designs");
	}

	@When("the user clicks the Drawer left option")
	public void the_user_clicks_the_drawer_left_option() {
	   adpa.clickDrawerLeft();
	   log.info("Clicking Drawer left");
	}

	@Then("the user is able to view a left side panel displaying the top categories")
	public void the_user_is_able_to_view_a_left_side_panel_displaying_the_top_categories() {
	    adpa.leftpanel();
	    if(true) {
	    	log.info("Left panel appeared");
	    }
	    else 
	    	log.error("Drawer left click failed");
	    
	    
	}

	@When("the user clicks on Drawer Right option")
	public void the_user_clicks_on_drawer_right_option() {
	    adpa.clickDrawerRight();
	    log.info("clicking drawer right button");
	}

	@Then("the application displays a right side panel listing particular product category")
	public void the_application_displays_a_right_side_panel_listing_particular_product_category() {
	   adpa.rightpanel();
	   if(true) {
	    	log.info("Right panel appeared");
	    }
	    else 
	    	log.error("Drawer right click failed");
	}

	@When("the user clicks on the Sticky Top button")
	public void the_user_clicks_on_the_sticky_top_button() {
	    adpa.clickstickytop();
	    log.info("Clicks on sticky top ");
	}

	@Then("the user is able to view a top panel appearing in the application")
	public void the_user_is_able_to_view_a_top_panel_appearing_in_the_application() {
	    adpa.Toppanel();
	    if(true) {
	    	log.info("top panel appeared");
	    }
	    else 
	    	log.error("Sticky top click failed");
	  
	    
	}

	@When("the user clicks the Sticky Bottom button")
	public void the_user_clicks_the_sticky_bottom_button() {
		adpa.clickstickybottom();
	    log.info("Clicks on sticky bottom ");
	}

	@Then("the user must view a bottom panel in the application")
	public void the_user_must_view_a_bottom_panel_in_the_application() {
	   adpa.bottompanel();
	   if(true) {
	    	log.info("bottom panel appeared");
	    }
	    else 
	    	log.error("sticky bottom click failed");
	}

	@When("the user clicks the popup")
	public void the_user_clicks_the_popup() {
		adpa.clickpopup();
	    log.info("Clicks on Popup ");
	}

	@Then("the application displays a popup box in the middle of the application")
	public void the_application_displays_a_popup_box_in_the_middle_of_the_application() {
	    adpa.Popup();
	    if(true) {
	    	log.info("Popup panel appeared");
	    }
	    else 
	    	log.error("popup button click failed");
	}
	@When("the user views the Dark Heading table")
	public void the_user_views_the_dark_heading_table() {
	    // Write code here that turns the phrase above into concrete actions
	    aopa.moveDarkHeading();
	}

	@Then("the table should display all expected column headers")
	public void the_table_should_display_all_expected_column_headers() {

	    List<String> headers = aopa.getData();

	    Assert.assertNotNull(headers, "Headers list is null");
	    Assert.assertFalse(headers.isEmpty(), "No headers found");

	    log.info("Headers displayed : {}", headers);
	}

	@Then("the table should display one or more records")
	public void the_table_should_display_one_or_more_records() {

	    List<String> records = aopa.getValues();

	    Assert.assertNotNull(records, "Records list is null");
	    Assert.assertFalse(records.isEmpty(), "No records found in the table");

	    log.info("Records displayed : {}", records);
	}
	@When("the user enters an email address in the newsletter field")
	public void the_user_enters_an_email_address_in_the_newsletter_field() {
	    // Write code here that turns the phrase above into concrete actions
		adpa.clickAnyWidgets();
		adpa.setEmail();
	}

	@When("the user clicks the Subscribe button")
	public void the_user_clicks_the_subscribe_button() {
	    // Write code here that turns the phrase above into concrete actions
	    adpa.clickSubcribe();
	}

	
	@Then("the alert message should contain {string}")
	public void the_alert_message_should_contain(String expectedMessage) {
		

	    adpa.handleAlert();
	}
	@When("user gets the list items from the Design page")
	public void user_gets_the_list_items_from_the_design_page() {

	    actualList = adpa.getListItems();

	    System.out.println("Actual List Items: " + actualList);
	}

	@Then("verify the list items with the expected data")
	public void verify_the_list_items_with_the_expected_data() {

	    List<String> expectedList = new ArrayList<>();

	    expectedList.add(ConfigReader.getListProperties().getProperty("list.item1"));
	    expectedList.add(ConfigReader.getListProperties().getProperty("list.item2"));
	    expectedList.add(ConfigReader.getListProperties().getProperty("list.item3"));

	    Assert.assertEquals(actualList, expectedList, "List items are not matching");

	    log.info("Expected List : {}", expectedList);
	    log.info("Actual List   : {}", actualList);
	    log.info("List items verified successfully.");
	}



}
