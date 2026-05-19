package com.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.CartPage;

import io.cucumber.datatable.DataTable;

public class CartPageActions {
	WebDriver driver = DriverClass.getDriver();
	BaseAction ba = new BaseAction();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	CartPage cp = new CartPage(driver);

	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOf(cp.productName)).getText();
	}

	public void sendQuantity(String q) {
		wait.until(ExpectedConditions.visibilityOf(cp.quantityCount));
		ba.sendKeys(cp.quantityCount,q);
		
	}

	public void clickQUpdateButton() {
		ba.click(cp.updateButton);
	}

	public String getQuantitySuccessMsg() {
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("Alert Message: " + alert.getText());
			alert.accept();
		} catch (Exception e) {

			System.out.println("No Alert Present");

		}

		return wait.until(ExpectedConditions.visibilityOf(cp.quantityUpdatedMsg)).getText();
	}

	public void clickRemoveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(cp.CartRemoveButton));
		ba.click(cp.CartRemoveButton);
	}

	public String getCartEmptyMsg() {
		return wait.until(ExpectedConditions.visibilityOf(cp.noCartProductMsg)).getText();
	}

	public void clickESTLink() {
		wait.until(ExpectedConditions.elementToBeClickable(cp.taxEstimateButton));
		ba.click(cp.taxEstimateButton);
	}

	public void selectCountryAndState(DataTable datatable) {
		wait.until(ExpectedConditions.visibilityOf(cp.dropDownopt1));
		List<Map<String, String>> data = datatable.asMaps(String.class, String.class);
		int countryIndex = Integer.parseInt(data.get(0).get("country"));
		int stateIndex = Integer.parseInt(data.get(0).get("state"));
		Select country = new Select(cp.dropDownopt1);
		country.selectByIndex(countryIndex);
		Select state = new Select(cp.dropDownopt2);
		state.selectByIndex(stateIndex);
	}

	public void clickgetQuotesButton() {
		ba.click(cp.getQuotesBUtton);
	}

	public void checkRadioButton() {
		wait.until(ExpectedConditions.elementToBeClickable(cp.radioButton));
		if (!cp.radioButton.isSelected()) {
			ba.click(cp.radioButton);
		}
	}

	public void clickApplyShippingButton() {
		ba.click(cp.applyShippingButton);
	}

	public String getSuccessETMsg() {
		return wait.until(ExpectedConditions.visibilityOf(cp.successMsgET)).getText();
	}
	
	 public List<String> storeAllProduct() {
		    try {
		        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		        System.out.println("Alert Found: " + alert.getText());
		        alert.accept();
		    }catch (Exception e) {

		        System.out.println("No Alert Present");
		    }
		    wait.until(ExpectedConditions.visibilityOfAllElements(cp.allProductName));
		    return cp.getProductName();
	    }
	 
	 public void quantitySend(DataTable db)
	 {
		wait.until(ExpectedConditions.visibilityOf(cp.quantityCount));
		List<Map<String, String>> data = db.asMaps(String.class, String.class);
		ba.sendKeys(cp.quantityCount,data.get(0).get("quantity"));
	 }

}
