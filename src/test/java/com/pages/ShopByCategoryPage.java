package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopByCategoryPage extends BasePage {

    public ShopByCategoryPage(WebDriver driver) {

        super(driver);
    }

    // -------------------------------------------------------------------------
    // FIX 1: The original xpath //a[@aria-label='Shop by Category'] was not
    // matching any visible element on the page. The Shop By Category button
    // uses a drawer-toggle pattern (data-toggle="mz-pure-drawer"). Using a
    // more reliable CSS selector that targets this pattern scoped to the nav.
    // -------------------------------------------------------------------------

    @FindBy(xpath = "a[data-toggle='mz-pure-drawer']")
    public WebElement shopByCategoryMenu;


    // -------------------------------------------------------------------------
    // FIX 3: The original xpath used 'Desktops and Monitors' (with "and") but
    // the actual DOM text is 'Desktops & Monitors' (with ampersand). Corrected
    // to match what the live site renders in the dropdown menu.
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//span[normalize-space()='Desktops & Monitors']")
    public WebElement desktopsCategory;


    // Web Cameras — no change needed, text matches the live site

    @FindBy(xpath = "//span[normalize-space()='Web Cameras']")
    public WebElement cameras;


    // Phone, Tablets & Ipod — no change needed, text matches the live site

    @FindBy(xpath = "//span[normalize-space()='Phone, Tablets & Ipod']")
    public WebElement tablets;


    // Laptops & Notebooks — no change needed, text matches the live site

    @FindBy(xpath = "//span[normalize-space()='Laptops & Notebooks']")
    public WebElement laptops;
}
