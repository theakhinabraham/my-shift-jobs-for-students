package com.theakhinabraham.myshift;

public class CompanyData {
    String companyName, username, password, locality;

    public CompanyData(String companyName, String username, String password, String locality) {
        this.companyName = companyName;
        this.username = username;
        this.password = password;
        this.locality = locality;
    }

    public CompanyData(String companyName) {

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
