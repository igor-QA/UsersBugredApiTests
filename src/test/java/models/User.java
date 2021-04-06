package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String email;
    private String name;
    private List<Integer> tasks = Collections.singletonList(12);
    private List<Integer> companies = Arrays.asList(36, 37);
    private String inn;

    public User() {
        super();
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

    public List<Integer> getTasks() {
        return tasks;
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }

    public List<Integer> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Integer> companies) {
        this.companies = companies;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
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

    @Override
    public String toString() {
        return "{\n" +
                "\t\"email\": \"" + email + "\",\n" +
                "\t\"name\": \"" + name + "\",\n" +
                "\t\"tasks\": " + tasks + ",\n" +
                "\t\"companies\": " + companies + "\n" +
                '}';
    }
}