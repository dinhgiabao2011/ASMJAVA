package com.example.asmjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

    public static File file1 = new File("Manager.txt");
    public static File file2 = new File("Officer.txt");


    @FXML
    private TextField tfID;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfMonth;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<Manager> tvManager;
    @FXML
    private TableColumn<Manager, Integer> colID;
    @FXML
    private TableColumn<Manager, String> colName;
    @FXML
    private TableColumn<Manager, Integer> colAge;
    @FXML
    private TableColumn<Manager, Integer> colMonth;
    @FXML
    private TableColumn<Manager, Float> colSalary;
    @FXML
    private ObservableList<Manager> managerList;

    public void changeMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load(), 800, 500);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    public void addManager(ActionEvent event) {

        List<Manager> managers = Function.readManagerListFromFile(file1);
        List<Officer> officers = Function.readOfficerListFromFile(file2);
        try {
            if (tfID.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfAge.getText().trim().isEmpty() || tfMonth.getText().trim().isEmpty() || tfSalary.getText().trim().isEmpty()) {
                Function.addField();
            } else if (Function.IsManagerExisted(managers, Integer.parseInt(tfID.getText().trim())) || Function.IsOfficerExisted(officers, Integer.parseInt(tfID.getText().trim()))) {
                Function.employeeAlreadyExist();
            } else if (Integer.parseInt(tfID.getText().trim()) < 0) {
                Function.enterAgainId();
            } else if (Integer.parseInt(tfAge.getText().trim()) < 22 || Integer.parseInt(tfAge.getText().trim()) > 55) {
                Function.enterAgainAge();
            } else if (Integer.parseInt(tfMonth.getText().trim()) < 1 || Integer.parseInt(tfMonth.getText().trim()) > 12) {
                Function.enterAgainMonth();
            } else if (Float.parseFloat(tfSalary.getText().trim()) <= 1000 ) {
                Function.enterAgainSalary();
            } else {
                Manager manager = new Manager(Integer.parseInt(tfID.getText()),
                        tfName.getText(),
                        Integer.parseInt(tfAge.getText()),
                        Integer.parseInt(tfMonth.getText()),
                        Float.parseFloat(tfSalary.getText()));
                managers.add(manager);
                Function.addManagerToFile(managers,file1);
                Function.addComplete();
                tvManager.getItems().add(manager);
                tfID.setText("");
                tfName.setText("");
                tfAge.setText("");
                tfMonth.setText("");
                tfSalary.setText("");
            }
        } catch (NumberFormatException e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Alert!");
            addFail.setContentText("Please fill with the number!!!");
            addFail.show();
        }
    }

    @FXML
    public void chooseItemFromTable(MouseEvent event) {
        Manager manager = tvManager.getSelectionModel().getSelectedItem();
        if (manager != null) {
            tfID.setDisable(true);
            tfID.setText(colID.getCellData(manager).toString());
            tfName.setText(colName.getCellData(manager));
            tfAge.setText(colAge.getCellData(manager).toString());
            tfMonth.setText(colMonth.getCellData(manager).toString());
            tfSalary.setText(colSalary.getCellData(manager).toString());
        }
    }

    @FXML
    public void updateManager(ActionEvent event) {

        try {
            managerList = tvManager.getItems();
            int selectedItem = Integer.parseInt(tfID.getText());
            for (Manager manager : managerList) {
                if (manager.getId() == selectedItem) {
                    try{
                        if (Integer.parseInt(tfAge.getText().trim()) < 22 || Integer.parseInt(tfAge.getText().trim()) > 55) {
                            Function.enterAgainAge();
                        } else if (Integer.parseInt(tfMonth.getText().trim()) < 0 || Integer.parseInt(tfMonth.getText().trim()) > 13) {
                            Function.enterAgainMonth();
                        } else if (Float.parseFloat(tfSalary.getText().trim()) < 0) {
                            Function.enterAgainSalary();
                        } else {
                            manager.setName(tfName.getText());
                            manager.setAge(Integer.parseInt(tfAge.getText()));
                            manager.setMonth(Integer.parseInt(tfMonth.getText()));
                            manager.setManagerSalary(Float.parseFloat(tfSalary.getText()));
                            tvManager.setItems(managerList);
                            tvManager.refresh();
                            colID.getCellValueFactory();
                            colName.getCellValueFactory();
                            colAge.getCellValueFactory();
                            colMonth.getCellValueFactory();
                            colSalary.getCellValueFactory();
                            List<Manager> managers = tvManager.getItems();
                            Function.SaveManagersToFile(managers, file1);
                            Function.updateComplete();
                        }
                    } catch (NumberFormatException e) {
                        Alert addFail = new Alert(Alert.AlertType.ERROR);
                        addFail.setTitle("Alert!");
                        addFail.setContentText("Please fill with the number!!!");
                        addFail.show();
                    }

                }

            }
        } catch (NumberFormatException e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Alert!");
            addFail.setContentText("Please choose the person first!!!");
            addFail.show();
        }
    }

    @FXML
    public void deleteManager(ActionEvent event) {
        try
        {
            int selectID = tvManager.getSelectionModel().getSelectedIndex();
            tvManager.getItems().remove(selectID);
            colID.getCellValueFactory();
            colName.getCellValueFactory();
            colAge.getCellValueFactory();
            colMonth.getCellValueFactory();
            colSalary.getCellValueFactory();
            List<Manager> managers = tvManager.getItems();
            Function.SaveManagersToFile(managers, file1);
            Function.deleteComplete();
            tfID.setText("");
            tfName.setText("");
            tfAge.setText("");
            tfMonth.setText("");
            tfSalary.setText("");
            tfID.setDisable(false);
        } catch (Exception e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Alert!");
            addFail.setContentText("Please choose the person first!!!");
            addFail.show();
        }
    }

    @FXML
    public void searchManager(ActionEvent event) {
        managerList = FXCollections.observableArrayList(Function.readManagerListFromFile(file1));
        List<Manager> managers = new ArrayList<>();
        for (Manager e : managerList) {
            if (e.getName().matches("(?i).*\\b" + tfSearch.getText().trim() + "\\b.*"))
                managers.add(e);
        }
        managerList = FXCollections.observableArrayList(managers);
        tvManager.setItems(managerList);
    }

    @FXML
    public void refreshTable(ActionEvent event) {
        managerList = FXCollections.observableArrayList(Function.readManagerListFromFile(file1));
        tvManager.setItems(managerList);
    }

    @FXML
    public void refreshForm() {
        tfID.setDisable(false);
        tfID.setText("");
        tfName.setText("");
        tfAge.setText("");
        tfMonth.setText("");
        tfSalary.setText("");
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        managerList = FXCollections.observableArrayList(Function.readManagerListFromFile(file1));
        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("managerSalary"));
        tvManager.setItems(managerList);
    }
}
