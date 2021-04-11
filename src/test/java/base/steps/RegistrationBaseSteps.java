package base.steps;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Register;
import spec.ResponseError;
import spec.ResponseSuccess;
import generator.DataGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static spec.Endpoints.REGISTER;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class RegistrationBaseSteps extends DataGenerator {
    Register register;

    @Step("Зарегистрировать новый аккаунта")
    public void successRegisterNewAccount (){
        register = new Register(email, name, password);
    }

    @Step("Зарегистрировать новый аккаунта c некорректным Email")
    public void createPostRequestWithIncorrectEmail(){
        register = new Register(password, email, name);
    }

    @Step("Зарегистрировать аккаунт с отсутвием обязательного поля Имя")
    public void createPostRequestWithIncorrectName(){
        register = new Register(email, "", password);
    }

    @Step("Зарегистрировать аккаунт со спецсимволом в имени")
    public void createPostRequestWithSymbolInName(){
        String name = "@";
        register = new Register(email, name, password );
        //.body("name", is("@"));
    }

    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckErrorPostRequest(String str){
        spec()
                .body(register)
        .when()
                .post(REGISTER.path())
        .then()
                .spec(ResponseError.spec());
    }

    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckSuccessPostRequest(String str) {
        spec()
                .body(register)
        .when()
                .post(REGISTER.path())
        .then()
                .spec(ResponseSuccess.spec());
    }

    @Step("Зарегистрировать аккаунт с данными, которые уже есть в системе")
    public void registerAlreadyExistUser(){
        Response response = spec()
                .body(readFromFile("src/test/resources/userRegister.json"))
                .when()
                .post(REGISTER.path());
        try {
            getAssert(response.jsonPath().getString("type"), "error");
            //assertThat(response.jsonPath().getString("type")).isNotNull();
        } catch (NullPointerException e) {
            e.printStackTrace(); //("Пользователь с таким email уже существует");
        }
    }

    @Step("Проверить результат выполнения сценария")
    public void getAssert (String response, String equal) {
        assertThat(response).isEqualTo(equal);
    }
}