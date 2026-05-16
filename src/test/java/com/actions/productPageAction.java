package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductPage;

public class productPageAction {
	WebDriver driver=DriverClass.getDriver();
	ProductPage pp=new ProductPage(driver);
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	public String getBrandName() {
		return pp.BrandName.getText();
	}
	public String getInstockAvailability() {
		return pp.availability.getText();
	}
	public String getOutStockAvailability() {
		return pp.availabilityOutOfStock.getText();
	}
}
