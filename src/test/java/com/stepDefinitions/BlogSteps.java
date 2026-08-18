package com.stepDefinitions;

import com.actions.BlogActions;

import static org.testng.Assert.assertEquals;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BlogSteps {
	BlogActions bpa= new BlogActions();
    @When("The user clicks on Blog menu in navBar")
    public void the_user_clicks_on_blog_menu_in_nav_bar() {
    	bpa.clickBlogMenu();
    }

    @Then("The user should navigate to Latest Articles page")
    public void the_user_should_navigate_to_latest_articles_page() {

    }

    @When("clicks on first article in Latest Articles page")
    public void clicks_on_first_article_in_latest_articles_page() {
    	bpa.clickLatestArticle();
    }

    @Then("The selected article page should be displayed successfully")
    public void the_selected_article_page_should_be_displayed_successfully() {

    }

    @When("clicks on Business category from left side menu")
    public void clicks_on_business_category_from_left_side_menu() {

    }

    @When("clicks on Read More button in Business article")
    public void clicks_on_read_more_button_in_business_article() {

    }

    @Then("Business article page should be displayed successfully")
    public void business_article_page_should_be_displayed_successfully() {

    }

    @When("clicks on Electronics category from left side menu")
    public void clicks_on_electronics_category_from_left_side_menu() {

    }

    @When("clicks on Read More button in Electronics article")
    public void clicks_on_read_more_button_in_electronics_article() {

    }

    @Then("Electronics article page should be displayed successfully")
    public void electronics_article_page_should_be_displayed_successfully() {

    }

    @When("clicks on Technology category from left side menu")
    public void clicks_on_technology_category_from_left_side_menu() {

    }

    @When("clicks on Read More button in Technology article")
    public void clicks_on_read_more_button_in_technology_article() {

    }

    @Then("Technology article page should be displayed successfully")
    public void technology_article_page_should_be_displayed_successfully() {

    }

    @When("clicks on Fashion category from left side menu")
    public void clicks_on_fashion_category_from_left_side_menu() {

    }

    @When("clicks on Read More button in Fashion article")
    public void clicks_on_read_more_button_in_fashion_article() {

    }

    @Then("Fashion article page should be displayed successfully")
    public void fashion_article_page_should_be_displayed_successfully() {

    }
    @When("enter the name of user")
    public void enter_the_name_of_user() {
        // Write code here that turns the phrase above into concrete actions
    	bpa.setName();
    }

    @When("enter the email")
    public void enter_the_email() {
        // Write code here that turns the phrase above into concrete actions
    	bpa.setEmail();
    }

    @When("enter the message")
    public void enter_the_message() {
        // Write code here that turns the phrase above into concrete actions
    	bpa.setMessage();
    }

    @When("click the post comment Button")
    public void click_the_post_comment_button() {
        // Write code here that turns the phrase above into concrete actions
    	bpa.clickPostComment();
    }

    @Then("user can see the Thank you for your comment. It has been submitted to the webmaster for approval. Message")
    public void user_can_see_the_thank_you_for_your_comment_it_has_been_submitted_to_the_webmaster_for_approval_message() {
        // Write code here that turns the phrase above into concrete actions
    	assertEquals("Thank you for your comment. It has been submitted to the webmaster for approval.", bpa.getSubmissionMessage());
    }


}