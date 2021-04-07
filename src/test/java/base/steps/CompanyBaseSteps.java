package base.steps;

import io.qameta.allure.Step;
import models.Company;
import spec.ResponseError;
import spec.ResponseSuccess;
import tests.BaseTest;

import static spec.Request.spec;

public class CompanyBaseSteps extends BaseTest {
    Company company;

    @Step("Создать запрос на создание новой компании")
    public void createNewPostRequest() {
        company = new Company(companyName, companyType, companyUsers, emailOwner);
    }

    @Step("Создать запрос на создание новой компании без обязательного поля Email")
    public void createPostRequestWithEmptyEmail() {
        company = new Company(companyName, companyType, companyUsers ,"");
    }

    @Step("Создать запрос с некорректным Email")
    public void createPostRequestWithIncorrectEmail(){
        company = new Company(emailOwner, companyName, companyUsers, companyType);
    }
    @Step("Создать запрос с некорректным Типом")
    public void createPostRequestWithIncorrectType(String str) {
        String companyType = "LLC";
        company = new Company(companyName, companyType, companyUsers, emailOwner);
    }
    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckErrorPostRequest(String str) {
         spec()
                .body(company)
         .when()
                .post(companyEndPoint)
         .then()
                .spec(ResponseError.spec());
    }
    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckSuccessPostRequest(String str) {
         spec()
                .body(company)
         .when()
                .post(companyEndPoint)
         .then()
                .spec(ResponseSuccess.spec());
    }
}