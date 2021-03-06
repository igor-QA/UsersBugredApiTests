package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class ResponseSuccess {

    private static final ResponseSpecification success = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("type", equalTo("success"))
            .build();

    public static ResponseSpecification spec() {
        return success;
    }
}