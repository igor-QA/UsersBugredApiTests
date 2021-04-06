package tests;

import models.User;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class UserTests extends BaseTest {

    @Test(description = "Создание нового пользователя")
    public void createNewUser() {
        User user = new User(email, name);
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test(description = "Создание пользователя с уже существующими данными")
    public void createUserAlreadyExistTest() {
        spec()
                .body(readFromFile("src/test/resources/createUser.json"))
        .when()
                .post(userEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"))
                .body("message", equalTo("Пользователь с таким email уже существует"));
    }

    @Test(description = "Создание пользователя с отсутвием обязательного поля: {email}")
    public void emptyEmailTest() {
        User user = new User("", name);
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }

    @Test(description = "Создание пользователя с уникальным ИНН(12 цифр)")
    public void createUserWithINN(){
        User user = new User(email, name, inn);
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("success"));
               // .body("inn", notNullValue());
    }

    @Test(description = "Создание пользователя с ИНН(13 цифр), превышающий количество допустимых цифр")
    public void createUserWithLongINN(){
        User user = new User(email, name, inn + "6");
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }

    @Test(description = "Создание пользователя с буквами в ИНН")
    public void createUserWithLetterInINN(){
        String inn = "13131313131F";
        User user = new User(email, name, inn );
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }
}