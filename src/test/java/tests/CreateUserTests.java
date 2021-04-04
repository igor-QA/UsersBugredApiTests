package tests;

import tests.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTests extends BaseTest {

    @Tag(name="positive")
    @Test
    public void createUser() {
        Response response =
                given().
                        contentType("application/json").body
                        ("{\n" +
                                "  \"email\": \" \",\n" +
                                "  \"name\": \"Genzel Argue\",\n" +
                                "  \"tasks\": [56],\n" +
                                "  \"companies\": [7, 8]\n" +
                                "}\n").
                        when().
                        post( "/tasks/rest/createuser").
                        then().
                        statusCode(200).
                        assertThat().body("type", equalTo("success")).
                        extract().response();

    }
}
