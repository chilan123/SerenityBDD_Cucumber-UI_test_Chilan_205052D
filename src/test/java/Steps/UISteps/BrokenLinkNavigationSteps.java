package Steps.UISteps;

import io.cucumber.java.en.*;
import pages.NavigationLinksPage;
import net.serenitybdd.annotations.Steps;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BrokenLinkNavigationSteps {

    @Steps
    NavigationLinksPage navigationLinksPage;

    @Given("I am on the Magento {string} page")
    public void iAmOnTheMagentoHomepage(String pageName) {
        // Open the page using the page name
        navigationLinksPage.navigateToPage(pageName);
        navigationLinksPage.withTimeoutOf(5, TimeUnit.SECONDS);
    }

    @When("I click on each navigation link")
    public void iClickOnEachNavigationLink() {
        // Click on all navigation links one by one
        navigationLinksPage.clickEachNavigationLink();
    }

    @Then("I should be redirected to the correct page")
    public void iShouldBeRedirectedToCorrectPage() {
        // Verify redirection to the correct page for each link
        assertTrue("Not all links redirected to valid pages", navigationLinksPage.areThereInvalidPages());
    }

    @Then("I should not see a {string} error")
    public void iShouldNotSeeA404PageNotFoundError(String error) {
        // Verify no 404 error page is encountered
        assertTrue("404 Page Not Found error detected", navigationLinksPage.areTherePagesWith404Error());
    }

    @Then("All <a> tags should have a valid href attribute")
    public void allTagsShouldHaveValidHrefAttributes() {
        // Verify that all <a> tags have valid href attributes
        assertTrue("Some <a> tags are missing valid href attributes", navigationLinksPage.areAllTagsHaveHref());
    }
}
