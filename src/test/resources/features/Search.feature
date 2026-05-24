@Prasanna @Search
Feature: Search Feature - Prasanna Venkatesh K - 18-05-2026 Update - 18-05-2026
  Description: Validate that the search filter lists products based on the entered keyword.

  Background:
    Given the user is on the home page of the application
    When the user clicks on the search bar on the home page

  Scenario Outline: Validate the search filter functionality
    And the user enters "<keyword>" and presses Enter
    Then the application should display products based on the keyword

    Examples:
      | keyword |
      | iMac    |
      | Kiot    |

Scenario: Validate that the search results show only the manufacturer products
  And the user enters the product "keywords" and presses Enter
  Then the application should display products based on the keyword
  And the application should list only the manufacturer products based on the "keywords" 


  Scenario: Validate the search filter with empty data
    And the user enters "" and presses Enter
    Then the application should display all products
    And the url should be "https://ecommerce-playground.lambdatest.io/index.php?route=product%2Fsearch&search="
    And the product count should be 15