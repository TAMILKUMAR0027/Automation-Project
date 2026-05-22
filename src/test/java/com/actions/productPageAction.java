package com.actions;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ProductPage;

public class productPageAction extends BaseAction {

	WebDriver driver = DriverClass.getDriver();
	ProductPage pp = new ProductPage(driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	private static Logger log = LogManager.getLogger(productPageAction.class);

	public String getBrandName() {

		try {

			log.info("Getting brand name");

			waitForVisibility(pp.BrandName);

			String brand = getText(pp.BrandName);

			log.info("Brand name fetched successfully");

			return brand;

		} catch (Exception e) {

			log.error("Failed to get brand name", e);
			throw new RuntimeException("Failed to get brand name", e);
		}
	}

	public String getInstockAvailability() {

		try {

			log.info("Getting in stock availability");

			waitForVisibility(pp.availability);

			String availability = getText(pp.availability);

			log.info("In stock availability fetched successfully");

			return availability;

		} catch (Exception e) {

			log.error("Failed to get in stock availability", e);
			throw new RuntimeException("Failed to get in stock availability", e);
		}
	}

	
	public String getOutStockAvailability() {

		try {

			log.info("Getting out of stock availability");

			waitForVisibility(pp.availabilityOutOfStock);

			String availability = getText(pp.availabilityOutOfStock);

			log.info("Out of stock availability fetched successfully");

			return availability;

		} catch (Exception e) {

			log.error("Failed to get out of stock availability", e);
			throw new RuntimeException("Failed to get out of stock availability", e);
		}
	}

	public String getPrice() {

		try {

			log.info("Getting product price");

			waitForVisibility(pp.productPrice);

			String price = getText(pp.productPrice);

			log.info("Product price fetched successfully");

			return price;

		} catch (Exception e) {

			log.error("Failed to get product price", e);
			throw new RuntimeException("Failed to get product price", e);
		}
	}

	public String getProductTitle() {

		try {

			log.info("Getting product title");

			waitForVisibility(pp.productTitle);

			String title = getText(pp.productTitle);

			log.info("Product title fetched successfully");

			return title;

		} catch (Exception e) {

			log.error("Failed to get product title", e);
			throw new RuntimeException("Failed to get product title", e);
		}
	}

	public void setQuantity(String quantity) {

		try {

			log.info("Setting quantity : " + quantity);

			waitForVisibility(pp.quantityBox);

			sendKeys(pp.quantityBox, quantity);

			log.info("Quantity entered successfully");

		} catch (Exception e) {

			log.error("Failed to set quantity", e);
			throw new RuntimeException("Failed to set quantity", e);
		}
	}

	public String getQuantity() {

		try {

			log.info("Getting quantity");

			waitForVisibility(pp.quantityBox);

			String quantity = getAttribute(pp.quantityBox, "value");

			log.info("Quantity fetched successfully");

			return quantity;

		} catch (Exception e) {

			log.error("Failed to get quantity", e);
			throw new RuntimeException("Failed to get quantity", e);
		}
	}

	public void clickAskQuestion() {

		try {

			log.info("Clicking ask question button");

			waitForClickable(pp.questionForm);

			click(pp.questionForm);

			log.info("Ask question button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click ask question button", e);
			throw new RuntimeException("Failed to click ask question button", e);
		}
	}

	public void setName(String yourName) {

		try {

			log.info("Entering name");

			waitForVisibility(pp.name);

			sendKeys(pp.name, yourName);

			log.info("Name entered successfully");

		} catch (Exception e) {

			log.error("Failed to enter name", e);
			throw new RuntimeException("Failed to enter name", e);
		}
	}

	public void setEmail(String yourEmail) {

		try {

			log.info("Entering email");

			waitForVisibility(pp.email);

			sendKeys(pp.email, yourEmail);

			log.info("Email entered successfully");

		} catch (Exception e) {

			log.error("Failed to enter email", e);
			throw new RuntimeException("Failed to enter email", e);
		}
	}

	public void setSubject(String yourSubject) {

		try {

			log.info("Entering subject");

			waitForVisibility(pp.subject);

			sendKeys(pp.subject, yourSubject);

			log.info("Subject entered successfully");

		} catch (Exception e) {

			log.error("Failed to enter subject", e);
			throw new RuntimeException("Failed to enter subject", e);
		}
	}

	public void setMessage(String yourMessage) {

		try {

			log.info("Entering message");

			waitForVisibility(pp.Message);

			sendKeys(pp.Message, yourMessage);

			log.info("Message entered successfully");

		} catch (Exception e) {

			log.error("Failed to enter message", e);
			throw new RuntimeException("Failed to enter message", e);
		}
	}

	public void clickSendMessage() {

		try {

			log.info("Clicking send message button");

			waitForClickable(pp.sendMessage);

			click(pp.sendMessage);

			log.info("Send message button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click send message button", e);
			throw new RuntimeException("Failed to click send message button", e);
		}
	}

	public String getAlertMessage() {

		try {

			log.info("Getting alert message");

			WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='alert alert-success alert-notification w-50 alert-dismissible']")));

			String message = alert.getText().replace("×", "").trim();

			log.info("Alert message fetched successfully");

			return message;

		} catch (Exception e) {

			log.error("Failed to get alert message", e);
			throw new RuntimeException("Failed to get alert message", e);
		}
	}

	public void clickAddToCart() {

		try {

			log.info("Clicking add to cart button");

			waitForClickable(pp.addToCartBtn);

			click(pp.addToCartBtn);

			log.info("Add to cart button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click add to cart button", e);
			throw new RuntimeException("Failed to click add to cart button", e);
		}
	}

	public void clickWishListBtn() {

		try {

			log.info("Clicking wishlist button");

			waitForClickable(pp.wishListBtn);

			click(pp.wishListBtn);

			log.info("Wishlist button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click wishlist button", e);
			throw new RuntimeException("Failed to click wishlist button", e);
		}
	}

	public String getAddTocartConfirmation() {

		try {

			log.info("Getting add to cart confirmation");

			waitForVisibility(pp.sizeRequired);

			String message = getText(pp.sizeRequired);

			log.info("Add to cart confirmation fetched successfully");

			return message;

		} catch (Exception e) {

			log.error("Failed to get add to cart confirmation", e);
			throw new RuntimeException("Failed to get add to cart confirmation", e);
		}
	}

	public String getWishListConfirmation() {

		try {

			log.info("Getting wishlist confirmation");

			waitForVisibility(pp.wishList);

			String message = getText(pp.wishList);

			log.info("Wishlist confirmation fetched successfully");

			return message;

		} catch (Exception e) {

			log.error("Failed to get wishlist confirmation", e);
			throw new RuntimeException("Failed to get wishlist confirmation", e);
		}
	}

	public void clickSoftwareBreadcrumb() {

		try {

			log.info("Clicking software breadcrumb");

			waitForClickable(pp.softwareBreadCrumb);

			click(pp.softwareBreadCrumb);

			log.info("Software breadcrumb clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click software breadcrumb", e);
			throw new RuntimeException("Failed to click software breadcrumb", e);
		}
	}

	public String getMadatoryFieldsMessage() {

		try {

			log.info("Getting mandatory fields message");

			waitForVisibility(pp.mandatoryFields);

			String message = getText(pp.mandatoryFields);

			log.info("Mandatory fields message fetched successfully");

			return message;

		} catch (Exception e) {

			log.error("Failed to get mandatory fields message", e);
			throw new RuntimeException("Failed to get mandatory fields message", e);
		}
	}

	public void clickMinus() {

		try {

			log.info("Clicking minus button");

			waitForClickable(pp.minusBtn);

			click(pp.minusBtn);

			log.info("Minus button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click minus button", e);
			throw new RuntimeException("Failed to click minus button", e);
		}
	}

	public void clickAddToCartbutton() {

		try {

			log.info("Clicking add to cart popup button");

			waitForClickable(pp.addToCartButton);

			click(pp.addToCartButton);

			log.info("Add to cart popup button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click add to cart popup button", e);
			throw new RuntimeException("Failed to click add to cart popup button", e);
		}
	}

	public void viewCartPP() {

		try {

			log.info("Clicking view cart popup button");

			waitForClickable(pp.viewCartPopUpButton);

			click(pp.viewCartPopUpButton);

			log.info("View cart popup button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click view cart popup button", e);
			throw new RuntimeException("Failed to click view cart popup button", e);
		}
	}

	public void clickCompareBtn() {

		try {

			log.info("Clicking compare button");

			waitForClickable(pp.compareBtn);

			click(pp.compareBtn);

			log.info("Compare button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click compare button", e);
			throw new RuntimeException("Failed to click compare button", e);
		}
	}

	public String getConfirmationMessage() {

		try {

			log.info("Getting comparison confirmation message");

			waitForVisibility(pp.productComparisonMessage);

			String message = getText(pp.productComparisonMessage);

			log.info("Comparison confirmation fetched successfully");

			return message;

		} catch (Exception e) {

			log.error("Failed to get comparison confirmation message", e);
			throw new RuntimeException("Failed to get comparison confirmation message", e);
		}
	}

	public void clickCart() {

		try {

			log.info("Clicking cart button");

			waitForClickable(pp.cartButton);

			click(pp.cartButton);

			log.info("Cart button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click cart button", e);
			throw new RuntimeException("Failed to click cart button", e);
		}
	}

	public String getQuantityInCart() {

		try {

			log.info("Getting quantity in cart");

			waitForVisibility(pp.quantityField);

			String quantity = getAttribute(pp.quantityField, "value");

			log.info("Quantity in cart fetched successfully");

			return quantity;

		} catch (Exception e) {

			log.error("Failed to get quantity in cart", e);
			throw new RuntimeException("Failed to get quantity in cart", e);
		}
	}

	public void clickViewCompare() {

		try {

			log.info("Clicking view compare button");

			waitForClickable(pp.viewCompare);

			click(pp.viewCompare);

			log.info("View compare button clicked successfully");

		} catch (Exception e) {

			log.error("Failed to click view compare button", e);
			throw new RuntimeException("Failed to click view compare button", e);
		}
	}
}