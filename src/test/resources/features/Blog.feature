@Samiha @BlogFeature
Feature: SAMIHA_Blog_Feature_21_05_2026
Description:
To validate the Blog functionality of Ecommerce LambdaTest Website

  Background:
    Given The user is in HomePage of EcommerceLambdaTestWebsite

  @OpenBlogMenu
  Scenario: To verify whether the user can open Blog page successfully
    When The user clicks on Blog menu in navBar
    Then The user should navigate to Latest Articles page

  @OpenLatestArticle
  Scenario: To verify whether the user can open first latest article
    When The user clicks on Blog menu in navBar
    And clicks on first article in Latest Articles page
    Then The selected article page should be displayed successfully

  @BusinessCategory
  Scenario: To verify whether the user can open Business category article
    When The user clicks on Blog menu in navBar
    And clicks on Business category from left side menu
    And clicks on Read More button in Business article
    Then Business article page should be displayed successfully

  @ElectronicsCategory
  Scenario: To verify whether the user can open Electronics category article
    When The user clicks on Blog menu in navBar
    And clicks on Electronics category from left side menu
    And clicks on Read More button in Electronics article
    Then Electronics article page should be displayed successfully

  @TechnologyCategory
  Scenario: To verify whether the user can open Technology category article
    When The user clicks on Blog menu in navBar
    And clicks on Technology category from left side menu
    And clicks on Read More button in Technology article
    Then Technology article page should be displayed successfully

  @FashionCategory
  Scenario: To verify whether the user can open Fashion category article
    When The user clicks on Blog menu in navBar
    And clicks on Fashion category from left side menu
    And clicks on Read More button in Fashion article
    Then Fashion article page should be displayed successfully

  @PostComment
  Scenario: To verify that user can post comment in the article with valid details
    When The user clicks on Blog menu in navBar
    And clicks on first article in Latest Articles page
    And enter the name of user
    And enter the email 
    And enter the message 
    And click the post comment Button 
    Then user can see the Thank you for your comment. It has been submitted to the webmaster for approval. Message

