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

        Assert.assertEquals(ppa.getBrandName(), "Apple");
    }

    @When("User selects an option from the show products dropdown")
    public void user_selects_an_option_from_the_show_products_dropdown() {

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

        Assert.assertEquals(fpa.getDisplayedProductCount(), 25);

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

        Assert.assertEquals(ppa.getInstockAvailability(), "In Stock");
    }

    @When("User clicks the in-stock filter option and click one product")
    public void user_clicks_the_in_stock_filter_option_and_click_one_product() {

        fpa.clickAvailability();

        fpa.clickCanonProduct();
    }

    @Then("In-stock products should display availability status in product description")
    public void in_stock_products_should_display_availability_status_in_product_description() {

        Assert.assertEquals(ppa.getInstockAvailability(), "In Stock");

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

        Assert.assertEquals(ppa.getOutStockAvailability(), "Out Of Stock");
    }

}

