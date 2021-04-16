package generator;

import models.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static generator.TestData.*;

public class UserGenerator {

    public static User defaultUser() {
        return user(UUID.randomUUID().toString(),
                "test-01@gmail.com", "123456789012");
    }

    public static User newUser() {
        return user(username("en_US"),
                email("en_US"), INN());
    }

    public static User userWithoutEmail() {
        return user(username("en_US"), "", INN());
    }

   public static User newUserWithLongINN() {
        return user(username("en_US"),
                email("ru_Ru"),INN()+"1234567");

    }
    public static User userWithEmptyEmail() {
        return user(username("en_US"),
                "",INN());
    }

    public static User userWithIncorrectINN(String symbol) {
        return user(username("en_US"),
                email("en_US"), INN() + symbol);
    }

    private static User user(String username, String email, String inn) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setInn(inn);
        user.setCompanies(Arrays.asList(36, 37));
        user.setTasks(Collections.singletonList(12));
        return user;
    }
}