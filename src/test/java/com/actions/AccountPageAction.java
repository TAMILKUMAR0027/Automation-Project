package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.AccountPage;
import com.pages.LoginPage;

import io.cucumber.datatable.DataTable;

public class AccountPageAction {
	BaseAction ba = new BaseAction();
	WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(15));
	AccountPage ap = new AccountPage(DriverClass.getDriver());
	LoginPage lp = new LoginPage(DriverClass.getDriver());

	public void setVemail(String email) {
		ba.waitForVisibility(lp.LoginEmail);
		ba.sendKeys(lp.LoginEmail, email);
	}

	public void setVpass(String pass) {
		ba.waitForVisibility(lp.LoginPassword);
		ba.sendKeys(lp.LoginPassword, pass);
	}

	public void clickEditAccInfo() {
		ba.waitForVisibility(ap.editAccInfo);
		ba.click(ap.editAccInfo);
	}

	public void updateDetails(DataTable db) {
		ba.waitForVisibility(ap.telephoneEdit);
		List<Map<String, String>> data = db.asMaps(String.class, String.class);
		String telephone = data.get(0).get("telephone");
		ba.clear(ap.telephoneEdit);
		ba.sendKeys(ap.telephoneEdit, telephone);

	}

	public void clickEContinueBtn() {
		ba.click(ap.eContinueButton);
	}

	public String successMsgE() {
		ba.waitForVisibility(ap.editSuccess);
		return ba.getText(ap.editSuccess);
	}

	public void ivUpdateDetails(DataTable db) {
		ba.waitForVisibility(ap.fnameEI);
		List<Map<String, String>> data = db.asMaps(String.class, String.class);
		String fname = data.get(0).get("fname");
		ba.clear(ap.fnameEI);
		ba.sendKeys(ap.fnameEI, fname);
		String lname = data.get(0).get("lname");
		ba.clear(ap.lnameEI);
		ba.sendKeys(ap.lnameEI, lname);
		String email = data.get(0).get("email");
		ba.clear(ap.emailEI);
		ba.sendKeys(ap.emailEI, email);
		ba.clear(ap.telephoneEdit);
	}

	public String AccountEditMsg() {
		ba.waitForVisibility(ap.errorMsgEI);
		return ba.getText(ap.errorMsgEI);
	}
	
	public void clickSubscribeNewsLetter()
	{
		ba.waitForVisibility(ap.newsLetterSubscribe);
		ba.click(ap.newsLetterSubscribe);
	}
	
	public void clickNlRadioButton()
	{
		try
		{
			ba.waitForVisibility(ap.yesRadioNewsLetter);
			ba.click(ap.yesRadioNewsLetter);
		}
		catch(Exception e)
		{
			ba.waitForVisibility(ap.noRadioBtn);
			ba.click(ap.noRadioBtn);
		}
	}
	
	public void clickNlContinueBtn()
	{
		ba.click(ap.clickContinueNL);
	}
	public String successMsgNLSubscribe()
	{
		ba.waitForVisibility(ap.sucessMsgNL);
		return ba.getText(ap.sucessMsgNL);
	}
	public void clickAddressBook()
	{
		ba.waitForVisibility(ap.AddressBook);
		ba.click(ap.AddressBook);
	}
	public void clickNewAddress()
	{
		ba.waitForVisibility(ap.newAddress);
		ba.click(ap.newAddress);
	}

	public void clickDeleteAddress() {
		ba.waitForVisibility(ap.DeleteAddressInBookBtn);
		ba.click(ap.DeleteAddressInBookBtn);
	}
	
	public String DelteSuccessMsg() {
		ba.waitForVisibility(ap.DeleteAddressSuccessMessage);
		return ba.getText(ap.DeleteAddressSuccessMessage);
	}
	
	public void clickRewardPointsLink() {
		ba.waitForVisibility(ap.RewardPointsLink);
		ba.click(ap.RewardPointsLink);
		
	}
	
	public String rewardPointRedirection() {
		ba.waitForVisibility(ap.RewardSuccess);
		return ba.getText(ap.RewardSuccess);
	}
	
	public void clickMyVoucher()
	{
		ba.waitForVisibility(ap.myVoucher);
		ba.click(ap.myVoucher);
	}
	public void moveToElementOfMyAccount() {
		ba.waitForVisibility(lp.myAccLink);
		ba.moveToElement(lp.myAccLink);
	}
	public String getVoucherSuccessMsg()
	{
		ba.waitForVisibility(ap.voucherSuccessMsg);
		return ba.getText(ap.voucherSuccessMsg);
	}
	public String getInvalidEmailMessage() {
		ba.waitForVisibility(ap.invalidEmailMessage);
		return ba.getText(ap.invalidEmailMessage);
	}

}

