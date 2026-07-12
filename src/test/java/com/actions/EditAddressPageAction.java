package com.actions;

import com.pages.EditAddressPage;

public class EditAddressPageAction extends BaseAction {
	
	EditAddressPage eap=new EditAddressPage(getDriver());
	
	public void enterAddressChange(String fname,String lname,String AddressOne,String city) {
		clear(eap.firstName);
        sendKeys(eap.firstName, fname);

        clear(eap.lastName);
        sendKeys(eap.lastName, lname);

        clear(eap.AddressOne);
        sendKeys(eap.AddressOne, AddressOne);

        clear(eap.city);
        sendKeys(eap.city, city);
	}
	
	public void clickEditSubmit() {
		click(eap.EditAddressContinueBtn);
	}
	
	public String getEditAddressSuccessMsg() {
		return getText(eap.EditAddressSuccessMsg);
	}
}
