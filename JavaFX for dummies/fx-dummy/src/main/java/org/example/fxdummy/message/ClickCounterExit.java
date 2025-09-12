package org.example.fxdummy.message;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClickCounterExit extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Stage stage;
    int iClickCount = 0;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Button btnClickMe = new Button();
        btnClickMe.setText("Click me please");
        btnClickMe.setOnAction(e->btnClickMe_Click());

        Button btnClose = new Button();
        btnClose.setText("Close");
        btnClose.setOnAction(e->btnClose_Click());

        VBox pane = new VBox(10);
        pane.getChildren().addAll(btnClickMe,btnClose);
        pane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(pane,250,150);
        stage.setScene(scene);
        stage.setTitle("Click Counter");
        stage.setOnCloseRequest(e->{
            e.consume();
            btnClose_Click();
        });
        stage.show();
    }

    private void btnClose_Click() {
        boolean confirm = false;
        confirm = ConfirmationBox.show("Are you sure to quit","Confirmation","Yes","No");
        if (confirm) {
            stage.close();
        }
    }

    private void btnClickMe_Click() {
        iClickCount++;
        if(iClickCount==1){
            MessageBox.show("You clicked the button once " , "Click ");
        }else{
            MessageBox.show("You clicked the button for the " + iClickCount + " times", "Click ");
        }
    }
}
