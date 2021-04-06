package tests;

import base.steps.CompanyBaseSteps;
import io.qameta.allure.Story;
import models.Company;
import org.testng.annotations.Test;
import spec.ResponseError;

import static spec.Request.spec;
import static utils.FileUtils.readFromFile;

public class CompanyTests extends BaseTest {
    Company company;
    CompanyBaseSteps companyBaseSteps = new CompanyBaseSteps();

    @Test(description="Создание компании")
    @Story("Пользователь должен успешно создать компанию в системе")
    public void createNewCompany() {
        companyBaseSteps.createNewPostRequest();
        companyBaseSteps.sendAndCheckPostRequest("success");
    }

    @Test(description = "Создание компании, которая уже существует в системе")
    @Story("Пользователь не должен зарегистрировать компанию в системе, которая уже есть в системе ")
    public void createCompanyAlreadyExistTest(){
        spec()
                .body(readFromFile("src/test/resources/createCompany.json"))
        .when()
                .post(companyEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Test(description = "Создание компании с отсутвием обязательного поля: {email_owner}")
    @Story("Пользователь не должен зарегистрировать компанию в системе без Email")
    public void emptyEmailOwnerTest(){
        company = new Company(companyName, companyType, companyUsers ,"");
         spec()
                .body(company)
        .when()
                .post(companyEndPoint)
        .then()
                .spec(ResponseError.spec());
    }

    @Test(description = "Создание компании с некорректным: {email}")
    @Story("Пользователь не должен зарегистрировать компанию в системе с невалидным Email")
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
    @Story("Пользователь не должен зарегистрировать компанию в системе c некорректным типом")
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