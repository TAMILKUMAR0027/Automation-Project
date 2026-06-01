@Smoke @E2E
Feature: Smoke_E2E_Test_Suite - Prasanna Venkatesh K - 30-05-2025
  Description:
  End-to-End smoke test covering the critical happy path across all major modules —
  Homepage, Login, Search, Product Details, Cart, Checkout, and Logout.
  All steps are reused from existing feature step definitions.

  # ─────────────────────────────────────────────────────────────────
  # SCENARIO 1 — Homepage sanity (reuses @Prasanna @Smoke steps)
  # StepDef: PrasannaSD (Smoke feature)
  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_01 Validate homepage loads correctly
    Given User navigates to application url
    Then  User should be on correct url
    And   User should see correct page title
    And   User should see the application logo

  # ─────────────────────────────────────────────────────────────────
  # SCENARIO 2 — Login with valid credentials (reuses @Rishwanth @LoginFeature steps)
  # StepDef: LoginSD (LoginFeature)
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
  # SCENARIO 3 — Search for a product (reuses @Prasanna @Search steps)
  # StepDef: SearchDif
  # ─────────────────────────────────────────────────────────────────
    @E2E
    Scenario: E2E_03 Search and validate HP product results
      Given the user is on the home page
      When  the user clicks on the search bar on the home page
      And   the user enters "HP" and presses Enter
      Then  the application should display products based on the keyword
      And   the application should display products matching the keyword in their name



  # ─────────────────────────────────────────────────────────────────
  # SCENARIO 4 — Product details page (reuses @Tamil @productDetails steps)
  # StepDef: productPageDefinition
  # ─────────────────────────────────────────────────────────────────

  @E2E
  Scenario: E2E_04 Validate HP product details page
    When  the user clicks the "HP LP3065" and click the product
    Then  the application should be redirect to the current product page
    And   the product Availability should be "In Stock"
    And   Product title should be displayed
    And   Product price should be displayed
    And   Product availability status should be displayed

  # ─────────────────────────────────────────────────────────────────
  # SCENARIO 5 — Add product to cart (reuses @Rishwanth @CartFeature steps)# Ste
  # StepDef: CheckoutStep (navigate) + CartSD (cart assertion)
  # CartSD.getProductName() asserts "HP LP3065" — matches this flow
  # ─────────────────────────────────────────────────────────────────

  @E2E
  Scenario: E2E_05 Add HP LP3065 to cart and verify
    And   the user clicks the add to cart button
    And   the user clicks the shopping cart link from the popup
    Then  The added product should be in cart Page


  # ─────────────────────────────────────────────────────────────────
  # SCENARIO 6 — Checkout with existing account (reuses @Samiha @Checkout steps)
  # StepDef: CheckoutStep (@LoginCheckout path)
  # ─────────────────────────────────────────────────────────────────
  @E2E
  Scenario: E2E_06 Complete checkout with HP LP3065
    And   the user clicks the checkout button from the cart page
    Then  the application redirect to the chechkout
    And   the user clicks the "I want to use a new address"
    And   the user enters the firstname, lastname, company, address1, city, postcode, country, Regionstate
    And   the user clicks same billing address and cash on delivery and flat rate button
    And   the user clicks the terms & conditions button and continue the checkout
    Then  the order should be successfully placed and application redirect to the order confirmation
  # ─────────────────────────────────────────────────────────────────
  # SCENARIO 7 — Logout (reuses @Jothika @Logout steps)
  # StepDef: Logout
  # ─────────────────────────────────────────────────────────────────

  @E2E @E2EEnd
  Scenario: E2E_07 Logout from the application
    When the user clicks on Logout link
    Then  the user should receive an intimation message regarding logout
