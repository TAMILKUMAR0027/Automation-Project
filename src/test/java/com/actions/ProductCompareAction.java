package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductComparePage;

public class ProductCompareAction extends BaseAction {
	WebDriver driver = DriverClass.getDriver();
	ProductComparePage pcp = new ProductComparePage(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	public String getNoProductMessage() {
		wait.until(ExpectedConditions.visibilityOf(pcp.noProducts));
		return pcp.noProducts.getText();
	}
	public String getCanonTitle() {
		wait.until(ExpectedConditions.visibilityOf(pcp.CanonSDtitle));
		return pcp.CanonSDtitle.getText();
	}
	public String getiMacTitle() {
		wait.until(ExpectedConditions.visibilityOf(pcp.iMacTitle));
		return pcp.iMacTitle.getText();
	}
	public void clickRemoveCompare() {
		wait.until(ExpectedConditions.elementToBeClickable(pcp.removeCompare));
		click(pcp.removeCompare);
	}
	public String getConfirmationRemoved() {
		wait.until(ExpectedConditions.visibilityOf(pcp.removeConfirmation));
		return pcp.removeConfirmation.getText().replace("×", "").trim();
	}
}