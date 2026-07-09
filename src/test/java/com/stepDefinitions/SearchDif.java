package com.stepDefinitions;

import com.actions.SearchAction;
import com.exceptions.ExceptionHandling;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class SearchDif {

    private static final Logger logger = LogManager.getLogger(SearchDif.class);

    private final SearchAction searchAction = new SearchAction();

    private String keywordUsed;

    @When("the user clicks on the search bar on the home page")
    public void the_user_clicks_on_the_search_bar_on_the_home_page() {

        ExceptionHandling.safely("Click search bar on home page", () -> {
            searchAction.clickSearchBar();
            logger.info("Search bar clicked on home page.");
        });
    }

    @And("the user enters {string} and presses Enter")
    public void the_user_enters_and_presses_enter(String keyword) {

        Assert.assertNotNull(keyword, "[STEP ERROR] Keyword is null.");
        Assert.assertFalse(keyword.trim().isEmpty(), "[STEP ERROR] Keyword is blank.");

        keywordUsed = keyword;

        ExceptionHandling.safely("Enter keyword and press Enter: " + keyword, () -> {
            searchAction.enterKeywordAndPressEnter(keyword);
            logger.info("Search submitted with keyword: '{}'", keyword);
        });
    }

    @And("the user enters the product {string} and presses Enter")
    public void the_user_enters_the_product_and_presses_enter(String keyword) {

        Assert.assertNotNull(keyword, "[STEP ERROR] Product keyword is null.");
        Assert.assertFalse(keyword.trim().isEmpty(), "[STEP ERROR] Product keyword is blank.");

        keywordUsed = keyword;

        ExceptionHandling.safely("Enter product keyword and press Enter: " + keyword, () -> {
            searchAction.enterKeywordAndPressEnter(keyword);
            logger.info("Product search submitted with keyword: '{}'", keyword);
        });
    }

    @Then("the application should display products based on the keyword")
    public void the_application_should_display_products_based_on_the_keyword() {

        Boolean productsFound = ExceptionHandling.safeGet(
                "Check product list displayed",
                searchAction::isProductListDisplayed
        );

        logger.info("Product list displayed for keyword '{}': {}", keywordUsed, productsFound);

        if (!productsFound) {

            Boolean noProductMessageDisplayed = ExceptionHandling.safeGet(
                    "Check no product message displayed",
                    searchAction::isNoProductMessageDisplayed
            );

            Assert.assertTrue(
                    noProductMessageDisplayed,
                    String.format(
                            "[SEARCH RESULT] No products and no no-results message found.%nKeyword searched: '%s'",
                            keywordUsed
                    )
            );

            String noResultMsg = ExceptionHandling.safeGet(
                    "Get no product message",
                    searchAction::getNoProductMessage
            );

            Assert.assertFalse(
                    noResultMsg == null || noResultMsg.trim().isEmpty(),
                    "[SEARCH RESULT] No-results message is empty."
            );

            logger.info("No-results message shown for keyword '{}': '{}'", keywordUsed, noResultMsg);
            return;
        }

        Integer count = ExceptionHandling.safeGet(
                "Get product count",
                searchAction::getProductCount
        );

        Assert.assertTrue(
                count > 0,
                String.format(
                        "[SEARCH RESULT] Product list displayed but count is zero.%nKeyword searched: '%s'",
                        keywordUsed
                )
        );

        logger.info("Search results verified. Product count: {}", count);
    }

    @Then("the application should display products matching the keyword in their name")
    public void the_application_should_display_products_matching_the_keyword_in_their_name() {

        Boolean productsDisplayed = ExceptionHandling.safeGet(
                "Check product list displayed",
                searchAction::isProductListDisplayed
        );

        Assert.assertTrue(
                productsDisplayed,
                String.format("[SEARCH RESULT] No products found for keyword '%s'", keywordUsed)
        );

        Boolean keywordMatched = ExceptionHandling.safeGet(
                "Validate keyword in all product names",
                () -> searchAction.isKeywordPresentInAllResults(keywordUsed)
        );

        Assert.assertTrue(
                keywordMatched,
                String.format(
                        "[SEARCH RESULT] One or more products do not match keyword.%nKeyword searched: '%s'%nProduct count: %d",
                        keywordUsed,
                        searchAction.getProductCount()
                )
        );

        logger.info(
                "All products contain keyword '{}'. Count: {}",
                keywordUsed,
                searchAction.getProductCount()
        );
    }

    @Then("the application should display all products")
    public void the_application_should_display_all_products() {

        Boolean productsDisplayed = ExceptionHandling.safeGet(
                "Check all products displayed",
                searchAction::isProductListDisplayed
        );

        Assert.assertTrue(
                productsDisplayed,
                String.format("[SEARCH RESULT] Product grid is empty.%nKeyword searched: '%s'", keywordUsed)
        );

        Integer count = ExceptionHandling.safeGet(
                "Get all product count",
                searchAction::getProductCount
        );

        Assert.assertTrue(
                count > 0,
                String.format("[SEARCH RESULT] Product count is zero.%nKeyword searched: '%s'", keywordUsed)
        );

        logger.info("All-products view verified. Total products: {}", count);
    }

    @And("the url should be {string}")
    public void the_url_should_be(String expectedUrl) {

        String actualUrl = ExceptionHandling.safeGet(
                "Get current URL",
                searchAction::getCurrentUrl
        );

        logger.info("Current URL: {}", actualUrl);

        Assert.assertNotNull(actualUrl, "[URL ASSERT] Current URL is null.");

        Assert.assertFalse(actualUrl.trim().isEmpty(), "[URL ASSERT] Current URL is empty.");

        Assert.assertEquals(
                actualUrl,
                expectedUrl,
                String.format(
                        "[URL ASSERT] URL mismatch.%nExpected URL: '%s'%nActual URL: '%s'",
                        expectedUrl,
                        actualUrl
                )
        );

        logger.info("URL verified successfully: {}", actualUrl);
    }

    @And("the product count should be {int}")
    public void the_product_count_should_be(Integer expectedCount) {

        Integer actualCount = ExceptionHandling.safeGet(
                "Get product count",
                searchAction::getProductCount
        );

        logger.info(
                "Expected Product Count: {} | Actual Product Count: {}",
                expectedCount,
                actualCount
        );

        Assert.assertTrue(
                actualCount > 0,
                String.format("[PRODUCT COUNT] Product grid is empty.%nKeyword searched: '%s'", keywordUsed)
        );

        Assert.assertEquals(
                actualCount.intValue(),
                expectedCount.intValue(),
                String.format(
                        "[PRODUCT COUNT] Product count mismatch.%nExpected: %d%nActual: %d",
                        expectedCount,
                        actualCount
                )
        );

        logger.info("Product count verified successfully.");
    }

    @And("the application should list only the manufacturer products based on the {string}")
    public void the_application_should_list_only_the_manufacturer_products_based_on_the(String keyword) {

        Boolean productsFound = ExceptionHandling.safeGet(
                "Check manufacturer product list displayed",
                searchAction::isProductListDisplayed
        );

        logger.info(
                "Manufacturer validation started for '{}'. Products found: {}",
                keyword,
                productsFound
        );

        if (!productsFound) {

            Boolean noProductMessageDisplayed = ExceptionHandling.safeGet(
                    "Check no manufacturer products message",
                    searchAction::isNoProductMessageDisplayed
            );

            Assert.assertTrue(
                    noProductMessageDisplayed,
                    String.format("[MANUFACTURER FILTER] No products found for manufacturer '%s'", keyword)
            );

            logger.info("No manufacturer products found for '{}'", keyword);
            return;
        }

        ExceptionHandling.safely("Validate manufacturer products for: " + keyword, () -> {
            searchAction.selectManufacturer(keyword);
            logger.info("Manufacturer verified successfully: {}", keyword);
        });
    }
}