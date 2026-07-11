package com.actions;

import com.pages.GiftCertificatePage;

public class GiftCertificateAction extends BaseAction {
	GiftCertificatePage gcp=new GiftCertificatePage();
	public void enterToName(String toName) {
		gcp.toNameInput.sendKeys(toName);
	}

	public void enterToEmail(String toEmail) {
		gcp.toEmailInput.sendKeys(toEmail);
	}

	public void selectGiftCertificateTheme() {
		gcp.giftCertificateThemeRadioButton.click();
	}

	public void checkAgreeCheckbox() {
		gcp.agreeCheckBox.click();
	}

	public void clickContinueButton() {
		gcp.continueButton.click();
	}

}
