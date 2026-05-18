package com.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class BaseAction {

    public void click(WebElement element) {
        element.click();
    }

    public void sendKeys(WebElement element, String msg) {
        element.clear();
        element.sendKeys(msg);
    }

    public void sendKeysWithEnter(WebElement element, String msg) {
        element.clear();
        element.sendKeys(msg, Keys.ENTER);
    }
}