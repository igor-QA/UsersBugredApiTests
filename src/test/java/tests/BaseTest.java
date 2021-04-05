package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    ObjectMapper mapper = new ObjectMapper();
    Faker faker = new Faker(); //(new Locale("ru")
    //Экземпляры классов для степов будут здесь

    @BeforeClass
    public void setUp(){
    }
}
