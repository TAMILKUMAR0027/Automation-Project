package com.stepDefinitions;

import org.testng.Assert;

import com.actions.BlogActions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BlogSteps {

    BlogActions blogActions =
            new BlogActions();

    @When("The user clicks on Blog menu in navBar")
    public void the_user_clicks_on_blog_menu_in_nav_bar() {

        blogActions.clickBlogMenu();
    }

    @Then("The user should navigate to Latest Articles page")
    public void the_user_should_navigate_to_latest_articles_page() {

        Assert.assertTrue(
                blogActions
                        .isLatestArticlesPageDisplayed(),
                "Latest Articles page is not displayed");
    }

    @When("clicks on first article in Latest Articles page")
    public void clicks_on_first_article_in_latest_articles_page() {

        blogActions.clickFirstArticle();
    }

    @Then("The selected article page should be displayed successfully")
    public void the_selected_article_page_should_be_displayed_successfully() {

        Assert.assertTrue(
                blogActions
                        .isArticleDisplayed(),
                "Article page is not displayed");
    }

    @When("clicks on Business category from left side menu")
    public void clicks_on_business_category_from_left_side_menu() {

        blogActions.clickBusinessCategory();
    }

    @When("clicks on Read More button in Business article")
    public void clicks_on_read_more_button_in_business_article() {

        blogActions.clickReadMoreButton();
    }

    @Then("Business article page should be displayed successfully")
    public void business_article_page_should_be_displayed_successfully() {

        Assert.assertTrue(
                blogActions
                        .isArticleDisplayed(),
                "Business article page is not displayed");
    }

    @When("clicks on Electronics category from left side menu")
    public void clicks_on_electronics_category_from_left_side_menu() {

        blogActions.clickElectronicsCategory();
    }

    @When("clicks on Read More button in Electronics article")
    public void clicks_on_read_more_button_in_electronics_article() {

        blogActions.clickReadMoreButton();
    }

    @Then("Electronics article page should be displayed successfully")
    public void electronics_article_page_should_be_displayed_successfully() {

        Assert.assertTrue(
                blogActions
                        .isArticleDisplayed(),
                "Electronics article page is not displayed");
    }

    @When("clicks on Technology category from left side menu")
    public void clicks_on_technology_category_from_left_side_menu() {

        blogActions.clickTechnologyCategory();
    }

    @When("clicks on Read More button in Technology article")
    public void clicks_on_read_more_button_in_technology_article() {

        blogActions.clickReadMoreButton();
    }

    @Then("Technology article page should be displayed successfully")
    public void technology_article_page_should_be_displayed_successfully() {

        Assert.assertTrue(
                blogActions
                        .isArticleDisplayed(),
                "Technology article page is not displayed");
    }

    @When("clicks on Fashion category from left side menu")
    public void clicks_on_fashion_category_from_left_side_menu() {

        blogActions.clickFashionCategory();
    }

    @When("clicks on Read More button in Fashion article")
    public void clicks_on_read_more_button_in_fashion_article() {

        blogActions.clickReadMoreButton();
    }

    @Then("Fashion article page should be displayed successfully")
    public void fashion_article_page_should_be_displayed_successfully() {

        Assert.assertTrue(
                blogActions
                        .isArticleDisplayed(),
                "Fashion article page is not displayed");
    }
}