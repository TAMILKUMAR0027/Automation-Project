package com.stepDefinitions;

import com.actions.SearchAction;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class SearchDif {

    // =========================================================================
    // LOGGER & ACTION OBJECT
    // =========================================================================

    private static final Logger logger =
            LogManager.getLogger(SearchDif.class);

    SearchAction searchAction = new SearchAction();

    /**
     * Stores the current keyword used in the scenario.
     */
    private String keywordUsed;


    // =========================================================================
    // BACKGROUND STEPS
    // =========================================================================

    @When("the user clicks on the search bar on the home page")
    public void the_user_clicks_on_the_search_bar_on_the_home_page() {

        searchAction.clickSearchBar();

        logger.info("Search bar clicked on home page.");
    }


    // =========================================================================
    // SEARCH INPUT STEPS
    // =========================================================================

    @And("the user enters {string} and presses Enter")
    public void the_user_enters_and_presses_enter(String keyword) {

        Assert.assertNotNull(
                keyword,
                "[STEP ERROR] Keyword is null."
        );

        keywordUsed = keyword;

        searchAction.enterKeywordAndPressEnter(keyword);

        logger.info(
                "Search submitted with keyword: '{}'",
                keyword
        );
    }


    @And("the user enters the product {string} and presses Enter")
    public void the_user_enters_the_product_and_presses_enter(String keyword) {

        Assert.assertNotNull(
                keyword,
                "[STEP ERROR] Product keyword is null."
        );

        Assert.assertFalse(
                keyword.trim().isEmpty(),
                "[STEP ERROR] Product keyword is blank."
        );

        keywordUsed = keyword;

        searchAction.enterKeywordAndPressEnter(keyword);

        logger.info(
                "Manufacturer search submitted with keyword: '{}'",
                keyword
        );
    }


    // =========================================================================
    // SEARCH RESULT VALIDATION
    // =========================================================================

    @Then("the application should display products based on the keyword")
    public void the_application_should_display_products_based_on_the_keyword() {

        boolean productsFound =
                searchAction.isProductListDisplayed();

        logger.info(
                "Product list displayed for keyword '{}': {}",
                keywordUsed,
                productsFound
        );

        // ---------------------------------------------------------------------
        // NO PRODUCTS FOUND
        // ---------------------------------------------------------------------

        if (!productsFound) {

            Assert.assertTrue(
                    searchAction.isNoProductMessageDisplayed(),

                    String.format(
                            "[SEARCH RESULT] No products and no " +
                                    "'no-results' message found.%n" +
                                    "Keyword searched : '%s'",
                            keywordUsed
                    )
            );

            String noResultMsg =
                    searchAction.getNoProductMessage();

            Assert.assertFalse(
                    noResultMsg == null ||
                            noResultMsg.trim().isEmpty(),

                    "[SEARCH RESULT] No-results message is empty."
            );

            logger.info(
                    "No-results message shown for keyword '{}': '{}'",
                    keywordUsed,
                    noResultMsg
            );

            return;
        }

        // ---------------------------------------------------------------------
        // PRODUCTS FOUND
        // ---------------------------------------------------------------------

        int count =
                searchAction.getProductCount();

        Assert.assertTrue(
                count > 0,

                String.format(
                        "[SEARCH RESULT] Product list displayed " +
                                "but count is zero.%n" +
                                "Keyword searched : '%s'",
                        keywordUsed
                )
        );

        logger.info(
                "Search results verified. Product count: {}",
                count
        );
    }


    // =========================================================================
    // PRODUCT NAME VALIDATION
    // =========================================================================

    @Then("the application should display products matching the keyword in their name")
    public void the_application_should_display_products_matching_the_keyword_in_their_name() {

        Assert.assertTrue(
                searchAction.isProductListDisplayed(),

                String.format(
                        "[SEARCH RESULT] No products found " +
                                "for keyword '%s'",
                        keywordUsed
                )
        );

        Assert.assertTrue(
                searchAction.isKeywordPresentInAllResults(keywordUsed),

                String.format(
                        "[SEARCH RESULT] One or more products " +
                                "do not match keyword.%n" +
                                "Keyword searched : '%s'%n" +
                                "Product count    : %d",
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


    // =========================================================================
    // DISPLAY ALL PRODUCTS
    // =========================================================================

    @Then("the application should display all products")
    public void the_application_should_display_all_products() {

        Assert.assertTrue(
                searchAction.isProductListDisplayed(),

                String.format(
                        "[SEARCH RESULT] Product grid is empty.%n" +
                                "Keyword searched : '%s'",
                        keywordUsed
                )
        );

        int count =
                searchAction.getProductCount();

        Assert.assertTrue(
                count > 0,

                String.format(
                        "[SEARCH RESULT] Product count is zero.%n" +
                                "Keyword searched : '%s'",
                        keywordUsed
                )
        );

        logger.info(
                "All-products view verified. Total products: {}",
                count
        );
    }


    // =========================================================================
    // URL VALIDATION
    // =========================================================================

    @And("the url should be {string}")
    public void the_url_should_be(String expectedUrl) {

        String actualUrl =
                searchAction.getCurrentUrl();

        logger.info(
                "Current URL: {}",
                actualUrl
        );

        Assert.assertNotNull(
                actualUrl,
                "[URL ASSERT] Current URL is null."
        );

        Assert.assertFalse(
                actualUrl.trim().isEmpty(),
                "[URL ASSERT] Current URL is empty."
        );

        Assert.assertEquals(
                actualUrl,
                expectedUrl,

                String.format(
                        "[URL ASSERT] URL mismatch.%n" +
                                "Expected URL : '%s'%n" +
                                "Actual URL   : '%s'",
                        expectedUrl,
                        actualUrl
                )
        );

        logger.info(
                "URL verified successfully: {}",
                actualUrl
        );
    }


    // =========================================================================
    // PRODUCT COUNT VALIDATION
    // =========================================================================

    @And("the product count should be {int}")
    public void the_product_count_should_be(Integer expectedCount) {

        int actualCount =
                searchAction.getProductCount();

        logger.info(
                "Expected Product Count: {} | Actual Product Count: {}",
                expectedCount,
                actualCount
        );

        Assert.assertTrue(
                actualCount > 0,

                String.format(
                        "[PRODUCT COUNT] Product grid is empty.%n" +
                                "Keyword searched : '%s'",
                        keywordUsed
                )
        );

        Assert.assertEquals(
                actualCount,
                expectedCount.intValue(),

                String.format(
                        "[PRODUCT COUNT] Product count mismatch.%n" +
                                "Expected : %d%n" +
                                "Actual   : %d",
                        expectedCount,
                        actualCount
                )
        );

        logger.info(
                "Product count verified successfully."
        );
    }


    // =========================================================================
    // MANUFACTURER VALIDATION
    // =========================================================================

    @And("the application should list only the manufacturer products based on the {string}")
    public void the_application_should_list_only_the_manufacturer_products_based_on_the(
            String keyword) {

        boolean productsFound =
                searchAction.isProductListDisplayed();

        logger.info(
                "Manufacturer validation started for '{}'. Products found: {}",
                keyword,
                productsFound
        );

        // ---------------------------------------------------------------------
        // NO PRODUCTS FOUND
        // ---------------------------------------------------------------------

        if (!productsFound) {

            Assert.assertTrue(
                    searchAction.isNoProductMessageDisplayed(),

                    String.format(
                            "[MANUFACTURER FILTER] No products " +
                                    "found for manufacturer '%s'",
                            keyword
                    )
            );

            logger.info(
                    "No manufacturer products found for '{}'",
                    keyword
            );

            return;
        }

        // ---------------------------------------------------------------------
        // VALIDATE MANUFACTURER LABEL
        // ---------------------------------------------------------------------

        try {

            searchAction.selectManufacturer(keyword);

            logger.info(
                    "Manufacturer verified successfully: {}",
                    keyword
            );

        } catch (Exception e) {

            Assert.fail(
                    String.format(
                            "[MANUFACTURER FILTER] Manufacturer mismatch.%n" +
                                    "Expected : '%s'%n" +
                                    "Reason   : %s",
                            keyword,
                            e.getMessage()
                    )
            );
        }
    }
}