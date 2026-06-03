@Tamil @ContactForm
Feature: TamilKumar_30-05_2026_Contact Form Feature

  Background:
    Given The user is on the home page
    And The user clicks the AddsOn link
    And The user clicks the Widgets button

  @ValidInformation
  Scenario Outline: Verify contact form submission with valid details
    When The user enters "<Name>" in the Name field
    And The user enters "<Email>" in the Email field
    And The user enters "<Subject>" in the Subject field
    And The user enters "<Message>" in the Message field
    And The user clicks the Submit button
    Then The success message should be displayed

    Examples:
      | Name        | Email            | Subject         | Message                         |
      | Tamil Kumar | tamil@gmail.com  | Contact Request | This is a test contact message. |
      | John Smith  | john@example.com | Support Query   | Need help with the application. |
      | Tamil       | tk@gmail.com     | Request Form    | There is a bug                  |

  @InvalidInformation
  Scenario Outline: Verify contact form submission with invalid email
    When The user enters "<Name>" in the Name field
    And The user enters "<Email>" in the Email field
    And The user enters "<Subject>" in the Subject field
    And The user enters "<Message>" in the Message field
    And The user clicks the Submit button
    Then An email validation error message should be displayed and remain on the contact form page

    Examples:
      | Name        | Email          | Subject         | Message                         |
      | Tamil Kumar | tamilgmail.com | Contact Request | This is a test contact message. |
      | John Smith  | johnexample.com| Support Query   | Need help with the application. |
      | Tamil       | tkgmail.com    | Request Form    | There is a bug                  |