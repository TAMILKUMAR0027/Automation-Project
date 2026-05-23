package com.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import com.actions.LoginPageAction;
import com.actions.LogoutPageAction;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Logout  {
	WebDriver driver;
	LogoutPageAction lga=new LogoutPageAction();
	LoginPageAction lpa=new LoginPageAction();
	private static final Logger log = LogManager.getLogger(Logout.class);
	
	@Given("the user is logged into the application with valid email and password")
	public void the_user_is_logged_into_the_application_with_valid_email_and_password(io.cucumber.datatable.DataTable dataTable) {
		  List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
            String email = data.get(0).get("email");
		    String password = data.get(0).get("password");
            lpa.clickMyAccountLink();
		    lpa.enterEmailAndPass(email,password);
		    lpa.clickLoginButton();
		}
	

	@Given("the user is on the My Account page")
	public void the_user_is_on_the_my_account_page() {
		String actual = lpa.LoginSuccessMsg();
		String expected = "My Account";
		try {
			Assert.assertEquals(actual, expected);
			log.info("Login Successful");
		} catch (AssertionError e) {
			log.error("Login is failing Due to Wrong credentials,Error: " + e.getMessage());
			throw e;
		}
	}

	@When("the user clicks on Logout link")
	public void the_user_clicks_on_logout_link() {
		lga.Clickaccount();
		lga.clickLogout();
	    
	}

	
	@Then("the user should receive an intimation message regarding logout")
	public void the_user_should_receive_an_intimation_message_regarding_logout() {
		 String actual=lga.Message();
		 String expected="Account Logout";
		 try {
				Assert.assertEquals(actual, expected);
				log.info("Logut successful");
			}
			catch(AssertionError e) {
				log.error("Logout failed"+e.getMessage());
			}
	}

	
	
}
