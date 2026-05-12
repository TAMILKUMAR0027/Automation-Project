package com.pages;

import com.driver.DriverClass;
import org.openqa.selenium.By;

public class LaunchPages {

    public void launchApplication(String url) {
        DriverClass.getDriver().get(url);
    }

    public String getCurrentUrl() {
        return DriverClass.getDriver().getCurrentUrl();
    }

    public String getPageTitle() {
        return DriverClass.getDriver().getTitle();
    }

    public boolean getlogo() {
        return DriverClass.getDriver()
                .findElement(By.xpath("//img[@alt='Poco Electro']"))
                .isDisplayed();
    }
}