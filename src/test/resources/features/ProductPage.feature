@productDetails
Feature: TamilKumar_15_05_2026_Product Details Page Functionality

  Background:
    Given User launches the ecommerce application

  @productInformation
  Scenario: Validate product details information
    And user opens Canon product details page
    Then Product title should be displayed
    And Product price should be displayed
    And Product availability status should be displayed

  @quantity
  Scenario Outline: Set product quantity
    And user opens HP product details page
    When user updates product quantity to "<qty>"
    And click the cart
    Then product quantity should be updated successfully in the cart "<qty>"

    Examples:
      | qty |
      | 1   |
      | 3   |
      | 5   |

  @enquiry
  Scenario: Send product enquiry in the product page
    And user opens Canon product details page
    When user clicks on Ask Question option
    And user fills enquiry form
    Then enquiry should be sent successfully

  @multipleActions
  Scenario: Perform multiple operations on product page
    And user opens Canon product details page
    When user clicks Add to Cart button
    And user clicks Wishlist button
    Then both actions should be triggered successfully

  @navigation
  Scenario: Validate product navigation using breadcrumb
    And user opens Canon product details page
    When user clicks on breadcrumb category link Software
    Then user should be navigated to product category page successfully

  @negative_enquiry
  Scenario: Validate enquiry form without mandatory fields
    And user opens Canon product details page
    When user clicks on Ask Question option
    And user submits empty enquiry form
    Then validation message should be displayed for enquiry form

  @InvalidQuantity
  Scenario: Set Invalid product quantity
    And user opens Canon product details page
    When user clicks minus button 2 times
    Then product quantity should not be less than 1