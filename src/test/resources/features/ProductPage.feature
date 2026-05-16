@productDetails
Feature: Product Details Page Functionality

  Background:
    Given User launches the ecommerce application
    And User opens a product details page

  @productInformation
  Scenario: Validate product details information
    Then Product title should be displayed
    And Product price should be displayed
    And Product availability status should be displayed

  @quantity
  Scenario Outline: Set product quantity
    When user updates product quantity to "<qty>"
    Then product quantity should be updated successfully

    Examples:
      | qty |
      | 1   |
      | 3   |
      | 5   |

  @enquiry
  Scenario: Send product enquiry in the product page
    When user clicks on Ask Question option
    And user fills enquiry form 
    Then enquiry should be sent successfully

  @multipleActions
  Scenario: Perform multiple operations on product page
    When user clicks Add to Cart button
    And user clicks Wishlist button
    Then both actions should be triggered successfully

  @navigation
  Scenario: Validate product navigation using breadcrumb
    When user clicks on breadcrumb category link Software
    Then user should be navigated to product category page successfully
    
    @negative_enquiry
  Scenario: Validate enquiry form without mandatory fields
    When user clicks on Ask Question option
    And user submits empty enquiry form
    Then validation message should be displayed for enquiry form
    
    @InvalidQuantity
    Scenario Outline: Set Invalid product quantity
    When user clicks minus button 2 times
    Then product quantity should not be less than 1
    