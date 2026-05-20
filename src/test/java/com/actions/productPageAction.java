package com.actions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductPage;

public class productPageAction extends BaseAction {

    WebDriver driver = DriverClass.getDriver();
    ProductPage pp = new ProductPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private static Logger log = LogManager.getLogger(productPageAction.class);

    public String getBrandName() {
        try {
            log.info("Getting brand name");
            return pp.BrandName.getText();
        } catch (Exception e) {
            log.error("Failed to get brand name", e);
            throw e;
        }
    }

    public String getInstockAvailability() {
        try {
            log.info("Getting in-stock availability");
            return pp.availability.getText();
        } catch (Exception e) {
            log.error("Failed to get in-stock availability", e);
            throw e;
        }
    }

    public String getOutStockAvailability() {
        try {
            log.info("Getting out-of-stock availability");
            return pp.availabilityOutOfStock.getText();
        } catch (Exception e) {
            log.error("Failed to get out-of-stock availability", e);
            throw e;
        }
    }

    public String getPrice() {
        try {
            return pp.productPrice.getText();
        } catch (Exception e) {
            log.error("Failed to get price", e);
            throw e;
        }
    }

    public String getProductTitle() {
        try {
            return pp.productTitle.getText();
        } catch (Exception e) {
            log.error("Failed to get title", e);
            throw e;
        }
    }

    public void setQuantity(String quantity) {
        try {
            sendKeys(pp.quantityBox, quantity);
        } catch (Exception e) {
            log.error("Failed to set quantity", e);
            throw e;
        }
    }

    public String getQuantity() {
        try {
            return pp.quantityBox.getAttribute("value");
        } catch (Exception e) {
            log.error("Failed to get quantity", e);
            throw e;
        }
    }

    public void clickAskQuestion() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.questionForm));
            click(pp.questionForm);
        } catch (Exception e) {
            log.error("Failed Ask Question", e);
            throw e;
        }
    }

    public void setName(String name) {
        try {
            wait.until(ExpectedConditions.visibilityOf(pp.name));
            sendKeys(pp.name, name);
        } catch (Exception e) {
            log.error("Failed to set name", e);
            throw e;
        }
    }

    public void setEmail(String email) {
        try {
            sendKeys(pp.email, email);
        } catch (Exception e) {
            log.error("Failed to set email", e);
            throw e;
        }
    }

    public void setSubject(String subject) {
        try {
            sendKeys(pp.subject, subject);
        } catch (Exception e) {
            log.error("Failed to set subject", e);
            throw e;
        }
    }

    public void setMessage(String message) {
        try {
            sendKeys(pp.Message, message);
        } catch (Exception e) {
            log.error("Failed to set message", e);
            throw e;
        }
    }

    public void clickSendMessage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.sendMessage));
            click(pp.sendMessage);
        } catch (Exception e) {
            log.error("Failed send message", e);
            throw e;
        }
    }

    public String getAlertMessage() {
        try {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='alert alert-success alert-notification w-50 alert-dismissible']")));

            return alert.getText().replace("×", "").trim();

        } catch (Exception e) {
            log.error("Failed alert message", e);
            throw e;
        }
    }

    public void clickAddToCart() {
        try {
            click(pp.addToCartBtn);
        } catch (Exception e) {
            log.error("Failed Add to Cart", e);
            throw e;
        }
    }

    public void clickWishListBtn() {
        try {
            click(pp.wishListBtn);
        } catch (Exception e) {
            log.error("Failed Wishlist", e);
            throw e;
        }
    }

    public String getAddTocartConfirmation() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pp.sizeRequired)).getText();
        } catch (Exception e) {
            log.error("Cart confirmation failed", e);
            throw e;
        }
    }

    public String getWishListConfirmation() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pp.wishList)).getText();
        } catch (Exception e) {
            log.error("Wishlist confirmation failed", e);
            throw e;
        }
    }

    public void clickSoftwareBreadcrumb() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.softwareBreadCrumb));
            click(pp.softwareBreadCrumb);
        } catch (Exception e) {
            log.error("Breadcrumb failed", e);
            throw e;
        }
    }

    public String getMadatoryFieldsMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pp.mandatoryFields)).getText();
        } catch (Exception e) {
            log.error("Mandatory message failed", e);
            throw e;
        }
    }

    public void clickMinus() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.minusBtn));
            click(pp.minusBtn);
        } catch (Exception e) {
            log.error("Minus click failed", e);
            throw e;
        }
    }

    public void clickAddToCartbutton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.addToCartButton));
            click(pp.addToCartButton);
        } catch (Exception e) {
            log.error("Add to cart failed", e);
            throw e;
        }
    }

    public void viewCartPP() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.viewCartPopUpButton));
            click(pp.viewCartPopUpButton);
        } catch (Exception e) {
            log.error("View cart failed", e);
            throw e;
        }
    }

    public void clickCompareBtn() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.compareBtn));
            click(pp.compareBtn);
        } catch (Exception e) {
            log.error("Compare failed", e);
            throw e;
        }
    }

    public String getConfirmationMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pp.productComparisonMessage)).getText();
        } catch (Exception e) {
            log.error("Compare message failed", e);
            throw e;
        }
    }

    public void clickCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.cartButton));
            click(pp.cartButton);
        } catch (Exception e) {
            log.error("Cart click failed", e);
            throw e;
        }
    }

    public String getQuantityInCart() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(pp.quantityField)).getAttribute("value");
        } catch (Exception e) {
            log.error("Cart quantity failed", e);
            throw e;
        }
    }

    public void clickViewCompare() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pp.viewCompare));
            click(pp.viewCompare);
        } catch (Exception e) {
            log.error("View compare failed", e);
            throw e;
        }
    }
}