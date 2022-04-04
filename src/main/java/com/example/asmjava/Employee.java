package com.example.asmjava;

public class Employee {
    protected int Id;
    protected String name;
    protected int age;
    protected int month;


    public Employee(int Id, String name, int age, int month) throws NumberFormatException
    {
        this.Id = Id;
        this.name = name;
        this.age = age;
        this.month = month;
        if(month < 1 || month > 12)
        {
            throw new NumberFormatException();
        }
    }
    public Employee()
    {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {

        this.month = month;
    }

    @Override
    public String toString() {
        return Id  + ", " + name + ", " + age + ", " + month;
    }
}
