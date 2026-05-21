package com.actions;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;

public class BaseAction {

    WebDriver driver = DriverClass.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void click(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void jsClick(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickRadioButton(WebElement element) {

        if (!element.isSelected()) {

            element.click();
        }
    }

    public void selectCheckBox(WebElement element) {

        if (!element.isSelected()) {

            element.click();
        }
    }

    public void unSelectCheckBox(WebElement element) {

        if (element.isSelected()) {

            element.click();
        }
    }

    public void sendKeys(WebElement element, String msg) {

        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(msg);
    }

    public void sendKeysWithEnter(WebElement element, String msg) {

        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(msg, Keys.ENTER);
    }

    public String getText(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean isDisplayed(WebElement element) {

        return element.isDisplayed();
    }

    public boolean isEnabled(WebElement element) {

        return element.isEnabled();
    }

    public boolean isSelected(WebElement element) {

        return element.isSelected();
    }

    public String getAttribute(WebElement element, String attributeName) {

        return element.getAttribute(attributeName);
    }

    public void clear(WebElement element) {

        element.clear();
    }

    public void scrollIntoView(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

   

    public void waitForVisibility(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    

    public String getTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

   
}