package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class ResponseError {

    private static final ResponseSpecification error = new ResponseSpecBuilder()
            .expectStatusCode(200) //TODO 400
            .expectBody("type", equalTo("error"))
            .build();

    public static ResponseSpecification spec() {
        return error;
    }
}