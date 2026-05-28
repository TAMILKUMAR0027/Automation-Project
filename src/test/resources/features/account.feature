@AccountFeature @Rishwanth
Feature: Rishwanth_Adhishwar_23_05_2026_AccountFeature
description:To verify the fuctionality of edit account information

  Background:
    Given The user is in HomePage of Ecommerce Lambda TestWebsite

  @EditAccInfo
  Scenario: As a registered user i need to update my account information
    When The user clicks on myAccount link in navBar
    And The user Enters valid email and valid passwords
    And Clicks on Login Button
    And The User clicks on edit account information link on Account page
    And The user clear and enter new telephone number in input field
      | telephone   |
      | 90888999047 |
    And The user clicks on continue Button in Edit Information Page
    Then The user should see a sucess Message Your account has updated successfully
