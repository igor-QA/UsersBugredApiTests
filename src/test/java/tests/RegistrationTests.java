package tests;

import base.steps.RegistrationBaseSteps;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import generator.DataGenerator;

public class RegistrationTests extends DataGenerator {
    private final RegistrationBaseSteps registrationBaseSteps = new RegistrationBaseSteps();

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
        registrationBaseSteps.registerAlreadyExistUser();
        registrationBaseSteps.getAssert("error","error");
        //.body("message", equalTo("email vsk@gmail.ru уже есть в базе")); //TODO
    }
}