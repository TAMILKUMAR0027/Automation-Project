package com.actions;

import org.openqa.selenium.JavascriptExecutor;

import com.driver.DriverClass;
import com.pages.BlogPage;

public class BlogActions extends BaseAction {

    BlogPage blogPage;

    public BlogActions() {

        blogPage = new BlogPage(DriverClass.getDriver());
    }

    public void clickBlogMenu() {

        blogPage.getBlogMenu().click();
    }

    public void clickFirstArticle() {

        blogPage.getFirstArticleReadMore().click();
    }

    public void clickBusinessCategory() {

        blogPage.getBusinessCategory().click();
    }

    public void clickElectronicsCategory() {

        blogPage.getElectronicsCategory().click();
    }

    public void clickTechnologyCategory() {

        blogPage.getTechnologyCategory().click();
    }

    public void clickFashionCategory() {

        blogPage.getFashionCategory().click();
    }

    public void clickReadMoreButton() {

        blogPage.getReadMoreButton().click();
    }

    public void scrollToCommentSection() {

        JavascriptExecutor js =
                (JavascriptExecutor) DriverClass.getDriver();

        js.executeScript( "arguments[0].scrollIntoView(true);",
                blogPage.getCommentBox());
    }

    public void enterCommentDetails() {

        blogPage.getAuthorName().sendKeys("Samiha");
        blogPage.getAuthorEmail().sendKeys("samiha@gmail.com");
        blogPage.getCommentBox().sendKeys("Excellent article");
    }
    public void clickLatestArticle() {
    	click(blogPage.latestArticleText);
    }
   
    public void setName() {
    	sendKeys(blogPage.name, "Samiha");
    }
    public void setEmail() {
    	sendKeys(blogPage.email, "samiha@gmail.com");
    }
    public void setMessage() {
    	sendKeys(blogPage.comment, "The article was really good and excellent");
    }
    public void clickPostComment() {
    	click(blogPage.submitComment);
    }
    public String getSubmissionMessage() {
    	return getText(blogPage.submissionMessage);
    }
}