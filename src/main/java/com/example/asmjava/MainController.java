package com.example.asmjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public void changeManagerScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Manager.fxml"));
        Scene managerScene = new Scene(fxmlLoader.load(), 800, 500);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(managerScene);
        window.show();
    }

    public void changeOfficerScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Officer.fxml"));
        Scene officerScene = new Scene(fxmlLoader.load(), 800, 500);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(officerScene);
        window.show();
    }

    public void changeCommentScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Comment.fxml"));
        Scene commentScene = new Scene(fxmlLoader.load(), 800, 500);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(commentScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
