package com.bookit.step_definitions;

import com.bookit.pages.Room;
import com.bookit.utilities.APIUtilities;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;

public class APIStepDefinitions {
    private RequestSpecification requestSpecification; //this is what we put in given
    private Response response;//this is were we store response data
    private String token;//this is what we use for authentication
    private JsonPath jsonPath;//this is were we store JSON body
    private ContentType contentType;//this is what we use to setup content type

    @Given("authorization token is provided for {string}")
    public void authorization_token_is_provided_for(String role) {

        token= APIUtilities.getToken(role);

    }

    @Given("user accepts content type as {string}")
    public void user_accepts_content_type_as(String string) {
        if (string.toLowerCase().contains("json")){
            contentType=ContentType.JSON;
        }
        else if (string.toLowerCase().contains("xml")){
            contentType=ContentType.XML;
        }
        else if (string.toLowerCase().contains("html")){
            contentType=ContentType.HTML;
        }

    }

    @When("user sends GET request to {string}")
    public void user_sends_GET_request_to(String path) {

        response=given().accept(contentType).auth().oauth2(token).get(path);
    }

    @Then("user should be able to see {int} rooms")
    public void user_should_be_able_to_see_rooms(int expectedNumberOfRooms) {
        List<?> rooms=response.jsonPath().get();
        Assert.assertEquals(expectedNumberOfRooms,rooms.size());
        //response.then().body("", hasSize(expectedNumberOfRooms));
    }

    @Then("user verifies that response status code is {int}")
    public void user_verifies_that_response_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());

    }

    @Then("user should be able to see all room names")
    public void user_should_be_able_to_see_all_room_names() {
        List<Room> rooms=response.jsonPath().getList("",Room.class);
        rooms.forEach(room -> System.out.println(room.getName()));
    }

    @Then("user payload contains following room names:")
    public void user_payload_contains_following_room_names(List<String> dataTable) {

        List<String> roomNames=response.jsonPath().getList("name");
        Assert.assertTrue(roomNames.containsAll(dataTable));

       // MatcherAssert.assertThat(roomNames, hasItem(in(dataTable)));
    }

}
