package tests;

import models.Company;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class CompanyTests extends BaseTest {

    @Test(description="Создание компании")
    public void createNewCompany() {
        Company company = new Company(companyName, companyType, companyUsers ,emailOwner);

        spec()
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body( "type", is("success"));
    }

    @Test(description = "Создание компании, которая уже существует в системе")
    public void createCompanyAlreadyExistTest(){
        spec()
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

        spec()
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

        spec()
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

        spec()
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .statusCode(200)
                .body("type", is("error"));
    }
}