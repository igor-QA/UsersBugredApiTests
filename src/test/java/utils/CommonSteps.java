package utils;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;

public class CommonSteps {
        public Response checkResponseResult(Response response, String type, String error) {
            response.then()
                    .log().body()
                    .statusCode(200)
                    .body(type, is(error));
            return response;
        }
}