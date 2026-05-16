package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    // ========================= NAVIGATION =========================

    @FindBy(xpath = "//img[@alt='Poco Electro']")
    public WebElement homeLogo;

    @FindBy(xpath = "//a[contains(@class,'navbar-brand')]")
    public WebElement homeLogoAlt;

    // ========================= TOP PRODUCTS =========================

    @FindBy(xpath = "//h3[contains(text(),'Top Products')]")
    public WebElement topProductsHeading;

    // iMac card — anchored to product name, immune to dynamic ID changes
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]")
    public WebElement imacListingBox;

    // iMac wishlist button — relative to iMac card
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]//button[contains(@class,'wishlist')]")
    public WebElement imacWishlistBtn;

    // ========================= TOP COLLECTION =========================

    @FindBy(xpath = "//h3[contains(text(),'Top Collection')]")
    public WebElement topCollectionHeading;

    // Apple Cinema 30 card — anchored to product name
    // Note: The site shows 'Apple Cinema 30"' with inch symbol — adjust if needed
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[contains(normalize-space(),'Apple Cinema')]]")
    public WebElement appleCinemaProduct;

    // Apple Cinema 30 wishlist button — relative to its card
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[contains(normalize-space(),'Apple Cinema')]]//button[contains(@class,'wishlist')]")
    public WebElement appleCinemaWishlistBtn;

    // iPod Nano card — anchored to product name
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iPod Nano']]")
    public WebElement ipodNanoProduct;

    // iPod Nano wishlist button — relative to its card
    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iPod Nano']]//button[contains(@class,'wishlist')]")
    public WebElement ipodNanoWishlistBtn;

    // ========================= SUCCESS POPUP =========================

    // Toast notification paragraph text
    @FindBy(xpath = "//div[@id='notification-box-top']//div[contains(@class,'toast-body')]//p")
    public WebElement successNotification;

    // Wishlist link inside popup
    @FindBy(xpath = "//div[@id='notification-box-top']//a[contains(text(),'wish list')]")
    public WebElement wishlistPopupLink;

    // ========================= WISHLIST PAGE =========================

    @FindBy(xpath = "//h1[contains(text(),'My Wish List')]")
    public WebElement myWishListTitle;

    // Product name in wishlist table row 1
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr[1]//td[2]//a")
    public WebElement wishListProductName;

    // Product price in wishlist table row 1
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr[1]//td[5]")
    public WebElement wishListProductPrice;
}