package AppTests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "Steps/APISteps",
        tags = "@APITests",
        plugin = {"pretty"}
)
public class APITests {

}

