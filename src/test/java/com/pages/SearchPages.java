package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPages extends BasePage  {

    public SearchPages(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@id='entry_217822']//input[@placeholder='Search For Products']")
    public WebElement SearchBar;

    @FindBy(xpath = "//div[@id='entry_212469']//div[contains(@class,'product-thumb')]//h4/a")
    public List<WebElement> result;

    @FindBy(xpath = "//div[@id = 'entry_212469']//p")
    public WebElement resulterr;

    @FindBy(xpath = "//div[@class='mz-filter-value both ']//label")
    public WebElement manufacturer;

}
