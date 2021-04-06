package tests;

import base.steps.CompanyBaseSteps;
import io.qameta.allure.Story;
import models.Company;
import org.testng.annotations.Test;
import spec.ResponseError;
import spec.ResponseSuccess;

import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class CompanyTests extends BaseTest {
    Company company;
    CompanyBaseSteps companyBaseSteps = new CompanyBaseSteps();

    @Test(description="Создание компании")
    @Story("Пользователь должен успешно создать компанию в системе")

    public void createNewCompany() {
        companyBaseSteps.createNewPostRequest();
        companyBaseSteps.sendAndCheckPostRequest();
    }

    @Test(description = "Создание компании, которая уже существует в системе")
    public void createCompanyAlreadyExistTest(){
        spec()
                .body(readFromFile("src/test/resources/createCompany.json"))
        .when()
                .post(companyEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Test(description = "Создание компании с отсутвием обязательного поля: {email_owner}")
    public void emptyEmailOwnerTest(){
        company = new Company(companyName, companyType, companyUsers ,"");
         spec()
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .spec(ResponseSuccess.spec());

    }

    @Test(description = "Создание компании с некорректным: {email}")
    public void incorrectEmailTest(){
        company = new Company(emailOwner, companyName, companyUsers, companyType);
        spec()
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .spec(ResponseError.spec());

    }

    @Test(description = "Создание компании с некорректным типом")
    public void incorrectTypeCompanyTest(){
        String companyType = "LLC";
        company = new Company(companyName, companyType, companyUsers ,emailOwner);

        spec()
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .spec(ResponseError.spec());
    }
}