package com.actions;

import java.time.Duration;

import com.exceptions.ExceptionHandling;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LaunchPages;

import io.reactivex.rxjava3.functions.Action;

public class LaunchPageAction extends BaseAction {

	// ── Page Object & Wait Setup ──────────────────────────────────────────────
	LaunchPages lp;
	WebDriverWait wait;

	public LaunchPageAction() {
		lp   = new LaunchPages(DriverClass.getDriver());
		wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(20));
	}


	// =========================================================================
	// CATEGORY & MENU NAVIGATION
	// =========================================================================

	/**
	 * Clicks the "Shop By Categories" menu button.
	 * Waits for page load, then for the element to be clickable.
	 * Uses BaseAction.click() which internally applies an elementToBeClickable wait.
	 */
	public void clickCategories() {
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(lp.shopByCategories));
		try {
			click(lp.shopByCategories);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("shopByCategories clickable", 20, e);
		}
	}

	/**
	 * Clicks the "Desktop" category link.
	 * Waits for page load and element clickability before delegating to BaseAction.click().
	 */
	public void clickMonitor() {
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(lp.Desktop));
		try {
			click(lp.Desktop);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Desktop category clickable", 20, e);
		}
	}


	// =========================================================================
	// PRODUCT CLICK ACTIONS
	// =========================================================================

	/**
	 * Clicks the Canon EOS 5D product card.
	 * Waits for page load, scrolls into view, waits for clickability,
	 * then uses BaseAction.click().
	 */
	public void clickCanonES5Product() {
		waitForPageLoad();
		scrollIntoView(lp.canonES5Product);
		wait.until(ExpectedConditions.elementToBeClickable(lp.canonES5Product));
		try {
			click(lp.canonES5Product);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Canon EOS 5D product clickable", 20, e);
		}
	}

	/**
	 * Clicks the featured product in the "Top Collection" section.
	 * Waits for page load, scrolls element into viewport, then clicks via BaseAction.
	 */
	public void clickProductInTopCollection() {
		waitForPageLoad();
		scrollIntoView(lp.topCollectionProduct);
		wait.until(ExpectedConditions.elementToBeClickable(lp.topCollectionProduct));
		try {
			click(lp.topCollectionProduct);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Top Collection product clickable", 20, e);
		}
	}

	/**
	 * Clicks the iMac product link.
	 * Waits for page load, scrolls into view, waits for clickability.
	 */
	public void clickiMac() {
		waitForPageLoad();
		scrollIntoView(lp.iMac);
		wait.until(ExpectedConditions.elementToBeClickable(lp.iMac));
		try {
			click(lp.iMac);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("iMac product clickable", 20, e);
		}
	}

	/**
	 * Clicks the "Product Compare" link in the header or toolbar.
	 * Waits for page load and element clickability before clicking.
	 */
	public void clickProductCompare() {
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(lp.productCompareLink));
		try {
			click(lp.productCompareLink);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Product Compare link clickable", 20, e);
		}
	}

	/**
	 * Clicks the HP LP3065 product card.
	 * Waits for page load, scrolls into view, then clicks via BaseAction.
	 */
	public void clickHpProduct() {
		waitForPageLoad();
		scrollIntoView(lp.HPLP3065Product);
		wait.until(ExpectedConditions.elementToBeClickable(lp.HPLP3065Product));
		try {
			click(lp.HPLP3065Product);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("HP LP3065 product clickable", 20, e);
		}
	}


	// =========================================================================
	// CART ACTIONS
	// =========================================================================

	/**
	 * Adds three products to the cart in sequence using JS clicks.
	 * Each product is:
	 *   1. Waited for visibility   — confirms it is rendered in the DOM
	 *   2. Scrolled into view      — ensures it is not off-screen
	 *   3. Waited for clickability — confirms no overlay is blocking it
	 *   4. JS-clicked              — bypasses any CSS hover-reveal requirement
	 *
	 * Uses BaseAction.scrollIntoView() and BaseAction.jsClick() throughout.
	 * Uses BaseAction.waitForVisibility() as an alias for the visibility wait.
	 */
	public void addMultipleProduct() {

		// ── Cart Item 1 ───────────────────────────────────────────────────────
		try {
			waitForVisibility(lp.cartOne);                                   // confirm rendered
			scrollIntoView(lp.cartOne);                                      // bring into viewport
			wait.until(ExpectedConditions.elementToBeClickable(lp.cartOne)); // confirm unblocked
			jsClick(lp.cartOne);                                             // JS click (hover-revealed btn)
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Cart item 1 (cartOne) visibility/clickable", 20, e);
		}

		// ── Cart Item 2 ───────────────────────────────────────────────────────
		try {
			waitForVisibility(lp.cartTwo);
			scrollIntoView(lp.cartTwo);
			wait.until(ExpectedConditions.elementToBeClickable(lp.cartTwo));
			jsClick(lp.cartTwo);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Cart item 2 (cartTwo) visibility/clickable", 20, e);
		}

		// ── Cart Item 3 ───────────────────────────────────────────────────────
		try {
			waitForVisibility(lp.cartThree);
			scrollIntoView(lp.cartThree);
			wait.until(ExpectedConditions.elementToBeClickable(lp.cartThree));
			jsClick(lp.cartThree);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Cart item 3 (cartThree) visibility/clickable", 20, e);
		}
	}
	public void clickWidgets() {
		click(lp.widgetsButton);
	}


	public void clickAddonButton() {
		// TODO Auto-generated method stub
		Actions act = new Actions(getDriver());

	    act.moveToElement(lp.AddonsButton).perform();
	}
}