package com.actions;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.driver.DriverClass;

public class BaseAction {

	protected WebDriver getDriver() {
		return DriverClass.getDriver();
	}

	protected WebDriverWait getWait() {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(15));
	}

	
	// CLICK ACTIONS
	
	public void click(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void jsClick(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
	}

	
	// INPUT ACTIONS
	
	public void sendKeys(WebElement element, String value) {
		getWait().until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	// NEW: CLEAR METHOD
	public void clear(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}


	// WAIT ACTIONS
	
	public void waitForVisibility(WebElement element) {
		getWait().until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForClickable(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForPageLoad() {
		WebDriver driver = getDriver();

		getWait().until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
	}

	
	// UTILITIES
	
	public String getText(WebElement element) {
		return element.getText();
	}

	// NEW: IS DISPLAYED METHOD (SAFE)
	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}