package com.actions;

import com.pages.AddReviewPage;
import com.utils.CsvDataProvider;
import com.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddReviewPageAction extends BaseAction {

    private AddReviewPage arp;

    // =========================
    // PAGE INITIALIZATION
    // =========================
    private AddReviewPage getPage() {

        if (arp == null) {
            arp = new AddReviewPage(getDriver());
        }

        return arp;
    }

    // =========================
    // LAUNCH PRODUCT PAGE
    // =========================
    public void launchProductPage() {

        getDriver().get("https://ecommerce-playground.lambdatest.io/");

        waitForPageLoad();

        click(getPage().product);

        waitForVisibility(getPage().reviewtab);

        scrollIntoView(getPage().reviewtab);

        jsClick(getPage().reviewtab);
    }

    // =========================
    // EXCEL METHODS
    // =========================
    public void selectRating(int row) {

        try {

            String ratingValue =
                    ExcelUtils.getAddReviewData(row, 1);

            if (ratingValue == null || ratingValue.trim().isEmpty()) {
                ratingValue = "5";
            }

            By ratingLocator = By.cssSelector(
                    "input[name='rating'][value='" + ratingValue + "']"
            );

            WebElement ratingElement =
                    getDriver().findElement(ratingLocator);

            scrollIntoView(ratingElement);

            jsClick(ratingElement);

        } catch (Exception e) {

            System.out.println(
                    "Rating selection failed: " + e.getMessage());
        }
    }

    public void enterName(int row) {

        scrollIntoView(getPage().reviewname);

        waitForClickable(getPage().reviewname);

        clear(getPage().reviewname);

        sendKeys(
                getPage().reviewname,
                ExcelUtils.getAddReviewData(row, 1)
        );
    }

    public void enterReview(int row) {

        scrollIntoView(getPage().reviewtext);

        waitForClickable(getPage().reviewtext);

        clear(getPage().reviewtext);

        sendKeys(
                getPage().reviewtext,
                ExcelUtils.getAddReviewData(row, 2)
        );
    }

    public void clickWriteReview() {

        scrollIntoView(getPage().writeReview);

        waitForClickable(getPage().writeReview);

        jsClick(getPage().writeReview);
    }

    public String getSuccessMessage() {

        waitForVisibility(getPage().successMessage);

        return getText(getPage().successMessage);
    }

    public String getWarningMessage() {

        waitForVisibility(getPage().warningMessage);

        return getText(getPage().warningMessage);
    }

    public String expectedMessage(int row) {

        return ExcelUtils.getAddReviewData(row, 3);
    }

    // =========================
    // CSV METHODS
    // =========================
    public void selectRatingFromCSV(int row) {

        try {

            String ratingValue =
                    CsvDataProvider.getData1(row, 1);

            if (ratingValue == null || ratingValue.trim().isEmpty()) {
                ratingValue = "4";
            }

            By ratingLocator = By.cssSelector(
                    "input[name='rating'][value='" + ratingValue + "']"
            );

            WebElement ratingElement =
                    getDriver().findElement(ratingLocator);

            scrollIntoView(ratingElement);

            jsClick(ratingElement);

        } catch (Exception e) {

            System.out.println(
                    "CSV Rating selection failed: " + e.getMessage());
        }
    }

    public void enterNameFromCSV(int row) {

        scrollIntoView(getPage().reviewname);

        waitForClickable(getPage().reviewname);

        clear(getPage().reviewname);

        sendKeys(
                getPage().reviewname,
                CsvDataProvider.getData1(row, 1)
        );
    }

    public void enterReviewFromCSV(int row) {

        scrollIntoView(getPage().reviewtext);

        waitForClickable(getPage().reviewtext);

        clear(getPage().reviewtext);

        sendKeys(
                getPage().reviewtext,
                CsvDataProvider.getData1(row, 2)
        );
    }

    public String expectedCSVMessage(int row) {

        return CsvDataProvider.getData1(row, 3);
    }
}