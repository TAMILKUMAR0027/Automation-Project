package com.actions;

import com.pages.AddsOnPage;

public class AddOnPageAction extends BaseAction{
	AddsOnPage aop= new AddsOnPage(getDriver());
	LaunchPageAction lpa=new LaunchPageAction();
	public void sendName(String name) {
		// TODO Auto-generated method stub
		sendKeys(aop.formName, name);
	}
	public void sendEmail(String email) {
		sendKeys(aop.formEmail, email);
	}
	public void sendSubject(String subject) {
		sendKeys(aop.formSubject, subject);
	}
	public void sendMessage(String Message) {
		sendKeys(aop.formMessage, Message);
	}
	public void submitForm() {
		click(aop.clickSubmit);
	}
	public String getConfirmationMessage() {
		scrollIntoView(aop.confirmMessage);
		return getText(aop.confirmMessage).trim().replaceAll("x","");
	}
	
}
