package com.actions;

import com.driver.DriverClass;
import com.pages.SearchPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchAction {

    BaseAction baseAction = new BaseAction();
    WebDriver driver = DriverClass.getDriver();
    SearchPages searchPages = new SearchPages(driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public void clickSearchBar() {
        wait.until(d -> searchPages.SearchBar.isDisplayed() && searchPages.SearchBar.isEnabled());
        baseAction.click(searchPages.SearchBar);
    }

    public void enterKeywordAndPressEnter(String keyword) {
        wait.until(d -> searchPages.SearchBar.isDisplayed());
        baseAction.sendKeysWithEnter(searchPages.SearchBar, keyword);
    }

    public boolean isProductListDisplayed() {

        wait.until(d -> {
            if (!searchPages.result.isEmpty()) {
                return true;
            }
            try {
                return searchPages.resulterr.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });

        return !searchPages.result.isEmpty();
    }

    public int getProductCount() {
        wait.until(d -> {
            if (!searchPages.result.isEmpty()) {
                return true;
            }
            try {
                return searchPages.resulterr.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });

        return searchPages.result.size();
    }

    public boolean isKeywordPresentInAllResults(String keyword) {

        wait.until(d -> {
            if (!searchPages.result.isEmpty()) {
                return true;
            }
            try {
                return searchPages.resulterr.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });

        if (searchPages.result.isEmpty()) {
            return false;
        }

        for (WebElement product : searchPages.result) {
            String productName = product.getText().trim().toLowerCase();

            if (!productName.contains(keyword.trim().toLowerCase())) {
                System.out.println("Mismatch product found: " + productName);
                return false;
            }
        }
        return true;
    }

    public String getNoProductMessage() {
        wait.until(d -> searchPages.resulterr.isDisplayed());
        return searchPages.resulterr.getText();
    }

    public boolean isNoProductMessageDisplayed() {
        try {
            return searchPages.resulterr.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public void selectManufacturer(String expectedManufacturer) {

        wait.until(d -> searchPages.manufacturer.isDisplayed() && searchPages.manufacturer.isEnabled());

        String actualManufacturer = searchPages.manufacturer.getText().trim();

        if (!actualManufacturer.equalsIgnoreCase(expectedManufacturer.trim())) {
            throw new RuntimeException("Manufacturer mismatch! Expected: " + expectedManufacturer
                    + " but found: " + actualManufacturer);
        }

        System.out.println(actualManufacturer);
    }
}