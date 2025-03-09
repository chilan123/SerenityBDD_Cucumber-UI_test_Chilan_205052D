@UITests
Feature: Add to cart functionality

  @RequiresLogin
  Scenario: Add a product to the cart and verify
    Given I am viewing the product "Radiant Tee"
    When I select size "M"
    And I select color "Blue"
    And I set the quantity to "2"
    And I add the product to the cart
    Then I should see the success message "You added Radiant Tee to your shopping cart." on the product page
    And the cart should display the product "Radiant Tee"

#newly added
  Scenario: Add a product to the cart with an invalid quantity
    Given I am viewing the product "Radiant Tee"
    When I select size "M"
    And I select color "Blue"
    And I set the quantity to "-1"
    And I add the product to the cart
    Then I should see an error message "Please enter a quantity greater than 0"

  Scenario: Add a product to the cart with an excessive quantity
    Given I am viewing the product "Radiant Tee"
    When I select size "M"
    And I select color "Blue"
    And I set the quantity to "20000"
    And I add the product to the cart
    Then I should see an error message "The maximum you may purchase is 10000."


    Scenario: Add a product to the cart without selecting size
    Given I am viewing the product "Radiant Tee"
    And I select color "Blue"
    And I set the quantity to "1"
    And I add the product to the cart
    Then I should see a size selection error message "This is a required field."

   Scenario: Add a product to the cart without selecting color
    Given I am viewing the product "Radiant Tee"
    And I select size "M"
    And I set the quantity to "1"
    And I add the product to the cart
    Then I should see a color selection error message "This is a required field."
    
     




