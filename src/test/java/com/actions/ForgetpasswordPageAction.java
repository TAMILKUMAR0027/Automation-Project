package com.actions;

import java.time.Duration;
import java.util.Properties;

import com.driver.DriverClass;
import com.pages.ForgetPasswordPage;
import com.pages.LoginPage;
import com.utils.ConfigReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgetpasswordPageAction extends BaseAction {

    WebDriver driver = DriverClass.getDriver();

    LoginPage lp = new LoginPage(driver);
    ForgetPasswordPage fp = new ForgetPasswordPage(driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

   
    Properties prop = ConfigReader.getForgetPasswordProperties();

    public void launchWebUrl() {
        driver.get("https://ecommerce-playground.lambdatest.io/");
    }

    public void clickMyAccountLink() {
        wait.until(ExpectedConditions.visibilityOf(lp.myAccLink)).click();
    }

    public void clickForgotPassword() {
        click(fp.forgetpassword);
    }

    
    public void enterEmail(String emailKey) {
        wait.until(ExpectedConditions.visibilityOf(fp.email)).clear();
        fp.email.sendKeys(prop.getProperty(emailKey));
    }

    public void clickContinueButton() {
        click(fp.button);
    }

    public String getSuccessMessage() {
        return getText(fp.message);
    }

    public String getWarningMessage() {
        return getText(fp.warningmsg);
    }

    public String expectedSuccessMessage() {
        return prop.getProperty("message");
    }

    public String expectedErrorMessage() {
        return prop.getProperty("error_message");
    }
}