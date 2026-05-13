@ShopByCategory
Feature: Shop By Category Navigation

  Description:
  Verify that the user is able to navigate to different product categories
  using the Shop By Category menu in the ecommerce application.

  Background:
    Given the user launches the ecommerce application
    And the user is on the home page

  @CategoryNavigation
  Scenario Outline: Verify user can navigate using Shop By Category options
    When the user clicks on the "Top categories" menu
    And the user selects the "<Category>" category
    Then the user should be navigated to the "<Category>" page

    Examples:
      | Category              |
      | Desktops & Monitors   |
      | Web Cameras           |
      | Phone, Tablets & Ipod |
      | Laptops & Notebooks   |