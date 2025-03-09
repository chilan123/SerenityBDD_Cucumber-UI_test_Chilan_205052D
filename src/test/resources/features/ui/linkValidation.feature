@UITests
Feature: Broken link detection

  @RequiresLogin
  Scenario: Validate all navigation links for gear Page
    Given I am on the Magento "gear" page
    When I click on each navigation link
    Then I should be redirected to the correct page
    And I should not see a "404 Not Found" error
    Then All <a> tags should have a valid href attribute

  @RequiresLogin
  Scenario: Validate all navigation links for sales Page
    Given I am on the Magento "sale" page
    When I click on each navigation link
    Then I should be redirected to the correct page
    And I should not see a "404 Not Found" error
    And All <a> tags should have a valid href attribute

  @RequiresLogin
  Scenario: Validate all navigation links for what's new Page
    Given I am on the Magento "what is new" page
    When I click on each navigation link
    Then I should be redirected to the correct page
    And I should not see a "404 Not Found" error
    And All <a> tags should have a valid href attribute

  @RequiresLogin
  Scenario: Validate all navigation links for women Page
    Given I am on the Magento "women" page
    When I click on each navigation link
    Then I should be redirected to the correct page
    And I should not see a "404 Not Found" error
    And All <a> tags should have a valid href attribute

  Scenario: Validate all navigation links for men Page
    Given I am on the Magento "men" page
    When I click on each navigation link
    Then I should be redirected to the correct page
    And I should not see a "404 Not Found" error
    And All <a> tags should have a valid href attribute

  Scenario: Validate all navigation links for training Page
    Given I am on the Magento "training" page
    When I click on each navigation link
    Then I should be redirected to the correct page
    And I should not see a "404 Not Found" error
    And All <a> tags should have a valid href attribute
