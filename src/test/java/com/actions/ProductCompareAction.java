package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductComparePage;


public class ProductCompareAction {
	WebDriver driver = DriverClass.getDriver();
   ProductComparePage pcp=new ProductComparePage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public String getNoProductMessage() {
    	return wait.until(ExpectedConditions.visibilityOf(pcp.noProducts)).getText();
    }
    public String getCanonTitle() {
    	return pcp.CanonSDtitle.getText();
    }
    public String getiMacTitle() {
    	return pcp.iMacTitle.getText();
    }
    public void clickRemoveCompare() {
    	 wait.until(ExpectedConditions.elementToBeClickable(pcp.removeCompare)).click();
    }
    public String getConfirmationRemoved() {
    	return wait.until(ExpectedConditions.visibilityOf(pcp.removeConfirmation)).getText().replace("×", "").trim();
    }
}
