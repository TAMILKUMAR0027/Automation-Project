package com.actions;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LaunchPages;

public class LaunchPageAction extends BaseAction {

	BaseAction ba;

	LaunchPages lp;

	WebDriverWait wait;

	public LaunchPageAction() {

		ba = new BaseAction();

		lp = new LaunchPages(DriverClass.getDriver());

		wait = new WebDriverWait(
				DriverClass.getDriver(),
				Duration.ofSeconds(15));
	}

	public void clickCategories() {

		wait.until(
				ExpectedConditions.elementToBeClickable(
						lp.shopByCategories));

		click(lp.shopByCategories);
	}

	public void clickMonitor() {

		wait.until(
				ExpectedConditions.elementToBeClickable(
						lp.Desktop));

		click(lp.Desktop);
	}

	public void clickCanonES5Product() {

		wait.until(
				ExpectedConditions.elementToBeClickable(
						lp.canonES5Product));

		click(lp.canonES5Product);
	}

	public void clickProductInTopCollection() {

		wait.until(
				ExpectedConditions.elementToBeClickable(
						lp.topCollectionProduct));

		click(lp.topCollectionProduct);
	}

	public void clickiMac() {

		wait.until(
				ExpectedConditions.elementToBeClickable(
						lp.iMac));

		click(lp.iMac);
	}

	public void clickProductCompare() {

		click(lp.productCompareLink);
	}

	public void clickHpProduct() {

		click(lp.HPLP3065Product);
	}

	public void addMultipleProduct() {

		ba.waitForVisibility(lp.cartOne);

		ba.scrollIntoView(lp.cartOne);

		ba.jsClick(lp.cartOne);

		ba.waitForVisibility(lp.cartTwo);

		ba.scrollIntoView(lp.cartTwo);

		ba.jsClick(lp.cartTwo);

		ba.waitForVisibility(lp.cartThree);

		ba.scrollIntoView(lp.cartThree);

		ba.jsClick(lp.cartThree);
	}
}