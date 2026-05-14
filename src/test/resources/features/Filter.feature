Feature: Product Filter Functionality

  Background:
    Given User is on Home page click on the Shop By categories Page and Click any one option

  

  @verifyByManufacture
  Scenario: Validate filter by manufacturer
    When User clicks manufacturer filter any brand element 
    And product should be displayed based on filtered results and Clicks any one product
    Then check the product brand name in description should be matches the filter 


  