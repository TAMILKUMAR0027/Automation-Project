@LoginFeature
Feature: Rishwanth_Login_Feature_13_05_2026
description:
To validate the login Functionality of ecommerce lambdaTest Application with both valid and invalid credentials

  Background:
    Given The user is in HomePage of Ecommerce LambadaTest Application

  @validCredentials
  Scenario Outline: To check the login Functionality with valid credentialss
    When The user clicks on My account link
    And The user Enters valid email as "<email>" and valid password as "<password>"
    And Clicks on Login Button
    Then The user should be successfully Logged in.

    Examples:
      | email               | password  |
      | testlogin@gmail.com | testlogin |
      | gb@gmail.com        | gblogin   |

  @invalidCredentials
  Scenario Outline: To check the login Functionality with invalid credentialss
    When The user clicks on My account link
    And The user Enters invalid email as "<email>" and invalid password as "<password>"
    And Clicks on Login Button
    Then The user should see an Error message Warning: No match for E-Mail Address and/or Password.

    Examples:
      | email               | password   |
      | testlogin@gmail.com | testTetsng |
      |                     | testTetsng |
      | bat@gmail.com       |            |
