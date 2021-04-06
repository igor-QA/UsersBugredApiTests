package tests;

import models.Register;
import org.testng.annotations.Test;
import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static utils.FileUtils.readFromFile;

public class RegistrationTests extends BaseTest {

    @Test(description = "Регистрация нового аккаунта")
    public void registerNewAccount() {

        Register register = new Register(email, name, password);
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test(description = "Регистрация пользователя, который уже существует в системе")
    public void registerUserAlreadyExist() {
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/userRegister.json"))
        .when()
                .post(registerEndPoint)
        .then()
                .statusCode(200) //TODO
                .body("type", is("error"));
                //.body("message", equalTo("email vsk@gmail.ru уже есть в базе")); TODO
    }

    @Test(description = "Регистрация с некорректным: {email}")
    public void incorrectEmailTest() {
        Register register = new Register(password, email, name);
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .statusCode(200)
                .body("type", is ("error"));
    }

    @Test(description = "Регистрация с отсутвием обязательного поля: {name}")
    public void emptyNameTest() {
        Register register = new Register(email, name, "");
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .statusCode(200)
                .body("type", is ("error"));
    }

    @Test(description = "Регистрация со спецсимволом в имени")
    public void registerWithSymbolInNameTest(){
        String name = "@";
        Register register = new Register(email, name, password );
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post(registerEndPoint)
        .then()
                .statusCode(200)
                .body("name", is("@"));
    }
}