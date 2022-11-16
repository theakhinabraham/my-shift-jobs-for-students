package com.theakhinabraham.myshift;

public class Job {

    String roles, description, locality, requirements;
    String salary;

    public Job(){}

    public Job(String roles, String description, String salary, String locality, String requirements) {
        this.roles = roles;
        this.description = description;
        this.salary = salary;
        this.locality = locality;
        this.requirements = requirements;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
}
