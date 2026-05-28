@Prasanna @Search
Feature: Search Feature - LambdaTest Playground
  Author      : Prasanna Venkatesh K
  Created     : 18-05-2026
  Update      : 22-05-2026
  Last Updated: 28-05-2026
  Description : Validate that the search filter lists products based on the entered keyword.

  Background:
    Given the user is on the home page
    When  the user clicks on the search bar on the home page

  # ---------------------------------------------------------------------------
  # SCENARIO OUTLINE — Direct product-name keyword search
  # Validates: results grid, AND every card name contains the keyword.
  # "Kiot" is an intentional no-results case — validated by no-results message.
  # ---------------------------------------------------------------------------

  @Smoke @KeywordSearch
  Scenario Outline: Validate search results match the entered keyword
    And  the user enters "<keyword>" and presses Enter
    Then the application should display products based on the keyword
    And  the application should display products matching the keyword in their name

    Examples:
      | keyword |
      | iMac    |
      |         |

  @Smoke @NoResultSearch
  Scenario Outline: Validate no-results message for unmatched keywords
    And  the user enters "<keyword>" and presses Enter
    Then the application should display products based on the keyword

    Examples:
      | keyword |
      | Kiot    |
      | ffgok   |

  # ---------------------------------------------------------------------------
  # SCENARIO — Manufacturer filter
  # Validates: results appear, manufacturer label on page matches "Apple".
  # Product names ("iMac", "iPod") will NOT contain "Apple" — that is correct.
  # ---------------------------------------------------------------------------

  @Regression @ManufacturerFilter
  Scenario: Validate search results show only manufacturer products
    And  the user enters the product "apple" and presses Enter
    Then the application should display products based on the keyword
    And  the application should list only the manufacturer products based on the "apple"

  # ---------------------------------------------------------------------------
  # SCENARIO — Empty keyword (enable once site behaviour confirmed)
  # ---------------------------------------------------------------------------

  # @Regression @EmptySearch
  # Scenario: Validate search with empty input shows full product catalogue
  #   And  the user enters "" and presses Enter
  #   Then the application should display all products
  #   And  the url should be "https://ecommerce-playground.lambdatest.io/index.php?route=product%2Fsearch&search="
  #   And  the product count should be 15
