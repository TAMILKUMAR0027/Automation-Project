package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    // Home Logo
    @FindBy(xpath = "//img[@alt='Poco Electro']")
    public WebElement homeLogo;

    @FindBy(xpath = "//a[contains(@class,'navbar-brand')]")
    public WebElement homeLogoAlt;

    // Top Products Section Heading
    @FindBy(xpath = "//h3[contains(text(),'Top Products')]")
    public WebElement topProductsHeading;

    // iMac product card — located by product NAME, not hardcoded ID
    // Finds the card that contains an <a> or text matching "iMac"
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]")
    public WebElement imacListingBox;

    // iMac Image (fallback hover target)
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]//img")
    public WebElement imacImage;

    // Wishlist button inside iMac card — relative to product name, no hardcoded ID
    // Matches <button class="wishlist-XXXXX ..."> inside the iMac card
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]//button[contains(@class,'wishlist')]")
    public WebElement imacWishlistBtn;

    // Success Notification
    @FindBy(xpath = "//div[@id='notification-box-top']//p")
    public WebElement successNotification;

    // Wishlist Link inside Popup
    @FindBy(xpath = "//div[@id='notification-box-top']//a[contains(text(),'wish list')]")
    public WebElement wishlistPopupLink;

    // My Wishlist Title
    @FindBy(xpath = "//h1[contains(text(),'My Wish List')]")
    public WebElement myWishListTitle;

    // Wishlist Product Name
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr[1]//td[2]//a")
    public WebElement wishListProductName;

    // Wishlist Product Price
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr[1]//td[5]")
    public WebElement wishListProductPrice;
}