Feature: TamilKumar_29_05_2026 Affiliate Account

  Background:
    Given the user on home page
    And click My account link
    When The affiliate user enters valid email and valid password
      | email               | password  |
      | testlogin@gmail.com | testlogin |
    And Clicks on Login Button

  @Tamil @AffilateAccount @LoginAffilateAccount
  Scenario: Login and register affiliate account with valid inputs
    And click Affilate account register link
    And enter the valid details for registering via Test data
    And click continue Button
    Then user can the message of successfully your account had been updated

  @Tamil @CustomAffilateAccount
  Scenario: Login user can use custom affilate tracking for a product
	When the user clicks on custom affilate tracking link
	And enter the product name and click generate link button
	Then user can see the generated affilate link for the product
	