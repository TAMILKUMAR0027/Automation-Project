package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//img[@alt='Poco Electro']")
    public WebElement homeLogo;

    @FindBy(xpath = "//a[contains(@class,'navbar-brand')]")
    public WebElement homeLogoAlt;

    @FindBy(xpath = "//h3[contains(text(),'Top Products')]")
    public WebElement topProductsHeading;

    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]")
    public WebElement imacListingBox;

    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iMac']]//button[contains(@class,'wishlist')]")
    public WebElement imacWishlistBtn;

    @FindBy(xpath = "//h3[contains(text(),'Top Collection')]")
    public WebElement topCollectionHeading;

    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[contains(normalize-space(),'Apple Cinema')]]")
    public WebElement appleCinemaProduct;

    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[contains(normalize-space(),'Apple Cinema')]]//button[contains(@class,'wishlist')]")
    public WebElement appleCinemaWishlistBtn;

    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iPod Nano']]")
    public WebElement ipodNanoProduct;

    @FindBy(xpath = "//div[contains(@class,'product-thumb') and .//a[normalize-space()='iPod Nano']]//button[contains(@class,'wishlist')]")
    public WebElement ipodNanoWishlistBtn;


    // ========================= TOAST NOTIFICATION =========================

    @FindBy(xpath = "//div[@id='notification-box-top']//div[contains(@class,'toast-body')]//p")
    public WebElement successNotification;

    @FindBy(xpath = "//div[@id='notification-box-top']//p")
    public WebElement successNotificationFallback;

    @FindBy(xpath = "//div[@id='notification-box-top']//a[contains(text(),'wish list')]")
    public WebElement wishlistPopupLink;


    // ========================= REMOVE SUCCESS ALERT =========================

    @FindBy(xpath = "//div[contains(@class,'alert-success') and contains(@class,'alert-dismissible')]")
    public WebElement removalSuccessAlert;


    // ========================= WISHLIST PAGE =========================

    @FindBy(xpath = "//h1[contains(text(),'My Wish List')]")
    public WebElement myWishListTitle;

    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr//td[2]//a")
    public List<WebElement> wishListProductNames;

    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr//td[5]")
    public List<WebElement> wishListProductPrices;

    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr")
    public List<WebElement> wishListRows;


    // ✅ FIXED: Stable wishlist sidebar button
    @FindBy(xpath = "//aside[@id='column-right']//a[contains(@href,'account/wishlist')]")
    public WebElement wishListbtn;


    // ========================= SEARCH =========================

    @FindBy(xpath = "//div[@id='entry_217822']//input[@placeholder='Search For Products']")
    public WebElement SearchBar;

    @FindBy(xpath = "//a[@id='mz-product-grid-image-34-212469']//div[@class='carousel-item active']//img[@title='iPod Shuffle']")
    public WebElement ipodShuffleProduct;

    @FindBy(xpath = "//div[@id='image-gallery-216811']//button[@title='Add to Wish List']")
    public WebElement ipodShuffleWishlistBtn;
}