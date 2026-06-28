@AccountFeature @Rishwanth
Feature: Rishwanth_Adhishwar_23_05_2026_AccountFeature updated on 15/06/2026
description:To verify the fuctionality of edit account information

  Background:
    Given The user is in HomePage of Ecommerce Lambda TestWebsite

  @EditAccInfo
  Scenario: As a registered user i need to update my account information
    When The user clicks on myAccount link in navBar
    And The user Enters valid email and valid passwords
    And Clicks on Login Button
    And The User clicks on edit account information link on Account page
    And The user clear and enter new telephone number in input field
      | telephone   |
      | 90888999047 |
    And The user clicks on continue Button in Edit Information Page
    Then The user should see a sucess Message Your account has updated successfully

  @InvalidEditAccInfo
  Scenario: User is on Edit account information page and Enters detials Except one mandatory input field should be see a Warning message
    When The user clicks on myAccount link in navBar
    And The user Enters valid email and valid passwords
    And Clicks on Login Button
    And The User clicks on edit account information link on Account page
    And The user Clear all Existing Information in input field and Update Details Except one Input Field
      | fname | lname | email                |
      | Tamil | Arasu | tamilArasu@gmail.com |
    Then The user Should be thrown with a Warning message

  @SubscribeNewsLetter
  Scenario: As a registered user I want to Subscribe NewsLetter,So that I can receive email Regularly
    When The user clicks on myAccount link in navBar
    And The user Enters valid email and valid passwords
    And Clicks on Login Button
    And The User clicks on Subscribe newsletter Link in Account Page
    And check the radio Button as yes
    And Click The  Continue Button
    Then The user Should be Successfully Subscribed and a Success Message should be Displayed

  @AddNewAddress
  Scenario: As a Login in user i need add a new address in the address book
    When The user clicks on myAccount link in navBar
    And The user Enters valid email and valid passwords
    And Clicks on Login Button
	And click the address book and click new address
	And enter the valid details 
	And click continue Button
	Then user can the see the successfully address added message 
	