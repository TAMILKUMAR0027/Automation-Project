@Tamil @ProductCompare
Feature: TamilKumar_17_05_2026_Product_compare_functionality Updated_18_05_2026

  Background:
    Given the user is on the homepage

  @CompareWithoutProducts
  Scenario: To perform compare without adding products to list
    When the user clicks product compare without adding product
    Then the user should see no products to compare error "You have not chosen any products to compare."

  @compareWithProduct
  Scenario: Compare a product from home page
    When the user selects any product from the home page
    And clicks on Compare this Product on the product details page
    Then the compare confirmation message should be displayed

  @compareWithMultipleProduct
  Scenario: Compare multiple products from home page
    When the user selects first product from the home page and click compare button
    And the user selects second product from the home page and click compare button
    Then the user should see both products in the comparison page
  @ModifyInTheCompare
  Scenario: User need to remove the product from Compare
    When the user selects any product from the home page
	And clicks on Compare this Product on the product details page
	And click compare Button
	And remove the product from compare 
	Then the user get an message "Success: You have modified your product comparison!"
