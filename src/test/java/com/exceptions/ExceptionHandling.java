package com.exceptions;

// ─────────────────────────────────────────────────────────────────────────────
//  ExceptionHandling.java
//  Central exception catalogue for the Selenium + Cucumber + TestNG framework.
//
//  STRUCTURE
//  ─────────
//  1. Static inner classes  → custom (user-defined) exceptions
//  2. Static utility methods → wrap / rethrow standard Selenium / Java /
//                              TestNG exceptions with descriptive messages
// ─────────────────────────────────────────────────────────────────────────────

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class ExceptionHandling {

    // =========================================================================
    // 1. CUSTOM (USER-DEFINED) EXCEPTIONS
    // =========================================================================

    // ── 1a. Element not interactable after explicit wait ──────────────────────
    /**
     * Thrown when an element is found in the DOM but cannot be interacted with
     * (e.g. hidden behind a modal, zero opacity, off-screen) even after waiting.
     *
     * Usage:
     *   throw new ExceptionHandling.ElementNotInteractableException(
     *       "Wishlist button", "iMac card");
     */
    public static class ElementNotInteractableException extends RuntimeException {
        public ElementNotInteractableException(String elementName, String context) {
            super(String.format(
                    "[ELEMENT NOT INTERACTABLE] '%s' could not be clicked/typed into. " +
                            "Context: %s. " +
                            "Possible causes: covered by overlay, visibility:hidden, opacity:0, " +
                            "off-viewport, or animation in progress.",
                    elementName, context));
        }
        public ElementNotInteractableException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // ── 1b. Product not found in wishlist table ───────────────────────────────
    /**
     * Thrown when a product that was expected to be present in the wishlist
     * table is missing after navigation.
     *
     * Usage:
     *   throw new ExceptionHandling.ProductNotInWishlistException("iMac");
     */
    public static class ProductNotInWishlistException extends RuntimeException {
        public ProductNotInWishlistException(String productName) {
            super(String.format(
                    "[PRODUCT NOT IN WISHLIST] '%s' was not found in the wishlist table. " +
                            "Verify the wishlist action succeeded and the product locator is correct.",
                    productName));
        }
    }

    // ── 1c. Toast / success notification did not appear ──────────────────────
    /**
     * Thrown when the expected success / error toast notification is absent
     * after performing a wishlist (or any AJAX-driven) action.
     *
     * Usage:
     *   throw new ExceptionHandling.ToastNotDisplayedException("iMac", 25);
     */
    public static class ToastNotDisplayedException extends RuntimeException {
        public ToastNotDisplayedException(String actionContext, int waitSeconds) {
            super(String.format(
                    "[TOAST NOT DISPLAYED] No notification toast appeared within %ds " +
                            "after action: '%s'. " +
                            "Check #notification-box-top is rendered and the AJAX call succeeded.",
                    waitSeconds, actionContext));
        }
    }

    // ── 1d. Search returned unexpected / zero results ─────────────────────────
    /**
     * Thrown when a search for a known keyword yields no results,
     * or when result items do not match the searched keyword.
     *
     * Usage:
     *   throw new ExceptionHandling.SearchResultException("MacBook", 0);
     *   throw new ExceptionHandling.SearchResultException("MacBook", "iPod Shuffle");
     */
    public static class SearchResultException extends RuntimeException {
        // Zero results variant
        public SearchResultException(String keyword, int resultCount) {
            super(String.format(
                    "[SEARCH RESULT ERROR] Search for '%s' returned %d results. " +
                            "Expected at least 1 matching product.",
                    keyword, resultCount));
        }
        // Keyword mismatch variant
        public SearchResultException(String keyword, String mismatchedProduct) {
            super(String.format(
                    "[SEARCH RESULT MISMATCH] Product '%s' does not contain keyword '%s'. " +
                            "Search filter may be broken.",
                    mismatchedProduct, keyword));
        }
    }

    // ── 1e. Manufacturer mismatch on product / search page ───────────────────
    /**
     * Thrown when the displayed manufacturer does not match the expected one.
     * Replaces the raw RuntimeException currently thrown in SearchAction.
     *
     * Usage:
     *   throw new ExceptionHandling.ManufacturerMismatchException("Apple", "Samsung");
     */
    public static class ManufacturerMismatchException extends RuntimeException {
        public ManufacturerMismatchException(String expected, String actual) {
            super(String.format(
                    "[MANUFACTURER MISMATCH] Expected: '%s' | Actual: '%s'. " +
                            "Verify the product page loaded correctly.",
                    expected, actual));
        }
    }

    // ── 1f. Page navigation / URL mismatch ───────────────────────────────────
    /**
     * Thrown when the driver lands on an unexpected URL or the page title
     * does not match what was expected after a navigation action.
     *
     * Usage:
     *   throw new ExceptionHandling.PageNavigationException(
     *       "Wishlist Page", expectedUrl, actualUrl);
     */
    public static class PageNavigationException extends RuntimeException {
        public PageNavigationException(String pageName,
                                       String expectedUrl,
                                       String actualUrl) {
            super(String.format(
                    "[PAGE NAVIGATION ERROR] Could not reach '%s'. " +
                            "Expected URL: '%s' | Actual URL: '%s'.",
                    pageName, expectedUrl, actualUrl));
        }
    }

    // ── 1g. JS / AJAX alert detected when it should not be ───────────────────
    /**
     * Thrown when an unexpected JavaScript alert (usually an AJAX error dialog)
     * appears and blocks test execution.
     *
     * Usage:
     *   throw new ExceptionHandling.UnexpectedJsAlertException("iMac", alertText);
     */
    public static class UnexpectedJsAlertException extends RuntimeException {
        public UnexpectedJsAlertException(String context, String alertText) {
            super(String.format(
                    "[UNEXPECTED JS ALERT] Alert appeared during action '%s'. " +
                            "Alert text: \"%s\". " +
                            "Likely an AJAX/server error — check network tab.",
                    context, alertText));
        }
    }

    // ── 1h. Wishlist button state error ──────────────────────────────────────
    /**
     * Thrown when the wishlist button is already in the 'wished' state
     * and the automatic de-select + re-select logic fails.
     *
     * Usage:
     *   throw new ExceptionHandling.WishlistStateException("Apple Cinema 30");
     */
    public static class WishlistStateException extends RuntimeException {
        public WishlistStateException(String productName) {
            super(String.format(
                    "[WISHLIST STATE ERROR] Could not toggle wishlist state for '%s'. " +
                            "Button may already be in 'wished' state and removal failed.",
                    productName));
        }
    }

    // ── 1i. Unsupported / unknown product name passed to a switch ─────────────
    /**
     * Thrown when a product name is passed to a dispatch method
     * (e.g. hoverAndClickWishlistButton) that has no matching case.
     *
     * Usage:
     *   throw new ExceptionHandling.UnknownProductException(
     *       "hoverAndClickWishlistButton", productName);
     */
    public static class UnknownProductException extends RuntimeException {
        public UnknownProductException(String methodName, String productName) {
            super(String.format(
                    "[UNKNOWN PRODUCT] Method '%s' has no case for product '%s'. " +
                            "Add a case block and a matching page-object locator.",
                    methodName, productName));
        }
    }

    // ── 1j. Driver / browser setup failure ───────────────────────────────────
    /**
     * Thrown when the WebDriver instance cannot be initialised or is null.
     *
     * Usage:
     *   throw new ExceptionHandling.DriverInitialisationException("Chrome", cause);
     */
    public static class DriverInitialisationException extends RuntimeException {
        public DriverInitialisationException(String browserName, Throwable cause) {
            super(String.format(
                    "[DRIVER INIT ERROR] Failed to create WebDriver for browser '%s'. " +
                            "Check driver binaries, browser version, and WebDriverManager config.",
                    browserName), cause);
        }
    }


    // =========================================================================
    // 2. STANDARD-EXCEPTION UTILITY METHODS
    //    Use these to translate raw Selenium / Java exceptions into
    //    descriptive, actionable messages before they reach the test report.
    // =========================================================================

    /**
     * Wraps NoSuchElementException with element details.
     *
     * Usage (in Page-Object or Action class):
     *   ExceptionHandling.handleNoSuchElement("Wishlist button", "#wish-btn", e);
     */
    public static void handleNoSuchElement(String elementName,
                                           String locator,
                                           NoSuchElementException cause) {
        Assert.fail(String.format(
                "[NO SUCH ELEMENT] '%s' not found in DOM. " +
                        "Locator used: '%s'. " +
                        "Cause: %s",
                elementName, locator, cause.getMessage()));
    }

    /**
     * Wraps TimeoutException with wait-context details.
     *
     * Usage:
     *   ExceptionHandling.handleTimeout("iMac wishlist button visibility", 15, e);
     */
    public static void handleTimeout(String waitContext,
                                     int secondsWaited,
                                     TimeoutException cause) {
        Assert.fail(String.format(
                "[TIMEOUT] Waited %ds for: '%s'. " +
                        "Element may be absent, hidden, or the page did not load. " +
                        "Cause: %s",
                secondsWaited, waitContext, cause.getMessage()));
    }

    /**
     * Wraps StaleElementReferenceException.
     *
     * Usage:
     *   ExceptionHandling.handleStaleElement("Wishlist row - iMac", e);
     */
    public static void handleStaleElement(String elementName,
                                          StaleElementReferenceException cause) {
        Assert.fail(String.format(
                "[STALE ELEMENT] '%s' reference became stale (DOM was refreshed/rebuilt). " +
                        "Re-fetch the element before interacting. " +
                        "Cause: %s",
                elementName, cause.getMessage()));
    }

    /**
     * Wraps ElementClickInterceptedException.
     *
     * Usage:
     *   ExceptionHandling.handleClickIntercepted("Add-to-Wishlist btn", "Cookie banner", e);
     */
    public static void handleClickIntercepted(String elementName,
                                              String interceptingElement,
                                              ElementClickInterceptedException cause) {
        Assert.fail(String.format(
                "[CLICK INTERCEPTED] Click on '%s' was intercepted by '%s'. " +
                        "Dismiss the overlay/modal first or use JS click as a fallback. " +
                        "Cause: %s",
                elementName, interceptingElement, cause.getMessage()));
    }

    /**
     * Wraps WebDriverException (generic Selenium runtime error).
     *
     * Usage:
     *   ExceptionHandling.handleWebDriverException("JS click on wishlist btn", e);
     */
    public static void handleWebDriverException(String actionContext,
                                                WebDriverException cause) {
        Assert.fail(String.format(
                "[WEBDRIVER EXCEPTION] Unexpected driver error during '%s'. " +
                        "Cause: %s",
                actionContext, cause.getMessage()));
    }

    /**
     * Wraps InvalidSelectorException.
     *
     * Usage:
     *   ExceptionHandling.handleInvalidSelector("Product card", "//div[@]", e);
     */
    public static void handleInvalidSelector(String elementName,
                                             String badLocator,
                                             InvalidSelectorException cause) {
        Assert.fail(String.format(
                "[INVALID SELECTOR] XPath/CSS for '%s' is malformed: '%s'. " +
                        "Fix the locator in the Page-Object class. " +
                        "Cause: %s",
                elementName, badLocator, cause.getMessage()));
    }

    /**
     * Wraps JavascriptException (thrown by JavascriptExecutor).
     *
     * Usage:
     *   ExceptionHandling.handleJavascriptException("forceRevealElement - iMac", e);
     */
    public static void handleJavascriptException(String scriptContext,
                                                 JavascriptException cause) {
        Assert.fail(String.format(
                "[JAVASCRIPT ERROR] Script execution failed in context '%s'. " +
                        "Cause: %s",
                scriptContext, cause.getMessage()));
    }

    /**
     * Wraps MoveTargetOutOfBoundsException (Actions / hover).
     *
     * Usage:
     *   ExceptionHandling.handleMoveTargetOutOfBounds("iMac product card hover", e);
     */
    public static void handleMoveTargetOutOfBounds(String elementName,
                                                   MoveTargetOutOfBoundsException cause) {
        Assert.fail(String.format(
                "[MOVE TARGET OUT OF BOUNDS] Cannot hover over '%s' — element outside viewport. " +
                        "Use scrollIntoView before Actions.moveToElement(). " +
                        "Cause: %s",
                elementName, cause.getMessage()));
    }

    /**
     * Safe executor — runs a block and translates any Selenium exception into a
     * descriptive Assert.fail().  Useful for one-liners in step definitions.
     *
     * Usage:
     *   ExceptionHandling.safely("Click wishlist btn for iMac",
     *       () -> wishlistActions.addIMacToWishlist());
     */
    public static void safely(String actionDescription, Runnable action) {
        try {
            action.run();
        } catch (NoSuchElementException e) {
            Assert.fail("[NO SUCH ELEMENT] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (TimeoutException e) {
            Assert.fail("[TIMEOUT] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            Assert.fail("[STALE ELEMENT] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (ElementClickInterceptedException e) {
            Assert.fail("[CLICK INTERCEPTED] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (ElementNotInteractableException e) {
            Assert.fail("[NOT INTERACTABLE] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (JavascriptException e) {
            Assert.fail("[JS ERROR] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (WebDriverException e) {
            Assert.fail("[WEBDRIVER ERROR] During: '" + actionDescription + "'. " + e.getMessage());
        }
    }

    /**
     * Safe supplier — like safely() but returns a value.
     *
     * Usage:
     *   String msg = ExceptionHandling.safeGet(
     *       "Get toast message", () -> wishlistActions.getWishlistSuccessMessageGeneric());
     */
    public static <T> T safeGet(String actionDescription, Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NoSuchElementException e) {
            Assert.fail("[NO SUCH ELEMENT] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (TimeoutException e) {
            Assert.fail("[TIMEOUT] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            Assert.fail("[STALE ELEMENT] During: '" + actionDescription + "'. " + e.getMessage());
        } catch (WebDriverException e) {
            Assert.fail("[WEBDRIVER ERROR] During: '" + actionDescription + "'. " + e.getMessage());
        }
        return null; // unreachable — Assert.fail() throws
    }
}