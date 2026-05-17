package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LaunchPages;

public class LaunchPageAction  {

    WebDriver driver = DriverClass.getDriver();
    LaunchPages lp = new LaunchPages(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	public void clickCategories() {
		lp.shopByCategories.click();
		wait.until(ExpectedConditions.elementToBeClickable(lp.Desktop));
		
	}
	public void clickMonitor() {
		lp.Desktop.click();
	}
	public void clickCanonES5Product() {
		wait.until(ExpectedConditions.elementToBeClickable(lp.canonES5Product));
		lp.canonES5Product.click();
	}
	
	public void clickProductInTopCollection()
	{
		wait.until(ExpectedConditions.visibilityOf(lp.topCollectionProduct)).click();
	}
	public void clickiMac() {
		wait.until(ExpectedConditions.elementToBeClickable(lp.iMac)).click();
	}
	public void clickProductCompare() {
		lp.productCompareLink.click();
	}
	public void clickHpProduct() {
		lp.HPLP3065Product.click();
	}
}