package com.actions;

import com.driver.DriverClass;
import com.pages.WishListPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WishListActions {

    WebDriver driver = DriverClass.getDriver();
    WishListPage wp = new WishListPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


    private void pause(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    private String dismissAlertIfPresent() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.dismiss();
            System.out.println("Dismissed JS alert: " + text);
            return text;
        } catch (TimeoutException e) {
            return null;
        }
    }

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

    private void hoverAndClickWishlist(WebElement productCard,
                                       WebElement wishlistBtn,
                                       String productLabel) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // 1. Scroll into view
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", productCard);
        pause(500);



        // 2. Hover to trigger CSS :hover
        actions.moveToElement(productCard).perform();
        pause(700);

        // 3. Force-reveal button
        forceRevealElement(wishlistBtn, productLabel);

        // 4. Remove first if already wishlisted
        try {
            String btnClass = wishlistBtn.getAttribute("class");
            if (btnClass != null && btnClass.contains("wished")) {
                System.out.println("[" + productLabel + "] Already wishlisted — removing first...");
                js.executeScript("arguments[0].click();", wishlistBtn);
                pause(1200);
                dismissAlertIfPresent();
                pause(500);
                actions.moveToElement(productCard).perform();
                pause(700);
                forceRevealElement(wishlistBtn, productLabel);
                pause(300);
            }
        } catch (Exception e) {
            System.out.println("The wishlist button is display " + wishlistBtn.isDisplayed());
            System.out.println("[" + productLabel + "] Could not check wished state: " + e.getMessage());
        }

        // 5. Click to add
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wishlistBtn));
            js.executeScript("arguments[0].click();", wishlistBtn);
            System.out.println("[" + productLabel + "] Wishlist button clicked successfully");
        } catch (Exception e) {
            Assert.fail("[" + productLabel + "] Click failed. Cause: " + e.getMessage());
        }

        // 6. Check for AJAX error alert
        String alertText = dismissAlertIfPresent();
        if (alertText != null) {
            Assert.fail("[" + productLabel + "] AJAX error after click: " + alertText);
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


    public void scrollToTopProducts() {
        clickHomeLogo();
        wait.until(ExpectedConditions.visibilityOf(wp.topProductsHeading));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", wp.topProductsHeading);
        System.out.println("Scrolled to Top Products section");
    }

    public void addIMacToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.imacListingBox));

        // Enabled is used....
        System.out.println("element wishlist hover is enabled" + wp.imacWishlistBtn.isEnabled());
        hoverAndClickWishlist(card, wp.imacWishlistBtn, "iMac");
    }


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


    public String getWishlistSuccessMessage(String productNameFragment) {

        By freshToast = By.xpath(
                "//div[@id='notification-box-top']" +
                        "//*[contains(text(),'" + productNameFragment + "')]" +
                        "/ancestor::div[contains(@class,'toast')]//p"
        );

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(freshToast));
            return driver.findElement(freshToast).getText().trim();
        } catch (TimeoutException e) {
            try {
                return wp.successNotificationFallback.getText().trim();
            } catch (Exception ex) {
                Assert.fail("No success toast found for [" + productNameFragment + "]. " +
                        "Toast did not appear or AJAX call failed.");
                return "";
            }
        }
    }

    public void clickWishlistLinkFromPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(wp.wishlistPopupLink)).click();
        System.out.println("Clicked wishlist link from popup");
    }


    public String getRemovalSuccessMessage() {

        By alertDiv = By.xpath(
                "//div[contains(@class,'alert-success') and contains(@class,'alert-dismissible')]"
        );

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertDiv));

            // Get full text — includes "×" from the close button
            // Use JS to get only the text nodes (excludes child element text like <i> and <button>)
            WebElement alert = driver.findElement(alertDiv);

            String fullText = (String) ((JavascriptExecutor) driver).executeScript(
                    "var el = arguments[0];" +
                            "var text = '';" +
                            "for (var i = 0; i < el.childNodes.length; i++) {" +
                            "  if (el.childNodes[i].nodeType === 3) {" +
                            "    text += el.childNodes[i].textContent;" +
                            "  }" +
                            "}" +
                            "return text.trim();",
                    alert
            );

            // Fallback: if JS text extraction returns empty, use full getText()
            if (fullText == null || fullText.isEmpty()) {
                fullText = alert.getText().trim();
                // Remove the × close button character if present
                fullText = fullText.replace("×", "").trim();
            }

            System.out.println("Removal alert text: " + fullText);
            return fullText;

        } catch (TimeoutException e) {
            Assert.fail("Removal success alert not found. " +
                    "Expected: 'Success: You have modified your wish list!' " +
                    "Locator: //div[contains(@class,'alert-success')]");
            return "";
        }
    }


    public void waitForWishlistPage() {
        String alertText = dismissAlertIfPresent();
        if (alertText != null) {
            System.out.println("Dismissed stale JS alert: " + alertText);
        }
        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }


    public List<String> getAllWishlistProductNames() {
        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));
        wait.until(ExpectedConditions.visibilityOfAllElements(wp.wishListProductNames));

        List<String> names = new ArrayList<>();
        for (WebElement el : wp.wishListProductNames) {
            String name = el.getText().trim();
            if (!name.isEmpty()) {
                names.add(name);
                System.out.println("  Wishlist row: " + name);
            }
        }
        System.out.println("Total products in wishlist: " + names.size());
        return names;
    }

    public List<String> getAllWishlistProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(wp.wishListProductPrices));

        List<String> prices = new ArrayList<>();
        for (WebElement el : wp.wishListProductPrices) {
            prices.add(el.getText().trim());
        }
        return prices;
    }

    public boolean isProductPresentInWishlist(String productName) {
        By locator = By.xpath(
                "//table[contains(@class,'table')]//tbody//tr//td[2]//a" +
                        "[contains(normalize-space(),'" + productName + "')]"
        );
        return !driver.findElements(locator).isEmpty();
    }


    public void navigateToWishlistViaAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(wp.wishListbtn)).click();
        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));
        System.out.println("Navigated to wishlist page via account menu");
    }


    public void removeProductFromWishlist(String productName) {

        wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));


        By removeBtn = By.xpath(
                "//table[contains(@class,'table')]//tbody//tr" +
                        "[.//td[2]//a[contains(normalize-space(),'" + productName + "')]]" +
                        "//a[@title='Remove' or contains(@href,'remove')]"
        );

        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            pause(300);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            System.out.println("Clicked remove for: " + productName);
        } catch (TimeoutException e) {
            Assert.fail("Remove button not found for [" + productName + "]. " +
                    "Confirm product exists in wishlist table before removing.");
        }
    }
}