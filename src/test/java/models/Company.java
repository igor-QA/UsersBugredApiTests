package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Company {
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("company_type")
    private String companyType;
    @JsonProperty("company_users")
    private List<String> companyUsers;
    @JsonProperty("email_owner")
    private String emailOwner;

    public Company() { super(); }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public List<String> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(List<String> companyUsers) {
        this.companyUsers = companyUsers;
    }

    public String getEmailOwner() {
        return emailOwner;
    }

    public void setEmailOwner(String emailOwner) {
        this.emailOwner = emailOwner;
    }

    public Company(String companyName, String companyType, List<String> companyUsers, String emailOwner) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyUsers = companyUsers;
        this.emailOwner = emailOwner;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"company_name\": \"" + companyName + "\",\n" +
                "\t\"company_type\": \"" + companyType + "\",\n" +
                "\t\"company_users\": " + companyUsers.toString() + "\n" +
                "\t\"email_owner\": \"" + emailOwner + "\"\n" +
                '}';
    }
}