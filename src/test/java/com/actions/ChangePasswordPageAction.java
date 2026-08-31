package com.actions;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ChangePasswordPage;

public class ChangePasswordPageAction {
	BaseAction ba = new BaseAction();
	WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(15));
	ChangePasswordPage cp=new ChangePasswordPage(DriverClass.getDriver());
	
	public void enterCurrentPassword(String currentPassword) {
		ba.waitForVisibility(cp.passwordField);
		ba.sendKeys(cp.passwordField, currentPassword);
	}
	public void enterNewPassword(String newPassword) {
		ba.waitForVisibility(cp.newPasswordField);
		ba.sendKeys(cp.newPasswordField, newPassword);
	}
	public void clickContinueBtn() {
		ba.waitForVisibility(cp.passwordContinueBtn);
		ba.click(cp.passwordContinueBtn);
	}
	public String getPasswordChangeSuccessMsg() {
		ba.waitForVisibility(cp.passwordChangeSuccessMsg);
		return ba.getText(cp.passwordChangeSuccessMsg);
	}
	public String getPasswordFieldWarnMsg() {
		ba.waitForVisibility(cp.emptyPasswordMsg);
		return ba.getText(cp.emptyPasswordMsg);
	}
	public String getConfirmPasswordFieldWarnMsg() {
		ba.waitForVisibility(cp.emptyPasswordConfirmMsg);
		return ba.getText(cp.emptyPasswordConfirmMsg);
	}

}
