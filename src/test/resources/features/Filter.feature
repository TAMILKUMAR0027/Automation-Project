@smoke
Feature: TamilKumar_13/05/2026 Product Filter Functionality

  Background:
    Given User is on Home page click on the Shop By categories Page and Click any one option

  @verifyByManufacture
  Scenario: Validate filter by manufacturer

    When User clicks manufacturer filter any brand element
    And product should be displayed based on filtered results and Clicks any one product
    Then check the product brand name in description should be matches the filter

  @showByTotalProduct
  Scenario: Validate total displayed products based on selected count

    When User send an option from the show products dropdown
    And User stores the displayed products in a list
    Then Displayed product count should match the selected dropdown value
  @filterByAvailability
Scenario: Validate product availability
  When User clicks the in-stock filter option  and Products should display based on availability
  And User clicks any one product
  Then Product availability status should be displayed in product description
