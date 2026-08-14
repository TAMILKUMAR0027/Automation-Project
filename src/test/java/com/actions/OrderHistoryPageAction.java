package com.actions;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.AccountPage;
import com.pages.LoginPage;
import com.pages.OrderHistoryPage;

public class OrderHistoryPageAction extends BaseAction {
	BaseAction ba = new BaseAction();
	WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(15));
	AccountPage ap = new AccountPage(DriverClass.getDriver());
	OrderHistoryPage ohp=new OrderHistoryPage(DriverClass.getDriver());
	
	public String orderIdgetHP() {
		ba.waitForVisibility(ohp.orderIdHistory);
		return ba.getText(ohp.orderIdHistory); 
	}
	
	public void clickViewOrderHistoryBtn() {
		ba.waitForVisibility(ohp.viewBtn);
		ba.click(ohp.viewBtn);
	}
	public String orderInformationPage() {
		ba.waitForVisibility(ohp.orderIdInfo);
		return ba.getText(ohp.orderIdInfo);
	}
	
	public void clickReturn() {
		ba.waitForVisibility(ohp.returnBtn);
		ba.scrollIntoView(ohp.returnBtn);
		ba.jsClick(ohp.returnBtn);
	}
	
	public void clickReturnReason() {
		ba.waitForVisibility(ohp.returnReason);
		ba.click(ohp.returnReason);
	}
	
	public void clickReturnDetailSubmit() {
		ba.waitForVisibility(ohp.returnDeatilsSubmitBtn);
		ba.click(ohp.returnDeatilsSubmitBtn);
	}
	
	public String getProductReturnSuccess() {
		ba.waitForVisibility(ohp.returnSuccessMsg);
		return ba.getText(ohp.returnSuccessMsg);
	}
	
	
	


}
