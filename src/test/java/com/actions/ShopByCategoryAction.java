package com.actions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ShopByCategoryPage;

public class ShopByCategoryAction {

    private static final Logger log = LogManager.getLogger(ShopByCategoryAction.class);

    WebDriver driver = DriverClass.getDriver();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    ShopByCategoryPage sbcp = new ShopByCategoryPage(driver);

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void launchWebUrl() {

        try {

            driver.get("https://ecommerce-playground.lambdatest.io/#mz-component-1626147655");

            driver.manage().window().maximize();

            log.info("Application launched successfully");

        } catch (Exception e) {

            log.error("Failed to launch application : " + e.getMessage());
        }
    }

    public void clickShopByCategory() {

        try {

            wait.until(ExpectedConditions.visibilityOf(sbcp.shopByCategoryMenu));

            wait.until(ExpectedConditions.elementToBeClickable(sbcp.shopByCategoryMenu));

            js.executeScript("arguments[0].scrollIntoView(true);", sbcp.shopByCategoryMenu);

            js.executeScript("arguments[0].click();", sbcp.shopByCategoryMenu);

            log.info("Clicked Shop By Category Menu");

        } catch (Exception e) {

            log.error("Unable to click Shop By Category Menu : " + e.getMessage());
        }
    }

    public void selectCategory(String category) {

        try {

            // -----------------------------------------------------------------
            // FIX 2: The original condition checked for "Desktops & Monitors"
            // (with ampersand) but the feature file passes "Desktops and
            // Monitors" (with "and"). The condition now matches the exact
            // string the Scenario Outline sends in at runtime.
            // -----------------------------------------------------------------

            if (category.equalsIgnoreCase("Desktops and Monitors")) {

                wait.until(ExpectedConditions.visibilityOf(sbcp.desktopsCategory));

                js.executeScript("arguments[0].click();", sbcp.desktopsCategory);

                log.info("Selected Desktops & Monitors");

            } else if (category.equalsIgnoreCase("Web Cameras")) {

                wait.until(ExpectedConditions.visibilityOf(sbcp.cameras));

                js.executeScript("arguments[0].click();", sbcp.cameras);

                log.info("Selected Web Cameras");

            } else if (category.equalsIgnoreCase("Phone, Tablets & Ipod")) {

                wait.until(ExpectedConditions.visibilityOf(sbcp.tablets));

                js.executeScript("arguments[0].click();", sbcp.tablets);

                log.info("Selected Phone, Tablets & Ipod");

            } else if (category.equalsIgnoreCase("Laptops & Notebooks")) {

                wait.until(ExpectedConditions.visibilityOf(sbcp.laptops));

                js.executeScript("arguments[0].click();", sbcp.laptops);

                log.info("Selected Laptops & Notebooks");

            } else {

                log.error("Invalid Category Name: " + category);
            }

        } catch (Exception e) {

            log.error("Failed to select category : " + e.getMessage());
        }
    }

    public String getPageTitle() {

        String title = "";

        try {

            title = driver.getTitle();

            log.info("Page title fetched successfully");

        } catch (Exception e) {

            log.error("Unable to fetch page title : " + e.getMessage());
        }

        return title;
    }
}
