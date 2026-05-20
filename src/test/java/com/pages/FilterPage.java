package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPage extends BasePage {

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[@for='mz-fm-0-8']")
    public WebElement appleBtn;
    @FindBy(xpath = "//select[@id='input-limit-212402']")
    public WebElement dropdownBtn;
    @FindBy(xpath = "//label[@for='mz-fss-0--1']")
    public WebElement inStockAvailabilityOption;
    @FindBy(xpath = "//label[@for='mz-fss-0-5']")
    public WebElement OutOfStockAvailabilityOption;
    @FindBy(xpath = "//div[@id='entry_216826']//span[text()='In Stock']")
    public WebElement inStockFilter;
    @FindBy(xpath = "//div[@class='product-thumb']//h4")
    public List<WebElement> allProducts;
    @FindBy(xpath = "//div[@class='carousel-item active']//img[@title='iPod Touch']")
    public WebElement iPodTouchProduct;
    @FindBy(xpath = "//a[@id='mz-product-grid-image-51-212408']//div[@class='carousel-item active']//img[@title='Canon EOS 5D']")
    public WebElement canonProduct;
    @FindBy(xpath = "//img[@title='HTC Touch HD']")
    public WebElement HTCTouchHDProduct;
    @FindBy(xpath = "//a[contains(@id,'mz-product-grid-image') and @title='HP LP3065']")
    public WebElement hpProduct;
    @FindBy(xpath = "//*[@id='mz-filter-panel-0-0']/div/div[1]/span[2]")
    public WebElement slider;
    @FindBy(xpath = "//div[@id='mz-filter-panel-0-0']//input[@placeholder='Maximum Price']")
    public WebElement input;
    @FindBy(xpath = "//h1[@class='h4']")
    public WebElement softwaretTitle;
    @FindBy(xpath = "//span[contains(@class,'badge-success')]")
    public WebElement instockAvailability;
    @FindBy(xpath = "//span[contains(@class,'badge-danger')]")
    public WebElement outstockAvailability;
    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : allProducts) {
            productNames.add(product.getText());
        }
        return productNames;
    }
}