package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    // Wishlist button (Header icon)
    @FindBy(xpath = "//a[contains(@href,'account/wishlist')]")
    public WebElement wishlistButton;

    // Notification message after adding product to wishlist
    @FindBy(xpath = "//div[@id='notification-box-top']//p")
    public WebElement notificationMessage;

    // Success message close button (X)
    @FindBy(xpath = "//div[@id='notification-box-top']//button[contains(@class,'close')]")
    public WebElement closeNotification;

    // Product name in wishlist table
    @FindBy(xpath = "//div[@id='content']//table//td[@class='text-left']/a")
    public WebElement wishlistProductName;

    // Remove button in wishlist
    @FindBy(xpath = "//div[@id='content']//a[contains(@data-original-title,'Remove')]")
    public WebElement removeButton;

    // Empty wishlist message
    @FindBy(xpath = "//div[@id='content']//p[contains(text(),'Your wish list is empty')]")
    public WebElement emptyWishlistMessage;
}