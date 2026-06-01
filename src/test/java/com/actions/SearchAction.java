package com.actions;

import com.driver.DriverClass;
import com.exceptions.ExceptionHandling;
import com.pages.SearchPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchAction extends BaseAction {

    // ── Page Object & Wait Setup ──────────────────────────────────────────────
    SearchPages searchPages;
    WebDriverWait wait;

    public SearchAction() {
        searchPages = new SearchPages(getDriver());                          // use BaseAction.getDriver()
        wait        = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
    }


    // =========================================================================
    // SEARCH BAR ACTIONS
    // =========================================================================

    /**
     * Clicks the search bar after waiting for page load, visibility, and clickability.
     * Delegates the actual click to BaseAction.click() which re-applies the clickable wait.
     */
    public void clickSearchBar() {
        waitForPageLoad();                                                   // BaseAction: JS readyState
        waitForVisibility(searchPages.SearchBar);                           // BaseAction: visibility wait
        waitForClickable(searchPages.SearchBar);                            // BaseAction: clickable wait
        try {
            click(searchPages.SearchBar);                                   // BaseAction: safe click
        } catch (TimeoutException e) {
            ExceptionHandling.handleTimeout("Search bar clickable", 15, e);
        }
    }

    /**
     * Clears the search bar, types the keyword, and submits with ENTER.
     * Waits for page load and visibility before typing.
     * Uses BaseAction.sendKeys() which internally waits for visibility and clears.
     */
    public void enterKeywordAndPressEnter(String keyword) {
        waitForPageLoad();                                                   // ensure page is stable
        waitForVisibility(searchPages.SearchBar);                           // BaseAction: visibility wait
        try {
            sendKeys(searchPages.SearchBar, keyword);                       // BaseAction: clear + type
            searchPages.SearchBar.sendKeys(Keys.ENTER);
        } catch (TimeoutException e) {
            ExceptionHandling.handleTimeout("Search bar visibility for keyword entry", 15, e);
        }
    }


    // =========================================================================
    // RESULT VALIDATION ACTIONS
    // =========================================================================

    /**
     * Waits until either product result cards OR the "no results" message appears.
     * Returns true if product cards are present, false if only the error is shown.
     */
    public boolean isProductListDisplayed() {
        // Wait for results grid OR no-result message — whichever renders first
        wait.until(driver ->
                !searchPages.result.isEmpty() ||
                        isDisplayed(searchPages.resulterr)                          // BaseAction: safe isDisplayed
        );
        return !searchPages.result.isEmpty();
    }

    /**
     * Returns the count of product cards visible after a search.
     * Waits for the same dual condition as isProductListDisplayed().
     * Returns 0 when the no-result message is shown instead of cards.
     */
    public int getProductCount() {
        // Wait for results grid OR no-result message
        wait.until(driver ->
                !searchPages.result.isEmpty() ||
                        isDisplayed(searchPages.resulterr)                          // BaseAction: safe isDisplayed
        );
        return searchPages.result.size();
    }

    /**
     * Checks that every result card's displayed name contains the searched keyword.
     * Returns false (and logs the offending name) as soon as a mismatch is found.
     * Throws ExceptionHandling.SearchResultException when the result list is empty.
     */
    public boolean isKeywordPresentInAllResults(String keyword) {
        // Wait for results grid OR no-result message
        wait.until(driver ->
                !searchPages.result.isEmpty() ||
                        isDisplayed(searchPages.resulterr)                          // BaseAction: safe isDisplayed
        );

        // Custom exception: a keyword that should match returned zero products
        if (searchPages.result.isEmpty()) {
            throw new ExceptionHandling.SearchResultException(keyword, 0);
        }

        // Iterate and verify each card name contains the keyword (case-insensitive)
        for (WebElement product : searchPages.result) {
            String name = getText(product).trim().toLowerCase();            // BaseAction: getText()

            if (!name.contains(keyword.toLowerCase().trim())) {
                System.out.println("Mismatch product: " + name);
                return false;
            }
        }
        return true;
    }


    // =========================================================================
    // NO-RESULT MESSAGE HELPERS
    // =========================================================================

    /**
     * Waits for page load and the no-results element visibility,
     * then returns its text via BaseAction.getText().
     */
    public String getNoProductMessage() {
        waitForPageLoad();                                                   // ensure page is stable
        waitForVisibility(searchPages.resulterr);                           // BaseAction: visibility wait
        return getText(searchPages.resulterr);                              // BaseAction: getText()
    }

    /**
     * Returns true if the "no results" message is currently visible on the page.
     * Delegates to BaseAction.isDisplayed() which swallows any element-not-found exception.
     */
    public boolean isNoProductMessageDisplayed() {
        return isDisplayed(searchPages.resulterr);                          // BaseAction: safe isDisplayed
    }


    // =========================================================================
    // URL HELPER
    // =========================================================================

    /** Returns the current browser URL — used in URL-based step assertions. */
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();                                  // BaseAction: getDriver()
    }


    // =========================================================================
    // MANUFACTURER CHECK
    // =========================================================================

    /**
     * Reads the manufacturer label and validates it against the expected value (case-insensitive).
     * Waits for page load and visibility before reading.
     * Throws ExceptionHandling.ManufacturerMismatchException instead of raw RuntimeException.
     */
    public void selectManufacturer(String expectedManufacturer) {
        waitForPageLoad();                                                   // ensure page is stable
        waitForVisibility(searchPages.manufacturer);                        // BaseAction: visibility wait

        String actual = getText(searchPages.manufacturer).trim();           // BaseAction: getText()

        if (!actual.equalsIgnoreCase(expectedManufacturer.trim())) {
            // Custom exception: manufacturer on page does not match expected value
            throw new ExceptionHandling.ManufacturerMismatchException(
                    expectedManufacturer, actual);
        }

        System.out.println("Manufacturer verified: " + actual);
    }
}