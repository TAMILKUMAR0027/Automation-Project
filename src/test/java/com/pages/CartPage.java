package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//td[@class='text-left']//a[contains(text(),'HP LP3065')]")
	public WebElement productName;

	@FindBy(xpath = "//input[contains(@name,'quantity')]")
	public WebElement quantityCount;

	@FindBy(xpath = "//button[@type='submit' and contains(@class,'btn-primary')]")
	public WebElement updateButton;

	@FindBy(css = ".alert.alert-success.alert-dismissible")
	public WebElement quantityUpdatedMsg;

	@FindBy(xpath = "//button[@class='btn btn-danger']")
	public WebElement CartRemoveButton;

	@FindBy(xpath = "//div[@id='content']//p[contains(text(),'Your shopping cart is empty!')]")
	public WebElement noCartProductMsg;

	@FindBy(xpath = "//h5[normalize-space()='Estimate Shipping & Taxes']")
	public WebElement taxEstimateButton;

	@FindBy(xpath = "//select[@id='input-country']")
	public WebElement dropDownopt1;

	@FindBy(xpath = "//select[@id='input-zone']")
	public WebElement dropDownopt2;

	@FindBy(xpath = "//button[@id='button-quote']")
	public WebElement getQuotesBUtton;

	@FindBy(xpath = "//input[@name='shipping_method']")
	public WebElement radioButton;

	@FindBy(xpath = "//button[@id='button-shipping']")
	public WebElement applyShippingButton;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	public WebElement successMsgET;

	@FindBy(xpath = "//td[@class='text-left']/a")
	public List<WebElement> allProductName;

	public List<String> getProductName() {
		List<String> pn = new ArrayList<>();
		for (WebElement product : allProductName) {
			pn.add(product.getText());
		}
		return pn;
	}

}
