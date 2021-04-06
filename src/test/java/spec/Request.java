package spec;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;
import static io.restassured.RestAssured.with;

public class Request {

    private static final RequestSpecification SPEC =  with()
            .baseUri("http://users.bugred.ru")
            .basePath("/tasks/rest")
            .filter(filters().withCustomTemplates())
            .contentType(ContentType.JSON)
            .log().all();

    public static RequestSpecification spec(){
        return SPEC;
    }
}