package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.AccountPageAction;
import com.actions.ReturnsPageAction;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReturnsSD {
	AccountPageAction apa = new AccountPageAction();
	ReturnsPageAction rpa = new ReturnsPageAction();
	private static final Logger log = LogManager.getLogger(ReturnsSD.class);
	@When("the user clicks on returns link")
	public void the_user_clicks_on_returns_link() {
	    apa.clickReturnsLink();
	}
	@Then("the user should redirected to product returns page")
	public void the_user_should_redirected_to_product_returns_page() {
	    String actual=rpa.getProductReturnsHeading();
	    Assert.assertTrue(actual.contains("Product Returns"));
	}
	@When("the user clicks on first return product view Button")
	public void the_user_clicks_on_first_return_product_view_button() {
	    rpa.clickFirstViewBtn();
	}
	@Then("the user should redirected to return Information page")
	public void the_user_should_redirected_to_return_information_page() {
		String actual=rpa.getProductReturnsInfoHeading();
	    Assert.assertTrue(actual.contains("Return Information"));
	}
	
}
