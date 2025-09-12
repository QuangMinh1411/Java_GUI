package com.quangminh.javafxdummy.userinput;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RolePlayer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quangminh/javafxdummy/role-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Role Player");
        stage.setScene(scene);
        stage.show();
    }
}
