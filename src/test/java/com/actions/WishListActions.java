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
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
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

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", productCard);
        pause(500);

        actions.moveToElement(productCard).perform();
        pause(700);

        forceRevealElement(wishlistBtn, productLabel);

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
            System.out.println("[" + productLabel + "] Could not check wished state: " + e.getMessage());
        }

        try {
            js.executeScript("arguments[0].click();", wishlistBtn);
            System.out.println("[" + productLabel + "] Wishlist button clicked successfully");
        } catch (Exception e) {
            Assert.fail("[" + productLabel + "] Click failed. Cause: " + e.getMessage());
        }

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

    // ========================= NAVIGATION =========================

    public void scrollToTopProducts() {
        clickHomeLogo();
        wait.until(ExpectedConditions.visibilityOf(wp.topProductsHeading));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", wp.topProductsHeading);
        System.out.println("Scrolled to Top Products section");
    }

    public void scrollToTopCollection() {
        clickHomeLogo();
        wait.until(ExpectedConditions.visibilityOf(wp.topCollectionHeading));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", wp.topCollectionHeading);
        System.out.println("Scrolled to Top Collection section");
    }

    public void navigateToWishlistViaAccount() {

        By wishlistHeaderLink = By.xpath("//a[contains(@href,'account/wishlist')]");

        try {
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistHeaderLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));
            System.out.println("Navigated to wishlist page via JS click");
        } catch (Exception e) {
            System.out.println("Timeout waiting for wishlist sidebar button. Navigating via URL fallback...");
            driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/wishlist");
            wait.until(ExpectedConditions.visibilityOf(wp.myWishListTitle));
            System.out.println("Navigated to wishlist page via URL");
        }
    }
    // ========================= ADD TO WISHLIST =========================

    public void addIMacToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.imacListingBox));
        hoverAndClickWishlist(card, wp.imacWishlistBtn, "iMac");
    }

    public void addAppleCinemaToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.appleCinemaProduct));
        hoverAndClickWishlist(card, wp.appleCinemaWishlistBtn, "Apple Cinema 30");
    }

    public void addIpodNanoToWishlist() {
        WebElement card = wait.until(ExpectedConditions.visibilityOf(wp.ipodNanoProduct));
        hoverAndClickWishlist(card, wp.ipodNanoWishlistBtn, "iPod Nano");
    }

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
            default:
                Assert.fail("Unknown product [" + productName + "]. " +
                        "Add a case in hoverAndClickWishlistButton() and a locator in WishListPage.java.");
        }
    }

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
            default:
                Assert.fail("Unknown product [" + productName + "]. " +
                        "Add a case in addProductToWishlistByName() and a locator in WishListPage.java.");
        }
    }

    // ========================= SEARCH =========================

    public void searchForProduct(String searchTerm) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wp.SearchBar));
            wp.SearchBar.clear();
            wp.SearchBar.sendKeys(searchTerm);
            wp.SearchBar.sendKeys(Keys.ENTER);
            System.out.println("Searched for: " + searchTerm);
        } catch (TimeoutException e) {
            Assert.fail("Search bar not found or not clickable. " +
                    "Locator: //div[@id='entry_217822']//input[@placeholder='Search For Products']");
        }
    }

    public void clickProductFromSearchResults(String productName) {
        switch (productName.toLowerCase()) {
            case "ipod shuffle":
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(wp.ipodShuffleProduct));
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].scrollIntoView({block:'center'});",
                                    wp.ipodShuffleProduct);
                    pause(400);
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", wp.ipodShuffleProduct);
                    System.out.println("Clicked product from search results: " + productName);
                } catch (TimeoutException e) {
                    Assert.fail("iPod Shuffle not found in search results. " +
                            "Locator: //a[@id='mz-product-grid-image-34-212469']//img[@title='iPod Shuffle']");
                }
                break;
            default:
                Assert.fail("Unknown search result product [" + productName + "]. " +
                        "Add a case in clickProductFromSearchResults() and a locator in WishListPage.java.");
        }
    }

    public void clickHeartButtonOnProductPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(wp.ipodShuffleWishlistBtn));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});",
                            wp.ipodShuffleWishlistBtn);
            pause(400);

            forceRevealElement(wp.ipodShuffleWishlistBtn, "iPod Shuffle - Product Page Heart");

            try {
                String btnClass = wp.ipodShuffleWishlistBtn.getAttribute("class");
                if (btnClass != null && btnClass.contains("wished")) {
                    System.out.println("[iPod Shuffle] Already wishlisted — removing first...");
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", wp.ipodShuffleWishlistBtn);
                    pause(1200);
                    dismissAlertIfPresent();
                    pause(500);
                    forceRevealElement(wp.ipodShuffleWishlistBtn, "iPod Shuffle - Product Page Heart");
                    pause(300);
                }
            } catch (Exception e) {
                System.out.println("[iPod Shuffle] Could not check wished state: " + e.getMessage());
            }

            wait.until(ExpectedConditions.elementToBeClickable(wp.ipodShuffleWishlistBtn));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", wp.ipodShuffleWishlistBtn);
            System.out.println("Clicked heart button on product detail page for iPod Shuffle");

            String alertText = dismissAlertIfPresent();
            if (alertText != null) {
                Assert.fail("[iPod Shuffle] AJAX error after heart click: " + alertText);
            }

        } catch (TimeoutException e) {
            Assert.fail("Heart/wishlist button not found on product detail page. " +
                    "Locator: //div[@id='image-gallery-216811']//button[@title='Add to Wish List']");
        }
    }

    // ========================= SUCCESS TOAST =========================

    // ✅ FIXED: Use fresh By locator instead of PageFactory element
    public String getWishlistSuccessMessageGeneric() {

        By toastMsg = By.xpath("//div[@id='notification-box-top']//div[contains(@class,'toast-body')]//p");
        By toastFallback = By.xpath("//div[@id='notification-box-top']//p");

        try {
            WebElement toast = new WebDriverWait(driver, Duration.ofSeconds(25))
                    .until(ExpectedConditions.visibilityOfElementLocated(toastMsg));

            return toast.getText().trim();

        } catch (TimeoutException e) {

            try {
                WebElement toast2 = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(toastFallback));

                return toast2.getText().trim();

            } catch (Exception ex) {
                Assert.fail("No success toast appeared. Check #notification-box-top is present after wishlist action.");
                return "";
            }
        }
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

    // ========================= WISHLIST ASSERTIONS =========================

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

    // ========================= REMOVE PRODUCT =========================

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

            // Grab the row element to check for staleness (page reload)
            WebElement row = driver.findElement(By.xpath("//table[contains(@class,'table')]//tbody//tr[.//td[2]//a[contains(normalize-space(),'" + productName + "')]]"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            System.out.println("Clicked remove for: " + productName);

            // Wait for the page to reload by waiting for the row to become stale
            try {
                wait.until(ExpectedConditions.stalenessOf(row));
                System.out.println("Page reloaded after removing: " + productName);
            } catch (Exception e) {
                System.out.println("Staleness wait timed out or failed, continuing...");
            }
        } catch (TimeoutException e) {
            Assert.fail("Remove button not found for [" + productName + "]. " +
                    "Confirm product exists in wishlist table before removing.");
        }
    }

    public String getRemovalSuccessMessage() {
        By alertDiv = By.xpath(
                "//div[contains(@class,'alert-success') and contains(@class,'alert-dismissible')]"
        );

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertDiv));
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

            if (fullText == null || fullText.isEmpty()) {
                fullText = alert.getText().trim();
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
}