package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LoginPage extends PageObject {

    private final By USERNAME_FIELD = By.id("email");
    private final By PASSWORD_FIELD = By.id("pass");
    private final By LOGIN_BUTTON = By.id("send2");
    private final By SIGN_IN_BUTTON = By.xpath("//*[contains(text(),'Sign In')]");

    public void login(String username, String password) {
        $(SIGN_IN_BUTTON).click();
        withTimeoutOf(5, TimeUnit.SECONDS);
        $(USERNAME_FIELD).type(username);   // Enter username
        $(PASSWORD_FIELD).type(password);   // Enter password
        $(LOGIN_BUTTON).click();           // Click the login button
    }
}
