package com.actions;

import java.util.Properties;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pages.AccountPage;
import com.pages.AffilateAccountPage;
import com.utils.ConfigReader;

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

    public void enterTaxID(String taxID) {
        sendKeys(aap.taxID, taxID);
    }

    public void enterChequeName(String chequeName) {
        sendKeys(aap.chequeName, chequeName);
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

    // =====================================================
    // MAIN METHOD (FIXED)
    // =====================================================
    public void enterAffilateDetails() {


        String company = prop.getProperty("company");
        String website = prop.getProperty("website");
        String taxID = prop.getProperty("taxID");
        String chequeName = prop.getProperty("chequeName");

      
    }
    public void clickCustomAffilateLink() {
		click(aap.CustomAffilateLink);
	}
    public void seTTrackingProduct() {
    	sendKeys(aap.TrackingLinkGenerator, "HTC Touch HD");
    	click(aap.productLink);
    }
    public String getGeneratedLink() {
		return (aap.trackingLink).getAttribute("value");
	}
    
}