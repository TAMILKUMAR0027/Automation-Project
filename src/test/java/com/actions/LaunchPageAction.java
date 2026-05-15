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
}