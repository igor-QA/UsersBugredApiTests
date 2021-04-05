package tests;

import models.Register;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static utils.FileUtils.readFromFile;
import static org.hamcrest.Matchers.*;

public class DoRegisterTests extends BaseTest {

    String email;
    String name;
    String password;

    //Register register;
    @BeforeMethod(description = "Рандомные данные")
    public void generateData() {
        email = faker.internet().emailAddress();
        name = faker.name().firstName();
        password = faker.internet().password();
    }

    @Test(description = "Регистрация нового пользователя")
    public void createRegister() {
        Register register = new Register(email, name, password);
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post("/doregister")
        .then()
                .statusCode(200)
                .body("name", notNullValue());
    }
    //TODO в негативных тестах возвращает статус код 200, баг;
    @Test(description = "Регистрация пользователя, который уже существует в системе")
    public void registerUserAlreadyExistTest() {
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/userRegister.json"))
        .when()
                .post("/doregister")
        .then()
                .statusCode(400)
                .body("type", is("error"))
                .body("message", is(" email vsk@gmail.ru уже есть в базе"));
    }

    @Test(description = "Регистрация с некорректным: {email}")
    public void incorrectEmailTest() {
        Register register = new Register(password, email, name);
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post("/doregister")
        .then()
                .statusCode(400)
                .body("type", is ("error"));
    }

    @Test(description = "Регистрация с отсутвием обязательного поля: {name}")
    public void emptyNameTest() {
        Register register = new Register(email, name, "");
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post("/doregister")
        .then()
                .statusCode(400)
                .body("type", is ("error"));
    }

    @Test(description = "Регистрация со спецсимволом в имени")
    public void test(){
        String name = "@";
        Register register = new Register(email, name, password );
        given()
                .spec(Request.spec())
                .body(register)
        .when()
                .post("/doregister")
        .then()
                .statusCode(200)
                .body("name", is("@"));

    }
}

/*
    Здесь пытался пользоваться и разбиратьс в Jackson м обьеденить проверки
    public Register getResult(Response response) throws JsonProcessingException {
        Register result;
        if (response.body().jsonPath().get("type") == null) {
            result = mapper.readValue(response.asString(), Register.class);
        } else {
            result = getError(response);
        }
        return result;
        }
 */


