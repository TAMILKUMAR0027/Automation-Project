package com.actions;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	import com.driver.DriverClass;
	import com.pages.AddReviewPage;
	import com.utils.AddReviewExcelReader;
	import com.utils.CsvDataProvider;

	public class AddReviewPageAction extends BaseAction {

	    WebDriver driver = DriverClass.getDriver();
	    AddReviewPage arp = new AddReviewPage(driver);

	    // Launch Product Page
	    public void launchProductPage() {

	        driver.get("https://ecommerce-playground.lambdatest.io/");

	        click(arp.product);

	        waitForVisibility(arp.reviewtab);

	        scrollIntoView(arp.reviewtab);

	        click(arp.reviewtab);
	    }

	    // ================== EXCEL METHODS ==================

	    // Select Rating from Excel
	    public void selectRating(int row) {

	        try {

	            String ratingValue = AddReviewExcelReader.getData(row, 1);

	            if (ratingValue == null || ratingValue.trim().isEmpty()) {

	                ratingValue = "5";
	            }

	            By ratingLocator =
	                    By.cssSelector("input[name='rating'][value='" + ratingValue + "']");

	            WebElement ratingElement = driver.findElement(ratingLocator);

	            scrollIntoView(ratingElement);

	            jsClick(ratingElement);

	        } catch (Exception e) {

	            System.out.println("Rating selection failed: " + e.getMessage());
	        }
	    }

	    // Enter Name from Excel
	    public void enterName(int row) {

	        scrollIntoView(arp.reviewname);

	        waitForClickable(arp.reviewname);

	        clear(arp.reviewname);

	        sendKeys(arp.reviewname,
	                CsvDataProvider.getData1(row, 1));
	    }

	    // Enter Review from Excel
	    public void enterReview(int row) {

	        scrollIntoView(arp.reviewtext);

	        waitForClickable(arp.reviewtext);

	        clear(arp.reviewtext);

	        sendKeys(arp.reviewtext,
	                CsvDataProvider.getData1(row, 2));
	    }

	    // Click Write Review
	    public void clickWriteReview() {

	        scrollIntoView(arp.writeReview);

	        waitForClickable(arp.writeReview);

	        jsClick(arp.writeReview);
	    }

	    // Success Message
	    public String getSuccessMessage() {

	        waitForVisibility(arp.successMessage);

	        return getText(arp.successMessage);
	    }

	    // Warning Message
	    public String getWarningMessage() {

	        waitForVisibility(arp.warningMessage);

	        return getText(arp.warningMessage);
	    }

	    // Expected Message from Excel
	    public String expectedMessage(int row) {

	        return AddReviewExcelReader.getData(row, 3);
	    }

	    // ================== CSV METHODS ==================

	    // Select Rating from CSV
	    public void selectRatingFromCSV(int row) {

	        try {

	            String ratingValue = CsvDataProvider.getData1(row, 1);

	            if (ratingValue == null || ratingValue.trim().isEmpty()) {

	                ratingValue = "4";
	            }

	            By ratingLocator =
	                    By.cssSelector("input[name='rating'][value='" + ratingValue + "']");

	            WebElement ratingElement = driver.findElement(ratingLocator);

	            scrollIntoView(ratingElement);

	            jsClick(ratingElement);

	        } catch (Exception e) {

	            System.out.println("CSV Rating selection failed: " + e.getMessage());
	        }
	    }

	    // Enter Name from CSV
	    public void enterNameFromCSV(int row) {

	        scrollIntoView(arp.reviewname);

	        waitForClickable(arp.reviewname);

	        clear(arp.reviewname);

	        sendKeys(arp.reviewname,
	                CsvDataProvider.getData1(row, 1));
	    }

	    // Enter Review from CSV
	    public void enterReviewFromCSV(int row) {

	        scrollIntoView(arp.reviewtext);

	        waitForClickable(arp.reviewtext);

	        clear(arp.reviewtext);

	        sendKeys(arp.reviewtext,
	                CsvDataProvider.getData1(row, 2));
	    }

	    // Expected Message from CSV
	    public String expectedCSVMessage(int row) {

	        return CsvDataProvider.getData1(row, 3);
	    }
	}