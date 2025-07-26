package com.example.emailsystem.model;

public class Employee {
    private String name;
    private String email;
    private String department;
    private String doj;
    private String newDesignation;
    private String newSalary;
    private String effectiveDate;
    private String letterType; //

    public Employee() {}

    public Employee(String name, String email, String department, String doj,
                    String newDesignation, String newSalary, String effectiveDate, String letterType) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.doj = doj;
        this.newDesignation = newDesignation;
        this.newSalary = newSalary;
        this.effectiveDate = effectiveDate;
        this.letterType = letterType;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getDoj() { return doj; }
    public String getNewDesignation() { return newDesignation; }
    public String getNewSalary() { return newSalary; }
    public String getEffectiveDate() { return effectiveDate; }
    public String getLetterType() { return letterType; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setDoj(String doj) { this.doj = doj; }
    public void setNewDesignation(String newDesignation) { this.newDesignation = newDesignation; }
    public void setNewSalary(String newSalary) { this.newSalary = newSalary; }
    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }
    public void setLetterType(String letterType) { this.letterType = letterType; }
}
