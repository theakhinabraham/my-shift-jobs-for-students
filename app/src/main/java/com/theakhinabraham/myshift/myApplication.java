package com.theakhinabraham.myshift;

public class myApplication {

    String role, time, address, salary, status;
    int jobID;

    public myApplication(String role, String time, String address, String salary, String status, int jobID) {
        this.role = role;
        this.time = time;
        this.address = address;
        this.salary = salary;
        this.status = status;
        this.jobID = jobID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
}
