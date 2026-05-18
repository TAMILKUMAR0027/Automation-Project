@Checkout
Feature: Checkout Functionality

  Background:
    Given the user launches the ecommerce application

  @LoginCheckout
  Scenario: Verify checkout using existing login account

    Given the user navigates to an in-stock product page
    And the user selects a size from the dropdown
    And the user clicks on Add to Cart button
    And the user clicks on Checkout button
    And the user enters login credentials from excel
    And the user clicks on Login button

    Then the user should be on the checkout billing page
    When the user fills in the billing address details
    And the user clicks on Continue button in billing section

    Then the user should see the delivery method section
    When the user clicks on Continue button in delivery section

    Then the user should see the payment method section
    And the user selects "Cash on Delivery" payment option
    And the user clicks on Continue button in payment section

    Then the user should see the order confirmation summary
    When the user clicks on Confirm Order button

    Then the order should be placed successfully
    And the user should be on the success page

  @RegisterCheckout
  Scenario: Verify checkout error when payment method is not selected using register account

    Given the user navigates to an in-stock product page
    And the user selects a size from the dropdown
    And the user clicks on Add to Cart button
    And the user clicks on Checkout button

    When the user selects "Register Account" option
    And the user enters registration details
      | firstName | lastName     | email               | telephone  | password | confirmPassword | company     | address1       | address2       | city    | postCode | country | region    |
      | Samiha    | Muhabathulla | samiha123@gmail.com | 9876543210 | Test@123 | Test@123        | ABC Company | 12 Main Street | Near Bus Stand | Chennai | 600001   | India   | Tamilnadu |
    And the user agrees to the privacy policy
    And the user clicks on Continue button

    Then the user should be navigated to the delivery method section
    When the user clicks on Continue button in delivery section

    Then the user should be navigated to the payment method section
    And the user does NOT select any payment method
    And the user clicks on Continue button in payment section

    Then the user should see a warning "Please select a payment method!"

  @EmptyCartCheckout
  Scenario: Verify checkout is blocked when cart is empty

    Given the user is logged in
    And the cart is empty
    When the user navigates directly to the checkout page
    Then the user should be redirected to the cart page
    And the user should see "Your shopping cart is empty!" message

  @OutOfStockCheckout
  Scenario: Verify out of stock product cannot be added to cart

    Given the user navigates to an out-of-stock product page
    Then the user should see "Out Of Stock" label on the product page
    And the "Add to Cart" button should not be visible
    And the "Buy Now" button should not be visible