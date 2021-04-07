package base.steps;

import io.qameta.allure.Step;
import models.Register;
import spec.ResponseError;
import spec.ResponseSuccess;
import tests.BaseTest;

import static spec.Request.spec;

public class RegistrationBaseSteps extends BaseTest {
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
                .post(registerEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckSuccessPostRequest(String str) {
        spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseSuccess.spec());
    }
}
