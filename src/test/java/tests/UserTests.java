package tests;

import base.steps.UserBaseSteps;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {
    UserBaseSteps userBaseSteps = new UserBaseSteps();

    @Test(description = "Создание нового пользователя")
    @Story("Пользователь должен успешно создать новый аккаунт")
    public void createNewUser() {
        userBaseSteps.successRegisterNewUser();
        userBaseSteps.sendAndCheckSuccessPostRequest("success");
    }

    @Test(description = "Создание пользователя с отсутвием обязательного поля: {email}")
    @Story("Пользователь не должен успешно создать аккаунт без указаия Email")
    public void emptyEmailTest() {
        userBaseSteps.createUserWithEmptyEmail();
        userBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Создание пользователя с уникальным ИНН(12 цифр)")
    @Story("Пользователь должен успешно создать аккаунт c валидным ИНН")
    public void createUserWithINN() {
        userBaseSteps.createNewUserWithINN();
        userBaseSteps.sendAndCheckSuccessPostRequest("success");
    }

    @Test(description = "Создание пользователя с ИНН(13 цифр), превышающий количество допустимых цифр")
    @Story("Пользователь не должен успешно создать аккаунт с невалидным ИНН")
    public void createUserWithLongINN() {
        userBaseSteps.createNewUserWithLongINN();
        userBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Создание пользователя с буквами и символами в ИНН")
    @Story("Пользователь не должен успешно создать аккаунт с буквами ИНН")
    public void createUserWithSymbolInINN() {
        userBaseSteps.createNewUserWithLetterInINN();
        userBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Создание пользователя с уже существующими данными")
    @Story("Пользователь не должен успешно создать аккаунт c данными, которые уже есть в системе")
    public void createUserAlreadyExistTest() {
        userBaseSteps.createAlreadyExistUser();
        userBaseSteps.getAssert("error","error");
    }
}