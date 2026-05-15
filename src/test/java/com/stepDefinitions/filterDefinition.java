package com.stepDefinitions;

import java.util.List;

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

    LaunchPages lp = new LaunchPages(DriverClass.getDriver());

    FilterPageAction fpa = new FilterPageAction();

    productPageAction ppa = new productPageAction();

    LaunchPageAction lpa = new LaunchPageAction();

    List<String> products;

    @Given("User is on Home page click on the Shop By categories Page and Click any one option")
    public void user_is_on_home_page_click_on_the_shop_by_categories_page_and_click_any_one_option() {

        try {

            String url = ConfigReader.getProperties().getProperty("url");

            lp.launchApplication(url);

        } catch (Exception e) {

            throw e;
        }
    }

    @When("User clicks manufacturer filter any brand element")
    public void user_clicks_manufacturer_filter_any_brand_element() {

        lpa.clickCategories();

        fpa.clickManufacture();
    }

    @When("product should be displayed based on filtered results and Clicks any one product")
    public void product_should_be_displayed_based_on_filtered_results_and_clicks_any_one_product() {

        fpa.clickProduct();
    }

    @Then("check the product brand name in description should be matches the filter")
    public void check_the_product_brand_name_in_description_should_be_matches_the_filter() {

        Assert.assertEquals(ppa.getBrandName(), "Apple");
    }
    	
        @When("User send an option from the show products dropdown")
        public void user_send_an_option_from_the_show_products_dropdown() {
        	lpa.clickCategories();

            fpa.clickManufacture();
          

            fpa.selectDropdownByVisibleText("25");
        }
    
    @When("User stores the displayed products in a list")
    public void user_stores_the_displayed_products_in_a_list() {

        products = fpa.storeAllProducts();

        System.out.println(products);
    }

    @Then("Displayed product count should match the selected dropdown value")
    public void displayed_product_count_should_match_the_selected_dropdown_value() {

        

        Assert.assertEquals(25,fpa.getDisplayedProductCount());
    }
}