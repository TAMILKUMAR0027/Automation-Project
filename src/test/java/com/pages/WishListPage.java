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


    // Toast body paragraph — for add-to-wishlist success
    @FindBy(xpath = "//div[@id='notification-box-top']//div[contains(@class,'toast-body')]//p")
    public WebElement successNotification;

    // Fallback — any p tag inside notification box
    @FindBy(xpath = "//div[@id='notification-box-top']//p")
    public WebElement successNotificationFallback;

    // Wishlist link inside add-toast popup
    @FindBy(xpath = "//div[@id='notification-box-top']//a[contains(text(),'wish list')]")
    public WebElement wishlistPopupLink;



    // "Success: You have modified your wish list!"
    @FindBy(xpath = "//div[contains(@class,'alert-success') and contains(@class,'alert-dismissible')]")
    public WebElement removalSuccessAlert;

    // ========================= WISHLIST PAGE =========================

    @FindBy(xpath = "//h1[contains(text(),'My Wish List')]")
    public WebElement myWishListTitle;

    // ALL product name links in wishlist table — every row
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr//td[2]//a")
    public List<WebElement> wishListProductNames;

    // ALL price cells in wishlist table — every row
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr//td[5]")
    public List<WebElement> wishListProductPrices;

    // ALL rows in wishlist table
    @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr")
    public List<WebElement> wishListRows;

    // Wishlist nav button via account sidebar
    @FindBy(xpath = "//aside[@id='column-right']/child::div//a[4]/following-sibling::a[1]")
    public WebElement wishListbtn;
}