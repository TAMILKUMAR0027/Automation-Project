package com.actions;

import org.openqa.selenium.WebDriver;

import com.driver.DriverClass;
import com.pages.LaunchPages;

public class LaunchPageAction  {
	

	WebDriver driver=DriverClass.getDriver();
	LaunchPages lp=new LaunchPages(driver);
	

	public void clickCategories() {
		lp.shopByCategories.click();
		lp.Desktop.click();
	}
}