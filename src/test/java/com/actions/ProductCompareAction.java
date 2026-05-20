package com.actions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductComparePage;

public class ProductCompareAction extends BaseAction {

    WebDriver driver = DriverClass.getDriver();
    ProductComparePage pcp = new ProductComparePage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private static Logger log = LogManager.getLogger(ProductCompareAction.class);

    public String getNoProductMessage() {
        try {
            log.info("Getting 'No Products' message");
            wait.until(ExpectedConditions.visibilityOf(pcp.noProducts));
            String msg = pcp.noProducts.getText();
            log.info("No product message: " + msg);
            return msg;
        } catch (Exception e) {
            log.error("Failed to get no product message", e);
            throw e;
        }
    }

    public String getCanonTitle() {
        try {
            log.info("Getting Canon product title");
            wait.until(ExpectedConditions.visibilityOf(pcp.CanonSDtitle));
            String title = pcp.CanonSDtitle.getText();
            log.info("Canon title: " + title);
            return title;
        } catch (Exception e) {
            log.error("Failed to get Canon title", e);
            throw e;
        }
    }

    public String getiMacTitle() {
        try {
            log.info("Getting iMac product title");
            wait.until(ExpectedConditions.visibilityOf(pcp.iMacTitle));
            String title = pcp.iMacTitle.getText();
            log.info("iMac title: " + title);
            return title;
        } catch (Exception e) {
            log.error("Failed to get iMac title", e);
            throw e;
        }
    }

    public void clickRemoveCompare() {
        try {
            log.info("Clicking remove compare button");
            wait.until(ExpectedConditions.elementToBeClickable(pcp.removeCompare));
            click(pcp.removeCompare);
        } catch (Exception e) {
            log.error("Failed to click remove compare", e);
            throw e;
        }
    }

    public String getConfirmationRemoved() {
        try {
            log.info("Getting remove confirmation message");
            wait.until(ExpectedConditions.visibilityOf(pcp.removeConfirmation));
            String msg = pcp.removeConfirmation.getText().replace("×", "").trim();
            log.info("Remove confirmation: " + msg);
            return msg;
        } catch (Exception e) {
            log.error("Failed to get remove confirmation message", e);
            throw e;
        }
    }
}