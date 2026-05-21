package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[@class='list-unstyled m-0']//a[contains(text(),'Apple')]")
	public WebElement BrandName;

	@FindBy(xpath = "//span[contains(@class,'badge-success')]")
	public WebElement availability;

	@FindBy(xpath = "//span[contains(@class,'badge-danger')]")
	public WebElement availabilityOutOfStock;

	@FindBy(xpath = "//h3[@class='price-new mb-0']")
	public WebElement productPrice;

	@FindBy(xpath = "//h1[@class='h3']")
	public WebElement productTitle;

	@FindBy(xpath = "//div[@id='entry_216841']//input[@name='quantity']")
	public WebElement quantityBox;

	@FindBy(xpath = "//a[@aria-label='Ask Question']")
	public WebElement questionForm;

	@FindBy(xpath = "//input[@placeholder='Your name']")
	public WebElement name;

	@FindBy(xpath = "//input[@placeholder='Your email']")
	public WebElement email;

	@FindBy(xpath = "//input[@placeholder='Subject']")
	public WebElement subject;

	@FindBy(xpath = "//textarea[@placeholder='Message']")
	public WebElement Message;

	@FindBy(xpath = "//button[contains(normalize-space(),'Send message')]")
	public WebElement sendMessage;

	public By submissionConfirmation = By.xpath("//div[contains(@class,'alert') and contains(.,'successfully sent')]");


	@FindBy(xpath = "//div[@id='entry_216842']//button[@title='Add to Cart'][normalize-space()='Add to Cart']")
	public WebElement addToCartBtn;

	@FindBy(xpath = "//a[@class='btn btn-primary btn-block']")
	public WebElement addToCartButton;



	@FindBy(xpath = "(//i[contains(@class,'fa-heart')])[3]")
	public WebElement wishListBtn;

	@FindBy(xpath = "//a[contains(text(),'wish list')]")
	public WebElement wishList;


	@FindBy(xpath = "//div[contains(@class,'text-danger') and contains(text(),'Size required')]")
	public WebElement sizeRequired;

	@FindBy(xpath = "(//i[contains(@class,'fa-minus-circle')])[2]")
	public WebElement minusBtn;

	@FindBy(xpath = "(//div/child::input[@class='form-control'])[1]")
	public WebElement quantityField;


	@FindBy(xpath = "//li[@class='breadcrumb-item']//a[contains(text(),'Software')]")
	public WebElement softwareBreadCrumb;

	@FindBy(xpath = "//div[contains(text(),'Name must be between 3 and 32 characters!')]")
	public WebElement mandatoryFields;

	@FindBy(xpath = "//a[contains(text(),'View Cart')]")
	public WebElement viewCartPopUpButton;

	@FindBy(xpath = "//a[contains(text(),'Checkout')]")
	public WebElement cartButton;


	@FindBy(xpath = "//button[contains(text(),'Compare this Product')]")
	public WebElement compareBtn;

	@FindBy(xpath = "//p[contains(text(),'Success: You have added')]")
	public WebElement productComparisonMessage;

	@FindBy(xpath = "//a[@class='btn btn-secondary btn-block']")
	public WebElement viewCompare;

}
