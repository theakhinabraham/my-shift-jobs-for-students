package com.theakhinabraham.myshift;

public class Job {

    String role, description, salary, address, requirements, time;
    Boolean isAvailable;
    int jobID;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public Job(int jobID) {
        this.jobID = jobID;
    }

    public Job() {
    }

    public Job(String role, String description, String salary, String address, String requirements, String time, Boolean isAvailable) {
        this.role = role;
        this.description = description;
        this.salary = salary;
        this.address = address;
        this.requirements = requirements;
        this.time = time;
        this.isAvailable = isAvailable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
