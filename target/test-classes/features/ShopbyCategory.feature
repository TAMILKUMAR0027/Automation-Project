@ShopByCategory
Feature: Shop By Category Navigation
  Description:
  Verify that the user is able to navigate to different product categories
  using the Shop By Category menu.

  Background:
    Given the user launches the ecommerce application

  @ValidCategoryNavigation
  Scenario Outline: Verify user can navigate using Shop By Category options

    When the user clicks on the Shop by Category menu
    And the user selects the "<Category>" category
    Then the user should navigate to the Category page and the page title should contain "<Expected Title>"

    Examples:
      | Category              | Expected Title |
      | Desktops & Monitors   | Monitors       |
      | Web Cameras           | Web Cameras    |
      | Phone, Tablets & Ipod | Tablets        |
      | Laptops & Notebooks   | Laptops        |
