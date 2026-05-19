package com.actions;

import com.driver.DriverClass;
import com.pages.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckoutAction extends BaseAction {

    CheckoutPage checkoutPage;
    WebDriverWait wait;
    JavascriptExecutor js;

    public CheckoutAction() {
        checkoutPage = new CheckoutPage(DriverClass.getDriver());
        wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(20));
        js = (JavascriptExecutor) DriverClass.getDriver();
    }

    // ── Login before LoginCheckout scenario ──
    public void loginAsRegisteredUser() {
        DriverClass.getDriver().get(
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/login");

        if (DriverClass.getDriver().getCurrentUrl().contains("route=account/account")) {
            System.out.println("Already logged in.");
            return;
        }

        LoginPageAction lpa = new LoginPageAction();
        lpa.enterEmailAndPass("testlogin@gmail.com", "testlogin");
        lpa.clickLoginButton();

        String actual = lpa.LoginSuccessMsg();
        String expected = "My Account";
        Assert.assertEquals(actual, expected, "Login failed!");

        System.out.println("Login successful");
    }

    public void clickHpProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.hpProductImage));
        click(checkoutPage.hpProductImage);
    }

    public void clickShoppingCartFromPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.shoppingCartPopupLink));
        js.executeScript("arguments[0].click();", checkoutPage.shoppingCartPopupLink);
        System.out.println("Clicked Shopping Cart link from popup");
    }

    public void clickCheckoutFromCartPage() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.cartPageCheckoutBtn));
        js.executeScript("arguments[0].click();", checkoutPage.cartPageCheckoutBtn);
        Thread.sleep(2000); // Give time for checkout page to load
    }

    public void clickNavbarCart() {
        By navCartDiv = By.xpath("//div[@id='entry_217825']//div[@class='icon svg-icon']");
        WebElement cartDiv = wait.until(ExpectedConditions.elementToBeClickable(navCartDiv));
        new Actions(DriverClass.getDriver())
                .moveToElement(cartDiv)
                .click()
                .perform();
    }

    public void clickSidebarCheckout() {
        By checkoutLink = By.xpath("//a[normalize-space()='Checkout']");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutLink));
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isCheckoutOrLoginPageDisplayed() {
        String currentUrl = DriverClass.getDriver().getCurrentUrl();
        return currentUrl.contains("checkout") || currentUrl.contains("account/login");
    }

    // ==================== FIXED METHODS ====================

    public void selectNewAddress() {
        waitForCheckoutPageToLoad();

        By newAddressRadio = By.xpath("//label[@for = 'input-payment-address-new']");  // Best locator
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(newAddressRadio));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", radio);
        if (!radio.isSelected()) {
            js.executeScript("arguments[0].click();", radio);
        }
        System.out.println("Selected: I want to use a new address");
    }

    public void selectRegisterAccount() {
        waitForCheckoutPageToLoad();

        By registerRadio = By.xpath("//label[@for = 'input-account-register']");  // Best locator
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(registerRadio));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", radio);
        if (!radio.isSelected()) {
            js.executeScript("arguments[0].click();", radio);
        }
        System.out.println("Selected: Register Account");
    }

    // Robust wait for checkout page
    private void waitForCheckoutPageToLoad() {
        wait.until(ExpectedConditions.urlContains("checkout"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//form[contains(@id,'form')] | //div[contains(@class,'checkout')]")));
    }

    // ── Fill Billing Address ──
    public void enterBillingDetails(String firstName, String lastName, String company,
                                    String address1, String city, String postcode,
                                    String country, String region) {

        wait.until(ExpectedConditions.visibilityOf(checkoutPage.firstNameInput));

        sendKeys(checkoutPage.firstNameInput, firstName);
        sendKeys(checkoutPage.lastNameInput, lastName);
        sendKeys(checkoutPage.companyInput, company);
        sendKeys(checkoutPage.address1Input, address1);
        sendKeys(checkoutPage.cityInput, city);
        sendKeys(checkoutPage.postcodeInput, postcode);

        new Select(checkoutPage.countrySelect).selectByVisibleText(country);

        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.regionStateSelect));
        new Select(checkoutPage.regionStateSelect).selectByVisibleText(region);
    }

    public void clickSameBillingAddress() {
        By sameAddrLabel = By.xpath("//label[@for='input-shipping-address-same']");
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(sameAddrLabel));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);

        if (!checkoutPage.sameBillingAddressCheckbox.isSelected()) {
            js.executeScript("arguments[0].click();", label);
        }
    }

    public void selectCashOnDelivery() {
        By codLabel = By.xpath("//label[@for='input-payment-method-cod']");
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(codLabel));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
        js.executeScript("arguments[0].click();", label);
    }

    public void selectFlatRate() {
        By flatLabel = By.xpath("//label[@for='input-shipping-method-flat.flat']");
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(flatLabel));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
        js.executeScript("arguments[0].click();", label);
    }

    public void clickTermsAndConditions() {
        By termsLabel = By.xpath("//label[@for='input-agree']");
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(termsLabel));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
        js.executeScript("arguments[0].click();", label);
    }

    public void continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.continueCheckoutBtn));
        js.executeScript("arguments[0].click();", checkoutPage.continueCheckoutBtn);
    }

    public boolean isOrderPlacedSuccessfully() {
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.confirm)).click();
        return wait.until(ExpectedConditions.visibilityOf(checkoutPage.orderConfirmationMessage))
                .isDisplayed();
    }

    public void enterRegistrationDetails(String firstName, String lastName, String email,
                                         String telephone, String password, String confirmPassword,
                                         String company, String address1, String address2,
                                         String city, String postCode, String country, String region) {

        wait.until(ExpectedConditions.visibilityOf(checkoutPage.regFirstNameInput));

        sendKeys(checkoutPage.regFirstNameInput, firstName);
        sendKeys(checkoutPage.regLastNameInput, lastName);
        sendKeys(checkoutPage.regEmailInput, email);
        sendKeys(checkoutPage.regTelephoneInput, telephone);
        sendKeys(checkoutPage.regPasswordInput, password);
        sendKeys(checkoutPage.regConfirmPasswordInput, confirmPassword);
        sendKeys(checkoutPage.regCompanyInput, company);
        sendKeys(checkoutPage.regAddress1Input, address1);
        sendKeys(checkoutPage.regAddress2Input, address2);
        sendKeys(checkoutPage.regCityInput, city);
        sendKeys(checkoutPage.regPostcodeInput, postCode);

        new Select(checkoutPage.regCountrySelect).selectByVisibleText(country);

        wait.until(driver -> new Select(checkoutPage.regRegionStateSelect).getOptions().size() > 1);
        new Select(checkoutPage.regRegionStateSelect).selectByVisibleText(region);
    }

    public void agreeToPrivacyPolicy() {
        By privacyLabel = By.xpath("//label[@for='input-account-agree']");
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(privacyLabel));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
        js.executeScript("arguments[0].click();", label);
    }

    public boolean isEmptyCartMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(checkoutPage.emptyCartMessage))
                .isDisplayed();
    }
}
