package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductPage;

public class productPageAction extends BaseAction {
	WebDriver driver = DriverClass.getDriver();
	ProductPage pp = new ProductPage(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
		return pp.productTitle.getText();
	}

	public void setQuantity(String quantity) {
		sendKeys(pp.quantityBox, quantity);
	}

	public String getQuantity() {
		return pp.quantityBox.getAttribute("value");
	}

	public void clickAskQuestion() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.questionForm));
		click(pp.questionForm);
	}

	public void setName(String yourName) {
		wait.until(ExpectedConditions.visibilityOf(pp.name));
		sendKeys(pp.name, yourName);
	}

	public void setEmail(String yourEmail) {
		sendKeys(pp.email, yourEmail);
	}

	public void setSubject(String yourSubject) {
		sendKeys(pp.subject, yourSubject);
	}

	public void setMessage(String yourMessage) {
		sendKeys(pp.Message, yourMessage);
	}
	public void clickSendMessage() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.sendMessage));
		click(pp.sendMessage);
	}

	public String getAlertMessage() {

		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='alert alert-success alert-notification w-50 alert-dismissible']")));
	return alert.getText().replace("×", "").trim();
	}
	public void clickAddToCart() {
		click(pp.addToCartBtn);
	}
	public void clickWishListBtn() {
		click(pp.wishListBtn);
	}
	public String getAddTocartConfirmation() {
		wait.until(ExpectedConditions.visibilityOf(pp.sizeRequired));
	return pp.sizeRequired.getText();
	}
	public String getWishListConfirmation() {
		return wait.until(ExpectedConditions.visibilityOf(pp.wishList)).getText();
	}
	public void clickSoftwareBreadcrumb() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.softwareBreadCrumb));
		click(pp.softwareBreadCrumb);
	}
	public String getMadatoryFieldsMessage() {
		wait.until(ExpectedConditions.visibilityOf(pp.mandatoryFields));
	return pp.mandatoryFields.getText();
	}
	public void clickMinus() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.minusBtn));
		click(pp.minusBtn);
	}
	public void clickAddToCartbutton() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.addToCartButton));
		click(pp.addToCartButton);
	}
	public void viewCartPP() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.viewCartPopUpButton));
		click(pp.viewCartPopUpButton);
	}
	public void clickCompareBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.compareBtn));
		click(pp.compareBtn);
	}
	public String getConfirmationMessage() {
		return wait.until(ExpectedConditions.visibilityOf(pp.productComparisonMessage)).getText();
	}
	public void clickCart() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.cartButton));
		click(pp.cartButton);
	}
	public String getQuantityInCart() {
		return wait.until(ExpectedConditions.visibilityOf(pp.quantityField)).getAttribute("value");
	}

	public void clickViewCompare() {
		wait.until(ExpectedConditions.elementToBeClickable(pp.viewCompare));
		click(pp.viewCompare);
	}
}