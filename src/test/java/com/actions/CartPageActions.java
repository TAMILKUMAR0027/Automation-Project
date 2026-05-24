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
	BaseAction ba = new BaseAction();
	WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), Duration.ofSeconds(15));
	CartPage cp = new CartPage(DriverClass.getDriver());

	public String getProductName() {
		ba.waitForVisibility(cp.productName);
		return ba.getText(cp.productName);
	}

	public void sendQuantity(String q) {
		ba.waitForVisibility(cp.quantityCount);
		ba.sendKeys(cp.quantityCount, q);

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

			System.out.println(e.getMessage());

		}
		ba.waitForVisibility(cp.quantityUpdatedMsg);
		return ba.getText(cp.quantityUpdatedMsg);
	}

	public void clickRemoveButton() {
		ba.waitForClickable(cp.CartRemoveButton);
		ba.click(cp.CartRemoveButton);
	}

	public String getCartEmptyMsg() {
		ba.waitForVisibility(cp.noCartProductMsg);
		return ba.getText(cp.noCartProductMsg);
	}

	public void clickESTLink() {
		ba.waitForClickable(cp.taxEstimateButton);
		ba.click(cp.taxEstimateButton);
	}

	public void selectCountryAndState(DataTable datatable) {
		ba.waitForVisibility(cp.dropDownopt1);
		List<Map<String, String>> data = datatable.asMaps(String.class, String.class);
		int countryIndex = Integer.parseInt(data.get(0).get("country"));
		int stateIndex = Integer.parseInt(data.get(0).get("state"));
		Select country = new Select(cp.dropDownopt1);
		ba.click(cp.dropDownopt1);
		country.selectByIndex(countryIndex);
		Select state = new Select(cp.dropDownopt2);
		ba.click(cp.dropDownopt2);
		state.selectByIndex(stateIndex);
	}

	public void clickgetQuotesButton() {
		ba.click(cp.getQuotesBUtton);
	}

	public void checkRadioButton() {
		ba.waitForClickable(cp.radioButton);
		if (!cp.radioButton.isSelected()) {
			ba.click(cp.radioButton);
		}
	}

	public void clickApplyShippingButton() {
		ba.click(cp.applyShippingButton);
	}

	public String getSuccessETMsg() {
		ba.waitForVisibility(cp.successMsgET);
		return ba.getText(cp.successMsgET);
	}

	public List<String> storeAllProduct() {
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("Alert Found: " + alert.getText());
			alert.accept();
		} catch (Exception e) {

			System.out.println("No Alert Present");
		}
		wait.until(ExpectedConditions.visibilityOfAllElements(cp.allProductName));
		return cp.getProductName();
	}

	public void quantitySend(DataTable db) {
		wait.until(ExpectedConditions.visibilityOf(cp.quantityCount));
		List<Map<String, String>> data = db.asMaps(String.class, String.class);
		ba.sendKeys(cp.quantityCount, data.get(0).get("quantity"));
	}

}
