package com.actions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.FilterPage;

public class FilterPageAction {

    WebDriver driver = DriverClass.getDriver();
    FilterPage fp = new FilterPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public void clickManufacture() {
        wait.until(ExpectedConditions.elementToBeClickable(fp.appleBtn));
        fp.appleBtn.click();
    }
    public void clickProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(fp.iPodTouchProduct));
        fp.iPodTouchProduct.click();
    }
    public void selectDropdownByVisibleText(String value) {
    	
        fp.dropdownBtn.sendKeys(value);
    }
    public List<String> storeAllProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));
        return fp.getProductNames();
    }
    public int getDisplayedProductCount() {

        wait.until(ExpectedConditions.visibilityOfAllElements(fp.allProducts));

        return fp.allProducts.size();
    }
}