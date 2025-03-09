@UITests
Feature: Search functionality

  Scenario: Search with valid product name
    Given I am on the Magento homepage
    When I search "jacket" in the search bar
    Then I should see "Search results for: 'jacket'"
    And I should see a list of products related to "jacket"
    And The product names should contain "jacket"

  Scenario: Search with invalid product name
    Given I am on the Magento homepage
    When I search "xyz123" in the search bar
    Then I should see a message saying "Your search returned no results."


#newly added  
    Scenario: Search with partial product name
    Given I am on the Magento homepage
    When I search "jack" in the search bar
    Then I should see "Search results for: 'jack'"
    And I should see a list of products related to "jack"
    And The product names should contain "jack"
