@Smoke @E2E @WishlistE2E
Feature: Smoke_E2E_Test_Suite_With_Wishlist - Prasanna Venkatesh K - 14-06-2026
  Description:
  End-to-End smoke test: Homepage → Login → Search → Product Details →
  Add to Wishlist (Heart Button) → Logout

  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_01 Validate homepage loads correctly
    Given User navigates to application url
    Then  User should be on correct url
    And   User should see correct page title
    And   User should see the application logo

  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_02 Login with valid credentials
    When  The user clicks on My account link
    And   The user Enters valid email  and valid password
      | email               | password  |
      | testlogin@gmail.com | testlogin |
    And   Clicks on Login Button
    Then  The user should be successfully Logged in.

  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_03 Search for HP product
    Given the user is on the home page
    When  the user clicks on the search bar on the home page
    And   the user enters "HP" and presses Enter
    Then  the application should display products based on the keyword
    And   the application should display products matching the keyword in their name

  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_04 Validate HP LP3065 product details
    When  the user clicks the "HP LP3065" and click the product
    Then  the application should be redirect to the current product page
    And   the product Availability should be "In Stock"
    And   Product title should be displayed
    And   Product price should be displayed
    And   Product availability status should be displayed

  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_05 Add HP LP3065 to Wishlist via Product Detail Page
    When  the user redirect to the product page and clicks the heart button inside the product image
    Then  a wishlist success notification should be displayed
    And   the user clicks the wishlist link from the notification popup
    Then  the user should be redirected to the "My Wish List" page
    And   the wishlist product details should match the selected product

  # ─────────────────────────────────────────────────────────────────
  @E2E @E2EEnd
  Scenario: E2E_06 Logout from the application
    When the user clicks on Logout link
    Then the user should receive an intimation message regarding logout