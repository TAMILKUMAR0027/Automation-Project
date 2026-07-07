package com.actions;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.driver.DriverClass;
import com.pages.BlogPage;

public class BlogActions {

    WebDriver driver;
    BlogPage blogPage;

    public BlogActions() {

        driver = DriverClass.getDriver();
        blogPage = new BlogPage();

        PageFactory.initElements(driver, blogPage);
    }

    public void clickBlogMenu() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(blogPage.blogMenu));

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        blogPage.blogMenu);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        blogPage.blogMenu));

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        blogPage.blogMenu);
    }

}