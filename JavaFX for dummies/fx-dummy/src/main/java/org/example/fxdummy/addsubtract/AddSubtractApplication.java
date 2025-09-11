package org.example.fxdummy.addsubtract;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddSubtractApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fxdummy/addsubtract-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setTitle("Add/Subtract");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
