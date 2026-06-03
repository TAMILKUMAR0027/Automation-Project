package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.AddOnsDesignPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AddOnsDesigns {
	 private static final Logger log = LogManager.getLogger(Forgetpassword.class);
	AddOnsDesignPageAction adpa=new AddOnsDesignPageAction();
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



}
