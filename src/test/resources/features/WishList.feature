@WishlistFeature
Feature: Wishlist Feature - LambdaTest Playground
  Description: Validate Wishlist functionality end-to-end

  Background:
    Given The user is on the home page of Website
    And the user navigate to Top Products section

  @Smoke @ProductAdded
  Scenario: Add single product to wishlist
    When the user click the iMac product and add to the wishlist
    Then the product should be added to the wishList
    And the user clicks wishList button
    Then the application should navigate to the MyWishList page

  @Regression @MultipleProduct
  Scenario: Add multiple products to wishlist
    When the user adds multiple products to the wishlist
    Then all selected products should be displayed in the wishList page

  @Regression @ValidateProductDetails
  Scenario: Validate wishlist product details
    When the user click the iMac product and add to the wishlist
    And the user clicks wishList button
    Then the application should navigate to the MyWishList page
    And the user should see correct product name "iMac"
    And the user should see correct product price
    And the user should see correct stock status

  @Smoke @RemoveProduct
  Scenario: Remove product from wishlist
    When the user click the iMac product and add to the wishlist
    And the user clicks wishList button
    Then the application should navigate to the MyWishList page
    When the user removes the product from wishlist
    Then the product should be removed successfully from wishList