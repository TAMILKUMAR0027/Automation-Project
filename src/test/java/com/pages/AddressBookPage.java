package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage extends BasePage {

    public AddressBookPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@id='input-address-1']")
    public WebElement address1;

    @FindBy(xpath = "//input[@id='input-city']")
    public WebElement city;

    @FindBy(xpath = "//input[@id='input-postcode']")
    public WebElement postCode;

    @FindBy(xpath = "//select[@id='input-zone']")
    public WebElement regionState;

    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement continueBtn;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    public WebElement successMessage;

}