package com.actions;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ReturnsPage;

public class ReturnsPageAction {
	BaseAction ba = new BaseAction();
	WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(15));
	ReturnsPage rp = new ReturnsPage(DriverClass.getDriver());
	
	public String getProductReturnsHeading() {
		ba.waitForVisibility(rp.productReturnsHeading);
		return ba.getText(rp.productReturnsHeading);
	}
	
	public void clickFirstViewBtn() {
		ba.waitForVisibility(rp.productReturnFirstViewBtn);
		ba.click(rp.productReturnFirstViewBtn);
	}
	
	public String getProductReturnsInfoHeading() {
		ba.waitForVisibility(rp.productInfoText);
		return ba.getText(rp.productInfoText);
	}
	
}
