package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    ObjectMapper mapper = new ObjectMapper();
    Faker faker = new Faker(); //(new Locale("ru")


//    public final String companyName = faker.company().name();
//    public String email = faker.bothify("????##@gmail.com");
//    public final String phoneNumber = faker.phoneNumber().phoneNumber();

    @BeforeClass
    public void setUp(){

    }
}
