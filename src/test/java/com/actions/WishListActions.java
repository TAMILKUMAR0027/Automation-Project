package com.actions;

import com.exceptions.ExceptionHandling;
import com.pages.WishListPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WishListActions extends BaseAction {

    private static final Logger logger   = LogManager.getLogger(WishListActions.class);
    // ── Page Object & Wait Setup ──────────────────────────────────────────────
    WishListPage wp;
    WebDriverWait wait;

    // Toast captured immediately after a product-detail-page wishlist click.
// The site's toast auto-hides a few seconds after appearing, and by the
// time a later Cucumber step re-queries the DOM it can already be gone.
// Capturing it right after the click and handing it to the next reader
// avoids that race. Null means "nothing captured — fall back to a live wait."
    private volatile String pendingToastMessage = null;

    public WishListActions() {
        wp   = new WishListPage(getDriver());                                // BaseAction: getDriver()
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
    }


// =========================================================================
// PRIVATE UTILITY METHODS
// =========================================================================

    /**
     * Pauses execution for the given milliseconds.
     * Used to let animations, hover transitions, and AJAX responses settle.
     */
    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Checks for any browser JS alert and dismisses it if present.
     * Uses a short 3-second wait so normal flows are not slowed down.
     * Returns the alert text so callers can decide whether it was an error.
     */
    private String dismissAlertIfPresent() {
        try {
            WebDriverWait alertWait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.dismiss();
            System.out.println("Dismissed JS alert: " + text);
            return text;
        } catch (TimeoutException e) {
            // No alert present — normal path
            return null;
        }
    }

    /**
     * Force-reveals a hidden wishlist button via JavaScript style overrides.
     * Needed because hover-triggered buttons are invisible until the card is hovered.
     * Re-finds the element from its locator every call, so no stale references are held.
     * Throws ExceptionHandling.ElementNotInteractableException on any failure.
     */
    private void forceRevealElement(By locator, String productLabel) {
        try {
            WebElement element = getDriver().findElement(locator);          // BaseAction: getDriver()
            ((JavascriptExecutor) getDriver()).executeScript(
                    "var b = arguments[0];" +
                            "b.style.setProperty('display',    'block',   'important');" +
                            "b.style.setProperty('opacity',    '1',       'important');" +
                            "b.style.setProperty('visibility', 'visible', 'important');",
                    element
            );
        } catch (JavascriptException e) {
            // Custom handler: JS could not reveal element — likely detached from DOM
            ExceptionHandling.handleJavascriptException(
                    "forceRevealElement - " + productLabel, e);
        } catch (Exception e) {
            // Custom exception: element unreachable after reveal attempt
            throw new ExceptionHandling.ElementNotInteractableException(
                    "Wishlist button for [" + productLabel + "]",
                    "forceRevealElement — Check XPath in WishListPage.java. Cause: " + e.getMessage()
            );
        }
    }

    /**
     * Core wishlist hover-and-click flow:
     *   1. Scroll the product card into view              (BaseAction.scrollIntoView)
     *   2. Hover over the card to trigger CSS hover state (Actions.moveToElement)
     *   3. Force-reveal the wishlist button via JS        (forceRevealElement)
     *   4. If already wishlisted ('wished' class), toggle off first
     *   5. JS-click the wishlist button                   (BaseAction.jsClick)
     *   6. Fail if an unexpected AJAX alert fires          (ExceptionHandling)
     *
     * Both locators are re-resolved from By each time they're needed, so no
     * WebElement reference is held across DOM updates (no staleness handling needed).
     */
    private void hoverAndClickWishlist(By productCardLocator,
                                       By wishlistBtnLocator,
                                       String productLabel) {

        Actions actions = new Actions(getDriver());                         // BaseAction: getDriver()

        // ── Step 1: Scroll card into viewport ────────────────────────────────
        scrollIntoView(productCardLocator);                                 // BaseAction: scrollIntoView(By)
        pause(500);

        // ── Step 2: Hover to trigger CSS hover state ──────────────────────────
        try {
            WebElement productCard = getDriver().findElement(productCardLocator);
            actions.moveToElement(productCard).perform();
        } catch (MoveTargetOutOfBoundsException e) {
            // Custom handler: card outside scrollable viewport
            ExceptionHandling.handleMoveTargetOutOfBounds(
                    productLabel + " product card", e);
        }
        pause(700);

        // ── Step 3: Force-reveal the wishlist button ──────────────────────────
        forceRevealElement(wishlistBtnLocator, productLabel);

        // ── Step 4: Toggle off already-wishlisted state ────────────────────────
        // NOTE: this site does NOT add a "wished" class to the button. It only
        // flips the title attribute ("Add to Wish List" <-> "Remove") and swaps
        // the icon between far fa-heart (outline) and fas fa-heart (solid),
        // while onclick always calls wishlist.add(...) regardless of state.
        // Checking button class here never detects the already-added state.
        try {
            WebElement wishlistBtn = getDriver().findElement(wishlistBtnLocator);
            String btnTitle = wishlistBtn.getAttribute("title");
            boolean alreadyWishlisted = btnTitle != null && btnTitle.trim().equalsIgnoreCase("Remove");
            if (alreadyWishlisted) {
                System.out.println("[" + productLabel + "] Already wishlisted — removing first...");
                jsClick(wishlistBtnLocator);                                // BaseAction: jsClick(By)
                pause(1200);
                dismissAlertIfPresent();
                pause(500);
                WebElement productCardAgain = getDriver().findElement(productCardLocator);
                actions.moveToElement(productCardAgain).perform();
                pause(700);
                forceRevealElement(wishlistBtnLocator, productLabel);
                pause(300);
            }
        } catch (Exception e) {
            // Non-fatal: log and proceed; main click below will still run
            System.out.println("[" + productLabel + "] Could not check wished state: " + e.getMessage());
        }

        // ── Step 5: Click the wishlist button ────────────────────────────────
        try {
            jsClick(wishlistBtnLocator);                                    // BaseAction: jsClick(By)
            System.out.println("[" + productLabel + "] Wishlist button clicked successfully");
        } catch (JavascriptException e) {
            // Custom handler: JS click failed — button may have disappeared
            ExceptionHandling.handleJavascriptException(
                    "Wishlist button click - " + productLabel, e);
        }

        // ── Step 6: Guard against AJAX error alerts ───────────────────────────
        String alertText = dismissAlertIfPresent();
        if (alertText != null) {
            // Custom exception: unexpected JS alert after AJAX call
            throw new ExceptionHandling.UnexpectedJsAlertException(
                    productLabel + " wishlist click", alertText);
        }
    }

    /**
     * Navigates back to the homepage by clicking the site logo.
     * Falls back to an alternate logo locator if the primary is not clickable.
     * Non-fatal — logs a warning and continues if neither logo is reachable.
     */
    private void clickHomeLogo() {
        try {
            waitForClickable(wp.homeLogo);                                  // BaseAction: waitForClickable(By)
            click(wp.homeLogo);                                             // BaseAction: click(By)
        } catch (TimeoutException e) {
            // Primary logo not clickable — try alternate locator
            try {
                waitForClickable(wp.homeLogoAlt);                           // BaseAction: waitForClickable(By)
                click(wp.homeLogoAlt);                                      // BaseAction: click(By)
            } catch (TimeoutException ex) {
                // Neither logo reachable — non-fatal, log and continue
                System.out.println("Home logo not clickable, continuing...");
            }
        }
    }


// =========================================================================
// SCROLL / NAVIGATION ACTIONS
// =========================================================================

    /**
     * Navigates home and scrolls to the "Top Products" section.
     * Waits for page load and heading visibility before scrolling.
     */
    public void scrollToTopProducts() {
        clickHomeLogo();
        waitForPageLoad();                                                   // BaseAction: JS readyState
        waitForVisibility(wp.topProductsHeading);                           // BaseAction: visibility wait(By)
        scrollIntoView(wp.topProductsHeading);                             // BaseAction: scrollIntoView(By)
        System.out.println("Scrolled to Top Products section");
    }

    /**
     * Navigates home and scrolls to the "Top Collection" section.
     * Waits for page load and heading visibility before scrolling.
     */
    public void scrollToTopCollection() {
        clickHomeLogo();
        waitForPageLoad();                                                   // BaseAction: JS readyState
        waitForVisibility(wp.topCollectionHeading);                         // BaseAction: visibility wait(By)
        scrollIntoView(wp.topCollectionHeading);                           // BaseAction: scrollIntoView(By)
        System.out.println("Scrolled to Top Collection section");
    }

    /**
     * Navigates to the wishlist page via the account header link.
     * Falls back to a direct URL if the link cannot be reached in time.
     * Throws ExceptionHandling.PageNavigationException if the page never loads.
     */
    public void navigateToWishlistViaAccount() {
        By wishlistHeaderLink = By.xpath("//a[contains(@href,'account/wishlist')]");
        String fallbackUrl    = "https://ecommerce-playground.lambdatest.io/index.php?route=account/wishlist";

        try {
            // Primary: wait for the account menu wishlist link and JS-click it
            waitForVisibility(wishlistHeaderLink);                          // BaseAction: visibility wait(By)
            jsClick(wishlistHeaderLink);                                    // BaseAction: jsClick(By)
            waitForVisibility(wp.myWishListTitle);                         // BaseAction: visibility wait(By)
            System.out.println("Navigated to wishlist page via JS click");

        } catch (TimeoutException e) {
            // Fallback: navigate directly via URL
            System.out.println("Timeout on header link — falling back to URL...");
            getDriver().get(fallbackUrl);                                   // BaseAction: getDriver()

            try {
                waitForVisibility(wp.myWishListTitle);                     // BaseAction: visibility wait(By)
                System.out.println("Navigated to wishlist page via URL fallback");
            } catch (TimeoutException te) {
                // Custom exception: page never loaded even after URL fallback
                throw new ExceptionHandling.PageNavigationException(
                        "My Wish List", fallbackUrl, getDriver().getCurrentUrl());
            }
        }
    }


// =========================================================================
// ADD-TO-WISHLIST ACTIONS — Individual Products
// =========================================================================

    /**
     * Waits for the iMac card, then hovers and clicks the wishlist button.
     * Uses BaseAction.waitForVisibility() before delegating to hoverAndClickWishlist().
     */
    public void addIMacToWishlist() {
        waitForPageLoad();                                                   // BaseAction: page load
        waitForVisibility(wp.imacListingBox);                              // BaseAction: visibility wait(By)
        hoverAndClickWishlist(wp.imacListingBox, wp.imacWishlistBtn, "iMac");
    }

    /**
     * Waits for the Apple Cinema 30 card, then hovers and clicks the wishlist button.
     */
    public void addAppleCinemaToWishlist() {
        waitForPageLoad();                                                   // BaseAction: page load
        waitForVisibility(wp.appleCinemaProduct);                          // BaseAction: visibility wait(By)
        hoverAndClickWishlist(wp.appleCinemaProduct, wp.appleCinemaWishlistBtn, "Apple Cinema 30");
    }

    /**
     * Waits for the iPod Nano card, then hovers and clicks the wishlist button.
     */
    public void addIpodNanoToWishlist() {
        waitForPageLoad();                                                   // BaseAction: page load
        waitForVisibility(wp.ipodNanoProduct);                             // BaseAction: visibility wait(By)
        hoverAndClickWishlist(wp.ipodNanoProduct, wp.ipodNanoWishlistBtn, "iPod Nano");
    }

    /**
     * Waits for the Canon EOS 5D card, then hovers and clicks the wishlist button.
     * NEW — added to fix UnknownProductException for CSV rows referencing
     * "Canon EOS 5D" (AddMultipleProduct2 / RemoveProduct1).
     */
    public void addCanonEOS5DToWishlist() {
        waitForPageLoad();                                                   // BaseAction: page load
        waitForVisibility(wp.canonEOS5DProduct);                           // BaseAction: visibility wait(By)
        hoverAndClickWishlist(wp.canonEOS5DProduct, wp.canonEOS5DWishlistBtn, "Canon EOS 5D");
    }

    /**
     * Dispatcher: routes a product name to the correct add-to-wishlist method.
     * Throws ExceptionHandling.UnknownProductException for unrecognised names.
     */
    public void hoverAndClickWishlistButton(String productName) {
        switch (productName.toLowerCase()) {
            case "imac":
                addIMacToWishlist();
                break;
            case "apple cinema 30":
                addAppleCinemaToWishlist();
                break;
            case "ipod nano":
                addIpodNanoToWishlist();
                break;
            case "canon eos 5d":
                addCanonEOS5DToWishlist();
                break;
            default:
                // Custom exception: no case defined — add case + locator in WishListPage.java
                throw new ExceptionHandling.UnknownProductException(
                        "hoverAndClickWishlistButton", productName);
        }
    }

    /**
     * Alias dispatcher for adding by name — mirrors hoverAndClickWishlistButton.
     * Used by step definitions phrased as "add [product] to wishlist".
     */
    public void addProductToWishlistByName(String productName) {
        switch (productName.toLowerCase()) {
            case "apple cinema 30":
                addAppleCinemaToWishlist();
                break;
            case "ipod nano":
                addIpodNanoToWishlist();
                break;
            case "imac":
                addIMacToWishlist();
                break;
            case "canon eos 5d":
                addCanonEOS5DToWishlist();
                break;
            default:
                // Custom exception: no case defined — add case + locator in WishListPage.java
                throw new ExceptionHandling.UnknownProductException(
                        "addProductToWishlistByName", productName);
        }
    }


// =========================================================================
// SEARCH ACTIONS (on Wishlist context)
// =========================================================================

    /**
     * Types a search term into the wishlist-context search bar and submits with ENTER.
     * Waits for page load and clickability, then uses BaseAction.sendKeys() to clear + type.
     */
    public void searchForProduct(String searchTerm) {
        waitForPageLoad();                                                   // BaseAction: page load
        waitForClickable(wp.SearchBar);                                     // BaseAction: clickable wait(By)
        try {
            sendKeys(wp.SearchBar, searchTerm);                             // BaseAction: clear + sendKeys(By)
            getDriver().findElement(wp.SearchBar).sendKeys(Keys.ENTER);     // BaseAction: getDriver()
            System.out.println("Searched for: " + searchTerm);
        } catch (TimeoutException e) {
            ExceptionHandling.handleTimeout(
                    "Search bar — Locator: //div[@id='entry_217822']" +
                            "//input[@placeholder='Search For Products']", 15, e);
        }
    }

    /**
     * Clicks a product link from the search results grid.
     * Scrolls into view and JS-clicks via BaseAction.
     * Throws ExceptionHandling.UnknownProductException for unrecognised product names.
     */
    public void clickProductFromSearchResults(String productName) {
        switch (productName.toLowerCase()) {
            case "ipod shuffle":
                try {
                    waitForPageLoad();                                       // BaseAction: page load
                    waitForClickable(wp.ipodShuffleProduct);                // BaseAction: clickable wait(By)
                    scrollIntoView(wp.ipodShuffleProduct);                  // BaseAction: scroll(By)
                    pause(400);
                    jsClick(wp.ipodShuffleProduct);                         // BaseAction: jsClick(By)
                    System.out.println("Clicked from search results: " + productName);
                } catch (TimeoutException e) {
                    ExceptionHandling.handleTimeout(
                            "iPod Shuffle in search results — " +
                                    "Locator: //a[@id='mz-product-grid-image-34-212469']" +
                                    "//img[@title='iPod Shuffle']", 15, e);
                }
                break;
            default:
                // Custom exception: product not mapped — add case + locator
                throw new ExceptionHandling.UnknownProductException(
                        "clickProductFromSearchResults", productName);
        }
    }

    /**
     * Clicks the heart/wishlist button on the iPod Shuffle product detail page.
     * Handles the already-wishlisted state by toggling off first.
     * Validates no AJAX error alert fires after the click.
     * Generic method to click Heart / Wishlist button on ANY product detail page
     * Works for HP LP3065, iPod Shuffle, or any other product.
     * Resolves the button fresh from its locator on every step — no stale references.
     */
    public void clickHeartButtonOnProductPage() {
        try {
            waitForPageLoad();

            // Generic locator for Wishlist heart button on product detail page.
            // Scoped to the product image-gallery container (id starts with
            // "image-gallery-") so it can never match an unrelated wishlist
            // button in a related-products/upsell carousel elsewhere on the
            // same page — a plain contains(@class,'wishlist') match is too
            // broad and can silently grab the wrong button.
            By heartButtonLocator = By.xpath(
                    "//div[contains(@id,'image-gallery-')]" +
                            "//button[@title='Add to Wish List' or contains(@title,'Wish List') " +
                            "or contains(@class,'wishlist')]"
            );

            waitForVisibility(heartButtonLocator);                          // BaseAction: visibility wait(By)

            scrollIntoView(heartButtonLocator);                             // BaseAction: scroll(By)
            pause(600);

            // Force reveal in case it's hidden behind hover CSS
            forceRevealElement(heartButtonLocator, "Product Detail Heart Button");

            // If already wishlisted, toggle it off first.
            // Same site quirk as hoverAndClickWishlist(): no "wished" class is
            // ever added — only the title attribute flips between
            // "Add to Wish List" and "Remove", and the heart icon swaps
            // between far (outline) and fas (solid). Detect via title instead.
            WebElement heartBtn = getDriver().findElement(heartButtonLocator);
            String btnTitle = heartBtn.getAttribute("title");
            boolean alreadyWishlisted = btnTitle != null && btnTitle.trim().equalsIgnoreCase("Remove");
            if (alreadyWishlisted) {
                logger.info("Product already in wishlist - removing first before re-adding");
                jsClick(heartButtonLocator);                                // BaseAction: jsClick(By)
                pause(1200);
                forceRevealElement(heartButtonLocator, "Product Detail Heart Button");
            }

            // Main click
            jsClick(heartButtonLocator);                                    // BaseAction: jsClick(By)
            logger.info("Successfully clicked heart button on product detail page");

            // Handle any unexpected alert
            dismissAlertIfPresent();

            // Capture the confirmation toast immediately — on this page the
            // toast auto-hides quickly, so we grab it here rather than letting
            // a later step re-query the DOM after it may have already vanished.
            pendingToastMessage = captureToastImmediately();

        } catch (TimeoutException e) {
            ExceptionHandling.handleTimeout(
                    "Heart/Wishlist button on product detail page", 20, e);
        } catch (Exception e) {
            logger.error("Failed to click heart button on product page", e);
            throw new RuntimeException("Heart button click failed", e);
        }
    }


// =========================================================================
// SUCCESS MESSAGE / TOAST HELPERS
// =========================================================================

    /**
     * Short, best-effort capture of the toast right after a click, used only
     * by clickHeartButtonOnProductPage(). Non-fatal on failure — returns null
     * so the caller falls back to the normal longer wait in
     * getWishlistSuccessMessageGeneric() if nothing was caught here.
     */
    private String captureToastImmediately() {
        By toastMsg      = By.xpath(
                "//div[@id='notification-box-top']//div[contains(@class,'toast-body')]//p");
        By toastFallback = By.xpath(
                "//div[@id='notification-box-top']//p");
        try {
            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(6));
            try {
                WebElement toast = shortWait.until(ExpectedConditions.visibilityOfElementLocated(toastMsg));
                return getText(toast);
            } catch (TimeoutException e) {
                WebElement toast2 = shortWait.until(ExpectedConditions.visibilityOfElementLocated(toastFallback));
                return getText(toast2);
            }
        } catch (Exception e) {
            // Nothing caught in the short window — normal, not an error.
            return null;
        }
    }

    /**
     * Waits up to 25 seconds for the success toast in #notification-box-top.
     * If clickHeartButtonOnProductPage() already captured a toast for this
     * action (see pendingToastMessage), that value is returned immediately
     * instead of re-querying a DOM node that may have already been hidden.
     * Falls back to a simpler //p locator if the primary is absent.
     * Throws ExceptionHandling.ToastNotDisplayedException if neither appears.
     */
    public String getWishlistSuccessMessageGeneric() {

        // Prefer a message captured immediately after the triggering click.
        if (pendingToastMessage != null) {
            String captured = pendingToastMessage;
            pendingToastMessage = null; // consume once
            System.out.println("Using pre-captured toast message: " + captured);
            return captured;
        }

        By toastMsg      = By.xpath(
                "//div[@id='notification-box-top']//div[contains(@class,'toast-body')]//p");
        By toastFallback = By.xpath(
                "//div[@id='notification-box-top']//p");

        try {
            // Primary: standard toast-body paragraph
            new WebDriverWait(getDriver(), Duration.ofSeconds(25))
                    .until(ExpectedConditions.visibilityOfElementLocated(toastMsg));
            return getText(toastMsg);                                       // BaseAction: getText(By)

        } catch (TimeoutException e) {
            // Fallback: simpler //p locator in the notification box
            try {
                new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(toastFallback));
                return getText(toastFallback);                              // BaseAction: getText(By)

            } catch (TimeoutException ex) {
                // Custom exception: no toast appeared after wishlist action
                throw new ExceptionHandling.ToastNotDisplayedException(
                        "getWishlistSuccessMessageGeneric", 35);
            }
        }
    }

    /**
     * Waits for a product-specific toast by matching the product name fragment.
     * Falls back to the page-object fallback locator on timeout.
     * Throws ExceptionHandling.ToastNotDisplayedException if both strategies fail.
     */
    public String getWishlistSuccessMessage(String productNameFragment) {
        By freshToast = By.xpath(
                "//div[@id='notification-box-top']" +
                        "//*[contains(text(),'" + productNameFragment + "')]" +
                        "/ancestor::div[contains(@class,'toast')]//p"
        );

        try {
            // Primary: product-name-specific toast locator
            wait.until(ExpectedConditions.visibilityOfElementLocated(freshToast));
            return getText(freshToast);                                     // BaseAction: getText(By)

        } catch (TimeoutException e) {
            // Fallback: generic success notification from page object
            try {
                return getText(wp.successNotificationFallback);            // BaseAction: getText(By)
            } catch (Exception ex) {
                // Custom exception: neither toast strategy returned a message
                throw new ExceptionHandling.ToastNotDisplayedException(
                        "getWishlistSuccessMessage - product: " + productNameFragment, 15);
            }
        }
    }

    /**
     * Clicks the "go to wishlist" link inside the success popup.
     * Waits for clickability before using BaseAction.click().
     */
    public void clickWishlistLinkFromPopup() {
        waitForClickable(wp.wishlistPopupLink);                            // BaseAction: clickable wait(By)
        click(wp.wishlistPopupLink);                                       // BaseAction: click(By)
        System.out.println("Clicked wishlist link from popup");
    }


// =========================================================================
// WISHLIST PAGE HELPERS
// =========================================================================

    /**
     * Dismisses any stale JS alert, then waits for the wishlist page title.
     * Uses BaseAction.waitForVisibility() for the page title check.
     */
    public void waitForWishlistPage() {
        // Dismiss any leftover alert from a previous action
        String alertText = dismissAlertIfPresent();
        if (alertText != null) {
            System.out.println("Dismissed stale JS alert: " + alertText);
        }
        waitForVisibility(wp.myWishListTitle);                             // BaseAction: visibility wait(By)
    }

    /** Returns the browser tab title — used in page-title step assertions. */
    public String getCurrentPageTitle() {
        return getDriver().getTitle();                                      // BaseAction: getDriver()
    }

    /**
     * Collects all product name texts from the wishlist table.
     * Waits for the page title and all name cells to be visible.
     * Re-resolves the locator to a fresh list of elements every call.
     */
    public List<String> getAllWishlistProductNames() {
        waitForVisibility(wp.myWishListTitle);                             // BaseAction: visibility wait(By)
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(wp.wishListProductNames));

        List<String> names = new ArrayList<>();
        List<WebElement> rows = getDriver().findElements(wp.wishListProductNames); // BaseAction: getDriver()
        for (WebElement el : rows) {
            String name = getText(el).trim();                              // BaseAction: getText(WebElement)
            if (!name.isEmpty()) {
                names.add(name);
                System.out.println("  Wishlist row: " + name);
            }
        }
        System.out.println("Total products in wishlist: " + names.size());
        return names;
    }

    /**
     * Collects all price texts from the wishlist table.
     * Waits for all price cells to be visible, then reads each via BaseAction.getText().
     */
    public List<String> getAllWishlistProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(wp.wishListProductPrices));

        List<String> prices = new ArrayList<>();
        List<WebElement> rows = getDriver().findElements(wp.wishListProductPrices); // BaseAction: getDriver()
        for (WebElement el : rows) {
            prices.add(getText(el).trim());                                // BaseAction: getText(WebElement)
        }
        return prices;
    }

    /**
     * Checks whether a named product is present in the wishlist table.
     * Returns true/false — does not throw; callers use this for assertions.
     */
    public boolean isProductPresentInWishlist(String productName) {
        By locator = By.xpath(
                "//table[contains(@class,'table')]//tbody//tr//td[2]//a" +
                        "[contains(normalize-space(),'" + productName + "')]"
        );
        return !getDriver().findElements(locator).isEmpty();               // BaseAction: getDriver()
    }

    /**
     * Removes a product from the wishlist by clicking its Remove link.
     * Waits for the row locator to become invisible (page reload) to confirm removal.
     * No WebElement reference is held across the reload, so no staleness handling is needed.
     * Throws ExceptionHandling timeout handler if the Remove button is not found.
     */
    public void removeProductFromWishlist(String productName) {
        waitForVisibility(wp.myWishListTitle);                             // BaseAction: visibility wait(By)

        By rowLocator = By.xpath(
                "//table[contains(@class,'table')]//tbody//tr" +
                        "[.//td[2]//a[contains(normalize-space(),'" + productName + "')]]"
        );

        By removeBtn = By.xpath(
                "//table[contains(@class,'table')]//tbody//tr" +
                        "[.//td[2]//a[contains(normalize-space(),'" + productName + "')]]" +
                        "//a[@title='Remove' or contains(@href,'remove')]"
        );

        try {
            waitForClickable(removeBtn);                                   // BaseAction: clickable wait(By)
            scrollIntoView(removeBtn);                                     // BaseAction: scroll(By)
            pause(300);

            jsClick(removeBtn);                                            // BaseAction: jsClick(By)
            System.out.println("Clicked remove for: " + productName);

            // Wait for the row locator to disappear — confirms the page reloaded after removal
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(rowLocator));
                System.out.println("Page reloaded after removing: " + productName);
            } catch (TimeoutException e) {
                // Non-fatal: removal may still have succeeded
                System.out.println("Invisibility wait timed out, continuing...");
            }

        } catch (TimeoutException e) {
            // Custom handler: Remove button not found for the given product
            ExceptionHandling.handleTimeout(
                    "Remove button for [" + productName + "] — " +
                            "confirm product exists in wishlist before removing", 15, e);
        }
    }

    /**
     * Reads the alert-success banner text after a wishlist removal.
     * Uses JS to extract only raw text nodes, excluding the '×' close button.
     * Falls back to getText() + strip if JS returns empty.
     * Throws ExceptionHandling timeout handler if no banner appears.
     */
    public String getRemovalSuccessMessage() {
        By alertDiv = By.xpath(
                "//div[contains(@class,'alert-success') " +
                        "and contains(@class,'alert-dismissible')]"
        );

        try {
            // Wait for the success banner to appear after removal
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertDiv));

            // Extract raw text nodes only — avoids the '×' close-button text
            String fullText = (String) ((JavascriptExecutor) getDriver()).executeScript(
                    "var el = arguments[0]; var text = '';" +
                            "for (var i = 0; i < el.childNodes.length; i++) {" +
                            "  if (el.childNodes[i].nodeType === 3) {" +
                            "    text += el.childNodes[i].textContent;" +
                            "  }" +
                            "}" +
                            "return text.trim();",
                    getDriver().findElement(alertDiv)                       // BaseAction: getDriver()
            );

            // Fallback to getText() if JS extraction returns nothing
            if (fullText == null || fullText.isEmpty()) {
                fullText = getText(alertDiv).replace("×", "").trim();      // BaseAction: getText(By)
            }

            System.out.println("Removal alert text: " + fullText);
            return fullText;

        } catch (TimeoutException e) {
            // Custom handler: success banner never appeared after removal click
            ExceptionHandling.handleTimeout(
                    "Removal success alert — " +
                            "Expected: 'Success: You have modified your wish list!' — " +
                            "Locator: //div[contains(@class,'alert-success')]", 15, e);
            return ""; // unreachable — handleTimeout calls Assert.fail()
        }
    }

}