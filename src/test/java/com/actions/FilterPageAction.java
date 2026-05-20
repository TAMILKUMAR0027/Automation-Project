package com.actions;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.driver.DriverClass;
import com.pages.FilterPage;

public class FilterPageAction extends BaseAction {

    WebDriver driver = DriverClass.getDriver();
    FilterPage fp = new FilterPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    private static Logger log = LogManager.getLogger(FilterPageAction.class);

    public void clickManufacture() {
        try {
            log.info("Clicking manufacturer filter (Apple)");
            wait.until(ExpectedConditions.elementToBeClickable(fp.appleBtn));
            click(fp.appleBtn);
            log.info("Manufacturer filter clicked successfully");
        } catch (Exception e) {
            log.error("Failed to click manufacturer", e);
            throw new RuntimeException("Failed to click manufacturer", e);
        }
    }

    public void clickProduct() {
        try {
            log.info("Clicking product (iPod Touch)");
            wait.until(ExpectedConditions.elementToBeClickable(fp.iPodTouchProduct));
            click(fp.iPodTouchProduct);
            log.info("Product clicked successfully");
        } catch (Exception e) {
            log.error("Failed to click product", e);
            throw new RuntimeException("Failed to click product", e);
        }
    }

    public void selectDropdownByVisibleText(String value) {
        try {
            log.info("Selecting dropdown value: " + value);
            wait.until(ExpectedConditions.elementToBeClickable(fp.dropdownBtn));
            Select select = new Select(fp.dropdownBtn);
            select.selectByVisibleText(value);
            log.info("Dropdown selected successfully: " + value);
        } catch (Exception e) {
            log.error("Dropdown selection failed", e);
            throw new RuntimeException("Dropdown selection failed", e);
        }
    }

    public List<String> storeAllProducts() {
        try {
            log.info("Fetching all product names from UI");
            wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));
            List<String> products = fp.getProductNames();
            log.info("Total products fetched: " + products.size());
            return products;
        } catch (Exception e) {
            log.error("Failed to fetch product list", e);
            throw new RuntimeException("Failed to fetch product list", e);
        }
    }

    public int getDisplayedProductCount() {
        try {
            log.info("Getting displayed product count");
            wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));
            int count = fp.allProducts.size();
            log.info("Displayed product count: " + count);
            return count;
        } catch (Exception e) {
            log.error("Failed to get product count", e);
            throw new RuntimeException("Failed to get product count", e);
        }
    }

    public void clickAvailability() {
        try {
            log.info("Clicking In-stock filter");
            wait.until(ExpectedConditions.elementToBeClickable(fp.inStockAvailabilityOption));
            click(fp.inStockAvailabilityOption);
            log.info("In-stock filter clicked");
        } catch (Exception e) {
            log.error("Failed to click availability filter", e);
            throw new RuntimeException("Failed to click availability filter", e);
        }
    }

    public void clickCanonProduct() {
        try {
            log.info("Clicking Canon product");
            wait.until(ExpectedConditions.elementToBeClickable(fp.canonProduct));
            click(fp.canonProduct);
            log.info("Canon product clicked");
        } catch (Exception e) {
            log.error("Failed to click Canon product", e);
            throw new RuntimeException("Failed to click Canon product", e);
        }
    }

    public void clickOutofStockOption() {
        try {
            log.info("Clicking Out-of-stock filter");
            wait.until(ExpectedConditions.elementToBeClickable(fp.OutOfStockAvailabilityOption));
            click(fp.OutOfStockAvailabilityOption);
            log.info("Out-of-stock filter clicked");
        } catch (Exception e) {
            log.error("Failed to click out-of-stock filter", e);
            throw new RuntimeException("Failed to click out-of-stock filter", e);
        }
    }

    public void clickHTCTouchHD() {
        try {
            log.info("Clicking HTC Touch HD product");
            wait.until(ExpectedConditions.elementToBeClickable(fp.HTCTouchHDProduct));
            click(fp.HTCTouchHDProduct);
            log.info("HTC product clicked");
        } catch (Exception e) {
            log.error("Failed to click HTC product", e);
            throw new RuntimeException("Failed to click HTC product", e);
        }
    }

    public void clickAvailabilitys() {
        try {
            log.info("Clicking in-stock filter (alternate)");
            wait.until(ExpectedConditions.elementToBeClickable(fp.inStockFilter));
            click(fp.inStockFilter);
            log.info("In-stock filter (alternate) clicked");
        } catch (Exception e) {
            log.error("Failed to click in-stock filter", e);
            throw new RuntimeException("Failed to click in-stock filter", e);
        }
    }

    public void clickHp() {
        try {
            log.info("Clicking HP product");
            wait.until(ExpectedConditions.elementToBeClickable(fp.hpProduct));
            click(fp.hpProduct);
            log.info("HP product clicked");
       } catch (Exception e) {
            log.error("Failed to click HP product", e);
            throw new RuntimeException("Failed to click HP product", e);
        }
    }
    public void moveSlider() {
        try {
            log.info("Moving price slider");
            wait.until(ExpectedConditions.visibilityOf(fp.slider));
            Actions act = new Actions(driver);
            act.dragAndDropBy(fp.slider, -10, 0).perform();
            log.info("Slider moved successfully");
        } catch (Exception e) {
            log.error("Slider movement failed", e);
            throw new RuntimeException("Slider movement failed", e);
        }
    }
    public String getValue() {
        try {
            log.info("Getting slider value");
            String value = wait.until(ExpectedConditions.visibilityOf(fp.input))
                    .getAttribute("value");
            log.info("Slider value: " + value);
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
            log.info("Items fetched successfully: " + items.size());
            return items;
        } catch (Exception e) {
            log.error("Failed to get items", e);
            throw new RuntimeException("Failed to get items", e);
        }
    }

	public String getSoftwareTitle() {
		// TODO Auto-generated method stub
		return fp.softwaretTitle.getText();
	}
}