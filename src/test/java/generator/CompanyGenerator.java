package generator;

import models.Company;

import java.util.List;

import static generator.TestData.*;

public class CompanyGenerator {

    public static Company defaultCompany(){
        return company(companyName("EN_uk"),companyType("EN_uk"),"", companyUsers());
    }

    private static Company company (String companyName, String companyType, String emailOwner, List<String> companyUsers ) {
    Company company = new Company();
    company.setCompanyName(companyName);
    company.setCompanyType(companyType);
    company.setCompanyUsers(companyUsers);
    company.setEmailOwner(emailOwner);
            return company;
    }
}