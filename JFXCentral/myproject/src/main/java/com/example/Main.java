package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WIDTH = 640;
    private static final double HEIGHT = 480;

    @Override
    public void start(Stage stage) throws IOException {
        // var loader = new FXMLLoader(getClass().getResource("main.fxml"));
        // var scene = new Scene(loader.load());
        // scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        BorderPane layoutManager = new BorderPane();
        layoutManager.setCenter(new Label("Hello, JavaFX!"));
        layoutManager.setLeft(new Button("Main Menu"));
        Scene scene = new Scene(layoutManager, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Introduction to JAVAFX");
        stage.centerOnScreen();
        stage.show();
    }

}
