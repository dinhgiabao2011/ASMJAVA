package com.example.asmjava;

public class Officer extends Employee {
    protected Float officerSalary;

    public Officer(int Id, String name, int age, int month, Float officerSalary) {
        super(Id, name, age, month);
        this.officerSalary = officerSalary;
    }

    public Officer() {
    }

    public Float getOfficerSalary() {
        return officerSalary;
    }

    public void setOfficerSalary(Float officerSalary) {
        this.officerSalary = officerSalary;
    }

    @Override
    public String toString() {
        return Id  + ", " + name + ", " + age + ", " + month + ", " + officerSalary;
    }
}
