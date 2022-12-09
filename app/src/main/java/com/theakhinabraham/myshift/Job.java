package com.theakhinabraham.myshift;

public class Job {

    String role, description, locality, requirements, salary;

    public Job() {
    }

    public Job(String role, String description, String locality, String requirements, String salary) {
        this.role = role;
        this.description = description;
        this.locality = locality;
        this.requirements = requirements;
        this.salary = salary;
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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}