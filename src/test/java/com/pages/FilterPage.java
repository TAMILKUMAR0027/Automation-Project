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

    @FindBy(xpath = "//div[@class='carousel-item active']//img[@title='iPod Touch']")
    public WebElement iPodTouchProduct;

    @FindBy(xpath = "//select[@id='input-limit-212402']")
    public WebElement dropdownBtn;

    @FindBy(xpath = "//div[@class='product-thumb']//h4")
    public List<WebElement> allProducts;

    public List<String> getProductNames() {

        List<String> productNames = new ArrayList<>();
        for (WebElement product : allProducts) {
            productNames.add(product.getText());
        }
        return productNames;
    }
}