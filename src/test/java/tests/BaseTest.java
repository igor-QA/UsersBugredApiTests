package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    ObjectMapper mapper = new ObjectMapper();
    Faker faker = new Faker(); //(new Locale("ru")

//    CreateCompanyBaseSteps createCompanyBaseSteps = new CreateCompanyBaseSteps();
//    CreateUserBaseSteps createUserBaseSteps = new CreateUserBaseSteps();
//    DoRegisterBaseSteps doRegisterBaseSteps = new DoRegisterBaseSteps();

    @BeforeClass
    public void setUp(){
    }
}
