package com.quangminh.javafxdummy.pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PizzaApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quangminh/javafxdummy/pizza-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Pizza Order");
        stage.setScene(scene);
        stage.show();

    }
}
