package Steps.UISteps;

import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
import pages.ProductPage;

import static org.junit.Assert.assertTrue;

public class AddReviewStepDefinitions {
    @Steps
    ProductPage productPage;

    @Given("I am on the product page for {string}")
    public void iAmViewingTheProduct(String productName) {
        // Open the product page using the product name
        productPage.openAt("/" + productName.toLowerCase().replace(" ", "-") + ".html");
    }

    @When("I click the {string} button")
    public void navigateToReviewTab(String buttonName) {
        productPage.navigateToReviewTab();
    }

    @When("I fill in the review form with:")
    public void fillReviewForm(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMap(String.class, String.class);
        productPage.fillReviewForm(
                data.getOrDefault("Review",""),
                data.getOrDefault("Summary of Review",""),
                data.getOrDefault("Nickname",""),
                Integer.parseInt(data.getOrDefault("Rating","0"))
        );
    }

    @When("I submit the review")
    public void submitReview() {
        productPage.submitReview();
    }

    @Then("I should see a success message saying {string}")
    public void verifyReviewSuccessMessage(String expectedMessage) {
        assert productPage.isReviewSuccessMessageDisplayed(expectedMessage) : "Review success message not displayed!";
    }

    @Then("I should see my review : {string} among other reviews")
    public void verifyReviewAmongOtherReviews(String review) {
        assertTrue("Review is not visible among reviews", productPage.isReviewVisibleAmongReviews(review));
    }

    @Then("I should not see a success message saying {string}.")
    public void verifyNoSuccessMessage(String expectedMessage) {
        assert productPage.isReviewSuccessMessageDisplayed(expectedMessage) : "Review success message is displayed!, Even with missing values.";
    }

}
