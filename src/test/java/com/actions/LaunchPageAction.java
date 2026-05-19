package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.driver.DriverClass;
import com.pages.LaunchPages;

public class LaunchPageAction extends BaseAction {
	WebDriver driver = DriverClass.getDriver();
	LaunchPages lp = new LaunchPages(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	public void clickCategories() {
		click(lp.shopByCategories);
		wait.until(ExpectedConditions.elementToBeClickable(lp.Desktop));
	}
	public void clickMonitor() {
		click(lp.Desktop);
	}
	public void clickCanonES5Product() {
		wait.until(ExpectedConditions.elementToBeClickable(lp.canonES5Product));
		click(lp.canonES5Product);
	}
	public void clickProductInTopCollection() {
		wait.until(ExpectedConditions.visibilityOf(lp.topCollectionProduct));
		click(lp.topCollectionProduct);
	}
	public void clickiMac() {
		wait.until(ExpectedConditions.elementToBeClickable(lp.iMac));
		click(lp.iMac);
	}
	public void clickProductCompare() {
		click(lp.productCompareLink);
	}
	public void clickHpProduct() {
		click(lp.HPLP3065Product);
	}
}