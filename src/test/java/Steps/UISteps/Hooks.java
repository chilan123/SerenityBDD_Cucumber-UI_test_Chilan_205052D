package Steps.UISteps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import net.serenitybdd.annotations.Steps;
import pages.LoginPage;

public class Hooks {

    @Steps
    LoginPage loginPage;

    @Before("@RequiresLogin") // Ensure this runs before other hooks or scenarios
    public void setUpDefaultLogin() {
        // Open the login page
        loginPage.openAt("/customer/account/login");

        // Perform login with default test credentials
        loginPage.login("ym@gmail.com", "Ymeka2000");
    }
}
