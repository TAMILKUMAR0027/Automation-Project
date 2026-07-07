Feature: Mega Menu

  Scenario: Verify user can open the Mega Menu
    Given the user launches the LambdaTest Ecommerce website
    And the user is on the Home page
    When the user hovers over the "Mega Menu"
    Then the Mega Menu dropdown should be displayed