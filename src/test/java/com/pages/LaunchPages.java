package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchPages {

	WebDriver driver;

	public LaunchPages(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void launchApplication(String url) {
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public boolean getlogo() {

		return driver.findElement(By.xpath("//img[@alt='Poco Electro']")).isDisplayed();
	}

	@FindBy(xpath = "//a[normalize-space()='Shop by Category']")
	public WebElement shopByCategories;

	@FindBy(xpath = "//div/span[contains(text(),'Desk')]")
	public WebElement Desktop;
	@FindBy(xpath = "//a[@id='mz-product-listing-image-39218404-0-0']//div[@class='carousel-item active']//img[@title='Canon EOS 5D']")
	public WebElement canonES5Product;

	@FindBy(xpath = "//a[@id='mz-product-listing-image-39218404-0-3']//div[@class='carousel-item active']//img[@title='HP LP3065']")
	public WebElement topCollectionProduct;

	@FindBy(xpath = "(//a[contains(@href,'product/compare')])[2]")
	public WebElement productCompareLink;
	@FindBy(xpath = "//a[@id='mz-product-listing-image-37218399-0-0']//div[@class='carousel-item active']//img[@title='iMac']")
	public WebElement iMac;
	@FindBy(xpath = "//a[@id='mz-product-listing-image-39218404-0-3']//div[@class='carousel-item active']//img[@title='HP LP3065']")
	public WebElement HPLP3065Product;
	@FindBy(xpath = "(//button[@title='Add to Cart'])[12]")
	public WebElement cartOne;

	@FindBy(xpath = "(//button[@title='Add to Cart'])[15]")
	public WebElement cartTwo;

	@FindBy(xpath = "(//button[@title='Add to Cart'])[14]")
	public WebElement cartThree;
}
