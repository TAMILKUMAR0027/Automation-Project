@WishlistFeature
Feature: Wishlist Feature - LambdaTest Playground - Prasanna Venkatesh K - 15-05-2026
  Description: Validate Wishlist functionality end-to-end

  Background:
    Given the user is on the home page
    And the user is a registered user

  @Smoke @ProductAdded
  Scenario: Add iMac product to wishlist
    And the user navigates to the Top Products section
    When the user selects the "iMac" product and adds it to the wishlist
    Then a wishlist success notification should be displayed for "iMac"
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    And the wishlist product details should match the selected product

  @Regression @MultipleProduct
  Scenario: Add multiple products to wishlist
    And the user navigates to the Top Collection section
    When the user selects the "Apple Cinema 30" product and adds it to the wishlist
    Then a wishlist success notification should be displayed for "Apple Cinema 30"
    When the user selects the "iPod Nano" product and adds it to the wishlist
    Then a wishlist success notification should be displayed for "iPod Nano"
    And the user clicks the wishlist link from the notification popup
    Then the user should be redirected to the "My Wish List" page
    Then all selected products should be displayed in the wishList page
#
#  @Regression @ValidateProductDetails
#  Scenario: Validate wishlist product details
#    When the user click the iMac product and add to the wishlist
#    And the user clicks wishList button
#    Then the application should navigate to the MyWishList page
#    And the user should see correct product name "iMac"
#    And the user should see correct product price
#    And the user should see correct stock status
#
#  @Smoke @RemoveProduct
#  Scenario: Remove product from wishlist
#    When the user click the iMac product and add to the wishlist
#    And the user clicks wishList button
#    Then the application should navigate to the MyWishList page
#    When the user removes the product from wishlist
#    Then the product should be removed successfully from wishList