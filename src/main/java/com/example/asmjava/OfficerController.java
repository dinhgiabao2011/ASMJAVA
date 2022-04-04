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

public class OfficerController implements Initializable {

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
    private TableView<Officer> tvOfficer;
    @FXML
    private TableColumn<Officer,Integer> colID;
    @FXML
    private TableColumn<Officer,String> colName;
    @FXML
    private TableColumn<Officer,Integer> colAge;
    @FXML
    private TableColumn<Officer,Integer> colMonth;
    @FXML
    private TableColumn<Officer,Float> colSalary;
    @FXML
    private ObservableList<Officer> officerList;

    public void changeMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load(), 800, 500);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    public void addOfficer(ActionEvent event)
    {
        List<Manager> managers = Function.readManagerListFromFile(file1);
        List<Officer> officers = Function.readOfficerListFromFile(file2);

        try {
            if(tfID.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfAge.getText().trim().isEmpty() || tfMonth.getText().trim().isEmpty() || tfSalary.getText().trim().isEmpty())
            {
                Function.addField();
            }
            else if (Function.IsOfficerExisted(officers,Integer.parseInt(tfID.getText().trim())) || Function.IsManagerExisted(managers,Integer.parseInt(tfID.getText().trim())))
            {
                Function.employeeAlreadyExist();
            }
            else if(Integer.parseInt(tfID.getText().trim()) < 0)
            {
                Function.enterAgainId();
            }
            else if(Integer.parseInt(tfAge.getText().trim()) < 22 || Integer.parseInt(tfAge.getText().trim()) > 55)
            {
                Function.enterAgainAge();
            }
            else if (Integer.parseInt(tfMonth.getText().trim()) < 0 || Integer.parseInt(tfMonth.getText().trim()) > 13)
            {
                Function.enterAgainMonth();
            }
            else if (Float.parseFloat(tfSalary.getText().trim()) < 0 || Float.parseFloat(tfSalary.getText().trim()) > 1000)
            {
                Function.enterAgainSalary();
            }
            else
            {
                Officer officer = new Officer(Integer.parseInt(tfID.getText()),
                        tfName.getText(),
                        Integer.parseInt(tfAge.getText()),
                        Integer.parseInt(tfMonth.getText()),
                        Float.parseFloat(tfSalary.getText()));
                officers.add(officer);
                Function.addOfficerToFile(officers,file2);
                Function.addComplete();
                tvOfficer.getItems().add(officer);
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
    public void chooseItemFromTable(MouseEvent event)
    {
        Officer officer = tvOfficer.getSelectionModel().getSelectedItem();
        if(officer != null)
        {
            tfID.setDisable(true);
            tfID.setText(colID.getCellData(officer).toString());
            tfName.setText(colName.getCellData(officer));
            tfAge.setText(colAge.getCellData(officer).toString());
            tfMonth.setText(colMonth.getCellData(officer).toString());
            tfSalary.setText(colSalary.getCellData(officer).toString());
        }
    }

    @FXML
    public void updateOfficer(ActionEvent event)
    {

        try {
            officerList = tvOfficer.getItems();
            int selectedItem = Integer.parseInt(tfID.getText());
            for(Officer officer: officerList)
            {
                if(officer.getId() == selectedItem)
                {
                    try {
                        if(Integer.parseInt(tfAge.getText().trim()) < 22 || Integer.parseInt(tfAge.getText().trim()) > 55)
                        {
                            Function.enterAgainAge();
                        }
                        else if (Integer.parseInt(tfMonth.getText().trim()) < 0 || Integer.parseInt(tfMonth.getText().trim()) > 13)
                        {
                            Function.enterAgainMonth();
                        }
                        else if (Float.parseFloat(tfSalary.getText().trim()) < 0)
                        {
                            Function.enterAgainSalary();
                        }
                        else {
                            officer.setName(tfName.getText());
                            officer.setMonth(Integer.parseInt(tfMonth.getText()));
                            officer.setOfficerSalary(Float.parseFloat(tfSalary.getText()));
                            tvOfficer.setItems(officerList);
                            tvOfficer.refresh();
                            colID.getCellValueFactory();
                            colName.getCellValueFactory();
                            colAge.getCellValueFactory();
                            colMonth.getCellValueFactory();
                            colSalary.getCellValueFactory();
                            List<Officer> officers = tvOfficer.getItems();
                            Function.SaveOfficerToFile(officers, file2);
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
    public void deleteOfficer(ActionEvent event)
    {
        try
        {
            int selectID = tvOfficer.getSelectionModel().getSelectedIndex();
            tvOfficer.getItems().remove(selectID);
            colID.getCellValueFactory();
            colName.getCellValueFactory();
            colAge.getCellValueFactory();
            colMonth.getCellValueFactory();
            colSalary.getCellValueFactory();
            List<Officer> officers =  tvOfficer.getItems();
            Function.SaveOfficerToFile(officers,file2);
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
    public void searchOfficer(ActionEvent event)
    {
        officerList = FXCollections.observableArrayList(Function.readOfficerListFromFile(file2));
        List<Officer> officers = new ArrayList<>();
        for (Officer e: officerList){
            if (e.getName().matches("(?i).*\\b" + tfSearch.getText().trim() + "\\b.*"))
                officers.add(e);
        }
        officerList = FXCollections.observableArrayList(officers);
        tvOfficer.setItems(officerList);
    }

    @FXML
    public void refreshTable(ActionEvent event)
    {
        officerList = FXCollections.observableArrayList(Function.readOfficerListFromFile(file2));
        tvOfficer.setItems(officerList);
    }
    @FXML
    public void refreshForm()
    {
        tfID.setDisable(false);
        tfID.setText("");
        tfName.setText("");
        tfAge.setText("");
        tfMonth.setText("");
        tfSalary.setText("");
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        officerList = FXCollections.observableArrayList(Function.readOfficerListFromFile(file2));
        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("officerSalary"));
        tvOfficer.setItems(officerList);
    }



}
