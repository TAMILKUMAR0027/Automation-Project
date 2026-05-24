@Prasanna @WishlistFeature
Feature: Wishlist Feature - LambdaTest Playground - Prasanna Venkatesh K - 15-05-2026 Update - 18-05-2026
  Description: Validate Wishlist functionality end-to-end — data driven from wishlist_data.csv

  Background:
    Given the user is on the home page
    And the user is a registered user

  @Smoke @ProductAdded
  Scenario: Add single product to wishlist
    And the user navigates to the Top Products section
    When the user hovers over the product "AddSingleProduct" and clicks the wishlist button
    Then a wishlist success notification should be displayed
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    And the wishlist product details should match the selected product

  @Regression @MultipleProduct
  Scenario: Add multiple products to wishlist
    And the user navigates to the Top Collection section
    When the user adds multiple products to the wishlist from csv
    Then a wishlist success notification should be displayed
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    Then all selected products should be displayed in the wishlist page

  @Smoke @RemoveProduct
  Scenario: Remove product from wishlist
    And the user navigates to the wishlist page via account menu
    When the user removes the product "RemoveProduct1" from the wishlist
    Then a wishlist removal success notification should be displayed

  @Regression @RemoveMultiProduct
  Scenario: Remove multiple products from the wishlist
    And the user navigates to the wishlist page via account menu
    When the user removes the following products from the wishlist
      | ProductName     |
      | Apple Cinema 30 |
      | iMac            |
    Then a wishlist removal success notification should be displayed

  @Smoke @AlterMethod
  Scenario: Add product to wishlist via search
    When the user searches for "apple" and presses Enter
    And the user moves to the "iPod Shuffle" and clicks the product
    And the user redirect to the product page and clicks the heart button inside the product image
    Then a wishlist success notification should be displayed
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    And the wishlist product details should match the selected product