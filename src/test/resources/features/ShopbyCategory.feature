@ShopByCategory
Feature: Shop By Category Navigation --Samiha 13/05/2026

  Description:
  Verify that the user is able to navigate to different product categories
  using the Shop By Category menu in the ecommerce application.

  Background:
    Given the user launches the ecommerce application
    And the user is on the home page
  @Smoke
  @ValidCategoryNavigation
  Scenario Outline: Verify user can navigate using valid Shop By Category options
    When the user clicks on the "Shop by Category" menu
    And the user selects the "<Category>" category
    Then the user should be navigated to the "<Category>" page

    Examples:
      | Category              |
      | Desktops & Monitors   |
      | Web Cameras           |
      | Phone, Tablets & Ipod |
      | Laptops & Notebooks   |

  @InvalidCategoryNavigation
  Scenario Outline: Verify invalid category is not available in Shop By Category
    When the user clicks on the "Shop by Category" menu
    Then the "<InvalidCategory>" category should not be available

    Examples:
      | InvalidCategory |
      | Watches         |
      | Gaming Consoles |
      | Smart TVs       |
      | Books           |