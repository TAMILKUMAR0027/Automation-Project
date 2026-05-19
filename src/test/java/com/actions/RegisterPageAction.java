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

	public void setFname(String fname) {
		wait.until(ExpectedConditions.visibilityOf(rp.fname));
		rp.fname.sendKeys(fname);
	}
	public void setLname(String lname) {
		wait.until(ExpectedConditions.visibilityOf(rp.lname));
		rp.lname.sendKeys(lname);
	}
	public void setEmail(String email) {
		rp.email.sendKeys(email);
	}
	public void setTelephone(String telephone) {
		rp.telephone.sendKeys(telephone);
	}
	public void setPassword(String pass) {
		rp.pass.sendKeys(pass);
	}
	public void setConfirmPassword(String cpass) {
		rp.cpass.sendKeys(cpass);
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
		try {

			wait.until(ExpectedConditions.alertIsPresent());

			driver.switchTo().alert().accept();

		} catch (Exception e) {

			System.out.println("No alert present");

		}

		return wait.until(ExpectedConditions.visibilityOf(rp.emptyFieldMsg)).getText();
	}

	

}
