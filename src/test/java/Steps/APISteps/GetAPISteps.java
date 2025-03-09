package Steps.APISteps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.StringContains.containsString;

public class GetAPISteps {
    private static final String GET_ALL_ENDPOINT = "http://localhost:7081/api/books";
    private static final String GET_ENDPOINT = "http://localhost:7081/api/books/";
    private Response response;

    @Given("User is authorized as an admin to get books")
    public void user_is_authorized_to_get_all_books_as_an_admin() {
        SerenityRest.reset();
        SerenityRest.given().auth().preemptive().basic("admin", "password");
    }

    @Given("User is authorized as a regular user to get books")
    public void user_is_authorized_to_get_all_books_as_a_regular_user() {
        SerenityRest.reset();
        SerenityRest.given().auth().preemptive().basic("user", "password");
    }

    @When("the admin user sends a GET all books request")
    public void the_admin_user_sends_a_get_all_books_request() {
        response = SerenityRest.given()
                .auth().preemptive().basic("admin", "password")
                .when().get(GET_ALL_ENDPOINT);
    }

    @When("the regular user sends a GET all books request")
    public void the_regular_user_sends_a_get_all_books_request() {
        response = SerenityRest.given()
                .auth().preemptive().basic("user", "password")
                .when().get(GET_ALL_ENDPOINT);
    }

    @When("the admin user sends a GET request with ID {int}")
    public void the_admin_user_sends_a_get_request_with_book_ID(Integer bookId) {
        response = SerenityRest.given()
                .auth().preemptive().basic("admin", "password")
                .when().get(GET_ENDPOINT+ bookId);
    }

    @When("the regular user sends a GET request with ID {int}")
    public void the_regular_user_sends_a_get_request_with_book_ID(Integer bookId) {
        response = SerenityRest.given()
                .auth().preemptive().basic("user", "password")
                .when().get(GET_ENDPOINT+ bookId);
    }


    @Then("the status of the response should be {int}")
    public void the_status_of_the_response_should_be(Integer statusCode) {
        assertThat(response.statusCode(), is(equalTo(statusCode)));
    }


    @And("the response should contain list of available books with Id {int}")
    public void the_response_should_contain_a_list_of_available_books_with_Id(Integer bookId) {
        response.then().body("$", not(empty()));
        response.then().body("size()", greaterThan(0));
        System.out.println(response.body().asString());

        // Check second book in the response(since first book is modified by updating)
        response.then().body("[1].title", equalTo("1984"));
        response.then().body("[1].author", equalTo("George Orwell"));
    }

    @And("the response should contain a book with Id {int}")
    public void the_response_should_contain_a_book_with_Id(Integer bookId) {
        response.then().body("$", not(empty()));
        response.then().body("size()", greaterThan(0));
        //For testing
        System.out.println(response.body().asString());

        // Check second book in the response(since first book is modified by updating)
        response.then().body("title", equalTo("1984"));
        response.then().body("author", equalTo("George Orwell"));
    }

    @And("the response should contain an error message \"Book not found\" for the book with Id {int}")
    public void the_response_should_contain_an_error_message_for_the_book_with_Id(Integer bookId) {
        assertThat(response.body().asString(), containsString("Book not found"));
    }

}
