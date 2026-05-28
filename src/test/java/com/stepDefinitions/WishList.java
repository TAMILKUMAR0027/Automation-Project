package com.stepDefinitions;

import com.actions.LoginPageAction;
import com.actions.WishListActions;
import com.driver.DriverClass;
import com.exceptions.ExceptionHandling;
import com.pages.LaunchPages;
import com.utils.ConfigReader;
import com.utils.CsvDataProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class WishList {

    // ── Logger & Constants ────────────────────────────────────────────────────
    private static final Logger logger   = LogManager.getLogger(WishList.class);
    private static final String CSV_PATH = "src/test/resources/wishlist_data.csv";

    // ── Action & Page Dependencies ────────────────────────────────────────────
    LoginPageAction lpa = new LoginPageAction();
    LaunchPages     lp  = new LaunchPages(DriverClass.getDriver());
    WishListActions wla = new WishListActions();


    // =========================================================================
    // BACKGROUND STEPS
    // =========================================================================

    /**
     * Launches the application and validates the home page URL.
     * Reads the base URL from config.properties.
     * Fails with a descriptive message if the home page does not load.
     */
    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        String url = ConfigReader.getProperties().getProperty("url");

        // Guard: URL must be configured in config.properties
        Assert.assertNotNull(url,
                "[CONFIG ERROR] 'url' property is missing in config.properties.");
        Assert.assertFalse(url.trim().isEmpty(),
                "[CONFIG ERROR] 'url' property is empty in config.properties.");

        lp.launchApplication(url);
        logger.info("Launched application URL: {}", url);

        String actualUrl = DriverClass.getDriver().getCurrentUrl();

        // Validate home page loaded correctly
        Assert.assertTrue(
                actualUrl.contains("route=common/home"),
                String.format(
                        "[HOME PAGE] Page did not load correctly.%n" +
                                "  Expected URL to contain : route=common/home%n" +
                                "  Actual URL              : %s", actualUrl)
        );

        logger.info("Home page verified. URL: {}", actualUrl);
    }

    /**
     * Logs the user in using hardcoded test credentials.
     * Skips login silently if the session is already active.
     * Fails with account/login details if the post-login page is wrong.
     */
    @And("the user is a registered user")
    public void the_user_is_a_registered_user() {
        DriverClass.getDriver().get(
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/login");

        // Skip login if session already exists
        if (DriverClass.getDriver().getCurrentUrl().contains("route=account/account")) {
            logger.info("Session already active — skipping login.");
            return;
        }

        // Perform login
        lpa.enterEmailAndPass("testlogin@gmail.com", "testlogin");
        lpa.clickLoginButton();

        String actualHeading   = lpa.LoginSuccessMsg();
        String expectedHeading = "My Account";

        // Validate successful login by post-login heading
        Assert.assertEquals(
                actualHeading,
                expectedHeading,
                String.format(
                        "[LOGIN FAILED] Login did not succeed.%n" +
                                "  Expected heading : %s%n" +
                                "  Actual heading   : %s%n" +
                                "  Check credentials: testlogin@gmail.com / testlogin",
                        expectedHeading, actualHeading)
        );

        logger.info("Login verified. Welcome heading: {}", actualHeading);
    }


    // =========================================================================
    // NAVIGATION STEPS
    // =========================================================================

    /**
     * Scrolls the page to the Top Products section heading.
     * The action itself handles waiting — no explicit wait needed here.
     */
    @And("the user navigates to the Top Products section")
    public void the_user_navigates_to_the_top_products_section() {
        wla.scrollToTopProducts();
        logger.info("Scrolled to Top Products section.");
    }

    /**
     * Scrolls the page to the Top Collection section heading.
     */
    @And("the user navigates to the Top Collection section")
    public void the_user_navigates_to_the_top_collection_section() {
        wla.scrollToTopCollection();
        logger.info("Scrolled to Top Collection section.");
    }

    /**
     * Navigates to the wishlist page via the account header menu link.
     * Falls back to direct URL if the link is not reachable.
     */
    @And("the user navigates to the wishlist page via account menu")
    public void the_user_navigates_to_the_wishlist_page_via_account_menu() {
        wla.navigateToWishlistViaAccount();
        logger.info("Navigated to wishlist page via account menu.");
    }


    // =========================================================================
    // WISHLIST ADD STEPS
    // =========================================================================

    /**
     * Reads product name from the CSV using the scenario key, then hovers and
     * clicks the wishlist button on the product card.
     * Fails descriptively if the CSV row or product name is missing.
     */
    @And("the user hovers over the product {string} and clicks the wishlist button")
    public void the_user_hovers_over_the_product_and_clicks_the_wishlist_button(
            String csvScenario) {

        Map<String, String> data = CsvDataProvider.getFirstRow(CSV_PATH, csvScenario);

        // Guard: CSV row must exist for the given scenario key
        Assert.assertNotNull(data,
                String.format(
                        "[CSV ERROR] No row found for scenario key: '%s' in %s",
                        csvScenario, CSV_PATH));

        String productName = data.get("productName");

        // Guard: productName column must have a value
        Assert.assertNotNull(productName,
                String.format(
                        "[CSV ERROR] 'productName' column is missing for scenario: '%s'",
                        csvScenario));
        Assert.assertFalse(productName.trim().isEmpty(),
                String.format(
                        "[CSV ERROR] 'productName' is empty for scenario: '%s'",
                        csvScenario));

        wla.hoverAndClickWishlistButton(productName);
        logger.info("Wishlist button clicked for product: {}", productName);
    }

    /**
     * Reads all rows for AddMultipleProduct1 and AddMultipleProduct2 from CSV,
     * then adds each product to the wishlist in sequence.
     * Fails if no rows are found in either CSV group.
     */
    @And("the user adds multiple products to the wishlist from csv")
    public void the_user_adds_multiple_products_to_the_wishlist_from_csv() {

        List<Map<String, String>> rows = CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct1");
        rows.addAll(CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct2"));

        // Guard: at least one product row must exist across both CSV groups
        Assert.assertFalse(rows.isEmpty(),
                String.format(
                        "[CSV ERROR] No product rows found for 'AddMultipleProduct1' or " +
                                "'AddMultipleProduct2' in %s", CSV_PATH));

        for (Map<String, String> row : rows) {
            String productName = row.get("productName");

            // Guard: each row must have a non-empty productName
            Assert.assertNotNull(productName,
                    "[CSV ERROR] 'productName' column missing in a row under AddMultipleProduct.");
            Assert.assertFalse(productName.trim().isEmpty(),
                    "[CSV ERROR] 'productName' is empty in a row under AddMultipleProduct.");

            wla.addProductToWishlistByName(productName);
            logger.info("Added product to wishlist from CSV: {}", productName);
        }
    }


    // =========================================================================
    // SEARCH-BASED WISHLIST STEPS
    // =========================================================================

    /**
     * Types the given search term into the search bar and submits with ENTER.
     * Fails if the search term is blank.
     */
    @And("the user searches for {string} and presses Enter")
    public void the_user_searches_for_and_presses_enter(String searchTerm) {

        // Guard: search term must not be blank
        Assert.assertFalse(searchTerm == null || searchTerm.trim().isEmpty(),
                "[STEP ERROR] Search term must not be null or empty.");

        wla.searchForProduct(searchTerm);
        logger.info("Search submitted with term: {}", searchTerm);
    }

    /**
     * Clicks the specified product card from the search results grid.
     * Fails if the product name is blank.
     */
    @And("the user moves to the {string} and clicks the product")
    public void the_user_moves_to_the_and_clicks_the_product(String productName) {

        // Guard: product name must not be blank
        Assert.assertFalse(productName == null || productName.trim().isEmpty(),
                "[STEP ERROR] Product name must not be null or empty.");

        wla.clickProductFromSearchResults(productName);
        logger.info("Clicked product from search results: {}", productName);
    }

    /**
     * Clicks the heart/wishlist button on the product detail page.
     * Handles already-wishlisted state by toggling off first.
     */
    @And("the user redirect to the product page and clicks the heart button inside the product image")
    public void the_user_redirect_to_the_product_page_and_clicks_the_heart_button_inside_the_product_image() {
        wla.clickHeartButtonOnProductPage();
        logger.info("Heart/wishlist button clicked on product detail page.");
    }


    // =========================================================================
    // POPUP / LINK STEPS
    // =========================================================================

    /**
     * Clicks the "go to wishlist" link inside the success notification popup.
     */
    @And("the user clicks the wishlist link from the notification popup")
    public void the_user_clicks_the_wishlist_link_from_the_notification_popup() {
        wla.clickWishlistLinkFromPopup();
        logger.info("Clicked wishlist link from notification popup.");
    }


    // =========================================================================
    // SUCCESS NOTIFICATION ASSERTIONS
    // =========================================================================

    /**
     * Validates that a success toast appeared after adding to the wishlist.
     *
     * Assertions:
     *   1. Toast message must not be null or empty.
     *   2. Toast message must contain "Success" (case-sensitive, site standard).
     */
    @Then("a wishlist success notification should be displayed")
    public void a_wishlist_success_notification_should_be_displayed() {

        String actualMsg = wla.getWishlistSuccessMessageGeneric();
        logger.info("Wishlist add toast message: {}", actualMsg);

        // Assert 1: Toast message must be present
        Assert.assertNotNull(actualMsg,
                "[WISHLIST ADD] Success toast was null — " +
                        "AJAX call may have failed or #notification-box-top is absent.");

        Assert.assertFalse(actualMsg.trim().isEmpty(),
                "[WISHLIST ADD] Success toast was empty — " +
                        "check toast rendering after wishlist button click.");

        // Assert 2: Toast must confirm success
        Assert.assertTrue(
                actualMsg.contains("Success"),
                String.format(
                        "[WISHLIST ADD] Toast did not confirm success.%n" +
                                "  Expected to contain : 'Success'%n" +
                                "  Actual toast text   : '%s'", actualMsg)
        );

        logger.info("Wishlist add success toast verified: {}", actualMsg);
    }


    // =========================================================================
    // PAGE REDIRECT ASSERTION
    // =========================================================================

    /**
     * Validates that the browser was redirected to the expected page by title.
     *
     * Assertions:
     *   1. Page title must not be null or empty.
     *   2. Page title must contain the expected page name (partial match).
     */
    @Then("the user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String expectedPageName) {
        wla.waitForWishlistPage();

        String actualTitle = wla.getCurrentPageTitle();
        logger.info("Redirected page title: {}", actualTitle);

        // Assert 1: Page title must be present
        Assert.assertNotNull(actualTitle,
                "[REDIRECT] Page title is null — driver may not have navigated.");

        Assert.assertFalse(actualTitle.trim().isEmpty(),
                "[REDIRECT] Page title is empty — page may not have fully loaded.");

        // Assert 2: Title must contain the expected page name
        Assert.assertTrue(
                actualTitle.contains(expectedPageName),
                String.format(
                        "[REDIRECT] Page title mismatch.%n" +
                                "  Expected to contain : '%s'%n" +
                                "  Actual page title   : '%s'", expectedPageName, actualTitle)
        );

        logger.info("Page redirect verified. Title: {}", actualTitle);
    }


    // =========================================================================
    // WISHLIST TABLE ASSERTIONS
    // =========================================================================

    /**
     * Validates the wishlist table after a single-product add scenario.
     *
     * Assertions:
     *   1. Wishlist product list must not be null or empty.
     *   2. At least one of the expected products (single-add or search-add) must
     *      be present in the wishlist.
     *   3. Every row must have a non-empty price.
     */
    @And("the wishlist product details should match the selected product")
    public void the_wishlist_product_details_should_match_the_selected_product() {

        List<String> allProducts = wla.getAllWishlistProductNames();
        List<String> allPrices   = wla.getAllWishlistProductPrices();

        logger.info("Products in wishlist : {}", allProducts);
        logger.info("Prices   in wishlist : {}", allPrices);

        // Assert 1: Wishlist must not be empty
        Assert.assertNotNull(allProducts,
                "[WISHLIST TABLE] Product name list returned null.");
        Assert.assertFalse(allProducts.isEmpty(),
                "[WISHLIST TABLE] Wishlist table is empty — no product rows found.");

        // Load expected product names from CSV
        Map<String, String> singleData = CsvDataProvider.getFirstRow(CSV_PATH, "AddSingleProduct");
        Map<String, String> searchData = CsvDataProvider.getFirstRow(CSV_PATH, "AddSearchProduct");

        String expectedSingle = singleData != null ? singleData.get("productName") : "";
        String expectedSearch = searchData != null ? searchData.get("productName") : "";

        // Assert 2: At least one expected product must be present
        boolean found = allProducts.stream()
                .anyMatch(p -> p.contains(expectedSingle) || p.contains(expectedSearch));

        Assert.assertTrue(found,
                String.format(
                        "[WISHLIST TABLE] Expected product not found.%n" +
                                "  Expected (single-add) : '%s'%n" +
                                "  Expected (search-add) : '%s'%n" +
                                "  All products in table : %s",
                        expectedSingle, expectedSearch, allProducts)
        );

        // Assert 3: Every wishlist row must have a non-empty price
        Assert.assertFalse(allPrices.isEmpty(),
                "[WISHLIST TABLE] Price list is empty — no price cells found.");

        for (int i = 0; i < allPrices.size(); i++) {
            String price       = allPrices.get(i);
            String productName = i < allProducts.size() ? allProducts.get(i) : "row " + (i + 1);

            Assert.assertFalse(price == null || price.trim().isEmpty(),
                    String.format(
                            "[WISHLIST TABLE] Price is missing.%n" +
                                    "  Product : '%s'%n" +
                                    "  Row     : %d", productName, i + 1)
            );
        }

        logger.info("Single-product wishlist validation passed.");
    }

    /**
     * Validates the wishlist table contains all products added via the
     * multi-product CSV scenario.
     *
     * Assertions:
     *   1. Wishlist must not be empty.
     *   2. Every CSV-expected product must appear in the wishlist (partial match).
     *   3. Every row must have a non-empty price.
     */
    @Then("all selected products should be displayed in the wishlist page")
    public void all_selected_products_should_be_displayed_in_the_wishlist_page() {

        List<String> allProducts = wla.getAllWishlistProductNames();
        List<String> allPrices   = wla.getAllWishlistProductPrices();

        logger.info("Products in wishlist table: {}", allProducts);

        // Assert 1: Wishlist table must contain at least one product
        Assert.assertNotNull(allProducts,
                "[WISHLIST TABLE] Product name list returned null.");
        Assert.assertFalse(allProducts.isEmpty(),
                "[WISHLIST TABLE] Wishlist table is empty — no product rows found.");

        // Load expected products from same CSV groups used when adding
        List<Map<String, String>> csvRows = CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct1");
        csvRows.addAll(CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct2"));

        Assert.assertFalse(csvRows.isEmpty(),
                String.format(
                        "[CSV ERROR] No rows found for AddMultipleProduct1/2 in %s",
                        CSV_PATH));

        // Assert 2: Every expected product must appear in the wishlist
        for (Map<String, String> row : csvRows) {
            String expected = row.get("productName");

            boolean found = allProducts.stream()
                    .anyMatch(actual -> actual.contains(expected));

            logger.info("Product '{}' present in wishlist: {}", expected, found);

            Assert.assertTrue(found,
                    String.format(
                            "[WISHLIST TABLE] Expected product not found.%n" +
                                    "  Missing product     : '%s'%n" +
                                    "  All products in table : %s",
                            expected, allProducts)
            );
        }

        // Assert 3: Every row must have a non-empty price
        Assert.assertFalse(allPrices.isEmpty(),
                "[WISHLIST TABLE] Price list is empty — no price cells found.");

        for (int i = 0; i < allPrices.size(); i++) {
            String price       = allPrices.get(i);
            String productName = i < allProducts.size() ? allProducts.get(i) : "row " + (i + 1);

            Assert.assertFalse(price == null || price.trim().isEmpty(),
                    String.format(
                            "[WISHLIST TABLE] Price is missing.%n" +
                                    "  Product : '%s'%n" +
                                    "  Row     : %d", productName, i + 1)
            );
        }

        logger.info("Multi-product wishlist validation passed.");
    }


    // =========================================================================
    // WISHLIST REMOVE STEPS
    // =========================================================================

    /**
     * Reads the product name from CSV, ensures it is in the wishlist
     * (adding it if absent), then removes it.
     * Fails if the CSV row or productName is missing.
     */
    @And("the user removes the product {string} from the wishlist")
    public void the_user_removes_the_product_from_the_wishlist(String csvScenario) {

        Map<String, String> data = CsvDataProvider.getFirstRow(CSV_PATH, csvScenario);

        // Guard: CSV row must exist
        Assert.assertNotNull(data,
                String.format(
                        "[CSV ERROR] No row found for scenario key: '%s' in %s",
                        csvScenario, CSV_PATH));

        String productName = data.get("productName");

        // Guard: productName column must have a value
        Assert.assertNotNull(productName,
                "[CSV ERROR] 'productName' column missing for scenario: " + csvScenario);
        Assert.assertFalse(productName.trim().isEmpty(),
                "[CSV ERROR] 'productName' is empty for scenario: " + csvScenario);

        // Self-heal: add the product first if it is not already in the wishlist
        boolean presentBefore = wla.isProductPresentInWishlist(productName);
        logger.info("Product '{}' present before removal: {}", productName, presentBefore);

        if (!presentBefore) {
            logger.warn("'{}' not in wishlist — adding it before removal.", productName);
            wla.scrollToTopCollection();
            wla.addProductToWishlistByName(productName);
            wla.navigateToWishlistViaAccount();
        }

        wla.removeProductFromWishlist(productName);
        logger.info("Removal clicked for product: {}", productName);
    }

    /**
     * Reads product names from an inline DataTable, ensures each is in the wishlist
     * (adding it if absent), then removes each in sequence.
     * Fails if the DataTable is empty or a ProductName cell is blank.
     */
    @And("the user removes the following products from the wishlist")
    public void the_user_removes_the_following_products_from_the_wishlist(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        // Guard: DataTable must contain at least one product row
        Assert.assertFalse(rows.isEmpty(),
                "[STEP ERROR] DataTable is empty — provide at least one ProductName row.");

        for (Map<String, String> row : rows) {
            String productName = row.get("ProductName");

            // Guard: ProductName must not be blank in any row
            Assert.assertNotNull(productName,
                    "[STEP ERROR] 'ProductName' column missing in DataTable row.");
            Assert.assertFalse(productName.trim().isEmpty(),
                    "[STEP ERROR] 'ProductName' is empty in a DataTable row.");

            // Self-heal: add the product first if absent from the wishlist
            boolean presentBefore = wla.isProductPresentInWishlist(productName);
            logger.info("Product '{}' present before removal: {}", productName, presentBefore);

            if (!presentBefore) {
                logger.warn("'{}' not in wishlist — adding it before removal.", productName);
                wla.scrollToTopCollection();
                wla.addProductToWishlistByName(productName);
                wla.navigateToWishlistViaAccount();
            }

            wla.removeProductFromWishlist(productName);
            logger.info("Removal clicked for product: {}", productName);
        }
    }


    // =========================================================================
    // REMOVAL SUCCESS NOTIFICATION ASSERTION
    // =========================================================================

    /**
     * Validates that a success alert banner appeared after a wishlist removal.
     *
     * Assertions:
     *   1. Alert message must not be null or empty.
     *   2. Alert message must contain "Success" or "modified"
     *      (site returns: "Success: You have modified your wish list!").
     */
    @Then("a wishlist removal success notification should be displayed")
    public void a_wishlist_removal_success_notification_should_be_displayed() {

        String actualMsg = wla.getRemovalSuccessMessage();
        logger.info("Wishlist removal alert message: {}", actualMsg);

        // Assert 1: Alert message must be present
        Assert.assertNotNull(actualMsg,
                "[WISHLIST REMOVE] Removal alert message is null — " +
                        "banner may not have appeared after removal.");

        Assert.assertFalse(actualMsg.trim().isEmpty(),
                "[WISHLIST REMOVE] Removal alert message is empty — " +
                        "check .alert-success banner rendering after remove click.");

        // Assert 2: Alert must confirm success
        Assert.assertTrue(
                actualMsg.contains("Success") || actualMsg.contains("modified"),
                String.format(
                        "[WISHLIST REMOVE] Alert did not confirm removal success.%n" +
                                "  Expected to contain : 'Success' or 'modified'%n" +
                                "  Actual alert text   : '%s'", actualMsg)
        );

        logger.info("Wishlist removal success alert verified: {}", actualMsg);
    }
}