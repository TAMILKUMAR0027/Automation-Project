package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Add inside CheckoutPage class
    @FindBy(id = "input-payment-address-new")
    public WebElement newAddressRadio;

    @FindBy(id = "input-account-register")
    public WebElement registerAccountRadio;

    // EmptyCartCheckout: navbar cart — target the parent div, not SVG
    @FindBy(xpath = "//div[@id='entry_217825']//div[@class='icon svg-icon']")
    public WebElement svgNavbarCart;

    // EmptyCartCheckout: sidebar checkout button
    @FindBy(xpath = "//a[normalize-space()='Checkout']")
    public WebElement sidebarCheckoutBtn;

    // HP LP3065 product image
    @FindBy(xpath = "//a[@id='mz-product-grid-image-47-212469']//div[@class='carousel-item active']//img[@title='HP LP3065']")
    public WebElement hpProductImage;

    // Shopping Cart link inside popup
    @FindBy(xpath = "//div[@id='notification-box-top']//a[contains(text(),'shopping cart')]")
    public WebElement shoppingCartPopupLink;

    // Checkout button on cart page
    @FindBy(xpath = "//div[@class='buttons d-flex']//a[text()='Checkout']")
    public WebElement cartPageCheckoutBtn;

    // ═══════════════════════════════════════════════
    //   LOGIN CHECKOUT billing address fields
    // ═══════════════════════════════════════════════

    @FindBy(xpath = "//input[@id='input-payment-firstname']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='input-payment-lastname']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='input-payment-company']")
    public WebElement companyInput;

    @FindBy(xpath = "//input[@id='input-payment-address-1']")
    public WebElement address1Input;

    @FindBy(xpath = "//input[@id='input-payment-city']")
    public WebElement cityInput;

    @FindBy(xpath = "//input[@id='input-payment-postcode']")
    public WebElement postcodeInput;

    @FindBy(xpath = "//select[@id='input-payment-country']")
    public WebElement countrySelect;

    @FindBy(xpath = "//select[@id='input-payment-zone']")
    public WebElement regionStateSelect;

    @FindBy(xpath = "//input[@id='input-shipping-address-same']")
    public WebElement sameBillingAddressCheckbox;

    @FindBy(xpath = "//button[@id='button-save']")
    public WebElement continueCheckoutBtn;


    @FindBy(xpath = "//button[@id='button-confirm']")
    public WebElement confirm;

    @FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
    public WebElement orderConfirmationMessage;

    // ═══════════════════════════════════════════════
    //   REGISTER CHECKOUT registration form fields
    // ═══════════════════════════════════════════════

    @FindBy(xpath = "//input[@id='input-payment-firstname']")
    public WebElement regFirstNameInput;

    @FindBy(xpath = "//input[@id='input-payment-lastname']")
    public WebElement regLastNameInput;

    @FindBy(xpath = "//input[@id='input-payment-email']")
    public WebElement regEmailInput;

    @FindBy(xpath = "//input[@id='input-payment-telephone']")
    public WebElement regTelephoneInput;

    @FindBy(xpath = "//input[@id='input-payment-password']")
    public WebElement regPasswordInput;

    @FindBy(xpath = "//input[@id='input-payment-confirm']")
    public WebElement regConfirmPasswordInput;

    @FindBy(xpath = "//input[@id='input-payment-company']")
    public WebElement regCompanyInput;

    @FindBy(xpath = "//input[@id='input-payment-address-1']")
    public WebElement regAddress1Input;

    @FindBy(xpath = "//input[@id='input-payment-address-2']")
    public WebElement regAddress2Input;

    @FindBy(xpath = "//input[@id='input-payment-city']")
    public WebElement regCityInput;

    @FindBy(xpath = "//input[@id='input-payment-postcode']")
    public WebElement regPostcodeInput;

    @FindBy(xpath = "//select[@id='input-payment-country']")
    public WebElement regCountrySelect;

    @FindBy(xpath = "//select[@id='input-payment-zone']")
    public WebElement regRegionStateSelect;

    // Empty cart message
    @FindBy(xpath = "//div[@id='content']//p")
    public WebElement emptyCartMessage;
}