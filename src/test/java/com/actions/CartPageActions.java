package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.CartPage;

import io.cucumber.datatable.DataTable;

public class CartPageActions {
	WebDriver driver=DriverClass.getDriver();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(15));
	CartPage cp = new CartPage(driver);
	
	public String getProductName()
	{
		return wait.until(ExpectedConditions.visibilityOf(cp.productName)).getText();
	}
	
	public void sendQuantity(String q)
	{
		wait.until(ExpectedConditions.visibilityOf(cp.quantityCount)).sendKeys(q);
	}
	
	public void clickQUpdateButton()
	{
		cp.updateButton.click();
	}
	public String getQuantitySuccessMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(cp.quantityUpdatedMsg)).getText();
	}
	
	public void clickRemoveButton()
	{
		wait.until(ExpectedConditions.visibilityOf(cp.CartRemoveButton)).click();
	}
	
	public String getCartEmptyMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(cp.noCartProductMsg)).getText();
	}
	
	public void clickESTLink()
	{
		wait.until(ExpectedConditions.visibilityOf(cp.taxEstimateButton)).click();
	}
	
	public void selectCountryAndState(DataTable datatable)
	{
		wait.until(ExpectedConditions.visibilityOf(cp.dropDownopt1));
		List<Map<String, String>> data = datatable.asMaps(String.class, String.class);
		int countryIndex = Integer.parseInt(data.get(0).get("country"));
		int stateIndex = Integer.parseInt(data.get(0).get("state"));
		Select country = new Select(cp.dropDownopt1);
		country.selectByIndex(countryIndex);
		Select state = new Select(cp.dropDownopt2);
		state.selectByIndex(stateIndex);
	}
	
	public void clickgetQuotesButton()
	{
		cp.getQuotesBUtton.click();
	}
	
	public void checkRadioButton()
	{
		wait.until(ExpectedConditions.visibilityOf(cp.radioButton)).click();
	}
	
	public void clickApplyShippingButton()
	{
		cp.applyShippingButton.click();
	}
	
	public String getSuccessETMsg()
	{
		return wait.until(ExpectedConditions.visibilityOf(cp.successMsgET)).getText();
	}
	
}
