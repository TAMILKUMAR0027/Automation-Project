package com.stepDefinitions;

import com.actions.SearchAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchDif {

    SearchAction searchAction = new SearchAction();
    String keywordUsed;

    @When("the user clicks on the search bar on the home page")
    public void theUserClicksOnTheSearchBarOnTheHomePage() {
        searchAction.clickSearchBar();
    }

    // Existing Step (Scenario Outline)
    @And("the user enters {string} and presses Enter")
    public void the_user_enters_and_presses_enter(String keyword) {
        keywordUsed = keyword;
        searchAction.enterKeywordAndPressEnter(keyword);
    }

    // New Step (For Manufacturer Scenario - Excel based step)
    @And("the user enters the product {string} and presses Enter")
    public void the_user_enters_the_product_and_presses_enter(String keyword) {
        keywordUsed = keyword;
        searchAction.enterKeywordAndPressEnter(keyword);
    }

    @Then("the application should display products based on the keyword")
    public void the_application_should_display_products_based_on_the_keyword() {

        boolean status = searchAction.isProductListDisplayed();

        if (!status) {
            Assert.assertTrue(searchAction.isNoProductMessageDisplayed(),
                    "Product list not displayed and no product message also not displayed");
            System.out.println(searchAction.getNoProductMessage());
            return;
        }

        Assert.assertTrue(searchAction.isKeywordPresentInAllResults(keywordUsed),
                "Search results contain products that do not match keyword: " + keywordUsed);
    }

    @Then("the application should display all products")
    public void the_application_should_display_all_products() {
        Assert.assertTrue(searchAction.isProductListDisplayed(), "All products are not displayed!");
    }

    @And("the url should be {string}")
    public void the_url_should_be(String expectedUrl) {
        Assert.assertEquals(searchAction.getCurrentUrl(), expectedUrl);
    }

    @And("the product count should be {int}")
    public void the_product_count_should_be(Integer expectedCount) {
        Assert.assertEquals(searchAction.getProductCount(), expectedCount.intValue(),
                "Product count mismatch for blank search!");
    }

  
        @And("the application should list only the manufacturer products based on the {string}")
    public void the_application_should_list_only_the_manufacturer_products_based_on_the(String keyword) {

        boolean status = searchAction.isProductListDisplayed();

        // Gracefully handle the scenario where no products match the keyword
        if (!status) {
            Assert.assertTrue(searchAction.isNoProductMessageDisplayed(),
                    "Product list not displayed and no product message also not displayed");
            return;
        }

        Assert.assertTrue(searchAction.isKeywordPresentInAllResults(keyword),
                "Manufacturer products mismatch. Non-manufacturer product found for keyword: " + keyword);
    }

}