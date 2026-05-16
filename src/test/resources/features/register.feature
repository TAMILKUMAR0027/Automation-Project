@RegisterFeature
Feature: Rishwanth_Login_Feature_13_05_2026
description:
To validate the registration process of new user in ecommerce lambda test website

  Background:
    Given The user is in HomePage of EcommerceLambdaTestWebsite

  Scenario: To verify the Registration of new user with valid details
    When The user clicks on myAccount link in navBar
    And clicks on Register link in Account page
    And Enter your personal details
      | fname     | lname     | email           | telephone  | password | confirmpassword |
      | Rishwanth | Adhishwar | remo4@gmail.com | 5432109876 | testyou  | testyou         |
    And check the privacy policy checbox
    And clicks on continue Button
    Then the user should see a page with confirmation text
