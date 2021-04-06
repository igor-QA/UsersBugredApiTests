package base.steps;

import io.qameta.allure.Step;
import models.Register;
import spec.ResponseError;
import spec.ResponseSuccess;
import tests.BaseTest;

import static spec.Request.spec;

public class RegistrationBaseSteps extends BaseTest {
    Register register;

    @Step("Проверить регистрация нового аккаунта")
    public void successRegisterNewAccount (){
        register = new Register(email, name, password);
    }

    @Step("Проверить Регистрацию нового аккаунта c некорректным Email")
    public void createPostRequestWithIncorrectEmail(){
        register = new Register(password, email, name);
    }

    @Step("Проверить регистрацию с отсутвием обязательного поля Имя")
    public void createPostRequestWithIncorrectName(){
        register = new Register(email, "", password);
    }

    @Step("Проверить регистрацию со спецсимволом в имени")
    public void createPostRequestWithSymbolInName(){
        String name = "@";
        register = new Register(email, name, password );
        //.body("name", is("@"));
    }

    @Step("Отправить POST запроос и проверить результат")
    public void sendAndCheckPostErrorRequest(String str){
        spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Step("Отправить POST запроос и проверить результат")
    public void sendAndCheckSuccessPostRequest(String str) {
        spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseSuccess.spec());
    }
}
