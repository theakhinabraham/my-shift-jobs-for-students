package com.theakhinabraham.myshift;

public class CompanyData {
    String companyName, username, password, age, locality;
    boolean isCompany;

    public CompanyData(String companyName, String username, String password, String age, String locality, boolean isCompany) {
        this.companyName = companyName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.locality = locality;
        this.isCompany = isCompany;
    }

    public CompanyData(String companyName) {

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
