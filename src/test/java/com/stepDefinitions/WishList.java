package com.stepDefinitions;

import com.actions.LoginPageAction;
import com.actions.WishListActions;
import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.utils.ConfigReader;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class WishList {

    private static final Logger logger = LogManager.getLogger(WishList.class);

    LoginPageAction lpa = new LoginPageAction();
    LaunchPages lp = new LaunchPages(DriverClass.getDriver());
    WishListActions wla = new WishListActions();

    // ========================= BACKGROUND =========================

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {

        String url = ConfigReader.getProperties().getProperty("url");
        lp.launchApplication(url);
        logger.info("Launched URL: " + url);

        Assert.assertTrue(
                DriverClass.getDriver().getCurrentUrl().contains("route=common/home"),
                "Home page URL mismatch. Actual URL: " + DriverClass.getDriver().getCurrentUrl()
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
                "Login failed. Expected page heading: [" + expected + "] but got: [" + actual + "]");

        logger.info("Login successful");
    }

    // ========================= SCROLL STEPS =========================

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

    // ========================= ADD PRODUCT STEP =========================

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
                Assert.fail("Unknown product name in feature file: [" + productName + "]. " +
                        "Add a case in WishList step definition and a method in WishListActions.");
        }
        logger.info("Added product to wishlist: " + productName);
    }

    // ========================= TOAST VALIDATION =========================

    // Takes product name as parameter — prevents reading stale toast from previous product
    @Then("a wishlist success notification should be displayed for {string}")
    public void a_wishlist_success_notification_should_be_displayed_for(String productName) {

        String msg = wla.getWishlistSuccessMessage(productName);
        logger.info("Toast for [" + productName + "]: " + msg);

        Assert.assertTrue(
                msg.contains("Success"),
                "Success toast not shown for [" + productName + "]. Actual message: [" + msg + "]"
        );
    }

    // ========================= POPUP LINK =========================

    @And("the user clicks the wishlist link from the notification popup")
    public void the_user_clicks_the_wishlist_link_from_the_notification_popup() {
        wla.clickWishlistLinkFromPopup();
        logger.info("Clicked wishlist link from popup");
    }

    // ========================= REDIRECT VALIDATION =========================

    @Then("the user should be redirected to the {string} page")
    public void the_user_should_be_redirected_to_the_page(String pageName) {

        wla.waitForWishlistPage();

        String actualTitle = wla.getCurrentPageTitle();
        logger.info("Redirected page title: " + actualTitle);

        Assert.assertTrue(
                actualTitle.contains(pageName),
                "Redirect failed. Expected title to contain: [" + pageName + "] but got: [" + actualTitle + "]"
        );
    }

    // ========================= SINGLE PRODUCT VALIDATION =========================

    @And("the wishlist product details should match the selected product")
    public void the_wishlist_product_details_should_match_the_selected_product() {

        String actualName  = wla.getWishlistProductName();
        String actualPrice = wla.getWishlistProductPrice();

        logger.info("Wishlist Product Name:  " + actualName);
        logger.info("Wishlist Product Price: " + actualPrice);

        Assert.assertEquals(actualName, "iMac",
                "Wishlist product name mismatch. Expected: [iMac] but got: [" + actualName + "]");

        Assert.assertFalse(actualPrice.isEmpty(),
                "Wishlist product price is empty for iMac");
    }

    // ========================= MULTI PRODUCT VALIDATION =========================

    @Then("all selected products should be displayed in the wishList page")
    public void all_selected_products_should_be_displayed_in_the_wish_list_page() {

        boolean appleFound = wla.isProductPresentInWishlist("Apple Cinema");
        boolean ipodFound  = wla.isProductPresentInWishlist("iPod Nano");

        logger.info("Apple Cinema found in wishlist: " + appleFound);
        logger.info("iPod Nano found in wishlist:    " + ipodFound);

        Assert.assertTrue(appleFound,
                "Apple Cinema 30 not found in wishlist table. " +
                        "Check if product name in table matches the XPath contains() search.");

        Assert.assertTrue(ipodFound,
                "iPod Nano not found in wishlist table. " +
                        "Check if product name in table matches the XPath contains() search.");

        logger.info("Both products verified in wishlist");
    }
}