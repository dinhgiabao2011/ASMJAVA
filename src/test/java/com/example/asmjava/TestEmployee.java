package com.example.asmjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEmployee {
    List<Manager> managers = new ArrayList<>();
    List<Officer> officers = new ArrayList<>();
    Manager manager1 = new Manager(1, "Gia Bao", 30,6,5000f);
    Officer officer1 = new Officer(2, "Cong Dung", 30, 5, 500f);
    @Test
    public void testIsEmpty(){
        Assertions.assertTrue(managers.isEmpty());
        Assertions.assertTrue(officers.isEmpty());
        managers.add(manager1);
        officers.add(officer1);
        Assertions.assertFalse(managers.isEmpty());
        Assertions.assertFalse(officers.isEmpty());
        managers.remove(manager1);
        officers.remove(officer1);
        Assertions.assertTrue(managers.isEmpty());
        Assertions.assertTrue(officers.isEmpty());
    }

    @Test
    public void testMonth()
    {
        Manager manager = new Manager();
        manager.setMonth(5);
        int monthToCheck = manager.setToMonth(manager);
        Assertions.assertEquals(5, monthToCheck);
    }
    @Test
    public void testManagerSalary()
    {
        Manager manager = new Manager();
        manager.setManagerSalary(5000f);
        float salaryToCheck = manager.setToSalary(manager);
        Assertions.assertEquals(5000, salaryToCheck);
    }
}
