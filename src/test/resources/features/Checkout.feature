@Samiha @Checkout
Feature: Checkout Functionality

  Background:
    Given the user launches the ecommerce application

  @LoginCheckout
  Scenario: Verify checkout using existing login account
    And the user should be an registered users
    When the user enters the "HP" in the search bar
    And the user clicks the "HP LP3065" and click the product
    Then the application should be redirect to the current product page
    And the product Availability should be "In Stock"
    And the user clicks the add to cart button
    And the user clicks the shopping cart link from the popup
    And the user clicks the checkout button from the cart page
    Then the application redirect to the chechkout
    And the user clicks the "I want to use a new address"
    And the user enters the firstname, lastname, company, address1, city, postcode, country, Regionstate
    And the user clicks same billing address and cash on delivery and flat rate button
    And the user clicks the terms & conditions button and continue the checkout
    Then the order should be successfully placed and application redirect to the order confirmation

  @RegisterCheckout
  Scenario: Verify checkout without login
    When the user enters the "HP" in the search bar
    And the user clicks the "HP LP3065" and click the product
    Then the application should be redirect to the current product page
    And the product Availability should be "In Stock"
    And the user clicks the add to cart button
    And the user clicks the shopping cart link from the popup
    And the user clicks the checkout button from the cart page
    Then the application redirect to the chechkout
    And the user selects "Register Account" option
    And the user enters registration details
      | firstName | lastName     | email                 | telephone  | password | confirmPassword | company     | address1       | address2       | city    | postCode | country | region     |
      | Samiha    | Muhabathulla | sami190@gmail.com | 9876543210 | Test@123 | Test@123        | ABC Company | 12 Main Street | Near Bus Stand | Chennai | 600001   | India   | Tamil Nadu |
    And the user agrees to the privacy policy
    And the user clicks on Continue button
    Then the order should be successfully placed and application redirect to the order confirmation

  @EmptyCartCheckout
  Scenario: Verify checkout is blocked when cart is empty
    When the user clicks the cart button in the top of the navbar
    And the application opens and slidebar and user clicks the checkout
    Then the application shows the shopping Cart is empty!
