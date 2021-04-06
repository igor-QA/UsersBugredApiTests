package tests;

import base.steps.CompanyBaseSteps;
import base.steps.RegistrationBaseSteps;
import base.steps.UserBaseSteps;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.List;

public class BaseTest {
    /**EndPoint*/
    final String companyEndPoint = "/createcompany";
    final String userEndPoint = "/createuser";
    final String registerEndPoint = "/doregister";
    /**JavaFaker*/
    Faker faker = new Faker(); //(new Locale("ru")
    final String companyName = faker.company().name();
    final String companyType = "ОАО";
    final String emailOwner = faker.internet().emailAddress();
    final List<String> companyUsers = Arrays.asList("test@test.com", "vsk.test@vsk.ru");
    final String email = faker.internet().emailAddress();
    final String name = faker.name().username();
    final String password = faker.internet().password();
    final String inn = faker.number().digits(12);

    @BeforeClass
    public static void setUp(){
        CompanyBaseSteps companyBaseSteps = new CompanyBaseSteps();
        RegistrationBaseSteps registrationBaseSteps = new RegistrationBaseSteps();
        UserBaseSteps userBaseSeps = new UserBaseSteps();
    }
}