@Launch
Feature: Launch the Ecommerce Application
  Description: Launch the application in browser based on runtime input and validate basic homepage elements

  Background:
    Given User navigates to application url

  @Smoke
  Scenario: Validate homepage url
    Then User should be on correct url

  @Smoke
  Scenario: Validate homepage title
    Then User should see correct page title

  @Smoke
  Scenario: Validate homepage logo
    Then User should see the application logo