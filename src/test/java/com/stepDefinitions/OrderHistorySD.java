package com.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.actions.AccountPageAction;
import com.actions.OrderHistoryPageAction;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderHistorySD {
	
	AccountPageAction apa = new AccountPageAction();
	OrderHistoryPageAction ohpa=new OrderHistoryPageAction();
	private static final Logger log = LogManager.getLogger(OrderHistorySD.class);
	public String orderId;
	
	
	@When("The User Clicks on View Button")
	public void the_user_clicks_on_view_button() {
		orderId=ohpa.orderIdgetHP();
		ohpa.clickViewOrderHistoryBtn();
	}

	@Then("The Order Information Page Should Be Visisble")
	public void the_order_information_page_should_be_visisble() {
		String actual=ohpa.orderInformationPage();
		Assert.assertTrue(actual.contains(orderId));
	}

}
