package tests;

import models.Company;
import org.testng.annotations.Test;
import spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static utils.FileUtils.readFromFile;

public class CompanyTests extends BaseTest {

    @Test(description="Создание компании")
    public void createNewCompany() {
        Company company = new Company(companyName, companyType, companyUsers ,emailOwner);
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body( "type", is("success"));
    }

    @Test(description = "Создание компании, которая уже существует в системе")
    public void createCompanyAlreadyExistTest(){
        given()
                .spec(Request.spec())
                .body(readFromFile("src/test/resources/createCompany.json"))
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }

    @Test(description = "Создание компании с отсутвием обязательного поля: {email_owner}")
    public void emptyEmailOwnerTest(){
        Company company = new Company(companyName, companyType, companyUsers ,"");
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));

    }

    @Test(description = "Создание компании с некорректным: {email}")
    public void incorrectEmailTest(){
        Company company = new Company(emailOwner, companyName, companyUsers, companyType);
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));

    }

    @Test(description = "Создание компании с некорректным типом")
    public void incorrectTypeCompanyTest(){
        String companyType = "LLC";
        Company company = new Company(companyName, companyType, companyUsers ,emailOwner);
        given()
                .spec(Request.spec())
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }
}