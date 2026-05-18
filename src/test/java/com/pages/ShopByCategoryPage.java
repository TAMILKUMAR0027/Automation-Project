package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopByCategoryPage extends BasePage {

    public ShopByCategoryPage(WebDriver driver) {

        super(driver);
    }


    @FindBy(xpath = "//a[contains(.,'Shop by Category')]")
    public WebElement shopByCategoryMenu;
    
    
    @FindBy(xpath = "//span[@class='title' and normalize-space()='Desktops and Monitors']")
    public WebElement desktopsCategory;


    @FindBy(linkText = "Web Cameras")
    public WebElement cameras;


    @FindBy(linkText = "Phone, Tablets & Ipod")
    public WebElement tablets;


    @FindBy(linkText = "Laptops & Notebooks")
    public WebElement laptops;
}