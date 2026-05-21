@Logout
Feature: Logout account in the application -Jothika 13/05/2026
Description:This feature is about the logout functionality of the application

  @validlogout
  Scenario Outline: Account logout using logout link in My Account
    Given the user is logged into the application with valid "<email>" and "<password>"
    And the user is on the My Account page
    When the user clicks on Logout link
    Then the user should receive an intimation "<message>" regarding logout

    Examples:
   |email               |password  | message        |
   |testlogin@gmail.com |testlogin | Account Logout |

  @invalidlogout
  Scenario Outline: Verify invalid logout functionality
    Given the user is not logged into the application with invalid "<email>" and "<password>"
    When the user navigates to the My Account page
    Then the logout link should not be displayed and the login link should be displayed
   
   Examples: 
    |email               |password |
    |testlogin1@gmail.com|testlogin|
