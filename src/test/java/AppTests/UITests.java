package AppTests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = "Steps/UISteps",
        tags = "@UITests",
        plugin = {"pretty"}
)
public class UITests {}

