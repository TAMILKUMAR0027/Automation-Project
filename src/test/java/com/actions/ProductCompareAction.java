package com.actions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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

            log.info("Getting no products message");

            waitForVisibility(pcp.noProducts);

            String msg = getText(pcp.noProducts);

            log.info("No products message fetched successfully");

            return msg;

        } catch (Exception e) {

            log.error("Failed to get no products message", e);
            throw new RuntimeException("Failed to get no products message", e);
        }
    }

    public String getCanonTitle() {

        try {

            log.info("Getting Canon product title");

            waitForVisibility(pcp.CanonSDtitle);

            String title = getText(pcp.CanonSDtitle);

            log.info("Canon title fetched successfully");

            return title;

        } catch (Exception e) {

            log.error("Failed to get Canon title", e);
            throw new RuntimeException("Failed to get Canon title", e);
        }
    }

    public String getiMacTitle() {

        try {

            log.info("Getting iMac product title");

            waitForVisibility(pcp.iMacTitle);

            String title = getText(pcp.iMacTitle);

            log.info("iMac title fetched successfully");

            return title;

        } catch (Exception e) {

            log.error("Failed to get iMac title", e);
            throw new RuntimeException("Failed to get iMac title", e);
        }
    }

    public void clickRemoveCompare() {

        try {

            log.info("Clicking remove compare button");

            waitForClickable(pcp.removeCompare);

            click(pcp.removeCompare);

            log.info("Remove compare button clicked successfully");

        } catch (Exception e) {

            log.error("Failed to click remove compare button", e);
            throw new RuntimeException("Failed to click remove compare button", e);
        }
    }

    public String getConfirmationRemoved() {

        try {

            log.info("Getting remove confirmation message");

            waitForVisibility(pcp.removeConfirmation);

            String msg = getText(pcp.removeConfirmation)
                    .replace("×", "")
                    .trim();

            log.info("Remove confirmation fetched successfully");

            return msg;

        } catch (Exception e) {

            log.error("Failed to get remove confirmation message", e);
            throw new RuntimeException("Failed to get remove confirmation message", e);
        }
    }
}