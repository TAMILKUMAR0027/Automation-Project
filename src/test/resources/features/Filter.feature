Feature: Product Filter Functionality

  Background: Given User is on filter page

  @VerifyFilterPage
  Scenario: Validate filter page title
    Then User should verify the filter page title

  @VerifyPricefilter
  Scenario: Validate filter by price
    When User applies price filter
    Then Products should display based on selected price range

  @verifyByManufacture
  Scenario: Validate filter by manufacturer
    When User selects manufacturer filter
    Then Products should display based on selected manufacturer

  @verifyByProductname
  Scenario: Validate filter by product name
    When User enters product name in filter
    Then Matching products should display successfully

  @verifyShowByproducts
  Scenario: Validate show by products
    When User changes show by product count
    Then Products count should update successfully

  @verifyStockAndOutofStock
  Scenario: Validate in stock and out of stock filter
    When User applies stock availability filter
    Then Products should display based on stock status
