package tests;

import io.qameta.allure.Story;
import models.Register;
import org.testng.annotations.Test;
import spec.ResponseError;
import spec.ResponseSuccess;

import static org.hamcrest.Matchers.*;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class RegistrationTests extends BaseTest {
    Register register;

    @Test(description = "Регистрация нового аккаунта")
    @Story("Пользователь должен успешно зарегистрировать новый аккаунт")
    public void registerNewAccount() {
         register = new Register(email, name, password);

         spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseSuccess.spec());
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

    @Test(description = "Регистрация с некорректным: {email}")
    @Story("Пользователь не должен зарегистрировать аккаунт с невалидным Email")
    public void incorrectEmailTest() {
        register = new Register(password, email, name);
        spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Test(description = "Регистрация с отсутвием обязательного поля: {name}")
    @Story("Пользователь не должен зарегистрировать аккаунт без указания Имени")
    public void emptyNameTest() {
        register = new Register(email, "", password);

        spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Test(description = "Регистрация со спецсимволом в имени")
    @Story("Пользователь не должен зарегистрировать аккаунт с использованием спецсимволов в Имени")
    public void registerWithSymbolInNameTest(){
        String name = "@";
        Register register = new Register(email, name, password );

        spec()
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .spec(ResponseSuccess.spec())
                .body("name", is("@"));
    }
}