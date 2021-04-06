package base.steps;

import io.qameta.allure.Step;
import models.Company;
import spec.ResponseSuccess;
import tests.BaseTest;

import static spec.Request.spec;

public class CompanyBaseSteps extends BaseTest {
    Company company;

    @Step("Создать запрос на создание новой компании")
    public void createNewPostRequest(){
        company = new Company(companyName, companyType, companyUsers ,emailOwner);
    }

    @Step("Отправить POST запроос и проверить результат")
    public void sendAndCheckPostRequest(String str){
        spec()
                .body(company)
       .when()
                .post(companyEndPoint)
       .then()
                .spec(ResponseSuccess.spec());
    }

}
