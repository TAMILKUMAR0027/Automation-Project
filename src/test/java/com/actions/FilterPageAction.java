package com.actions;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.FilterPage;

public class FilterPageAction extends BaseAction {

    WebDriver driver = DriverClass.getDriver();
    FilterPage fp = new FilterPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    private static Logger log = LogManager.getLogger(FilterPageAction.class);

    public void clickManufacture() {

        try {

            log.info("Clicking manufacturer filter");

            waitForClickable(fp.appleBtn);
            click(fp.appleBtn);

            log.info("Manufacturer filter clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click manufacturer", e);
            throw new RuntimeException("Failed to click manufacturer", e);
        }
    }

    public void clickProduct() {

        try {

            log.info("Clicking iPod Touch product");

            waitForClickable(fp.iPodTouchProduct);
            click(fp.iPodTouchProduct);

            log.info("Product clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click product", e);
            throw new RuntimeException("Failed to click product", e);
        }
    }

    public void selectDropdownByVisibleText(String value) {

        try {

            log.info("Selecting dropdown value : " + value);

            waitForClickable(fp.dropdownBtn);

            Select select = new Select(fp.dropdownBtn);
            select.selectByVisibleText(value);

            log.info("Dropdown value selected successfully");

        } catch (Exception e) {

            log.error("Dropdown selection failed", e);
            throw new RuntimeException("Dropdown selection failed", e);
        }
    }

    public List<String> storeAllProducts() {

        try {

            log.info("Fetching all product names");

            wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));

            List<String> products = fp.getProductNames();

            log.info("Products fetched successfully");

            return products;

        } catch (Exception e) {

            log.error("Failed to fetch product names", e);
            throw new RuntimeException("Failed to fetch product names", e);
        }
    }

    public int getDisplayedProductCount() {

        try {

            log.info("Getting displayed product count");

            wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));

            int count = fp.allProducts.size();

            log.info("Displayed product count : " + count);

            return count;

        } catch (Exception e) {

            log.error("Failed to get product count", e);
            throw new RuntimeException("Failed to get product count", e);
        }
    }

    public void clickAvailability() {

        try {

            log.info("Clicking availability filter");

            waitForClickable(fp.inStockAvailabilityOption);
            click(fp.inStockAvailabilityOption);

            log.info("Availability filter clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click availability filter", e);
            throw new RuntimeException("Failed to click availability filter", e);
        }
    }

    public void clickCanonProduct() {

        try {

            log.info("Clicking Canon product");

            waitForClickable(fp.canonProduct);
            click(fp.canonProduct);

            log.info("Canon product clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click Canon product", e);
            throw new RuntimeException("Failed to click Canon product", e);
        }
    }

    public void clickOutofStockOption() {

        try {

            log.info("Clicking Out Of Stock filter");

            waitForClickable(fp.OutOfStockAvailabilityOption);
            click(fp.OutOfStockAvailabilityOption);

            log.info("Out Of Stock filter clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click Out Of Stock filter", e);
            throw new RuntimeException("Failed to click Out Of Stock filter", e);
        }
    }

    public void clickHTCTouchHD() {

        try {

            log.info("Clicking HTC Touch HD product");

            waitForClickable(fp.HTCTouchHDProduct);
            click(fp.HTCTouchHDProduct);

            log.info("HTC Touch HD clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click HTC Touch HD", e);
            throw new RuntimeException("Failed to click HTC Touch HD", e);
        }
    }

    public void clickAvailabilitys() {

        try {

            log.info("Clicking in stock filter");

            waitForClickable(fp.inStockFilter);
            click(fp.inStockFilter);

            log.info("In stock filter clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click in stock filter", e);
            throw new RuntimeException("Failed to click in stock filter", e);
        }
    }

    public void clickHp() {

        try {

            log.info("Clicking HP product");

            waitForClickable(fp.hpProduct);
            click(fp.hpProduct);

            log.info("HP product clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click HP product", e);
            throw new RuntimeException("Failed to click HP product", e);
        }
    }

    public void moveSlider() {

        try {

            log.info("Moving slider");

            waitForVisibility(fp.slider);

            Actions act = new Actions(driver);
            act.dragAndDropBy(fp.slider, -10, 0).perform();

            log.info("Slider moved successfully");

        } catch (Exception e) {

            log.error("Failed to move slider", e);
            throw new RuntimeException("Failed to move slider", e);
        }
    }

    public String getValue() {

        try {

            log.info("Getting slider value");

            String value = wait.until(ExpectedConditions.visibilityOf(fp.input))
                    .getAttribute("value");

            log.info("Slider value : " + value);

            return value;

        } catch (Exception e) {

            log.error("Failed to get slider value", e);
            throw new RuntimeException("Failed to get slider value", e);
        }
    }

    public List<String> getItems() {

        try {

            log.info("Fetching items list");

            List<String> items = fp.getProductNames();

            log.info("Items fetched successfully");

            return items;

        } catch (Exception e) {

            log.error("Failed to fetch items", e);
            throw new RuntimeException("Failed to fetch items", e);
        }
    }

    public String getSoftwareTitle() {

        try {

            log.info("Getting software title");

            waitForVisibility(fp.softwaretTitle);

            String title = getText(fp.softwaretTitle);

            log.info("Software title fetched successfully");

            return title;

        } catch (Exception e) {

            log.error("Failed to get software title", e);
            throw new RuntimeException("Failed to get software title", e);
        }
    }
}