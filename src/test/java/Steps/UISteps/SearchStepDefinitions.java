package Steps.UISteps;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import pages.HomePage;

import static org.junit.Assert.assertTrue;

public class SearchStepDefinitions {

    @Steps
    HomePage homePage;

    @Given("I am on the Magento homepage")
    public void iAmOnTheMagentoHomepage() {
        //open the homepage
        homePage.openHomePage();
    }

    @When("I search {string} in the search bar")
    public void iSearchFor(String productName) {
        //Search the product using name
        homePage.searchForProduct(productName);
    }

    @Then("I should see {string}")
    public void isShouldSeeTheResultsMessageSaying(String message) {
        // Verify the success message is displayed on the product page
        assertTrue(message , homePage.isSearchResultMessageDisplayed(message));
    }

    @Then("I should see a list of products related to {string}")
    public void iShouldSeeAListOfProducts(String productName) {
        // Verify relevant products are displayed.
        assertTrue("Product results are not displayed", homePage.isProductListDisplayed());
    }

    @Then("The product names should contain {string}")
    public void iShouldSeeAListOfProductsContainMySearch(String productName) {
        // Verify the product name is displayed on among products
        assertTrue("Product Results not contains the name "+productName, homePage.isProductListContainProductName(productName));
    }

    @Then("I should see a message saying {string}")
    public void iShouldSeeAMessageSaying(String message) {
        // Verify error message is displayed on the page
        assertTrue("No results message is  displayed", homePage.isNoResultsMessageDisplayed());
    }
}
