@UITests
Feature: Sign Out functionality
  As a logged-in user
  I want to sign out from my account
  So that I can ensure my session is securely ended
  @RequiresLogin
  Scenario: Successful sign out
    Given I am logged in with valid credentials and at the home page
    When I click the "Sign Out" button on HomePage
    Then I should see the message "You are signed out"
    And In the home I could click "Sign In" button and then redirect to login page