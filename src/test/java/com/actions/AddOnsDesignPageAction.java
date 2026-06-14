package com.actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.driver.DriverClass;
import com.pages.AddOnsDesignsPage;

public class AddOnsDesignPageAction extends BaseAction {

	WebDriver driver = DriverClass.getDriver();
	AddOnsDesignsPage adp=new AddOnsDesignsPage(driver);
	Actions act = new Actions(driver);
	
	  public void launchWebUrl() {
	        driver.get("https://ecommerce-playground.lambdatest.io/");
	    }
	  
	  public void clickAddOns() {
		  act.moveToElement(adp.AddOns).perform();
		 
	  }
	  public void clickDesigns() {
		  waitForClickable(adp.designs);
		  click(adp.designs);
	  }
	  public void clickDrawerLeft() {
		  scrollIntoView(adp.Drawerleft);
		  waitForClickable(adp.Drawerleft);
		
	  }
	  public void leftpanel() {
		 
		  isDisplayed(adp.topcategories);
	  }
	  public void clickDrawerRight() {
		  scrollIntoView(adp.Drawerright);
		  click(adp.Drawerright);
	  }
	  public void rightpanel() {
		  isDisplayed(adp.rightpanel);
		  
			  
	  }
	  public void clickstickytop() {
		  scrollIntoView(adp.Stickytop);
		  click(adp.Stickytop);
	  }
	  public void Toppanel() {
		  isDisplayed(adp.toppanel);
	  }
	  public void clickstickybottom() {
		  scrollIntoView(adp.Stickybottom);
		  click(adp.Stickybottom);
	  }
	  public void bottompanel() {
		  isDisplayed(adp.bottompanel);
	  }
	  public void clickpopup() {
		  scrollIntoView(adp.popup);
		  click(adp.popup);
	  }
	  public void Popup() {
		  isDisplayed(adp.popupbox);
	  }
	  public void setEmail() {
		  sendKeys(adp.email, "jothika@gmail.com");
	  }
	  public void clickSubcribe() {
		  click(adp.subcribe);
	  }
	  public void clickAnyWidgets() {
		  click(adp.clickAnyWidgets);
	  }
	  public void handleAlert() {
		  getWait().until(ExpectedConditions.alertIsPresent());
		  Alert alert = driver.switchTo().alert();

	        String actualText = alert.getText();

	        Assert.assertTrue(actualText.contains(actualText));

	        alert.accept();
	  }
	  
}
