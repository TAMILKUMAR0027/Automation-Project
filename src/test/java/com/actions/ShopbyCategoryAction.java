package com.actions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LaunchPages;
import com.pages.ShopByCategoryPage;
import com.utils.ConfigReader;

public class ShopByCategoryAction {

    private static final Logger log =
            LogManager.getLogger(ShopByCategoryAction.class);

    WebDriverWait wait =
            new WebDriverWait(
                    DriverClass.getDriver(),
                    Duration.ofSeconds(20));

    LaunchPages lp =
            new LaunchPages(DriverClass.getDriver());

    ShopByCategoryPage sbcp =
            new ShopByCategoryPage(
                    DriverClass.getDriver());

    public void launchWebUrl() {

        try {

            DriverClass.getDriver().get(
                    ConfigReader.getProperties()
                            .getProperty("url"));

            DriverClass.getDriver()
                    .manage()
                    .window()
                    .maximize();

            DriverClass.getDriver()
                    .manage()
                    .timeouts()
                    .implicitlyWait(
                            Duration.ofSeconds(10));

            log.info(
                    "Application launched successfully");

        } catch (Exception e) {

            log.error(
                    "Failed to launch application : "
                            + e.getMessage());

            throw e;
        }
    }

    public void clickShopByCategory() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(
                            sbcp.shopByCategoryMenu));

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            sbcp.shopByCategoryMenu));

            clickElement(sbcp.shopByCategoryMenu);

            log.info(
                    "Clicked Shop By Category Menu");

        } catch (Exception e) {

            log.error(
                    "Unable to click Shop By Category Menu : "
                            + e.getMessage());

            throw e;
        }
    }

    public void selectCategory(String category) {

        try {

            if (category.equalsIgnoreCase(
                    "Desktops & Monitors")) {

                clickElement(sbcp.desktopsCategory);

                log.info(
                        "Selected Desktops & Monitors");
            }

            else if (category.equalsIgnoreCase(
                    "Web Cameras")) {

                clickElement(sbcp.cameras);

                log.info(
                        "Selected Web Cameras");
            }

            else if (category.equalsIgnoreCase(
                    "Phone, Tablets & Ipod")) {

                clickElement(sbcp.tablets);

                log.info(
                        "Selected Phone, Tablets & Ipod");
            }

            else if (category.equalsIgnoreCase(
                    "Laptops & Notebooks")) {

                clickElement(sbcp.laptops);

                log.info(
                        "Selected Laptops & Notebooks");
            }

        } catch (Exception e) {

            log.error(
                    "Failed to select category : "
                            + e.getMessage());

            throw e;
        }
    }

    public void clickElement(WebElement element) {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(
                            element));

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            element));

            JavascriptExecutor js =
                    (JavascriptExecutor) DriverClass.getDriver();

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element);

            Thread.sleep(1000);

            try {

                element.click();

            } catch (Exception e) {

                js.executeScript(
                        "arguments[0].click();",
                        element);
            }

        } catch (Exception e) {

            log.error(
                    "Unable to click element : "
                            + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    public String getPageTitle() {

        String title = "";

        try {

            title =
                    DriverClass.getDriver().getTitle();

            log.info(
                    "Page title fetched successfully");

        } catch (Exception e) {

            log.error(
                    "Unable to fetch page title : "
                            + e.getMessage());
        }

        return title;
    }
}