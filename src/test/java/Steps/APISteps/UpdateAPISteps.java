package Steps.APISteps;

import Steps.api.BookAPI;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;

public class UpdateAPISteps {

    private final BookAPI bookAPI = new BookAPI();
    private Response response;

    @Given("User is authorized as an admin")
    public void user_is_authorized_as_an_admin() {
        //Handled by Request Factory
    }

    @And("a book exists with Id {int}")
    public void a_book_exists_with_id(Integer bookId) {
        response = bookAPI.getBookById(bookId);
        response.then().statusCode(200);
    }

    @When("User updates the book with ID {int} setting title to {string}, author to {string}")
    public void user_updates_the_book_with_id_setting_title_to_author_to(Integer bookId, String title, String author) {
        response = bookAPI.updateBook(bookId, title, author);
    }

    @When("User sends a PUT request with invalid data including string in an integer field")
    public void user_send_put_request_with_invalid_data() {
        response = bookAPI.sendInvalidUpdateRequest("2", "Don Quixote", "Miguel de Cervantes");
    }

    @When("User sends a PUT request with valid update data")
    public void user_send_put_request_with_valid_update_data() {
        response = bookAPI.updateBook(101, "Treasure Island", "Robert Louis Stevenson");
    }

    @When("User sends a PUT request missing the author field")
    public void user_send_put_request_missing_the_author_field() {
        response = bookAPI.updateBookMissingAuthor(2, "Don Quixote updated title");
    }

    @When("User sends a PUT request without proper authentication credentials")
    public void user_send_put_request_without_proper_authentication_credentials() {
        response = bookAPI.updateWithoutAuthentication(1, "Unauthorized Title", "Unauthorized Author");
    }

    @But("the user sends a PUT request to update the book with ID {int} without proper authentication credentials")
    public void the_user_sends_a_put_request_to_update_the_book_without_proper_authentication_credentials(Integer bookId) {
        response = bookAPI.updateWithoutAuthentication(bookId, "Unauthorized Update", "Anonymous");
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertThat(response.statusCode(), is(equalTo(statusCode)));
    }

    @And("the book with Id {int} should have updated details")
    public void the_book_with_id_should_have_updated_details(Integer bookId) {
        response = bookAPI.getBookById(bookId);
        String updatedAuthor = response.jsonPath().getString("author");
        assertThat(updatedAuthor, is(equalTo("Mark Twain")));
    }

    @And("the response body should contain \"Book not found\"")
    public void the_response_should_indicate_that_the_book_with_id_does_not_exist() {
        assertThat(response.body().asString(), containsString("Book not found"));
    }

    @And("the response body should contain \"Mandatory parameters should not be null\"")
    public void the_response_should_include_a_message_that_required_fields_are_missing() {
        assertThat(response.body().asString(), containsString("Mandatory parameters should not be null"));
    }
}
