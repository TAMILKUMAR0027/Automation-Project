package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.driver.DriverClass;

public class AddOnsDesignsPage extends BasePage {
	public AddOnsDesignsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='AddOns']")
	public WebElement AddOns;

	@FindBy(xpath = "//span[normalize-space()='Designs']")
	public WebElement designs;

	@FindBy(xpath = "//div[@id='entry_215006']/child::a")
	public WebElement Drawerleft;

	@FindBy(xpath = "//div[@id='mz-component-1626147655']/child::h5")
	public WebElement topcategories;

	@FindBy(xpath = "//div[@id='entry_215007']/child::a")
	public WebElement Drawerright;

	@FindBy(xpath = "//div[@id='entry_215089']")
	public WebElement rightpanel;

	@FindBy(xpath = "//div[@id='entry_215008']/child::a")
	public WebElement Stickytop;

	@FindBy(xpath = "//div[@id='entry_215083']")
	public WebElement toppanel;

	@FindBy(xpath = "//div[@id='entry_215009']/child::a")
	public WebElement Stickybottom;

	@FindBy(xpath = "//div[@id='entry_215086']")
	public WebElement bottompanel;

	@FindBy(xpath = "//div[@id='entry_215010']/child::a")
	public WebElement popup;

	@FindBy(xpath = "//div[@id='entry_215094']")
	public WebElement popupbox;
	@FindBy(xpath = "//h4[normalize-space()='Dark heading']")
	public WebElement head;
	@FindBy(xpath = "//thead[@class=\"thead-dark\"]/tr/th")
	public List<WebElement> Headings;
	@FindBy(xpath = "//table[.//thead[@class='thead-dark']]//tbody/child::tr/td")
	public List<WebElement> datas;

}
