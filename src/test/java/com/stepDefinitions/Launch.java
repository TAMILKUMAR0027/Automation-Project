package com.stepDefinitions;

import com.pages.LaunchPages;
import com.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Launch {

    private static final Logger logger = LogManager.getLogger(Launch.class);
    LaunchPages lp = new LaunchPages();

    @Given("User navigates to application url")
    public void user_navigates_to_application_url() {
        try {
            String url = ConfigReader.getProperties().getProperty("url");
            logger.info("Launching application URL: " + url);

            lp.launchApplication(url);

        } catch (Exception e) {
            logger.error("Failed to launch application URL", e);
            throw e;
        }
    }

    @Then("User should be on correct url")
    public void user_should_be_to_correct_url() {
        try {
            String expectedUrl = ConfigReader.getProperties().getProperty("url");
            String actualUrl = lp.getCurrentUrl();

            logger.info("Expected URL: " + expectedUrl);
            logger.info("Actual URL: " + actualUrl);

            Assert.assertEquals(actualUrl, expectedUrl);

        } catch (AssertionError e) {
            logger.error("URL Validation Failed", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while validating URL", e);
            throw e;
        }
    }

    @Then("User should see correct page title")
    public void user_should_see_correct_page_title() {
        try {
            String actualTitle = lp.getPageTitle();
            logger.info("Page Title: " + actualTitle);

            Assert.assertTrue(actualTitle.contains("Your Store"),
                    "Page title mismatch. Actual Title: " + actualTitle);

        } catch (AssertionError e) {
            logger.error("Title Validation Failed", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while validating Title", e);
            throw e;
        }
    }

    @Then("User should see the application logo")
    public void user_should_See_the_application_logo() {
        try {
            boolean logo = lp.getlogo();
            logger.info("Logo Displayed Status: " + logo);

            Assert.assertTrue(logo, "Logo is NOT displayed on the homepage");

        } catch (AssertionError e) {
            logger.error("Logo Validation Failed", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while validating Logo", e);
            throw e;
        }
    }
}