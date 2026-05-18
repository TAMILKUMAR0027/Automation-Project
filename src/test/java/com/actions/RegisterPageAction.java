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

	public void clickMyAccount() {
		try {
			lp.myAccLink.isDisplayed();
			lp.myAccLink.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(lp.myAccLink)).click();

		}
	}

	public void registerLinkClick() {
		wait.until(ExpectedConditions.visibilityOf(ap.registerLink)).click();
	}

	public void enterPersonalDetails(DataTable dataTable) {
		wait.until(ExpectedConditions.visibilityOf(rp.fname));
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		rp.fname.sendKeys(data.get(0).get("fname"));
		rp.lname.sendKeys(data.get(0).get("lname"));
		rp.email.sendKeys(data.get(0).get("email"));
		rp.telephone.sendKeys(data.get(0).get("telephone"));
		rp.pass.sendKeys(data.get(0).get("password"));
		rp.cpass.sendKeys(data.get(0).get("confirmpassword"));
	}

	public void clickPrivacyPolicy() {
		if (!rp.privacyPolicyCheckBox.isSelected()) {
			rp.privacyPolicyCheckBox.click();
		}
	}

	public void continueButton() {
		rp.continueButton.click();
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
		rp.lname.sendKeys(data.get(0).get("lname"));
		rp.email.sendKeys(data.get(0).get("email"));
		rp.telephone.sendKeys(data.get(0).get("telephone"));
		rp.pass.sendKeys(data.get(0).get("password"));
		rp.cpass.sendKeys(data.get(0).get("confirmpassword"));
	}

}
