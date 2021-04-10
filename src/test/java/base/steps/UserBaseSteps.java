package base.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;
import spec.ResponseError;
import spec.ResponseSuccess;
import generate.DataGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static spec.Endpoints.USER;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class UserBaseSteps extends DataGenerator {
    User user;

    @Step("Создать новый аккаунт")
    public void successRegisterNewUser() {
        user = new User(email, name);
    }

    @Step("Создать аккаунта без Email")
    public void createUserWithEmptyEmail() {
        user = new User("", name);
    }

    @Step("Создать аккаунт с валидным ИНН")
    public void createNewUserWithINN() {
        user = new User(email, name, inn);
    }

    @Step("Создать аккаунт с невалидным ИНН(13 цифр)")
    public void createNewUserWithLongINN() {
        user = new User(email, name, inn + "6");
    }

    @Step("Создать аккаунт с буквами и символами в ИНН")
    public void createNewUserWithLetterInINN() {
        String inn = "13131313131F";
        user = new User(email, name, inn);
    }
    @Step("Отправить POST запроос и проверить успешность выполнения сценария")
    public void sendAndCheckSuccessPostRequest(String str) {
        spec()
                .body(user)
        .when()
                .post(USER.path())
        .then()
                .spec(ResponseSuccess.spec());
    }
    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckErrorPostRequest(String str) {
         spec()
                .body(user)
         .when()
                .post(USER.path())
         .then()
                .spec(ResponseError.spec());
    }

    @Step("Создать пользователя с данными, которые уже есть в системе")
    public void createAlreadyExistUser(){
        Response response = spec()
                .body(readFromFile("src/test/resources/createUser.json"))
                .when()
                .post(USER.path());
        try {
            getAssert(response.jsonPath().getString("type"), "error");
            //assertThat(response.jsonPath().getString("type")).isNotNull();
        } catch (NullPointerException e) {
            e.printStackTrace(); //("Пользователь с таким email уже существует");
        }
    }

    @Step("Проверить результат выполнения сценария")
    public void getAssert (String response, String equal){
        assertThat(response).isEqualTo(equal);
    }
}