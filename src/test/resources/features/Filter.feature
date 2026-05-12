Feature: Product Filter Functionality

  Scenario: Validate filter page title
    Given User is on filter page
    Then User should verify the filter page title

  Scenario: Validate filter by price
    Given User is on filter page
    When User applies price filter
    Then Products should display based on selected price range

  Scenario: Validate filter by manufacturer
    Given User is on filter page
    When User selects manufacturer filter
    Then Products should display based on selected manufacturer

  Scenario: Validate filter by product name
    Given User is on filter page
    When User enters product name in filter
    Then Matching products should display successfully

  Scenario: Validate show by products
    Given User is on filter page
    When User changes show by product count
    Then Products count should update successfully

  Scenario: Validate in stock and out of stock filter
    Given User is on filter page
    When User applies stock availability filter
    Then Products should display based on stock status