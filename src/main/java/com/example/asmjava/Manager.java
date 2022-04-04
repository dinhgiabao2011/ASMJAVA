package com.example.asmjava;

public class Manager extends Employee{
    protected Float managerSalary;

    public Manager(int Id, String name, int age, int month, Float managerSalary) {
        super(Id, name, age, month);
        this.managerSalary = managerSalary;
    }

    public Manager() {
    }

    public Float getManagerSalary() {
        return managerSalary;
    }

    public void setManagerSalary(Float managerSalary) {
        this.managerSalary = managerSalary;
    }

    @Override
    public String toString() {
        return Id  + ", " + name + ", " + age + ", " + month + ", " + managerSalary;
    }

    public int setToMonth (Manager manager)
    {
        int month = 1;
        if(month < 1 || month > 12)
        {
            System.out.println("Error");
        }
        month = manager.getMonth();
        return month;
    }

    public float setToSalary (Manager manager)
    {
        float salary = 1000;
        if(salary < 1000)
        {
            System.out.println("Error");
        }
        salary = manager.getManagerSalary();
        return salary;
    }
}
