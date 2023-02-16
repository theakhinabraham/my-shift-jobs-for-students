package com.theakhinabraham.myshift;

public class Applied {

    String fullName, username, age, message, locality, education;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Applied(String fullName, String username, String age, String message, String locality, String education) {
        this.fullName = fullName;
        this.username = username;
        this.age = age;
        this.message = message;
        this.locality = locality;
        this.education = education;


    }
}