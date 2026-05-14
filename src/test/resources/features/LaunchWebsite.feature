@Smoke
Feature: Launch the Ecommerce Application - Prasanna Venkatesh 12/05/2026
  Description: Launch the application in browser based on runtime input and validate basic homepage elements

  Background:
    Given User navigates to application url


  Scenario: Validate homepage url
    Then User should be on correct url


  Scenario: Validate homepage title
    Then User should see correct page title


  Scenario: Validate homepage logo
    Then User should see the application logo