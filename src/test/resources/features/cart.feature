@CartFeature
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
  Scenario Outline: To check the quantity is updating on a specific product in cart
    When The user clicks on a product in Top Collection menu
    And The user clicks on add to cart button in Product details page
    And The user clicks view-cart button in popup displayed
    And The user enter the quantity as "<quantity>" based on needs in quantity input box
    And The user clicks quantity update button
    Then The user should see a success message quantity updated successfully

    Examples:
      | quantity |
      | 5        |
