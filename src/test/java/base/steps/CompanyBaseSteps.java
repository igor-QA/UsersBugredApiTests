package base.steps;

import models.Company;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import spec.ResponseError;
import spec.ResponseSuccess;
import generate.DataGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static spec.Endpoints.COMPANY;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class CompanyBaseSteps extends DataGenerator {
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
                .post(COMPANY.path())
         .then()
                .spec(ResponseError.spec());
    }
    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckSuccessPostRequest(String str) {
         spec()
                .body(company)
         .when()
                .post(COMPANY.path())
         .then()
                .spec(ResponseSuccess.spec());
    }
    @Step("Создать запрос на создание компании с данными, которые уже есть в системе")
    public void createAlreadyExistCompany(){
        Response response = spec()
                .body(readFromFile("src/test/resources/createCompany.json"))
                .when()
                .post(COMPANY.path());
        try {
            getAssert(response.jsonPath().getString("type"), "error");
            assertThat(response.jsonPath().getString("type")).isNotNull();
        } catch (NullPointerException e) {
            e.printStackTrace(); //("Пользователь с таким email уже существует");
        }
    }

    @Step("Проверить результат выполнения сценария") //AbstractStringAssert<?>
    public void getAssert (String response, String equal){
        assertThat(response).isEqualTo(equal);
    }
}