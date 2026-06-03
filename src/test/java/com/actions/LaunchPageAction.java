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

	LaunchPages lp;
	WebDriverWait wait;

	public LaunchPageAction() {
		lp = new LaunchPages(DriverClass.getDriver());
		wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(20));
	}

	
	public void clickCategories() {
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(lp.shopByCategories));
		try {
			click(lp.shopByCategories);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("shopByCategories clickable", 20, e);
		}
	}

	public void clickMonitor() {
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(lp.Desktop));
		try {
			click(lp.Desktop);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Desktop category clickable", 20, e);
		}
	}

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


	public void clickiMac() {

		waitForPageLoad();

		scrollIntoView(lp.iMac);

		try {

			wait.until(ExpectedConditions.elementToBeClickable(lp.iMac));

			click(lp.iMac);

		} catch (ElementClickInterceptedException e) {

			JavascriptExecutor js = (JavascriptExecutor) DriverClass.getDriver();

			js.executeScript("arguments[0].click();", lp.iMac);

		} catch (TimeoutException e) {

			ExceptionHandling.handleTimeout("iMac product clickable", 20, e);
		}
	}

	
	public void clickProductCompare() {
		waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(lp.productCompareLink));
		try {
			click(lp.productCompareLink);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Product Compare link clickable", 20, e);
		}
	}

	
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

	
	public void addMultipleProduct() {

		try {
			waitForVisibility(lp.cartOne); // confirm rendered
			scrollIntoView(lp.cartOne); // bring into viewport
			wait.until(ExpectedConditions.elementToBeClickable(lp.cartOne)); // confirm unblocked
			jsClick(lp.cartOne); // JS click (hover-revealed btn)
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Cart item 1 (cartOne) visibility/clickable", 20, e);
		}
		try {
			waitForVisibility(lp.cartTwo);
			scrollIntoView(lp.cartTwo);
			wait.until(ExpectedConditions.elementToBeClickable(lp.cartTwo));
			jsClick(lp.cartTwo);
		} catch (TimeoutException e) {
			ExceptionHandling.handleTimeout("Cart item 2 (cartTwo) visibility/clickable", 20, e);
		}

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