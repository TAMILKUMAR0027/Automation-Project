@Jothika @Logout
Feature: Logout account in the application - Jothika 13/05/2026
  Description:
  This feature validates the logout functionality of the Ecommerce application.

  Background:
    Given The user is in Home Page of Ecommerce lambda test application

  @validlogout
  Scenario: Account logout using logout link in My Account
    Given the user is logged into the application with valid email and password
      | email               | password  |
      | testlogin@gmail.com | testlogin |
    And the user is on the My Account page
    When the user clicks on Logout link
    Then the user should receive an intimation message regarding logout
