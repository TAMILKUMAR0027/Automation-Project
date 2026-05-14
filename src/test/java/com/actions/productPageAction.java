package com.actions;

import org.openqa.selenium.WebDriver;

import com.driver.DriverClass;
import com.pages.ProductPage;

public class productPageAction {
	WebDriver driver=DriverClass.getDriver();
	ProductPage pp=new ProductPage(driver);
	public String getBrandName() {
		return pp.BrandName.getText();
	}
}
