package Steps.factory;

import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

//Factory Design Pattern
public class RequestFactory {

    public static RequestSpecification adminRequest() {
        return SerenityRest.given()
                .auth()
                .preemptive()
                .basic("admin", "password")
                .contentType("application/json");
    }
}
