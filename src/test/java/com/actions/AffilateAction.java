package com.actions;

import java.util.Properties;

import com.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pages.*;

public class AffilateAction extends BaseAction {

    Properties prop = ConfigReader.getAffiliateProperties();

    AccountPage ap = new AccountPage(getDriver());
    AffilateAccountPage aap = new AffilateAccountPage(getDriver());

    public void clickAffilateAccountRegister() {
        getWait().until(ExpectedConditions.elementToBeClickable(ap.affilateAccountLink));
        click(ap.affilateAccountLink);
    }

    public void enterCompanyName(String company) {
        sendKeys(aap.company, company);
    }

    public void enterWebsite(String website) {
        sendKeys(aap.website, website);
    }

    public void enterTaxID(String tax) {
        sendKeys(aap.taxID, tax);
    }

    public void enterChequeName(String name) {
        sendKeys(aap.chequeName, name);
    }

    public void clickCheckBox() {
        click(aap.agreeCheckBox);
    }

    public void clickContinue() {
        click(aap.continueButton);
    }

    public String getAccountCreationMessage() {
        getWait().until(ExpectedConditions.visibilityOf(ap.accountCreatedMessage));
        return getText(ap.accountCreatedMessage);
    }

    public void enterAffilateDetails() {

    	sendKeys(aap.company, prop.getProperty("company"));
        sendKeys(aap.website, prop.getProperty("website"));
        sendKeys(aap.taxID, prop.getProperty("taxID"));
        sendKeys(aap.chequeName, prop.getProperty("chequeName"));
    }
}