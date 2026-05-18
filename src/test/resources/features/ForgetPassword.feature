@Rest
Feature: Reset password through forget password -Jothika 13/05/2026
Description:This feature manages forget password functionality

  Background:
    Given the user is on the home page of the application
    When the user clicks login link
    And clicks on Forgotten Password

  @validemail
  Scenario Outline: Reset password using valid registered email
    When the user enters the valid "<email>"
    Then the user should be able to receive a "<message>" stating reset link sent to email.

    Examples:
      | email               | message                                                             |
      | testlogin@gmail.com | An email with a confirmation link has been sent your email address. |

  @invalidemail
  Scenario Outline: Reset password using invalid email
    When the user enters the invalid "<email>"
    Then the user should receive an "<error message>"

    Examples:
      | email                | error message                                                               |
      | testlogin1@gmail.com | Warning: The E-Mail Address was not found in our records, please try again! |

