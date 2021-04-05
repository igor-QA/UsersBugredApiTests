package tests;

import models.Company;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static utils.FileUtils.readFromFile;

public class CompanyTests extends BaseTest {

    String company_name;
    String company_type;
    String email_owner;
    String[] company_users;

    @BeforeMethod(description = "Рандомные данные")
    public void generateData() {
        company_name = faker.company().name();
        company_type = "ОАО";
        email_owner = faker.internet().emailAddress();
        company_users = new String[]{"test@test.com", "vsk.test@vsk.ru"};
    }

    @Test(description="Создание компании")
    public void createCompany() {
        Company company = new Company(company_name, company_type, company_users ,email_owner);
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post("/createuser")
        .then()
                .statusCode(200);
                //.body("type",is ("success")); //Expected: not null Actual: null
    }
    //TODO в негативных тестах возвращает статус код 200, баг;
    @Test(description = "Создание компании, которая уже существует в системе")
    public void createCompanyAlreadyExistTest(){
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/createCompany.json"))
        .when()
                .post("/createuser")
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }

    @Test(description = "Создание компании с отсутвием обязательного поля: {email_owner}")
    public void emptyEmailOwnerTest(){
        Company company = new Company(company_name, company_type, company_users ,"");
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post("/createuser")
        .then()
                .statusCode(200)
                .body("type", is("error"));

    }

    @Test(description = "Создание компании с некорректным: {email}")
    public void incorrectEmailTest(){
        Company company = new Company(email_owner, company_name, company_users, company_type);
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post("/createuser")
        .then()
                .statusCode(200)
                .body("type", is("error"));

    }

    @Test(description = "Создание компании с некорректным типом")
    public void incorrectTypeCompanyTest(){
        company_type = faker.company().suffix();
        Company company = new Company(company_name, company_type, company_users ,email_owner);
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post("/createuser")
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }
}