package com.actions;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LaunchPages;

public class LaunchPageAction extends BaseAction {

	LaunchPages lp;
	WebDriverWait wait;

	public LaunchPageAction() {

		lp = new LaunchPages(DriverClass.getDriver());

		wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(20));
	}

	public void clickCategories() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.shopByCategories));
		click(lp.shopByCategories);
	}

	public void clickMonitor() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.Desktop));
		click(lp.Desktop);
	}

	public void clickCanonES5Product() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.canonES5Product));
		click(lp.canonES5Product);
	}

	public void clickProductInTopCollection() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.topCollectionProduct));
		click(lp.topCollectionProduct);
	}

	public void clickiMac() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.iMac));
		click(lp.iMac);
	}

	public void clickProductCompare() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.productCompareLink));
		click(lp.productCompareLink);
	}

	public void clickHpProduct() {

		wait.until(ExpectedConditions.elementToBeClickable(lp.HPLP3065Product));
		click(lp.HPLP3065Product);
	}

	public void addMultipleProduct() {

		wait.until(ExpectedConditions.visibilityOf(lp.cartOne));
		scrollIntoView(lp.cartOne);
		wait.until(ExpectedConditions.elementToBeClickable(lp.cartOne));
		jsClick(lp.cartOne);

		wait.until(ExpectedConditions.visibilityOf(lp.cartTwo));
		scrollIntoView(lp.cartTwo);
		wait.until(ExpectedConditions.elementToBeClickable(lp.cartTwo));
		jsClick(lp.cartTwo);

		wait.until(ExpectedConditions.visibilityOf(lp.cartThree));
		scrollIntoView(lp.cartThree);
		wait.until(ExpectedConditions.elementToBeClickable(lp.cartThree));
		jsClick(lp.cartThree);
	}
}