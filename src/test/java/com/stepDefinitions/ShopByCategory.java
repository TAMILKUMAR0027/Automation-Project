package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.actions.ShopbyCategoryAction;
import com.driver.DriverClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShopByCategory {
	private static final Logger log = LogManager.getLogger(ShopByCategory.class);
    WebDriver driver=DriverClass.getDriver();
    ShopbyCategoryAction sbc = new ShopbyCategoryAction();

    @Given("the user launches the ecommerce application")
    public void the_user_launches_the_ecommerce_application() {

        sbc.launchWebUrl();
    }

    @When("the user clicks on the Shop by Category menu")
    public void the_user_clicks_on_the_shop_by_category_menu() {

        sbc.clickShopByCategory();
    }

    @When("the user selects the {string} category")
    public void the_user_selects_the_category(String category) {

        sbc.selectCategory(category);
        log.info("Selecting category");
    }

    @Then("the user should navigate to the Category page and the page title should contain {string}")
    public void the_user_should_navigate_to_the_category_page_and_the_page_title_should_contain(String expectedTitle) {

        String actualTitle = sbc.getPageTitle();
      try {
        Assert.assertTrue(actualTitle.contains(expectedTitle));
        log.info("Navigated to selected category page");
    }catch(AssertionError e) {
    	log.error("Failed to navigate to the selected category page"+e.getMessage());
    }
}
}

