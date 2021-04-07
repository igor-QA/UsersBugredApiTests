package base.steps;

import io.qameta.allure.Step;
import models.User;
import spec.ResponseError;
import spec.ResponseSuccess;
import tests.BaseTest;

import static spec.Request.spec;

public class UserBaseSteps extends BaseTest {
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
                .post(userEndPoint)
        .then()
                .spec(ResponseSuccess.spec());
    }
    @Step("Отправить POST запрос и проверить результат")
    public void sendAndCheckErrorPostRequest(String str) {
         spec()
                .body(user)
         .when()
                .post(userEndPoint)
         .then()
                .spec(ResponseError.spec());
    }
}