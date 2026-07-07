package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPage {

	@FindBy(xpath = "//a[contains(@href,'blog/home')]")
	public WebElement blogMenu;
	
	@FindBy(xpath="(//h4[contains(@class,'article-title')])[1]")
	public WebElement firstArticle;
	
    @FindBy(xpath = "//h3[contains(text(),'Latest Articles')]")
    public WebElement latestArticleText;

    @FindBy(xpath = "(//h4[contains(@class,'article-title')])[1]")
    public WebElement firstArticleReadMore;

    @FindBy(xpath = "//*[contains(normalize-space(),'Business')]")
    public WebElement businessCategory;

    @FindBy(xpath = "//*[contains(normalize-space(),'Electronics')]")
    public WebElement electronicsCategory;

    @FindBy(xpath = "//*[contains(normalize-space(),'Technology')]")
    public WebElement technologyCategory;

    @FindBy(xpath = "//*[contains(normalize-space(),'Fashion')]")
    public WebElement fashionCategory;
    
    @FindBy(xpath = "(//a[contains(.,'Read More')])[1]")
    public WebElement readMoreButton;
    
    @FindBy(xpath = "//h1 | //article | //div[contains(@class,'entry-content')]")
    public WebElement articleContent;

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
}