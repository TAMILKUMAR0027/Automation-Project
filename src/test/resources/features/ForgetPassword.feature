@Jothika @Rest
Feature: Reset password through Forget Password - Jothika 13/05/2026

  Description:
  This feature validates the Forget Password functionality for valid and invalid email inputs.

  Background:
    Given the user is on the home page of the application
    When the user clicks login link
    And clicks on Forgotten Password

  @validemail
  Scenario: Reset password using valid registered email
    When the user enters the valid email
    Then the user should be able to receive a message stating reset link sent to email

  @invalidemail
  Scenario: Reset password using invalid email
    When the user enters the invalid email
    Then the user should receive an error message