package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.CartPage;

public class CartPageActions {
	WebDriver driver=DriverClass.getDriver();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
	CartPage cp = new CartPage(driver);
	
	public String getProductName()
	{
		return wait.until(ExpectedConditions.visibilityOf(cp.productName)).getText();
	}
	
	public void sendQuantity(String q)
	{
		wait.until(ExpectedConditions.visibilityOf(cp.quantityCount)).sendKeys(q);
	}
	
	public void clickQUpdateButton()
	{
		cp.updateButton.click();
	}
	public String getQuantitySuccessMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(cp.quantityUpdatedMsg)).getText();
	}

}
