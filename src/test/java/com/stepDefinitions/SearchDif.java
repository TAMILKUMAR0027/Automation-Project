package com.stepDefinitions;

import com.actions.SearchAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SearchDif {

    SearchAction searchAction = new SearchAction();

    // Already the statement used to no duplication so blocked this line of code - Prasanna

//    @When("the user clicks on thearch bar on the home page")
//    public void theUserClicksOnThearchBarOnTheHomePage() {
//
//    }

    @When("the user clicks on the search bar on the home page")
    public void theUserClicksOnTheSearchBarOnTheHomePage() {
        searchAction.clickSearchBar();
    }

    @And("the user enters {string} and presses Enter")
    public void the_user_enters_and_presses_enter(String keyword) {
        searchAction.enterKeywordAndPressEnter(keyword);
    }

    @Then("the application should display products based on the keyword")
    public void the_application_should_display_products_based_on_the_keyword() {

        boolean status = searchAction.isProductListDisplayed();

        if (status) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(searchAction.isNoProductMessageDisplayed(),
                    "Product list not displayed and no product message also not displayed");
            System.out.println(searchAction.getNoProductMessage());
        }
    }

}