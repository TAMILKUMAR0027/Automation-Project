@WishlistFeature
Feature: Wishlist Feature - LambdaTest Playground - Prasanna Venkatesh K - 15-05-2026
  Description: Validate Wishlist functionality end-to-end — data driven from wishlist_data.csv

  Background:
    Given the user is on the home page
    And the user is a registered user

  @Smoke @ProductAdded
  Scenario Outline: Add iMac product to wishlist
    And the user navigates to the Top Products section
    When the user selects the "<productName>" product and adds it to the wishlist
    Then a wishlist success notification should be displayed for "<productName>"
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "<expectedPage>" page
    And the wishlist product details should match the selected product

    Examples: src/test/resources/testdata/wishlist_data.csv
      | productName | expectedPage |
      | iMac        | My Wish List |

  @Regression @MultipleProduct
  Scenario Outline: Add multiple products to wishlist
    And the user navigates to the Top Collection section
    When the user selects the "<product1>" product and adds it to the wishlist
    Then a wishlist success notification should be displayed for "<product1>"
    When the user selects the "<product2>" product and adds it to the wishlist
    Then a wishlist success notification should be displayed for "<product2>"
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "<expectedPage>" page
    Then all selected products should be displayed in the wishList page

    Examples: src/test/resources/testdata/wishlist_data.csv
      | product1        | product2  | expectedPage |
      | Apple Cinema 30 | iPod Nano | My Wish List |

  @Smoke @RemoveProduct
  Scenario Outline: Remove product from wishlist
    And the user navigates to the wishlist page via account menu
    When the user removes the product "<productName>" from the wishlist
    Then a wishlist removal success notification should be displayed

    Examples: src/test/resources/testdata/wishlist_data.csv
      | productName |
      | iPod Nano   |