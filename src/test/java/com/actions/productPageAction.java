package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	public String getPrice() {
		return pp.productPrice.getText();
	}
	public String getProductTitle() {
		// TODO Auto-generated method stub
		return pp.productTitle.getText();
	}
	public void setQuantity(String quantity) {
		pp.quantityBox.clear();
		pp.quantityBox.sendKeys(quantity);
	}
	public String getQuantity() {
		return pp.quantityBox.getAttribute("value");
	}
	public void clickAskQuestion() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.questionForm)).click();
	}
	public void setName(String yourName) {
		wait.until(ExpectedConditions.elementToBeClickable(pp.sendMessage));
		pp.name.sendKeys(yourName);
	}
	public void setEmail(String yourEmail) {
		pp.email.sendKeys(yourEmail);
	}
	public void setSubject(String yourSubject) {
		pp.subject.sendKeys(yourSubject);
	}
	public void setMessage(String yourMessage) {
		pp.Message.sendKeys(yourMessage);
	}
	public void clickSendMessage() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.sendMessage));
		pp.sendMessage.click();
	}
	public String getAlertMessage() {
	    WebElement alert = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-notification w-50 alert-dismissible']")));
	    return alert.getText().replace("×", "").trim();
	}
	public void clickAddToCart() {
		pp.addToCartBtn.click();
	}
	public void clickWishListBtn() {
		pp.wishListBtn.click();
	}
	public String getAddTocartConfirmation() {
		wait.until(ExpectedConditions.visibilityOf(pp.sizeRequired));
		return pp.sizeRequired.getText();
	}
	public String getWishListConfirmation() {
		return wait.until(ExpectedConditions.elementToBeClickable(pp.wishList)).getText();
	}
	public void clickSoftwareBreadcrumb() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.softwareBreadCrumb)).click();
	}
	public String getMadatoryFieldsMessage() {
		wait.until(ExpectedConditions.visibilityOf(pp.sendMessage));
		return pp.mandatoryFields.getText();
	}
	public void clickMinus() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.minusBtn));
		pp.minusBtn.click();
	}
	public void clickAddToCartbutton()
	{
		wait.until(ExpectedConditions.visibilityOf(pp.addToCartButton)).click();
	}
	public  void viewCartPP()
	{
		wait.until(ExpectedConditions.visibilityOf(pp.viewCartPopUpButton)).click();
	}
	public void clickCompareBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.compareBtn)).click();
	}
	public String getConfirmationMessage() {
		return wait.until(ExpectedConditions.visibilityOf(pp.productComparisonMessage)).getText();
	}
	public void clickCart() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.cartButton)).click();
	}
	public String getQuantityInCart() {
		return wait.until(ExpectedConditions.visibilityOf(pp.quantityField)).getAttribute("value");
	}
	public void clickViewCompare() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.viewCompare)).click();
	}
}
