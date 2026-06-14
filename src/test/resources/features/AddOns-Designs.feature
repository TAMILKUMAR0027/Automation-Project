@Jothika
Feature: AddOns-components in  designs-Jothika 30/05/2026
Description:This feature describes the components incorporated in design functionality of AddOns

  Background:
    Given the user is on the home page of the application
    And the user clicks on AddOns and selects designs

  Scenario: Clicking on Drawer Left options in components category
    When the user clicks the Drawer left option
    Then the user is able to view a left side panel displaying the top categories

  Scenario: Clicking on Drawer Right option of components category
    When the user clicks on Drawer Right option
    Then the application displays a right side panel listing particular product category

  Scenario: Clicking on Sticky Top button in componentscategory
    When the user clicks on the Sticky Top button
    Then the user is able to view a top panel appearing in the application

  Scenario: Clicking on Sticky Bottom button
    When the user clicks the Sticky Bottom button
    Then the user must view a bottom panel in the application

  Scenario: Clicking on Popup button
    When the user clicks the popup
    Then the application displays a popup box in the middle of the application

  @Tamil @TableHandling
  Scenario: Verifying structure and data availability in Dark Heading table
    When the user views the Dark Heading table
    Then the table should display all expected column headers
    And the table should display one or more records
@Jothika @Newsletter
  Scenario: Verifying error alert appears when subscribing to newsletter
    When the user enters an email address in the newsletter field
    And the user clicks the Subscribe button
    Then the alert message should contain "Internal Server Error"
