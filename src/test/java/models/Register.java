package models;

public class Register {
    private String email;
    private String name;
    private String password;

    public Register() {
        //пустой конструктор для Jackson
    }

    public Register(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"email\": \"" + email + "\",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"password\": \"" + password + "\"\n" +
                '}';
    }
}