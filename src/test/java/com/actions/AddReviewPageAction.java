package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.AddReviewPage;
import com.utils.AddReviewCSVReader;
import com.utils.AddReviewExcelReader;

public class AddReviewPageAction {

	WebDriver driver = DriverClass.getDriver();

	AddReviewPage arp = new AddReviewPage(driver);

	JavascriptExecutor js = (JavascriptExecutor) driver;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	Actions mouseAction = new Actions(driver);

	// Launch Product Page
	public void launchProductPage() {

		driver.get("https://ecommerce-playground.lambdatest.io/");

		wait.until(ExpectedConditions.elementToBeClickable(arp.product));

		arp.product.click();

		wait.until(ExpectedConditions.visibilityOf(arp.reviewtab));

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewtab);

		arp.reviewtab.click();
	}

	// Select Rating
	public void selectRating(int row) {

		 js.executeScript("window.scrollBy(0,800)");

		    try {

		        wait.until(
		            ExpectedConditions.presenceOfElementLocated(
		                By.cssSelector(
		"input[name='rating'][value='5']")));

		        js.executeScript(
		            "arguments[0].click();",
		            arp.rating);

		    } catch (Exception e) {

		        System.out.println(
		            "Rating element not found");
		    }
		}
	

	// Enter Name from Excel
	public void enterName(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewname);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewname));

		mouseAction.moveToElement(arp.reviewname).click().perform();

		arp.reviewname.clear();

		arp.reviewname.sendKeys(
				AddReviewExcelReader.getData(row, 1));
	}

	// Enter Review from Excel
	public void enterReview(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewtext);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewtext));

		mouseAction.moveToElement(arp.reviewtext).click().perform();

		arp.reviewtext.clear();

		arp.reviewtext.sendKeys(
				AddReviewExcelReader.getData(row, 2));
	}

	// Click Write Review
	public void clickWriteReview() {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.writeReview);

		wait.until(ExpectedConditions.elementToBeClickable(arp.writeReview));

		mouseAction.moveToElement(arp.writeReview).click().perform();
	}

	// Success Message
	public String getSuccessMessage() {

		return wait.until(
				ExpectedConditions.visibilityOf(
						arp.successMessage))
				.getText();
	}

	// Warning Message
	public String getWarningMessage() {

		return wait.until(
				ExpectedConditions.visibilityOf(
						arp.warningMessage))
				.getText();
	}

	// Expected Message from Excel
	public String expectedMessage(int row) {

		return AddReviewExcelReader.getData(row, 3);
	}

	// ================= CSV METHODS =================

	public void selectRatingFromCSV(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.rating);

		wait.until(ExpectedConditions.visibilityOf(arp.rating));

		mouseAction.moveToElement(arp.rating).click().perform();
	}

	public void enterNameFromCSV(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewname);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewname));

		mouseAction.moveToElement(arp.reviewname).click().perform();

		arp.reviewname.clear();

		arp.reviewname.sendKeys(
				AddReviewCSVReader.getData(row, 1));
	}

	public void enterReviewFromCSV(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewtext);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewtext));

		mouseAction.moveToElement(arp.reviewtext).click().perform();

		arp.reviewtext.clear();

		arp.reviewtext.sendKeys(
				AddReviewCSVReader.getData(row, 2));
	}

	public String expectedCSVMessage(int row) {

		return AddReviewCSVReader.getData(row, 3);
	}
}