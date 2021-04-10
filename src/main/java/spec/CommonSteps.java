package spec;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class CommonSteps {
        public void checkResponseResult(Response response, String type, String error) {
            response.then()
                    .log().body()
                    .statusCode(200)
                    .body(type, equalTo(error));

        }
}