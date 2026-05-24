package com.actions;

import com.driver.DriverClass;
import com.pages.SearchPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchAction {

    BaseAction baseAction = new BaseAction();
    WebDriver driver = DriverClass.getDriver();
    SearchPages searchPages = new SearchPages(driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // ---------------- CLICK SEARCH ----------------
    public void clickSearchBar() {
        wait.until(ExpectedConditions.visibilityOf(searchPages.SearchBar));
        wait.until(ExpectedConditions.elementToBeClickable(searchPages.SearchBar));

        baseAction.click(searchPages.SearchBar);
    }

    // ---------------- ENTER KEYWORD ----------------
    public void enterKeywordAndPressEnter(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchPages.SearchBar));

        searchPages.SearchBar.clear();
        searchPages.SearchBar.sendKeys(keyword);
        searchPages.SearchBar.sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    // ---------------- RESULT CHECK ----------------
    public boolean isProductListDisplayed() {

        // wait for either results OR no result message
        wait.until(driver ->
                !searchPages.result.isEmpty() ||
                searchPages.resulterr.isDisplayed()
        );

        return !searchPages.result.isEmpty();
    }

    // ---------------- PRODUCT COUNT ----------------
    public int getProductCount() {

        wait.until(driver ->
                !searchPages.result.isEmpty() ||
                searchPages.resulterr.isDisplayed()
        );

        return searchPages.result.size();
    }

    // ---------------- VALIDATE KEYWORD ----------------
    public boolean isKeywordPresentInAllResults(String keyword) {

        wait.until(driver ->
                !searchPages.result.isEmpty() ||
                searchPages.resulterr.isDisplayed()
        );

        if (searchPages.result.isEmpty()) {
            return false;
        }

        for (WebElement product : searchPages.result) {
            String name = product.getText().trim().toLowerCase();

            if (!name.contains(keyword.toLowerCase().trim())) {
                System.out.println("Mismatch product: " + name);
                return false;
            }
        }
        return true;
    }

    // ---------------- NO RESULT MESSAGE ----------------
    public String getNoProductMessage() {
        wait.until(ExpectedConditions.visibilityOf(searchPages.resulterr));
        return searchPages.resulterr.getText();
    }

    public boolean isNoProductMessageDisplayed() {
        try {
            return searchPages.resulterr.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ---------------- URL ----------------
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ---------------- MANUFACTURER CHECK ----------------
    public void selectManufacturer(String expectedManufacturer) {

        wait.until(ExpectedConditions.visibilityOf(searchPages.manufacturer));

        String actual = searchPages.manufacturer.getText().trim();

        if (!actual.equalsIgnoreCase(expectedManufacturer.trim())) {
            throw new RuntimeException(
                    "Manufacturer mismatch! Expected: " + expectedManufacturer +
                    " but found: " + actual
            );
        }

        System.out.println("Manufacturer: " + actual);
    }
}