@Checkout
Feature: Checkout Functionality using DataTable List

  Background:
    Given the user launches the ecommerce application


  @LoginCheckout
  Scenario: Verify checkout using existing login account

    Given the user adds an in-stock product to the cart
    And the user clicks on Checkout button

    When the user selects "Login" account option
    And the user enters login email "testlogin@gmail.com"
    And the user enters login password "testlogin"
    And the user clicks on Login button

    Then the user should navigate to confirm order page

    When the user clicks on Confirm Order button

    Then the order should be placed successfully
    And the user should navigate to success page



  @RegisterCheckout
  Scenario: Verify checkout error when payment method is not selected using register account

    Given the user adds an in-stock product to the cart
    And the user clicks on Checkout button
    When the user selects "Register Account" option

    And the user enters registration details
    | Samiha |Muhabathulla | samiha123@gmail.com |  9876543210 |Test@123|Test@123|ABC Company|12 Main Street|Near Bus Stand|Chennai|600001|India|Tamilnadu|
   

    And the user agrees to privacy policy
    And the user clicks on Continue button
    Then the user should remain on checkout page
    When the user clicks on Confirm Order button without selecting payment method
    Then the user should see payment method required alert


  @OutOfStockCheckout
  Scenario: Verify error message for out of stock product during checkout

    Given the user adds an out-of-stock product to the cart
    When the user clicks on Checkout button
    Then the user should see out of stock error message
    And the user should not be allowed to place the order

