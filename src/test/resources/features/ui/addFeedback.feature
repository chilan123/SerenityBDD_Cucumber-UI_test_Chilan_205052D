@UITests
Feature: Submit feedback on a product
  As a user
  I want to submit a review on a product
  So that I can share my feedback

  Scenario: Add a review with valid details
    Given I am on the product page for "Radiant Tee"
    When I click the "Review" button
    And I fill in the review form with:
      | Review           | "This jacket is awesome!" |
      | Summary of Review| "Excellent quality"       |
      | Nickname         | "JohnDoe"                 |
      | Rating           |  5                        |
    And I submit the review
    Then I should see a success message saying "You submitted your review for moderation."
    And I should see my review : "This jacket is awesome!" among other reviews

  Scenario: Add a review with atleast one missing details
    Given I am on the product page for "Radiant Tee"
    When I click the "Review" button
    And I fill in the review form with:
      | Summary of Review| "Excellent quality"       |
      | Nickname         | "JohnDoe"                 |
      | Rating           |  5                        |
    And I submit the review
    Then I should not see a success message saying "You submitted your review for moderation.".
