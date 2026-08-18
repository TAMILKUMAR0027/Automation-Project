package com.actions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pages.AddOnsDesignsPage;
import com.pages.AddsOnPage;
import com.stepDefinitions.AddOnsDesigns;

public class AddOnPageAction extends BaseAction{
	AddsOnPage aop= new AddsOnPage(getDriver());
	LaunchPageAction lpa=new LaunchPageAction();
	AddOnsDesignsPage aodp=new AddOnsDesignsPage(getDriver());
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
	public String getErrorMessage() {
		getWait().until(ExpectedConditions.visibilityOf(aop.emailErrorMessage));
		return getText(aop.emailErrorMessage);
	}
	public void moveDarkHeading() {
		getWait().until(ExpectedConditions.visibilityOf(aodp.head));
		Actions act= new Actions(getDriver());
		act.moveToElement(aodp.head);
	}
	public List<String> getData(){
		 // Write code here that turns the phrase above into concrete actions
	    List<String> data= new ArrayList<String>();
	    for(WebElement i:aodp.Headings) {
	    	data.add(i.getText());
	    }
	    return data;
	}
	public List<String> getValues(){
		List<String> data= new ArrayList<String>();
	    for(WebElement i:aodp.datas) {
	    	data.add(i.getText());
	    }
	    return data;
	}
}
