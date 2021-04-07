package base.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Company;
import org.assertj.core.api.AbstractStringAssert;
import spec.ResponseError;
import spec.ResponseSuccess;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

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
    @Step("Создать запрос на создание компании с данными, которые уже есть в системе")
    public void createAlreadyExistCompany(){
        Response response = spec()
                .body(readFromFile("src/test/resources/createCompany.json"))
                .when()
                .post(companyEndPoint);
        try {
            getAssert(response.jsonPath().getString("type"), "error");
            assertThat(response.jsonPath().getString("type")).isNotNull();
        } catch (NullPointerException e) {
            e.printStackTrace(); //("Пользователь с таким email уже существует");
        }
    }

    @Step("Проверить результат выполнения сценария")
    public AbstractStringAssert<?> getAssert (String response, String equal){
        return assertThat(response).isEqualTo(equal);
    }
}