package com.actions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.LoginPage;
import com.pages.LogoutPage;


public class LogoutPageAction extends BaseAction {

	LogoutPage lout;

	LoginPage lp;

	WebDriverWait wait;

	Actions act;

	public LogoutPageAction() {

		lout = new LogoutPage(DriverClass.getDriver());

		lp = new LoginPage(DriverClass.getDriver());

		wait = new WebDriverWait(
				DriverClass.getDriver(),
				Duration.ofSeconds(15));

		act = new Actions(DriverClass.getDriver());
	}

	public void launchWebUrl() {

		DriverClass.getDriver()
				.get("https://ecommerce-playground.lambdatest.io/");
	}

	public void clickMyAccountLink() {

		waitForVisibility(lp.myAccLink);

		click(lp.myAccLink);
	}

	public void enterEmailAndPass(
			String username,
			String password) {

		sendKeys(lp.LoginEmail, username);

		sendKeys(lp.LoginPassword, password);
	}

	public void clickLoginButton() {

		click(lp.LoginButton);
	}

	public String LoginSuccessMsg() {

		return getText(lp.LoginSuccessMessage);
	}

	public void Clickaccount() {

		act.moveToElement(lp.myAccLink).perform();
	}

	public void clickLogout() {

		// Before
//		waitForVisibility(lout.logoutBtn);
//
//		click(lout.logoutBtn);


		// After

		// Re-locate fresh every time — avoids StaleElementReferenceException
		// after page navigation during the E2E session

		getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Logout')]"))).click();

	}

	public String Message() {

		return getText(lout.Logoutmsg);
	}
}