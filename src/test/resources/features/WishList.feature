@Prasanna @WishlistFeature
Feature: Wishlist Feature - LambdaTest Playground
  Author      : Prasanna Venkatesh K
  Created     : 15-05-2026
  Updated     : 18-05-2026
  Last Update : 28-05-2026
  Description : Validate Wishlist functionality end-to-end — data-driven from wishlist_data.csv

  Background:
    Given the user is on the home page
    And the user is a registered user

  # ---------------------------------------------------------------------------
  # SMOKE SCENARIOS
  # ---------------------------------------------------------------------------

  @Smoke @ProductAdded
  Scenario: Add single product to wishlist from Top Products section
  # Validates: hover-reveal wishlist button, success toast, popup redirect, wishlist table entry
    And  the user navigates to the Top Products section
    When the user hovers over the product "AddSingleProduct" and clicks the wishlist button
    Then a wishlist success notification should be displayed
    And  the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    And  the wishlist product details should match the selected product

  @Smoke @RemoveProduct
  Scenario: Remove a single product from the wishlist
  # Validates: remove button, page reload, alert-success banner
    And  the user navigates to the wishlist page via account menu
    When the user removes the product "RemoveProduct1" from the wishlist
    Then a wishlist removal success notification should be displayed

  @Smoke @AlterMethod
  Scenario: Add product to wishlist via search and product detail page
  # Validates: search → product detail → heart button → toast → redirect → table
    When the user searches for "apple" and presses Enter
    And  the user moves to the "iPod Shuffle" and clicks the product
    And  the user redirect to the product page and clicks the heart button inside the product image
    Then a wishlist success notification should be displayed
    And  the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    And  the wishlist product details should match the selected product

  # ---------------------------------------------------------------------------
  # REGRESSION SCENARIOS
  # ---------------------------------------------------------------------------

  @Regression @MultipleProduct
  Scenario: Add multiple products to wishlist from Top Collection section
  # Validates: sequential wishlist adds (CSV-driven), toast, redirect, all products in table
    And  the user navigates to the Top Collection section
    When the user adds multiple products to the wishlist from csv
    Then a wishlist success notification should be displayed
    And  the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    Then all selected products should be displayed in the wishlist page

  @Regression @RemoveMultiProduct
  Scenario: Remove multiple products from the wishlist
  # Validates: sequential removal via inline DataTable, alert-success banner for last removal
    And  the user navigates to the wishlist page via account menu
    When the user removes the following products from the wishlist
      | ProductName     |
      | Apple Cinema 30 |
      | iMac            |
    Then a wishlist removal success notification should be displayed