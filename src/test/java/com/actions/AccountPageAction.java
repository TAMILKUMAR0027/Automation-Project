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
	
	public void clickEditAccInfo()
	{
		ba.waitForVisibility(ap.editAccInfo);
		ba.click(ap.editAccInfo);
	}
	
	public void updateDetails(DataTable db)
	{
		    ba.waitForVisibility(ap.telephoneEdit);
		    List<Map<String, String>> data = db.asMaps(String.class, String.class);
		    String telephone = data.get(0).get("telephone");
		    ba.clear(ap.telephoneEdit);
		    ba.sendKeys(ap.telephoneEdit, telephone);
		
	}
	
	public void clickEContinueBtn()
	{
		ba.click(ap.eContinueButton);
	}
	
	public String successMsgE()
	{
		 ba.waitForVisibility(ap.editSuccess);
		 return ba.getText(ap.editSuccess);
	}
	

}
