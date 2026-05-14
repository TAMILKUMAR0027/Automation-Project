package com.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.pages.LaunchPages;

public class LaunchPageAction {
	


	LaunchPages lp;
	WebDriver driver;

	public LaunchPageAction(WebDriver driver) {
		this.driver = driver;
		lp = new LaunchPages(driver);
	}

	public void clickCategories() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lp.shopByCategories);
		js.executeScript("arguments[0].click();", lp.Desktop);
	}
}