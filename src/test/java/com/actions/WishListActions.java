package com.actions;

import com.driver.DriverClass;
import com.pages.WishListPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WishListActions {

    WebDriver driver = DriverClass.getDriver();
    WishListPage wp = new WishListPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // ========================= PRIVATE HELPERS =========================

    private void pause(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    /**
     * Dismisses any native JS alert present on the page.
     * Returns the alert text if one was found, null otherwise.
     */
    private String dismissAlertIfPresent() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.dismiss();
            System.out.println("Dismissed JS alert: " + text);
            return text;
        } catch (TimeoutException e) {
            return null; // No alert — happy path
        }
    }

    /**
     * Core hover + force-reveal + JS-click pattern for any product card.
     * Handles:
     *   - CSS hover-revealed buttons (opacity/display:none)
     *   - Already-wishlisted products (removes then re-adds)
     *   - AJAX error alerts after click
     */
    private void hoverAndClickWishlist(WebElement productCard,
                                       WebElement wishlistBtn,
                                       String productLabel) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Step 1: Scroll card into center view
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", productCard);
        pause(500);

        // Step 2: Hover to trigger CSS :hover state
        actions.moveToElement(productCard).perform();
        pause(700);

        // Step 3: Force-reveal wishlist button
        forceRevealElement(wishlistBtn, productLabel);

        // Step 4: If already wishlisted, remove it first then re-add
        try {
            String btnClass = wishlistBtn.getAttribute("class");
            if (btnClass != null && btnClass.contains("wished")) {
                System.out.println("[" + productLabel + "] Already in wishlist — removing first...");
                js.executeScript("arguments[0].click();", wishlistBtn);
                pause(1200);
                dismissAlertIfPresent();
                pause(500);

                // Re-hover and re-reveal after removal
                actions.moveToElement(productCard).perform();
                pause(700);
                forceRevealElement(wishlistBtn, productLabel);
                pause(300);
            }
        } catch (Exception e) {
            System.out.println("[" + productLabel + "] Could not check wished state: " + e.getMessage());
        }

        // Step 5: Click wishlist button
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wishlistBtn));
            js.executeScript("arguments[0].click();", wishlistBtn);
            System.out.println("[" + productLabel + "] Wishlist button clicked successfully");
        } catch (Exception e) {
            Assert.fail("[" + productLabel + "] Wishlist button click failed. Cause: " + e.getMessage());
        }

        // Step 6: Check for AJAX error alert immediately after click
        String alertText = dismissAlertIfPresent();
        if (alertText != null) {
            Assert.fail("[" + productLabel + "] AJAX error after wishlist click. Alert: " + alertText +
                    ". Product may already be in wishlist or session may have expired.");
        }
    }

    /**
     * Force CSS visibility on a hover-hidden button.
     */
    private void forceRevealElement(WebElement element, String productLabel) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "var b = arguments[0];" +
                            "b.style.setProperty('display',    'block',   'important');" +
                            "b.style.setProperty('opacity',    '1',       'important');" +
                            "b.style.setProperty('visibility', 'visible', 'important');",
                    element
            );
        } catch (Exception e) {
            Assert.fail("[" + productLabel + "] Wishlist button not found in DOM. " +
                    "Check XPath in WishListPage.java. Cause: " + e.getMessage());
        }
    }

    private void clickHomeLogo() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wp.homeLogo)).click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(wp.homeLogoAlt)).click();
            } catch (Exception ex) {
                System.out.println("Home logo not clickable, continuing...");
            }
        }
    }

    // ========================= TOP PRODUCTS =========================

    public void scrollToTopProducts() {
        clickHomeLogo();
        wait.until(ExpectedConditions.visibilityOf(wp.topProductsHeading));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", wp.topProductsHeading);
        System.out.println("Scrolled to Top Products section");
    }

    public void addIMacToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.imacListingBox));
        hoverAndClickWishlist(card, wp.imacWishlistBtn, "iMac");
    }

    // ========================= TOP COLLECTION =========================

    public void scrollToTopCollection() {
        clickHomeLogo();
        wait.until(ExpectedConditions.visibilityOf(wp.topCollectionHeading));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", wp.topCollectionHeading);
        System.out.println("Scrolled to Top Collection section");
    }

    public void addAppleCinemaToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.appleCinemaProduct));
        hoverAndClickWishlist(card, wp.appleCinemaWishlistBtn, "Apple Cinema 30");
    }

    public void addIpodNanoToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.ipodNanoProduct));
        hoverAndClickWishlist(card, wp.ipodNanoWishlistBtn, "iPod Nano");
    }

    // ========================= POPUP VALIDATIONS =========================

    /**
     * Waits for a toast that contains the specific product name.
     * Prevents reading a stale toast from a previously added product.
     */
    public String getWishlistSuccessMessage(String productNameFragment) {

        // Build a dynamic XPath that waits for THIS product's toast specifically
        By freshToast = By.xpath(
                "//div[@id='notification-box-top']" +
                        "//*[contains(text(),'" + productNameFragment + "')]" +
                        "/ancestor::div[contains(@class,'toast')]" +
                        "//p"
        );

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(freshToast));
            WebElement toastText = driver.findElement(freshToast);
            return toastText.getText().trim();
        } catch (TimeoutException e) {
            // Fallback: return whatever is in the notification box
            try {
                return wp.successNotification.getText().trim();
            } catch (Exception ex) {
                Assert.fail("No success toast found for product [" + productNameFragment + "]. " +
                        "Toast may not have appeared or AJAX call failed.");
                return "";
            }
        }
    }

    public void clickWishlistLinkFromPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(wp.wishlistPopupLink)).click();
        System.out.println("Clicked wishlist link from popup");
    }

    // ========================= WISHLIST PAGE =========================

    public void waitForWishlistPage() {
        // Dismiss any lingering JS alert before interacting with the page
        String alertText = dismissAlertIfPresent();
        if (alertText != null) {
            System.out.println("Dismissed stale alert on wishlist page: " + alertText);
        }
        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    public String getWishlistProductName() {
        wait.until(ExpectedConditions.visibilityOf(wp.wishListProductName));
        return wp.wishListProductName.getText().trim();
    }

    public String getWishlistProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(wp.wishListProductPrice));
        return wp.wishListProductPrice.getText().trim();
    }

    /**
     * Searches wishlist table for a product by exact name.
     * Uses contains() to handle products like 'Apple Cinema 30"' with inch symbol.
     */
    public boolean isProductPresentInWishlist(String productName) {
        By locator = By.xpath(
                "//table[contains(@class,'table')]//tbody//tr//td[2]//a" +
                        "[contains(normalize-space(),'" + productName + "')]"
        );
        return !driver.findElements(locator).isEmpty();
    }
}