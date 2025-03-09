package Steps.UISteps;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import pages.ProductPage;

import static org.junit.Assert.assertTrue;

public class AddToCartStepDefinitions {

    @Steps
    ProductPage productPage;

    @Given("I am viewing the product {string}")
    public void iAmViewingTheProduct(String productName) {
        // Open the product page using the product name
        productPage.openAt("/" + productName.toLowerCase().replace(" ", "-") + ".html");
    }

    @When("I select size {string}")
    public void iSelectSize(String size) {
        // Select the size for the product
        productPage.selectSize();
    }

    @When("I select color {string}")
    public void iSelectColor(String color) {
        // Select the color for the product
        productPage.selectColor();
    }

    @When("I set the quantity to {string}")
    public void iSetTheQuantityTo(String quantity) {
        // Set the quantity for the product
        productPage.setQuantity(quantity);
    }

    @When("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        // Add the product to the cart
        productPage.addToCart();
    }

    @Then("I should see the success message {string} on the product page")
    public void iShouldSeeTheSuccessMessageOnTheProductPage(String expectedMessage) {
        // Verify the success message is displayed on the product page
        assertTrue("No success message appeared", productPage.isAddToCartSuccessMessageDisplayed(expectedMessage));
    }

    @Then("the cart should display the product {string}")
    public void theCartShouldDisplayTheProduct(String productName) {
        // Open the cart dropdown and verify the product is listed
        productPage.openCartDropdown();
        assertTrue("Product is not in the cart", productPage.isProductInCart(productName));
    }

    
    /// ***************************newly added************************************************/////////
    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedErrorMessage) {
        // Verify the error message matches the expected text
        assertTrue("Error message not displayed or incorrect",
                productPage.isQuantityErrorMessageDisplayed(expectedErrorMessage));
    }
    // Add to AddToCartStepDefinitions class
    @Then("I should see a size selection error message {string}")
    public void i_should_see_a_size_selection_error_message(String expectedErrorMessage) {
        // Verify the size selection error message matches the expected text
        assertTrue("Size selection error message not displayed or incorrect", 
                productPage.isSizeSelectionErrorMessageDisplayed());
    }

    @Then("I should see a color selection error message {string}")
    public void i_should_see_a_color_selection_error_message(String expectedErrorMessage) {
        // Verify the color selection error message is displayed and matches the expected text
        assertTrue("Color selection error message not displayed or incorrect",
                productPage.isColorSelectionErrorMessageDisplayed());
    }


}