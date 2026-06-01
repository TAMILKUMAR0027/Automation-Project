@Rishwanth @CartFeature
Feature: Rishwanth_Cart_Feature_17_05_2026
description:To verify the add to cart functionality in Ecommerce Lambda Test Website.

  Background:
    Given The user is in Home Page of Ecommerce lambda test application

  @CartAdd
  Scenario: To check the product added to cart is present in cart page
    When The user clicks on a product in Top Collection menu
    And The user clicks on add to cart button in Product details page
    And The user clicks view-cart button in popup displayed
    Then The added product should be in cart Page

  @CartQuantityupdate
  Scenario: To check the quantity is updating on a specific product in cart
    When The user clicks on a product in Top Collection menu
    And The user clicks on add to cart button in Product details page
    And The user clicks view-cart button in popup displayed
    And The user enter the quantity based on needs in quantity input box
      | quantity |
      | 5        |
    And The user clicks quantity update button
    Then The user should see a success message quantity updated successfully

  @CartProductRemove
  Scenario: To Check whether the product is removed from the cart by click on remove button
    When The user clicks on a product in Top Collection menu
    And The user clicks on add to cart button in Product details page
    And The user clicks view-cart button in popup displayed
    And The user clicks product Remove button
    Then The product should be removed from the cart and display no products in cart

  @CartShipAndEstimation
  Scenario: To Check Whether The Estimate shipping and tax functionality
    When The user clicks on a product in Top Collection menu
    And The user clicks on add to cart button in Product details page
    And The user clicks view-cart button in popup displayed
    And The user clicks on Estimate Shipping and Tax Link
    And select options from country and state dropdown
      | country | state |
      | 2       | 1     |
    And The user clicks Quotes Button
    And check the radio button in Flash Shipping popup
    And clicks on Apply Shipping Button
    Then The user should see a Success Message

  @AddMultipleProductsInCart
  Scenario: To check whether Multiple products can be added to cart
    When The user clicks on AddtoCart Button on Varios Product
    And The user clicks view-cart button in popup displayed
    Then All products Added in cart should be displayed in cart
