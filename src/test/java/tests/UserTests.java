package tests;

import io.qameta.allure.Story;
import models.User;
import org.testng.annotations.Test;
import spec.ResponseError;
import spec.ResponseSuccess;

import static org.hamcrest.Matchers.equalTo;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class UserTests extends BaseTest {
    User user;

    @Test(description = "Создание нового пользователя")
    @Story("Пользователь должен успешно создать новый аккаунт")
    public void createNewUser() {
        user = new User(email, name);
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .spec(ResponseSuccess.spec());
    }

    @Test(description = "Создание пользователя с уже существующими данными")
    @Story("Пользователь не должен успешно создать аккаунт, с данными которые уже есть в системе")
    public void createUserAlreadyExistTest() {
        spec()
                .body(readFromFile("src/test/resources/createUser.json"))
        .when()
                .post(userEndPoint)
        .then()
                .spec(ResponseError.spec())
                .body("message", equalTo("Пользователь с таким email уже существует"));
    }

    @Test(description = "Создание пользователя с отсутвием обязательного поля: {email}")
    @Story("Пользователь не должен успешно создать аккаунт без указаия Email")
    public void emptyEmailTest() {
        user = new User("", name);
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Test(description = "Создание пользователя с уникальным ИНН(12 цифр)")
    @Story("Пользователь должен успешно создать аккаунт c валидным ИНН")
    public void createUserWithINN(){
        user = new User(email, name, inn);
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .spec(ResponseSuccess.spec());
    }

    @Test(description = "Создание пользователя с ИНН(13 цифр), превышающий количество допустимых цифр")
    @Story("Пользователь не должен успешно создать аккаунт с невалидным ИНН")
    public void createUserWithLongINN(){
        user = new User(email, name, inn + "6");
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .spec(ResponseError.spec());

    }

    @Test(description = "Создание пользователя с буквами в ИНН")
    @Story("Пользователь не должен успешно создать аккаунт с буквами и символами в ИНН")
    public void createUserWithLetterInINN(){
        String inn = "13131313131F";
        User user = new User(email, name, inn );
        spec()
                .body(user)
        .when()
                .post(userEndPoint)
        .then()
                .spec(ResponseError.spec());
    }
}