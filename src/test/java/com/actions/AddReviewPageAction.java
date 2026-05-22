package com.actions;

import java.time.Duration;
import java.util.Map;

import com.utils.CsvDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.AddReviewPage;
import com.utils.AddReviewCSVReader;
import com.utils.AddReviewExcelReader;

public class AddReviewPageAction {

<<<<<<< HEAD
	//WebDriver driver = DriverClass.getDriver();

	AddReviewPage arp = new AddReviewPage(DriverClass.getDriver());

	JavascriptExecutor js = (JavascriptExecutor)DriverClass.getDriver();

	WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(20));

	Actions mouseAction = new Actions(DriverClass.getDriver());
=======
	WebDriver driver = DriverClass.getDriver();
	AddReviewPage arp = new AddReviewPage(driver);

	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	Actions mouseAction = new Actions(driver);
>>>>>>> dc3a25591a5d6556787c0735be080802b4922963

	// Launch Product Page
	public void launchProductPage() {

		DriverClass.getDriver().get("https://ecommerce-playground.lambdatest.io/");

		wait.until(ExpectedConditions.elementToBeClickable(arp.product));
		arp.product.click();

		wait.until(ExpectedConditions.visibilityOf(arp.reviewtab));
		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewtab);

		arp.reviewtab.click();
	}

	// ================== EXCEL METHODS ==================

	// Select Rating from Excel
	public void selectRating(int row) {

		try {
			String ratingValue = AddReviewExcelReader.getData(row, 0); // column 0 = rating

			if (ratingValue == null || ratingValue.trim().isEmpty()) {
				ratingValue = "5"; // default rating
			}

<<<<<<< HEAD
		        wait.until(
		            ExpectedConditions.presenceOfElementLocated( By.cssSelector("input[name='rating'][value='5']")));

		        js.executeScript( "arguments[0].click();", arp.rating);
=======
			By ratingLocator = By.cssSelector("input[name='rating'][value='" + ratingValue + "']");

			wait.until(ExpectedConditions.presenceOfElementLocated(ratingLocator));
>>>>>>> dc3a25591a5d6556787c0735be080802b4922963

			WebElement ratingElement = driver.findElement(ratingLocator);

			js.executeScript("arguments[0].scrollIntoView(true);", ratingElement);
			js.executeScript("arguments[0].click();", ratingElement);

		} catch (Exception e) {
			System.out.println("Rating selection failed: " + e.getMessage());
		}
	}

	// Enter Name from Excel
	public void enterName(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewname);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewname));

		mouseAction.moveToElement(arp.reviewname).click().perform();

		arp.reviewname.clear();

		arp.reviewname.sendKeys(CsvDataProvider.getData1(row, 1));
	}

	// Enter Review from Excel
	public void enterReview(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewtext);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewtext));

		mouseAction.moveToElement(arp.reviewtext).click().perform();

		arp.reviewtext.clear();

		arp.reviewtext.sendKeys(CsvDataProvider.getData1(row, 2));
	}

	// Click Write Review
	public void clickWriteReview() {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.writeReview);

		wait.until(ExpectedConditions.elementToBeClickable(arp.writeReview));

		mouseAction.moveToElement(arp.writeReview).click().perform();
	}

	// Success Message
	public String getSuccessMessage() {
		return wait.until(ExpectedConditions.visibilityOf(arp.successMessage)).getText();
	}

	// Warning Message
	public String getWarningMessage() {
		return wait.until(ExpectedConditions.visibilityOf(arp.warningMessage)).getText();
	}

	// Expected Message from Excel
	public String expectedMessage(int row) {
		return AddReviewExcelReader.getData(row, 3);
	}

	// ================== CSV METHODS ==================

	// Select Rating from CSV
	public void selectRatingFromCSV(int row) {

		try {
			String ratingValue = CsvDataProvider.getData1(row, 0); // column 0 = rating

			if (ratingValue == null || ratingValue.trim().isEmpty()) {
				ratingValue = "5";
			}

			By ratingLocator = By.cssSelector("input[name='rating'][value='" + ratingValue + "']");

			wait.until(ExpectedConditions.presenceOfElementLocated(ratingLocator));

			WebElement ratingElement = driver.findElement(ratingLocator);

			js.executeScript("arguments[0].scrollIntoView(true);", ratingElement);
			js.executeScript("arguments[0].click();", ratingElement);

		} catch (Exception e) {
			System.out.println("CSV Rating selection failed: " + e.getMessage());
		}
	}

	public void enterNameFromCSV(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewname);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewname));

		mouseAction.moveToElement(arp.reviewname).click().perform();

		arp.reviewname.clear();

		arp.reviewname.sendKeys(CsvDataProvider.getData1(row, 1));
	}

	public void enterReviewFromCSV(int row) {

		js.executeScript("arguments[0].scrollIntoView(true);", arp.reviewtext);

		wait.until(ExpectedConditions.elementToBeClickable(arp.reviewtext));

		mouseAction.moveToElement(arp.reviewtext).click().perform();

		arp.reviewtext.clear();

		arp.reviewtext.sendKeys(CsvDataProvider.getData1(row, 2));
	}

	public String expectedCSVMessage(int row) {
		return CsvDataProvider.getData1(row, 3);
	}
}