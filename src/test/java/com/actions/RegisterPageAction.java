package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.AccountPage;
import com.pages.LoginPage;
import com.pages.RegisterPage;

import io.cucumber.datatable.DataTable;

public class RegisterPageAction {
	WebDriver driver = DriverClass.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	AccountPage ap = new AccountPage(driver);
	LoginPage lp = new LoginPage(driver);
	RegisterPage rp = new RegisterPage(driver);
	BaseAction ba = new BaseAction();
	public void clickMyAccount() {
		try {
			lp.myAccLink.isDisplayed();
			ba.click(lp.myAccLink);
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(lp.myAccLink));
			ba.click(lp.myAccLink);

		}
	}

	public void registerLinkClick() {
		wait.until(ExpectedConditions.visibilityOf(ap.registerLink));
		ba.click(ap.registerLink);
	}

	public void enterPersonalDetails(DataTable dataTable) {
		wait.until(ExpectedConditions.visibilityOf(rp.fname));
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		ba.sendKeys(rp.fname, data.get(0).get("fname"));
		//rp.fname.sendKeys(data.get(0).get("fname"));
		ba.sendKeys(rp.lname, data.get(0).get("lname"));
		//rp.lname.sendKeys(data.get(0).get("lname"));
		ba.sendKeys(rp.email, data.get(0).get("email"));
		//rp.email.sendKeys(data.get(0).get("email"));
		ba.sendKeys(rp.telephone, data.get(0).get("telephone"));
		//rp.telephone.sendKeys(data.get(0).get("telephone"));
		ba.sendKeys(rp.pass, data.get(0).get("password"));
        //rp.pass.sendKeys(data.get(0).get("password"));
		ba.sendKeys(rp.cpass, data.get(0).get("confirmpassword"));
		//rp.cpass.sendKeys(data.get(0).get("confirmpassword"));
	}

	public void clickPrivacyPolicy() {
		if (!rp.privacyPolicyCheckBox.isSelected()) {
			ba.click(rp.privacyPolicyCheckBox);
		}
	}

	public void continueButton() {
		ba.click(rp.continueButton);
	}

	public String registerSuccess() {
		return wait.until(ExpectedConditions.visibilityOf(rp.rSuccess)).getText();
	}

	public String uncheckPPMsg() {
		return wait.until(ExpectedConditions.visibilityOf(rp.errMsgPP)).getText();
	}

	public String fieldEmptyWmsg() {
		return wait.until(ExpectedConditions.visibilityOf(rp.emptyFieldMsg)).getText();
	}

	public void enterPersonalDetailsone(DataTable dataTable) {
		wait.until(ExpectedConditions.visibilityOf(rp.fname));
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		ba.sendKeys(rp.lname, data.get(0).get("lname"));
		ba.sendKeys(rp.email, data.get(0).get("email"));
		ba.sendKeys(rp.telephone, data.get(0).get("telephone"));
		ba.sendKeys(rp.pass, data.get(0).get("password"));
		ba.sendKeys(rp.cpass, data.get(0).get("confirmpassword"));
	}

}
