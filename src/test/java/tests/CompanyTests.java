package tests;

import base.steps.CompanyBaseSteps;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class CompanyTests extends BaseTest {
    private final CompanyBaseSteps companyBaseSteps = new CompanyBaseSteps();

    @Test(description="Создание компании")
    @Story("Пользователь должен успешно создать компанию в системе")
    public void createNewCompany() {
        companyBaseSteps.createNewPostRequest();
        companyBaseSteps.sendAndCheckSuccessPostRequest("success");
    }

    @Test(description = "Создание компании с отсутвием обязательного поля: {email_owner}")
    @Story("Пользователь не должен зарегистрировать компанию в системе без Email")
    public void emptyEmailOwnerTest(){
       companyBaseSteps.createPostRequestWithEmptyEmail();
       companyBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Создание компании с некорректным: {email}")
    @Story("Пользователь не должен зарегистрировать компанию в системе с невалидным Email")
    public void incorrectEmailTest(){
        companyBaseSteps.createPostRequestWithIncorrectEmail();
        companyBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Создание компании с некорректным типом")
    @Story("Пользователь не должен зарегистрировать компанию в системе c некорректным типом")
    public void incorrectTypeCompanyTest(){
        companyBaseSteps.createPostRequestWithIncorrectType("LLC");
        companyBaseSteps.sendAndCheckErrorPostRequest("error");
    }

    @Test(description = "Создание компании, которая уже существует в системе")
    @Story("Пользователь не должен зарегистрировать компанию в системе, которая уже есть в системе ")
    public void createCompanyAlreadyExistTest(){
        companyBaseSteps.createAlreadyExistCompany();
        companyBaseSteps.getAssert("error","error");
    }
}