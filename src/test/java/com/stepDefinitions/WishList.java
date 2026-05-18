package com.stepDefinitions;

import com.actions.LoginPageAction;
import com.actions.WishListActions;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;
import com.utils.CsvDataProvider;
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

        lpa.launchWebUrl();
        lpa.clickMyAccountLink();
        lpa.enterEmailAndPass("testlogin@gmail.com", "testlogin");
        lpa.clickLoginButton();

        String actual   = lpa.LoginSuccessMsg();
        String expected = "My Account";

        Assert.assertEquals(actual, expected,
                "Login failed. Expected: [" + expected + "] but got: [" + actual + "]");

        logger.info("Login successful");
    }

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


    @When("the user selects the {string} product and adds it to the wishlist")
    public void the_user_selects_the_product_and_adds_it_to_the_wishlist(String productName) {

        switch (productName.toLowerCase()) {
            case "imac":
                wla.addIMacToWishlist();
                break;
            case "apple cinema 30":
                wla.addAppleCinemaToWishlist();
                break;
            case "ipod nano":
                wla.addIpodNanoToWishlist();
                break;
            default:
                Assert.fail("Unknown product [" + productName + "]. " +
                        "Add a case in WishList.java and a method in WishListActions.java.");
        }
        logger.info("Added to wishlist: " + productName);
    }


    @Then("a wishlist success notification should be displayed for {string}")
    public void a_wishlist_success_notification_should_be_displayed_for(String productName) {

        String msg = wla.getWishlistSuccessMessage(productName);
        logger.info("Add toast for [" + productName + "]: " + msg);

        Assert.assertTrue(
                msg.contains("Success"),
                "Add success toast not shown for [" + productName + "]. Actual: [" + msg + "]"
        );
    }


    @And("the user clicks the wishlist link from the notification popup")
    public void the_user_clicks_the_wishlist_link_from_the_notification_popup() {
        wla.clickWishlistLinkFromPopup();
        logger.info("Clicked wishlist link from popup");
    }


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


    @And("the wishlist product details should match the selected product")
    public void the_wishlist_product_details_should_match_the_selected_product() {

        // Load expected product from CSV
        Map<String, String> data  = CsvDataProvider.getFirstRow(CSV_PATH, "AddSingleProduct");
        String expectedProduct    = data.get("productName");

        // Get ALL products and prices from the entire wishlist table
        List<String> allProducts  = wla.getAllWishlistProductNames();
        List<String> allPrices    = wla.getAllWishlistProductPrices();

        logger.info("All products in wishlist: " + allProducts);
        logger.info("All prices   in wishlist: " + allPrices);

        // Assert expected product is present anywhere in the table
        boolean productFound = allProducts.stream()
                .anyMatch(p -> p.contains(expectedProduct));

        Assert.assertTrue(productFound,
                "Expected product [" + expectedProduct + "] not found in wishlist table. " +
                        "All products found: " + allProducts
        );

        for (int i = 0; i < allPrices.size(); i++) {
            String price       = allPrices.get(i);
            String productName = i < allProducts.size() ? allProducts.get(i) : "row " + (i + 1);

            Assert.assertFalse(price.isEmpty(),
                    "Price is empty for product [" + productName + "] at row " + (i + 1)
            );
        }

        logger.info("Single product validation passed — [" + expectedProduct + "] found in: " + allProducts);
    }


    @Then("all selected products should be displayed in the wishList page")
    public void all_selected_products_should_be_displayed_in_the_wish_list_page() {

        // Get ALL rows from the wishlist table
        List<String> allProducts = wla.getAllWishlistProductNames();
        List<String> allPrices   = wla.getAllWishlistProductPrices();

        logger.info("All products in wishlist table: " + allProducts);

        // Load expected products from CSV
        List<Map<String, String>> rows = CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct1");
        rows.addAll(CsvDataProvider.getData(CSV_PATH, "AddMultipleProduct2"));

        // Assert each CSV product is present in the table
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

        // Assert every product has a non-empty price
        for (int i = 0; i < allPrices.size(); i++) {
            String price       = allPrices.get(i);
            String productName = i < allProducts.size() ? allProducts.get(i) : "row " + (i + 1);

            Assert.assertFalse(price.isEmpty(),
                    "Price is empty for [" + productName + "] at row " + (i + 1)
            );
        }

        logger.info("Multi-product validation passed. All products and prices verified.");
    }


    @And("the user navigates to the wishlist page via account menu")
    public void the_user_navigates_to_the_wishlist_page_via_account_menu() {
        wla.navigateToWishlistViaAccount();
        logger.info("Navigated to wishlist page via account menu");
    }



    @When("the user removes the product {string} from the wishlist")
    public void the_user_removes_the_product_from_the_wishlist(String productName) {
        System.out.println(wla.isProductPresentInWishlist(productName));

        wla.removeProductFromWishlist(productName);
        logger.info("Clicked remove for product: " + productName);
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