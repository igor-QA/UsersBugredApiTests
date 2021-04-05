package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String email;
    private String name;
    private int[] tasks = {12};
    private int[] companies = {36, 37};
    private String inn;

    public User() {
        //пустой конструктор для Jackson
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(String email, String name, String inn) {
        this.email = email;
        this.name = name;
        this.inn = inn;
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

    public int[] getTasks() {
        return tasks;
    }

    public void setTasks(int[] tasks) {
        this.tasks = tasks;
    }

    public int[] getCompanies() {
        return companies;
    }

    public void setCompanies(int[] companies) {
        this.companies = companies;
    }
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"email\": \"" + email + "\",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"tasks\": " + Arrays.toString(tasks) + ",\n" +
                "\t\"companies\": " + Arrays.toString(companies) + "\n" +
                '}';
    }

}