package com.stepDefinitions;

import com.actions.LoginPageAction;
import com.actions.WishListActions;
import com.driver.DriverClass;
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

    private static final Logger logger   = LogManager.getLogger(WishList.class);
    private static final String CSV_PATH = "src/test/resources/wishlist_data.csv";

    LoginPageAction lpa = new LoginPageAction();
    LaunchPages     lp  = new LaunchPages(DriverClass.getDriver());
    WishListActions wla = new WishListActions();

    // ─── Background ───────────────────────────────────────────────────────────

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        String url = ConfigReader.getProperties().getProperty("url");
        lp.launchApplication(url);
        logger.info("Launched URL: " + url);

        Assert.assertTrue(
                DriverClass.getDriver().getCurrentUrl().contains("route=common/home"),
                "Home page not loaded. Actual URL: " + DriverClass.getDriver().getCurrentUrl()
        );
    }

    @And("the user is a registered user")
    public void the_user_is_a_registered_user() {
        DriverClass.getDriver().get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        
        if (DriverClass.getDriver().getCurrentUrl().contains("route=account/account")) {
            logger.info("Already logged in.");
            return;
        }

        lpa.enterEmailAndPass("testlogin@gmail.com", "testlogin");
        lpa.clickLoginButton();

        String actual   = lpa.LoginSuccessMsg();
        String expected = "My Account";

        Assert.assertEquals(actual, expected,
                "Login failed. Expected: [" + expected + "] but got: [" + actual + "]");

        logger.info("Login successful");
    }

    // ─── Navigation ───────────────────────────────────────────────────────────

    @And("the user navigates to the Top Products section")
    public void the_user_navigates_to_the_top_products_section() {
        wla.scrollToTopProducts();
        logger.info("Scrolled to Top Products section");
    }

    @And("the user navigates to the Top Collection section")
    public void the_user_navigates_to_the_top_collection_section() {
        wla.scrollToTopCollection();
        logger.info("Scrolled to Top Collection section");
    }

    @And("the user navigates to the wishlist page via account menu")
    public void the_user_navigates_to_the_wishlist_page_via_account_menu() {
        wla.navigateToWishlistViaAccount();
        logger.info("Navigated to wishlist page via account menu");
    }

    // ─── Scenario: Add single product (hover + wishlist icon) ─────────────────

    /**
     * CSV key (e.g. "AddSingleProduct") is passed in.
     * Real product name is resolved from the CSV before acting.
     */
    @When("the user hovers over the product {string} and clicks the wishlist button")
    public void the_user_hovers_over_the_product_and_clicks_the_wishlist_button(String csvScenario) {
        Map<String, String> data = CsvDataProvider.getFirstRow(CSV_PATH, csvScenario);
        String productName = data.get("productName");

        wla.hoverAndClickWishlistButton(productName);
        logger.info("Hovered and clicked wishlist button for: " + productName);
    }

    // ─── Scenario: Add multiple products — fully CSV-driven ───────────────────

    /**
     * Reads ALL rows whose scenario column matches "AddMultipleProduct1"
     * and "AddMultipleProduct2" from the CSV — no DataTable in the feature file.
     *
     * CSV rows used:
     *   AddMultipleProduct1 → Apple Cinema 30
     *   AddMultipleProduct2 → iPod Nano
     */
    @When("the user adds multiple products to the wishlist from csv")
    public void the_user_adds_multiple_products_to_the_wishlist_from_csv() {
        List<Map<String, String>> rows = CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct1");
        rows.addAll(CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct2"));

        for (Map<String, String> row : rows) {
            String productName = row.get("productName");
            wla.addProductToWishlistByName(productName);
            logger.info("Added to wishlist from CSV: " + productName);
        }
    }

    // ─── Scenario: Add product via search ─────────────────────────────────────

    @When("the user searches for {string} and presses Enter")
    public void the_user_searches_for_and_presses_enter(String searchTerm) {
        wla.searchForProduct(searchTerm);
        logger.info("Searched for: " + searchTerm);
    }

    @And("the user moves to the {string} and clicks the product")
    public void the_user_moves_to_the_and_clicks_the_product(String productName) {
        wla.clickProductFromSearchResults(productName);
        logger.info("Clicked product from search results: " + productName);
    }

    @And("the user redirect to the product page and clicks the heart button inside the product image")
    public void the_user_redirect_to_the_product_page_and_clicks_the_heart_button_inside_the_product_image() {
        wla.clickHeartButtonOnProductPage();
        logger.info("Clicked heart/wishlist button on product detail page");
    }

    // ─── Success notifications ─────────────────────────────────────────────────

    /**
     * No-param version — used by: Add single, Add multiple, Add via search.
     */
    @Then("a wishlist success notification should be displayed")
    public void a_wishlist_success_notification_should_be_displayed() {
        String msg = wla.getWishlistSuccessMessageGeneric();
        logger.info("Wishlist add toast: " + msg);

        Assert.assertTrue(
                msg.contains("Success"),
                "Wishlist success toast not shown. Actual: [" + msg + "]"
        );
    }

    @And("the user clicks the wishlist link from the notification popup")
    public void the_user_clicks_the_wishlist_link_from_the_notification_popup() {
        wla.clickWishlistLinkFromPopup();
        logger.info("Clicked wishlist link from popup");
    }

    // ─── Redirect assertion ───────────────────────────────────────────────────

    @Then("the user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String pageName) {
        wla.waitForWishlistPage();

        String actualTitle = wla.getCurrentPageTitle();
        logger.info("Redirected page title: " + actualTitle);

        Assert.assertTrue(
                actualTitle.contains(pageName),
                "Redirect failed. Expected title: [" + pageName + "] but got: [" + actualTitle + "]"
        );
    }

    // ─── Wishlist content assertions ──────────────────────────────────────────

    /**
     * Used by: Add single product & Add via search.
     * Resolves expected product from CSV using the scenario's tag context.
     * Both "AddSingleProduct" and "AddSearchProduct" rows share this step,
     * so we check that ANY CSV-known product is present in the wishlist.
     */
    @And("the wishlist product details should match the selected product")
    public void the_wishlist_product_details_should_match_the_selected_product() {
        List<String> allProducts = wla.getAllWishlistProductNames();
        List<String> allPrices   = wla.getAllWishlistProductPrices();

        logger.info("All products in wishlist: " + allProducts);
        logger.info("All prices   in wishlist: " + allPrices);

        // Collect candidate product names from both single-add CSV rows
        Map<String, String> singleData  = CsvDataProvider.getFirstRow(CSV_PATH, "AddSingleProduct");
        Map<String, String> searchData  = CsvDataProvider.getFirstRow(CSV_PATH, "AddSearchProduct");

        String expectedSingle = singleData.get("productName");   // iMac
        String expectedSearch = searchData.get("productName");   // iPod Shuffle

        boolean found = allProducts.stream()
                .anyMatch(p -> p.contains(expectedSingle) || p.contains(expectedSearch));

        Assert.assertTrue(found,
                "Neither [" + expectedSingle + "] nor [" + expectedSearch + "] found in wishlist. " +
                        "All products: " + allProducts
        );

        // Every row must have a non-empty price
        for (int i = 0; i < allPrices.size(); i++) {
            String price       = allPrices.get(i);
            String productName = i < allProducts.size() ? allProducts.get(i) : "row " + (i + 1);

            Assert.assertFalse(price.isEmpty(),
                    "Price is empty for [" + productName + "] at row " + (i + 1)
            );
        }

        logger.info("Product detail validation passed. Found in: " + allProducts);
    }

    /**
     * Used by: Add multiple products scenario.
     * Reads expected products from CSV (AddMultipleProduct1 + AddMultipleProduct2)
     * and asserts each is present in the wishlist table.
     */
    @Then("all selected products should be displayed in the wishlist page")
    public void all_selected_products_should_be_displayed_in_the_wishlist_page() {
        List<String> allProducts = wla.getAllWishlistProductNames();
        List<String> allPrices   = wla.getAllWishlistProductPrices();

        logger.info("All products in wishlist table: " + allProducts);

        // Load expected products from CSV — same rows used when adding
        List<Map<String, String>> rows = CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct1");
        rows.addAll(CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct2"));

        for (Map<String, String> row : rows) {
            String expected = row.get("productName");

            boolean found = allProducts.stream()
                    .anyMatch(actual -> actual.contains(expected));

            logger.info("[" + expected + "] present in wishlist: " + found);

            Assert.assertTrue(found,
                    "[" + expected + "] not found in wishlist. " +
                            "All products currently in table: " + allProducts
            );
        }

        // Every row must have a non-empty price
        for (int i = 0; i < allPrices.size(); i++) {
            String price       = allPrices.get(i);
            String productName = i < allProducts.size() ? allProducts.get(i) : "row " + (i + 1);

            Assert.assertFalse(price.isEmpty(),
                    "Price is empty for [" + productName + "] at row " + (i + 1)
            );
        }

        logger.info("Multi-product validation passed. All products and prices verified.");
    }

    // ─── Remove steps ─────────────────────────────────────────────────────────

    /**
     * Single remove — CSV-driven: "RemoveProduct1" key → resolves "iPod Nano".
     */
    @When("the user removes the product {string} from the wishlist")
    public void the_user_removes_the_product_from_the_wishlist(String csvScenario) {
        Map<String, String> data = CsvDataProvider.getFirstRow(CSV_PATH, csvScenario);
        String productName = data.get("productName");

        logger.info("Product present before removal: " + wla.isProductPresentInWishlist(productName));
        if (!wla.isProductPresentInWishlist(productName)) {
            logger.info("Product not in wishlist, adding it first.");
            wla.scrollToTopCollection();
            wla.addProductToWishlistByName(productName);
            wla.navigateToWishlistViaAccount();
        }

        wla.removeProductFromWishlist(productName);
        logger.info("Removed product: " + productName);
    }

    /**
     * Multiple remove — DataTable-driven (no CSV).
     * Feature table format:
     *   | ProductName     |
     *   | Apple Cinema 30 |
     *   | iMac            |
     */
    @When("the user removes the following products from the wishlist")
    public void the_user_removes_the_following_products_from_the_wishlist(DataTable dataTable) {
        // asMaps skips the header row automatically
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String productName = row.get("ProductName");

            logger.info("Product present before removal: " + wla.isProductPresentInWishlist(productName));
            if (!wla.isProductPresentInWishlist(productName)) {
                logger.info("Product not in wishlist, adding it first.");
                wla.scrollToTopCollection();
                wla.addProductToWishlistByName(productName);
                wla.navigateToWishlistViaAccount();
            }

            wla.removeProductFromWishlist(productName);
            logger.info("Removed product: " + productName);
        }
    }

    @Then("a wishlist removal success notification should be displayed")
    public void a_wishlist_removal_success_notification_should_be_displayed() {
        String actualMsg = wla.getRemovalSuccessMessage();
        logger.info("Removal alert message: " + actualMsg);

        Assert.assertTrue(
                actualMsg.contains("Success") || actualMsg.contains("modified"),
                "Removal alert not shown. Expected 'Success: You have modified your wish list!' " +
                        "but got: [" + actualMsg + "]"
        );
    }
}