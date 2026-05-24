@Tamil @AffilateAccount
Feature: TamilKumar_23_05_2026 Affilate account

  Background:
    Given the user on home page
    And click My account link

  @LoginAffilateAccount
  Scenario: Login and register affiliate account with valid inputs
  
    And The user Enters valid email and valid password
      | email               | password  |
      | testlogin@gmail.com | testlogin |
    And Clicks on Login Button
    And click Affilate account register link
    And enter the valid details for registering via Test data
    And click continue Button
    Then user can the message of successfully your account had been updated
	
	
	