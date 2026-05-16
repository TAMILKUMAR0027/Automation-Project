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

		return driver.findElement(
				By.xpath("//img[@alt='Poco Electro']"))
				.isDisplayed();
	}

	@FindBy(xpath = "//div/a[normalize-space()='Shop by Category']")
	public WebElement shopByCategories;

	@FindBy(xpath = "//span[normalize-space()='Desktops and Monitors']")
	public WebElement Desktop;
	@FindBy(xpath = "//a[@id='mz-product-listing-image-39218404-0-0']//div[@class='carousel-item active']//img[@title='Canon EOS 5D']")
	public WebElement canonES5Product;
}