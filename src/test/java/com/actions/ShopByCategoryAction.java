package com.actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.ShopByCategoryPage;

public class ShopByCategoryAction {

    WebDriver driver = DriverClass.getDriver();
WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
    ShopByCategoryPage sbcp = new ShopByCategoryPage(driver);

    public void launchWebUrl() {

        driver.get("https://ecommerce-playground.lambdatest.io/");
    }

    public void clickShopByCategory() {
    	
         wait.until(ExpectedConditions.elementToBeClickable(sbcp.shopByCategoryMenu)).click();
    }

    public void selectCategory(String category) {
         if(category=="Desktops & Monitors") {
        	 wait.until(ExpectedConditions.visibilityOf(sbcp.desktopsCategory)).click();
         }
         else if(category=="WebCameras") {
        	 wait.until(ExpectedConditions.visibilityOf(sbcp.cameras)).click();
         }
         else if(category=="Phone, Tablets & Ipod") {
        	 wait.until(ExpectedConditions.visibilityOf(sbcp.tablets)).click();
         }
         else if(category=="Laptops & Notebooks") {
        	 wait.until(ExpectedConditions.visibilityOf(sbcp.laptops)).click();
         }
    }

    public String getPageTitle() {

        return driver.getTitle();
    }
}