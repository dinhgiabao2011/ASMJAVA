package com.example.asmjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene homeScene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Employee Management Application");
        stage.setScene(homeScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}