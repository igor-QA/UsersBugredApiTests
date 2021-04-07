package tests;

import base.steps.RegistrationBaseSteps;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import spec.ResponseError;

import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class RegistrationTests extends BaseTest {
    RegistrationBaseSteps registrationBaseSteps = new RegistrationBaseSteps();

    @Test(description = "Регистрация нового аккаунта")
    @Story("Пользователь должен успешно зарегистрировать новый аккаунт")
    public void registerNewAccount() {
        registrationBaseSteps.successRegisterNewAccount();
        registrationBaseSteps.sendAndCheckSuccessPostRequest("success");
    }

    @Test(description = "Регистрация с некорректным: {email}")
    @Story("Пользователь не должен зарегистрировать аккаунт с невалидным Email")
    public void incorrectEmailTest() {
        registrationBaseSteps.createPostRequestWithIncorrectEmail();
        registrationBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Регистрация с отсутвием обязательного поля: {name}")
    @Story("Пользователь не должен зарегистрировать аккаунт без указания Имени")
    public void emptyNameTest() {
        registrationBaseSteps.createPostRequestWithIncorrectName();
        registrationBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Регистрация со спецсимволом в имени")
    @Story("Пользователь не должен зарегистрировать аккаунт с использованием спецсимволов в Имени")
    public void registerWithSymbolInNameTest(){
        registrationBaseSteps.createPostRequestWithSymbolInName();
        registrationBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Регистрация пользователя, который уже существует в системе")
    @Story("Пользователь не должен зарегистрировать аккаунт, который уже существует в системе")
    public void registerUserAlreadyExist() {
        spec()
                .body(readFromFile("src/test/resources/userRegister.json"))
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseError.spec());
                //.body("message", equalTo("email vsk@gmail.ru уже есть в базе")); //TODO
    }
}