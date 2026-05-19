package com.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.FilterPage;

public class FilterPageAction extends BaseAction {
	WebDriver driver = DriverClass.getDriver();
	FilterPage fp = new FilterPage(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	public void clickManufacture() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.appleBtn));
		click(fp.appleBtn);
	}
	public void clickProduct() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.iPodTouchProduct));
		click(fp.iPodTouchProduct);
	}
	public void selectDropdownByVisibleText(String value) {
		wait.until(ExpectedConditions.elementToBeClickable(fp.dropdownBtn));
		Select select = new Select(fp.dropdownBtn);
		select.selectByVisibleText(value);	
		}
	public List<String> storeAllProducts() {
		wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));
		return fp.getProductNames();
	}
	public int getDisplayedProductCount() {
		wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));
		return fp.allProducts.size();
	}
	public void clickAvailability() {
		click(fp.inStockAvailabilityOption);
	}
	public void clickCanonProduct() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.canonProduct));
		click(fp.canonProduct);
	}
	public void clickOutofStockOption() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.OutOfStockAvailabilityOption));
		click(fp.OutOfStockAvailabilityOption);
	}
	public void clickHTCTouchHD() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.HTCTouchHDProduct));
		click(fp.HTCTouchHDProduct);
	}
	public String getSoftwareTitle() {
		wait.until(ExpectedConditions.visibilityOf(fp.softwaretTitle));
		return fp.softwaretTitle.getText();
	}
	public List<String> getItems() {
		return fp.getProductNames();
	}
	public void moveSlider() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOf(fp.slider));
		js.executeScript("arguments[0].scrollIntoView(true);", fp.slider);
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(fp.slider, -50, 0).perform();
	}
	public String getValue() {
		wait.until(ExpectedConditions.visibilityOf(fp.input));
		return fp.input.getAttribute("value");
	}
	public void clickHp() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.hpProduct)).click();
	}
}