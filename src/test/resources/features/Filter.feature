@Tamil @smoke
Feature: TamilKumar_13_05_2026_Product Filter Functionality updated _19_05_2026

  Background:
    Given User is on Home page
    And User clicks on Shop By Categories
    And User selects any one category

  @verifyByManufacture
  Scenario: Validate filter by manufacturer
    When User clicks any manufacturer filter option
    And Products should display based on selected manufacturer
    And User clicks any one product
    Then Product brand name in description should match the selected filter

  @showByTotalProduct
  Scenario: Validate total displayed products based on selected count
    When User selects an option from the show products dropdown
    And User  displayed products
    Then Displayed product count should match the selected dropdown value

  @filterByAvailability
  Scenario: Validate product availability
    When User clicks the in-stock filter option
    And Products should display based on availability
    And User clicks any one product based on instock
    Then Product availability status should be displayed in product description

  @filterByAvailabilityOptions
  Scenario: Validate product availability based on different options
    When User clicks the in-stock filter option and click one product
    Then In-stock products should display availability status in product description
    When User clicks the out-of-stock filter option and click one product
    Then Out-of-stock products should display availability status in product description

  @priceFilter
  Scenario: Validate the price based on Slider
    When move the slider
    Then the price should be updated in filter page
