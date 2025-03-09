package Steps.UISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.HomePage;

import static org.junit.Assert.*;

public class SignOutSteps {

    private HomePage homePage;

    @Given("I am logged in with valid credentials and at the home page")
    public void iAmLoggedInWithValidCredentialsandAtTheHomePage() {
        homePage.open();
    }

    @When("I click the {string} button on HomePage")
    public void iClickTheButton(String buttonName) {
        if (buttonName.equals("Sign Out")) {
            homePage.clickSignOutButton();
        }
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String expectedMessage) {
        assertTrue("Logout Message Doesnt appear",homePage.isLoggedOutSuccessfully(expectedMessage));
    }

    @Then("In the home I could click {string} button and then redirect to login page")
    public void iShouldBeRedirectedToTheLoginPage(String buttonName) {
        assertTrue("Not logout properly",homePage.isLogInPageVisible());
    }
}

