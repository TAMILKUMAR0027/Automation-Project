package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.FilterPage;

public class FilterPageAction {
	WebDriver driver= DriverClass.getDriver();
	FilterPage fp=new FilterPage(driver);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	public void clickManufacture() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.Applebtn));
		fp.Applebtn.click();
	}
	public void clickProduct() {
		wait.until(ExpectedConditions.elementToBeClickable(fp.iPodTouchProduct));
		fp.iPodTouchProduct.click();
	}
}
