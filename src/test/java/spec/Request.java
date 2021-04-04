package spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;

public class Request {

    private static final RequestSpecification SPEC = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setBaseUri("http://users.bugred.ru/tasks/rest")
            .addFilter(filters().withCustomTemplates())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static RequestSpecification spec(){
        return SPEC;
    }
}