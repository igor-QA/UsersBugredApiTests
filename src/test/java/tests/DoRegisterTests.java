package tests;

import models.Register;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static utils.FileUtils.readFromFile;

public class DoRegisterTests extends BaseTest {

    String email;
    String name;
    String password;

    //Register register;

    @BeforeMethod(description = "Генирируем рандомные данные")
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
                .post("/tasks/rest/doregister")
                .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test(description = "Регистрация пользователя, который уже существует ")
    public void userAlreadyExistTest() {
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/userRegister.json"))
                .post("/tasks/rest/doregister")
                .then()
                .statusCode(400) //TODO возвращает статус код 200, баг;
                .body("type", is("error"))
                .body("message", is(" email vsk@gmail.ru уже есть в базе"));
    }

    @Test(description = "Регистрация с перестановкой обзятельных полей")
    public void incorrectMailTest() {
        Register register = new Register(password, email, name);
        given()
                .spec(Request.spec())
                .body(register)
                .when()
                .post("/tasks/rest/doregister")
                .then()
                .statusCode(200)
                .body("type", is("error"));
    }

}






/*
    //@Step("Getting RegisterResult")
    public EmptyModel getResult(Response response) throws JsonProcessingException {
        EmptyModel result;
        //addAttachMessage(response);
        if (response.body().jsonPath().get("type") == null) {
            result = mapper.readValue(response.asString(), Register.class);
        } else {
            result = getError(response);
        }
        return result;
    }
 */



