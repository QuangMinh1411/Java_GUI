package com.quangminh.javafxdummy.message;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClickCounter extends Application {
    int iClickCount = 0;
    private Button btn;


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        btn = new Button("Click me");
        btn.setOnAction(e -> buttonClick());
        BorderPane pane = new BorderPane();
        pane.setCenter(btn);

        Scene scene = new Scene(pane, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Click Counter");
        stage.show();
    }

    private void buttonClick() {
        iClickCount++;
        if (iClickCount == 1) {
            MessageBox.show("You clicked the button once " , "Click ");
        } else {
            MessageBox.show("You clicked the button for the " + iClickCount + " times", "Click ");
        }
    }
}
