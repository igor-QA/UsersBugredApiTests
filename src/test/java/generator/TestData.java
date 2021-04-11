package generator;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TestData {
    /**
     * Генерация e-mail
     * @param locale ru; en_US
     * @return e-mail
     */
    public static String email(String locale) {
        return email(locale, "gmail.com");
    }

    public static String email(String locale, String domain) {
        return faker(locale).internet().emailAddress(domain);
    }

    public static String username(String locale) {
        return faker(locale).name().username();
    }

    public static String password() {
        return faker("en_US").internet().password(9, 15);
    }

    public static String INN() {
        return faker("ru").number().digits(12);

    }

    /** Вынести в отедельный класс Company;
     * Добавить класс Registration;
     * */

    public static List<String> companyUsers() {
        List<String> users = new ArrayList<>();
        Collections.addAll(users, "test@test.com", "vsk.test@vsk.ru");
        return users;
    }

    public static String companyType(String locale) {
        return faker(locale).company().suffix();
    }

    public static String companyName(String locale) {
        return faker(locale).company().name();
    }

    private static Faker faker(String locale) {
        return new Faker(new Locale(locale));
    }
}