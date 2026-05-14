package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = DriverClass.getDriver();

        PageFactory.initElements(this.driver, this);

        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }
}