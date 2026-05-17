package com.stepDefinitions;

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
	LaunchPages lp = new LaunchPages(DriverClass.getDriver());
	LaunchPageAction lpa = new LaunchPageAction();
	ProductCompareAction pca = new ProductCompareAction();
	productPageAction ppa = new productPageAction();

	@Given("the user is on the homepage")
	public void the_user_is_on_the_homepage() {
		// Write code here that turns the phrase above into concrete actions
		String url = ConfigReader.getProperties().getProperty("url");
		lp.launchApplication(url);
	}

	@When("the user clicks product compare without adding product")
	public void the_user_clicks_product_compare() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickProductCompare();
	}

	@Then("the user should see no products to compare error {string}")
	public void the_user_should_see_no_products_to_compare_error(String string) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(string, pca.getNoProductMessage());
	}

	@When("the user selects any product from the home page")
	public void the_user_selects_any_product_from_the_home_page() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickCanonES5Product();
	}

	@When("clicks on Compare this Product on the product details page")
	public void clicks_on_compare_this_product_on_the_product_details_page() {
		// Write code here that turns the phrase above into concrete actions
		ppa.clickCompareBtn();
	}

	@Then("the compare confirmation message should be displayed")
	public void the_compare_confirmation_message_should_be_displayed() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(ppa.getConfirmationMessage().contains("Success: You have added"));
	}

	@When("the user selects first product from the home page and click compare button")
	public void the_user_selects_two_products_from_the_home_page() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickCanonES5Product();
		ppa.clickCompareBtn();

		DriverClass.getDriver().navigate().back();
	}

	@When("the user selects second product from the home page and click compare button")
	public void clicks_compare_this_product_on_both_product_detail_pages() {
		// Write code here that turns the phrase above into concrete actions
		lpa.clickiMac();
		ppa.clickCompareBtn();
		lpa.clickProductCompare();
	}

	@Then("the user should see both products in the comparison page")
	public void the_user_should_see_both_products_in_the_comparison_page() {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("iMac", pca.getiMacTitle());
		Assert.assertEquals("Canon EOS 5D", pca.getCanonTitle());
	}

	@When("click compare Button")
	public void click_compare_button() {
		// Write code here that turns the phrase above into concrete actions
	ppa.clickViewCompare();;
	}

	@When("remove the product from compare")
	public void remove_the_product_from_compare() {
		// Write code here that turns the phrase above into concrete actions
		pca.clickRemoveCompare();
	}

	@Then("the user get an message {string}")
	public void the_user_get_an_message(String string) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(string, pca.getConfirmationRemoved());
	}

}
