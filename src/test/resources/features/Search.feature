@Search
Feature: Search Feature - Prasanna Venkatesh K - 18-05-2026
  Description: Validate that the search filter lists products based on the entered keyword.

  Background:
    Given the user is on the home page of the application

  Scenario Outline: Validate the search filter functionality
    When the user clicks on the search bar on the home page
    And the user enters "<keyword>" and presses Enter
    Then the application should display products based on the keyword

    Examples:
      | keyword |
      | iMac    |
      | Kiot    |