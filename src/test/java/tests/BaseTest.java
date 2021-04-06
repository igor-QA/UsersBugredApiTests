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
    public final String companyEndPoint = "/createcompany";
    public final String userEndPoint = "/createuser";
    public final String registerEndPoint = "/doregister";
    /**JavaFaker*/
    Faker faker = new Faker(); //(new Locale("ru")
    public final String companyName = faker.company().name();
    public final String companyType = "ОАО";
    public final String emailOwner = faker.internet().emailAddress();
    public final List<String> companyUsers = Arrays.asList("test@test.com", "vsk.test@vsk.ru");
    public final String email = faker.internet().emailAddress();
    public final String name = faker.name().username();
    public final String password = faker.internet().password();
    public final String inn = faker.number().digits(12);

    @BeforeClass
    public static void setUp(){
        RegistrationBaseSteps registrationBaseSteps = new RegistrationBaseSteps();
        UserBaseSteps userBaseSeps = new UserBaseSteps();
        CompanyBaseSteps companyBaseSteps = new CompanyBaseSteps();
    }
}