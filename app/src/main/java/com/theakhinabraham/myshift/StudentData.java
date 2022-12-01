package com.theakhinabraham.myshift;

public class StudentData {
    String fullName, username, password, age, locality, education;
    Boolean isStudent = false;

    public StudentData(String fullName, String username, String password, String age, String locality, String education, Boolean isStudent) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.age = age;
        this.locality = locality;
        this.education = education;
        this.isStudent = isStudent;
    }

    public StudentData(String fullName) {

    }

    public Boolean getStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}