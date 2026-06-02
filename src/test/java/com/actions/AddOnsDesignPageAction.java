package com.actions;

import org.openqa.selenium.WebDriver;

import com.driver.DriverClass;
import com.pages.AddOnsDesignsPage;

public class AddOnsDesignPageAction extends BaseAction {

	WebDriver driver = DriverClass.getDriver();
	AddOnsDesignsPage adp=new AddOnsDesignsPage();
	  public void launchWebUrl() {
	        driver.get("https://ecommerce-playground.lambdatest.io/");
	    }
	  
	  public void clickAddOns() {
		  click(adp.AddOns);
	  }
	  public void clickDesigns() {
		  waitForClickable(adp.designs);
	  }
	  public void clickDrawerLeft() {
		  scrollIntoView(adp.Drawerleft);
		  click(adp.Drawerleft);
	  }
	  public void getPanelTitle() {
		  waitForVisibility(adp.topcategories);
		  getText(adp.topcategories);
	  }
	  public void

}
