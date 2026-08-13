package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    // =========================================================
    // REGISTER
    // =========================================================

    @FindBy(linkText = "Register")
    public WebElement registerLink;

    // =========================================================
    // EDIT ACCOUNT INFORMATION
    // =========================================================

    @FindBy(xpath = "//a[normalize-space()='Edit your account information']")
    public WebElement editAccInfo;

    @FindBy(xpath = "//input[@id='input-telephone']")
    public WebElement telephoneEdit;

    @FindBy(xpath = "//input[@value='Continue']")
    public WebElement eContinueButton;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    public WebElement editSuccess;

    // =========================================================
    // AFFILIATE ACCOUNT
    // =========================================================

    @FindBy(xpath = "//i[@class='fas fa-2x mb-1 fa-bullhorn']")
    public WebElement affilateAccountLink;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    public WebElement accountCreatedMessage;

    // =========================================================
    // EDIT ACCOUNT - INVALID DETAILS
    // =========================================================

    @FindBy(xpath = "//input[@id='input-firstname']")
    public WebElement fnameEI;

    @FindBy(xpath = "//input[@id='input-lastname']")
    public WebElement lnameEI;

    @FindBy(xpath = "//input[@id='input-email']")
    public WebElement emailEI;

    @FindBy(xpath = "//div[@class='col-sm-10']/child::input/following-sibling::div")
    public WebElement errorMsgEI;

    // =========================================================
    // NEWSLETTER
    // =========================================================

    @FindBy(xpath = "//div[@class='row']/child::div[5]/child::a[contains(text(),'Subscribe')]")
    public WebElement newsLetterSubscribe;

    @FindBy(id = "input-newsletter-yes")
    public WebElement yesRadioNewsLetter;

    @FindBy(xpath = "//label[@for='input-newsletter-no']")
    public WebElement noRadioBtn;

    @FindBy(xpath = "//div[@class='buttons clearfix']/child::div[2]/child::input")
    public WebElement clickContinueNL;

    @FindBy(xpath = "//div[@id='account-account']/child::div[1]")
    public WebElement sucessMsgNL;

    // =========================================================
    // ADDRESS BOOK
    // =========================================================

    @FindBy(xpath = "//a[normalize-space()='Address Book']/i")
    public WebElement AddressBook;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    public WebElement newAddress;

    @FindBy(xpath = "//tbody/tr[2]/td[2]/a[2]")
    public WebElement DeleteAddressInBookBtn;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    public WebElement DeleteAddressSuccessMessage;

    // =========================================================
    // REWARD POINTS
    // =========================================================

    @FindBy(xpath = "//a[text()=' Your Reward Points']")
    public WebElement RewardPointsLink;

    @FindBy(xpath = "//h1[text()='Your Reward Points']")
    public WebElement RewardSuccess;

    // =========================================================
    // MY VOUCHER / GIFT CERTIFICATE
    // =========================================================

    @FindBy(xpath = "//span[normalize-space()='My voucher']")
    public WebElement myVoucher;

    @FindBy(xpath = "//p[contains(text(),'Thank you for purchasing a gift certificate! Once ')]")
    public WebElement voucherSuccessMsg;

    // Invalid recipient email error message
    @FindBy(xpath = "//div[@class='text-danger']")
    public WebElement invalidEmailMessage;

    // =========================================================
    // ORDER HISTORY
    // =========================================================

    @FindBy(xpath = "//a[text()=' Order History']/i")
    public WebElement orderHistoryLink;
}