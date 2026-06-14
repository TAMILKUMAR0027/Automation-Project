package com.actions;


import com.pages.AddressBookPage;

public class AddressBookAction extends BaseAction {

	AddressBookPage abp=new AddressBookPage(getDriver());
	public void enterAddressDetails() {

        sendKeys(abp.firstName, "Tamil");
        sendKeys(abp.lastName, "Kumar");
        sendKeys(abp.address1, "123 Anna Nagar");
        sendKeys(abp.city, "Salem");
        sendKeys(abp.postCode, "636001");
        click(abp.regionState);
        sendKeys(abp.regionState,"conway");

        click(abp.continueBtn);
    }

    public String getSuccessMessage() {
        return getText(abp.successMessage);
    }

	

}
