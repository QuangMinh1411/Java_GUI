package org.example.fxdummy.hello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    int iClickCount = 0;
    @FXML
    private Button btn;
    @FXML
    private Label lbl;
    @FXML
    private void buttonClicked(ActionEvent event) {
       iClickCount++;
        if (iClickCount != 1) {
            lbl.setText("You clicked the button for the " + iClickCount + " time");
        } else {
            lbl.setText("You clicked the button for the first time");
        }
    }
}
