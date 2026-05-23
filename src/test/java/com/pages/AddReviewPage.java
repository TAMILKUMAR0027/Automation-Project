
package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddReviewPage extends BasePage {

	public AddReviewPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@id='mz-product-listing-image-39218404-0-1']//div[@class='carousel-item active']//img[@title='HTC Touch HD']")
	public WebElement product;

	@FindBy(xpath = "//a[contains(text(),'Reviews')]")
	public WebElement reviewtab;

	@FindBy(css = "input[name='rating'][value='5']")
	public WebElement rating;

	@FindBy(css = "input#input-name")
	public WebElement reviewname;

	@FindBy(xpath="//div[@class='form-group required']/child::textarea")
	public WebElement reviewtext;

	@FindBy(xpath = "//button[text()='Write Review']")
	public WebElement writeReview;

	@FindBy(xpath = "//h5[@class='content-title mb-3']/following-sibling::div[1]")
	public WebElement successMessage;

	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	public WebElement warningMessage;
}
