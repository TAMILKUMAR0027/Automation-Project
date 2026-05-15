package com.actions;

import com.driver.DriverClass;
import com.pages.WishListPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WishListActions {

    WebDriver driver = DriverClass.getDriver();
    WishListPage wp = new WishListPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public void scrollToTopProducts() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wp.homeLogo)).click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(wp.homeLogoAlt)).click();
            } catch (Exception ex) {
                System.out.println("Home Logo not clickable, continuing...");
            }
        }

        wait.until(ExpectedConditions.visibilityOf(wp.topProductsHeading));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", wp.topProductsHeading);

        System.out.println("Scrolled to Top Products section");
    }

    public void addIMacToWishlist() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Step 1: Wait for iMac card and scroll into view
        WebElement imacBox = wait.until(
                ExpectedConditions.visibilityOf(wp.imacListingBox)
        );
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", imacBox);

        // Step 2: Small pause for scroll to settle
        try { Thread.sleep(600); } catch (InterruptedException ignored) {}

        // Step 3: Hover over the iMac card to reveal action buttons
        actions.moveToElement(imacBox).perform();

        // Step 4: Small pause for CSS hover transition to complete
        try { Thread.sleep(700); } catch (InterruptedException ignored) {}

        // Step 5: Log the actual wishlist button class for debugging
        try {
            String actualClass = wp.imacWishlistBtn.getAttribute("class");
            System.out.println("iMac Wishlist Button Class: " + actualClass);
        } catch (Exception e) {
            System.out.println("Could not read wishlist button class: " + e.getMessage());
        }

        // Step 6: Force reveal via JS (handles opacity/display:none CSS hiding)
        try {
            js.executeScript(
                    "var el = arguments[0];" +
                            "el.style.setProperty('display',    'block',   'important');" +
                            "el.style.setProperty('opacity',    '1',       'important');" +
                            "el.style.setProperty('visibility', 'visible', 'important');",
                    wp.imacWishlistBtn
            );
        } catch (Exception e) {
            Assert.fail("iMac wishlist button not found in DOM. " +
                    "Verify the XPath: //div[contains(@class,'product-thumb') " +
                    "and .//a[normalize-space()='iMac']]//button[contains(@class,'wishlist')]. " +
                    "Cause: " + e.getMessage());
        }

        // Step 7: JS click (most reliable for hover-revealed buttons)
        try {
            js.executeScript("arguments[0].click();", wp.imacWishlistBtn);
            System.out.println("Clicked Wishlist button via JS click");
        } catch (Exception e) {
            Assert.fail("Wishlist button was found but click failed. Cause: " + e.getMessage());
        }
    }

    public void validateWishlistSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(wp.successNotification));

        String msg = wp.successNotification.getText();
        System.out.println("Wishlist Notification: " + msg);

        Assert.assertTrue(
                msg.contains("Success"),
                "Wishlist success message not shown. Actual message: [" + msg + "]"
        );
    }

    public void clickWishlistLinkFromPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(wp.wishlistPopupLink)).click();
        System.out.println("Clicked wishlist link in popup");
    }

    public void validateRedirectedPageTitle(String expectedTitle) {
        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));

        String actualTitle = driver.getTitle();
        System.out.println("Actual Page Title: " + actualTitle);

        Assert.assertTrue(
                actualTitle.contains(expectedTitle),
                "Page title mismatch. Expected: [" + expectedTitle + "] | Actual: [" + actualTitle + "]"
        );
    }

    public void validateWishListProductDetails(String expectedProductName) {
        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));

        String actualProductName = wp.wishListProductName.getText().trim();

        Assert.assertEquals(
                actualProductName,
                expectedProductName,
                "Wishlist product name mismatch. Expected: [" + expectedProductName + "] | Actual: [" + actualProductName + "]"
        );

        String price = wp.wishListProductPrice.getText();
        System.out.println("Wishlist Product: " + actualProductName + " | Price: " + price);
    }
}