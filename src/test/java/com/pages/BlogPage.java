package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPage extends BasePage {

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Blog')]")
    private WebElement blogMenu;

    @FindBy(xpath = "//h1[contains(text(),'Latest Articles')]")
    private WebElement latestArticleText;

    @FindBy(xpath = "(//div[contains(@class,'article')]//a[contains(text(),'Read More')])[1]")
    private WebElement firstArticleReadMore;

    @FindBy(xpath = "//a[contains(text(),'Business')]")
    private WebElement businessCategory;

    @FindBy(xpath = "//a[contains(text(),'Electronics')]")
    private WebElement electronicsCategory;

    @FindBy(xpath = "//a[contains(text(),'Technology')]")
    private WebElement technologyCategory;

    @FindBy(xpath = "//a[contains(text(),'Fashion')]")
    private WebElement fashionCategory;

    @FindBy(xpath = "(//a[contains(text(),'Read More')])[1]")
    private WebElement readMoreButton;

    @FindBy(xpath = "//div[contains(@class,'entry-content')]")
    private WebElement articleContent;

    @FindBy(id = "comment")
    private WebElement commentBox;

    @FindBy(id = "author")
    private WebElement authorName;

    @FindBy(id = "email")
    private WebElement authorEmail;

    @FindBy(id = "submit")
    private WebElement postCommentButton;

    public WebElement getBlogMenu() {
        return blogMenu;
    }

    public WebElement getLatestArticleText() {
        return latestArticleText;
    }

    public WebElement getFirstArticleReadMore() {
        return firstArticleReadMore;
    }

    public WebElement getBusinessCategory() {
        return businessCategory;
    }

    public WebElement getElectronicsCategory() {
        return electronicsCategory;
    }

    public WebElement getTechnologyCategory() {
        return technologyCategory;
    }

    public WebElement getFashionCategory() {
        return fashionCategory;
    }

    public WebElement getReadMoreButton() {
        return readMoreButton;
    }

    public WebElement getArticleContent() {
        return articleContent;
    }

    public WebElement getCommentBox() {
        return commentBox;
    }

    public WebElement getAuthorName() {
        return authorName;
    }

    public WebElement getAuthorEmail() {
        return authorEmail;
    }

    public WebElement getPostCommentButton() {
        return postCommentButton;
    }
}
