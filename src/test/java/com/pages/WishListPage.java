package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    // ============================================================
    // HOME
    // ============================================================

    public By homeLogo =
            By.xpath("//img[@alt='Poco Electro']");

    public By homeLogoAlt =
            By.xpath("//a[contains(@class,'navbar-brand')]");


    // ============================================================
    // TOP PRODUCTS
    // ============================================================

    public By topProductsHeading =
            By.xpath("//h3[contains(text(),'Top Products')]");


    // ============================================================
    // iMAC
    // ============================================================

    public By imacListingBox =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[normalize-space()='iMac']]"
            );

    public By imacWishlistBtn =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[normalize-space()='iMac']]" +
                            "//button[contains(@class,'wishlist')]"
            );


    // ============================================================
    // TOP COLLECTION
    // ============================================================

    public By topCollectionHeading =
            By.xpath("//h3[contains(text(),'Top Collection')]");


    // ============================================================
    // APPLE CINEMA
    // ============================================================

    public By appleCinemaProduct =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[contains(normalize-space(),'Apple Cinema')]]"
            );

    public By appleCinemaWishlistBtn =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[contains(normalize-space(),'Apple Cinema')]]" +
                            "//button[contains(@class,'wishlist')]"
            );


    // ============================================================
    // iPOD NANO
    // ============================================================

    public By ipodNanoProduct =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[normalize-space()='iPod Nano']]"
            );

    public By ipodNanoWishlistBtn =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[normalize-space()='iPod Nano']]" +
                            "//button[contains(@class,'wishlist')]"
            );


    // ============================================================
    // CANON EOS 5D   (NEW — was missing, caused UnknownProductException)
    // ============================================================

    public By canonEOS5DProduct =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[normalize-space()='Canon EOS 5D']]"
            );

    public By canonEOS5DWishlistBtn =
            By.xpath(
                    "//div[contains(@class,'product-thumb') " +
                            "and .//a[normalize-space()='Canon EOS 5D']]" +
                            "//button[contains(@class,'wishlist')]"
            );


    // ============================================================
    // NOTIFICATIONS
    // ============================================================

    public By successNotification =
            By.xpath(
                    "//div[@id='notification-box-top']" +
                            "//div[contains(@class,'toast-body')]//p"
            );

    public By successNotificationFallback =
            By.xpath(
                    "//div[@id='notification-box-top']//p"
            );


    // ============================================================
    // WISHLIST POPUP
    // ============================================================

    public By wishlistPopupLink =
            By.xpath(
                    "//a[@class='btn btn-secondary btn-block']"
            );


    // ============================================================
    // REMOVAL SUCCESS
    // ============================================================

    public By removalSuccessAlert =
            By.xpath(
                    "//div[contains(@class,'alert-success') " +
                            "and contains(@class,'alert-dismissible')]"
            );


    // ============================================================
    // MY WISHLIST PAGE
    // ============================================================

    public By myWishListTitle =
            By.xpath("//h1[contains(text(),'My Wish List')]");


    // ============================================================
    // WISHLIST TABLE
    // ============================================================

    public By wishListProductNames =
            By.xpath(
                    "//table[@class='table table-hover border']" +
                            "//tbody//tr//td[2]"
            );

    public By wishListProductPrices =
            By.xpath(
                    "//table[@class='table table-hover border']" +
                            "//tbody//tr//td[5]"
            );

    public By wishListRows =
            By.xpath(
                    "//table[@class='table table-hover border']" +
                            "//tbody//tr"
            );


    // ============================================================
    // WISHLIST SIDEBAR
    // ============================================================

    public By wishListbtn =
            By.xpath(
                    "//aside[@id='column-right']" +
                            "//a[contains(@href,'account/wishlist')]"
            );


    // ============================================================
    // SEARCH
    // ============================================================

    public By SearchBar =
            By.xpath(
                    "//div[@id='entry_217822']" +
                            "//input[@placeholder='Search For Products']"
            );


    // ============================================================
    // iPOD SHUFFLE
    // ============================================================

    public By ipodShuffleProduct =
            By.xpath(
                    "//a[@id='mz-product-grid-image-34-212469']" +
                            "//div[@class='carousel-item active']" +
                            "//img[@title='iPod Shuffle']"
            );

    public By ipodShuffleWishlistBtn =
            By.xpath(
                    "//div[@id='image-gallery-216811']" +
                            "//button"
            );
}