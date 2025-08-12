package com.jdojo.intro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImprovedHelloFXApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Enter your name:");
        TextField nameFld = new TextField();
        Label msg = new Label();
        msg.setStyle("-fx-text-fill: blue");

        Button sayHelloBtn = new Button("Say Hello");
        Button exitBtn = new Button("Exit");
        sayHelloBtn.setOnAction(event -> {
           String name = nameFld.getText();
           if(name.trim().length()>0){
               msg.setText("Hello "+name+"!");
           }else {
               msg.setText("Hello World!");
           }
        });

        exitBtn.setOnAction(event -> {
           Platform.exit();
        });

        VBox root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(label, nameFld, msg, sayHelloBtn, exitBtn);
        Scene scene = new Scene(root, 350, 150);
        stage.setScene(scene);
        stage.setTitle("Improved Hello FX Application");
        stage.show();
    }
}
