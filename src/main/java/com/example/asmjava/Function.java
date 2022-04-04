package com.example.asmjava;

import javafx.scene.control.Alert;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Function {


    public static void addManagerToFile(List<Manager> managers, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Manager o : managers) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Manager> readManagerListFromFile(File file) {
        List<Manager> managers = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                String eachManager[] = line.split(", ");
                int Id = Integer.parseInt(eachManager[0]);
                String name = eachManager[1];
                int age = Integer.parseInt(eachManager[2]);
                int month = Integer.parseInt(eachManager[3]);
                Float managerSalary = Float.parseFloat(eachManager[4]);
                managers.add(new Manager(Id, name, age, month, managerSalary));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return managers;
    }

    public static void SaveManagersToFile(List<Manager> managers, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Manager o : managers) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addOfficerToFile(List<Officer> officers, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Officer o : officers) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Officer> readOfficerListFromFile(File file) {
        List<Officer> officers = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                String eachOfficer[] = line.split(", ");
                int Id = Integer.parseInt(eachOfficer[0]);
                String name = eachOfficer[1];
                int age = Integer.parseInt(eachOfficer[2]);
                int month = Integer.parseInt(eachOfficer[3]);
                float officerSalary = Float.parseFloat(eachOfficer[4]);
                officers.add(new Officer(Id, name, age, month, officerSalary));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return officers;
    }

    public static void SaveOfficerToFile(List<Officer> officers, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Officer o : officers) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean IsManagerExisted(List<Manager> managers, Integer IdToCheck) {
        for (Manager o : managers) {
            if (IdToCheck == o.getId())
                return true;
        }
        return false;
    }

    public static boolean IsOfficerExisted(List<Officer> officers, Integer IdToCheck) {
        for (Officer o : officers) {
            if (IdToCheck == o.getId())
                return true;
        }
        return false;
    }

    public static void addComplete()
    {
        Alert addComplete = new Alert(Alert.AlertType.INFORMATION);
        addComplete.setTitle("Alert!");
        addComplete.setContentText("Add successfully");
        addComplete.show();
    }
    public static void addField()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Please complete all fields!!!");
        addFail.show();
    }

    public static void employeeAlreadyExist()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("ID Already exist, please enter another ID!!!");
        addFail.show();
    }

    public static void enterAgainId()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("IDs are not allowed to be negative numbers, please enter another ID!!!");
        addFail.show();
    }

    public static void enterAgainAge()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Age not less than 22 and older than 55, please enter another Age!!!");
        addFail.show();
    }

    public static void enterAgainMonth()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Month not less than 1 and older than 12, please enter another Month!!!");
        addFail.show();
    }

    public static void enterAgainSalary()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Salary are not allowed to be less than 1000$, please enter another Salary!!!");
        addFail.show();
    }

    public static void updateComplete()
    {
        Alert updateComplete = new Alert(Alert.AlertType.INFORMATION);
        updateComplete.setTitle("Alert!");
        updateComplete.setContentText("Update successfully");
        updateComplete.show();
    }
    public static void updateFail()
    {
        Alert updateFail = new Alert(Alert.AlertType.ERROR);
        updateFail.setTitle("Alert!");
        updateFail.setContentText("Update Fail!!!");
        updateFail.show();
    }
    public static void deleteComplete()
    {
        Alert deleteComplete = new Alert(Alert.AlertType.INFORMATION);
        deleteComplete.setTitle("Alert!");
        deleteComplete.setContentText("Delete successfully");
        deleteComplete.show();
    }
    public static void deleteFail()
    {
        Alert deleteFail = new Alert(Alert.AlertType.ERROR);
        deleteFail.setTitle("Alert!");
        deleteFail.setContentText("Delete Fail!!!");
        deleteFail.show();
    }
}
