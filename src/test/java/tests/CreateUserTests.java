package tests;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static utils.FileUtils.readFromFile;

public class CreateUserTests extends BaseTest {

    String email;
    String name;
    String inn = "131313131313";

    @BeforeMethod(description = "Рандомные данные")
    public void generateData() {
        email = faker.internet().emailAddress();
        name = faker.name().username();
    }

    @Test(description = "Создание нового пользователя")
    public void createUser() {
        User user = new User(email, name);
        given()
                .spec(Request.spec())
                .body(user)
                .when()
                .post("/createuser")
                .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test(description = "Создание пользователя с уже существуюшими данными")
    public void createUserAlreadyExistTest() {
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/createUser.json"))
                .post("/createuser")
                .then()
                .statusCode(200)
                .body("type", is("error"))
                .body("message", is("Пользователь с таким email уже существует"));
    }

    @Test(description = "Создание пользователя с отсутвием обязательного поля: {email}")
    public void emptyEmailTest() {
        User user = new User("", name);
        given()
                .spec(Request.spec())
                .body(user)
                .when()
                .post("/createuser")
                .then()
                .statusCode(200)
                .body("type", is("error"));
    }

}
