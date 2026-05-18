package com.actions;

import com.driver.DriverClass;
import com.pages.SearchPages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchAction {

    BaseAction baseAction = new BaseAction();
    WebDriver driver = DriverClass.getDriver();
    SearchPages searchPages = new SearchPages(driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public void clickSearchBar() {
        wait.until(d -> searchPages.SearchBar.isDisplayed() && searchPages.SearchBar.isEnabled());
        baseAction.click(searchPages.SearchBar);
    }

    public void enterKeywordAndPressEnter(String keyword) {
        wait.until(d -> searchPages.SearchBar.isDisplayed());
        baseAction.sendKeysWithEnter(searchPages.SearchBar, keyword);
    }

    public boolean isProductListDisplayed() {
        wait.until(d -> !searchPages.result.isEmpty() || searchPages.resulterr.isDisplayed());
        return !searchPages.result.isEmpty();
    }

    public String getNoProductMessage() {
        wait.until(d -> searchPages.resulterr.isDisplayed());
        return searchPages.resulterr.getText();
    }

    public boolean isNoProductMessageDisplayed() {
        try {
            return searchPages.resulterr.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}