package com.theakhinabraham.myshift;

public class Applied {

    String fullName, username, age, role, locality;

    public Applied() {
    }

    public Applied(String fullName, String username, String age, String role, String locality) {
        this.fullName = fullName;
        this.username = username;
        this.age = age;
        this.role = role;
        this.locality = locality;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}