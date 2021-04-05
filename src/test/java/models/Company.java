package models;

import java.util.Arrays;

public class Company {
    private String company_name;
    private String company_type;
    private String[] company_users = {"test@test.com", "vsk.test@vsk.ru"};
    private String email_owner;

    public Company() {
        //пустой конструктор для Jackson
    }

    public Company(String company_name, String company_type, String email_owner) {
        this.company_name = company_name;
        this.company_type = company_type;
        this.email_owner = email_owner;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"company_name\": \"" + company_name + "\",\n" +
                "\t\"company_type\": \"" + company_type + "\",\n" +
                "\t\"company_users\": " + Arrays.toString(company_users) + "\n" +
                "\t\"email_owner\": \"" + email_owner + "\"\n" +
                '}';
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String[] getCompany_users() {
        return company_users;
    }

    public void setCompany_users(String[] company_users) {
        this.company_users = company_users;
    }

    public String getEmail_owner() {
        return email_owner;
    }

    public void setEmail_owner(String email_owner) {
        this.email_owner = email_owner;
    }
}