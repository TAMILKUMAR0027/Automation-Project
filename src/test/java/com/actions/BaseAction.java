package com.actions;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

import com.driver.DriverClass;

public class BaseAction {

	protected WebDriver getDriver() {
		return DriverClass.getDriver();
	}

	protected WebDriverWait getWait() {
		return new WebDriverWait(
				getDriver(),
				Duration.ofSeconds(15)
		);
	}


	// ============================================================
	// CLICK ACTIONS - WebElement
	// ============================================================

	public void click(WebElement element) {
		getWait().until(
				ExpectedConditions.elementToBeClickable(element)
		);
		element.click();
	}


	// ============================================================
	// CLICK ACTIONS - By
	// ============================================================

	public void click(By locator) {
		getWait().until(
				ExpectedConditions.elementToBeClickable(locator)
		).click();
	}


	// ============================================================
	// JAVASCRIPT CLICK - WebElement
	// ============================================================

	public void jsClick(WebElement element) {
		((JavascriptExecutor) getDriver())
				.executeScript(
						"arguments[0].click();",
						element
				);
	}


	// ============================================================
	// JAVASCRIPT CLICK - By
	// ============================================================

	public void jsClick(By locator) {

		WebElement element = getWait().until(
				ExpectedConditions.presenceOfElementLocated(locator)
		);

		((JavascriptExecutor) getDriver())
				.executeScript(
						"arguments[0].click();",
						element
				);
	}


	// ============================================================
	// INPUT - WebElement
	// ============================================================

	public void sendKeys(WebElement element, String value) {

		getWait().until(
				ExpectedConditions.visibilityOf(element)
		);

		element.clear();
		element.sendKeys(value);
	}


	// ============================================================
	// INPUT - By
	// ============================================================

	public void sendKeys(By locator, String value) {

		WebElement element = getWait().until(
				ExpectedConditions.visibilityOfElementLocated(locator)
		);

		element.clear();
		element.sendKeys(value);
	}


	// ============================================================
	// CLEAR - WebElement
	// ============================================================

	public void clear(WebElement element) {

		getWait().until(
				ExpectedConditions.visibilityOf(element)
		);

		element.clear();
	}


	// ============================================================
	// CLEAR - By
	// ============================================================

	public void clear(By locator) {

		WebElement element = getWait().until(
				ExpectedConditions.visibilityOfElementLocated(locator)
		);

		element.clear();
	}


	// ============================================================
	// WAIT FOR VISIBILITY - WebElement
	// ============================================================

	public void waitForVisibility(WebElement element) {

		getWait().until(
				ExpectedConditions.visibilityOf(element)
		);
	}


	// ============================================================
	// WAIT FOR VISIBILITY - By
	// ============================================================

	public void waitForVisibility(By locator) {

		getWait().until(
				ExpectedConditions.visibilityOfElementLocated(locator)
		);
	}


	// ============================================================
	// WAIT FOR CLICKABLE - WebElement
	// ============================================================

	public void waitForClickable(WebElement element) {

		getWait().until(
				ExpectedConditions.elementToBeClickable(element)
		);
	}


	// ============================================================
	// WAIT FOR CLICKABLE - By
	// ============================================================

	public void waitForClickable(By locator) {

		getWait().until(
				ExpectedConditions.elementToBeClickable(locator)
		);
	}


	// ============================================================
	// PAGE LOAD
	// ============================================================

	public void waitForPageLoad() {

		WebDriver driver = getDriver();

		getWait().until(
				d -> ((JavascriptExecutor) d)
						.executeScript("return document.readyState")
						.equals("complete")
		);
	}


	// ============================================================
	// GET TEXT - WebElement
	// ============================================================

	public String getText(WebElement element) {
		return element.getText();
	}


	// ============================================================
	// GET TEXT - By
	// ============================================================

	public String getText(By locator) {

		WebElement element = getWait().until(
				ExpectedConditions.visibilityOfElementLocated(locator)
		);

		return element.getText();
	}


	// ============================================================
	// IS DISPLAYED - WebElement
	// ============================================================

	public boolean isDisplayed(WebElement element) {

		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}


	// ============================================================
	// IS DISPLAYED - By
	// ============================================================

	public boolean isDisplayed(By locator) {

		try {

			return getDriver()
					.findElement(locator)
					.isDisplayed();

		} catch (NoSuchElementException |
				 StaleElementReferenceException e) {

			return false;
		}
	}


	// ============================================================
	// SCROLL INTO VIEW - WebElement
	// ============================================================

	public void scrollIntoView(WebElement element) {

		((JavascriptExecutor) getDriver())
				.executeScript(
						"arguments[0].scrollIntoView(true);",
						element
				);
	}


	// ============================================================
	// SCROLL INTO VIEW - By
	// ============================================================

	public void scrollIntoView(By locator) {

		WebElement element = getWait().until(
				ExpectedConditions.presenceOfElementLocated(locator)
		);

		((JavascriptExecutor) getDriver())
				.executeScript(
						"arguments[0].scrollIntoView(true);",
						element
				);
	}
}