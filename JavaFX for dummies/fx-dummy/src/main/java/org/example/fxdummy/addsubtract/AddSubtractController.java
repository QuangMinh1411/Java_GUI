package org.example.fxdummy.addsubtract;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class AddSubtractController {

    @FXML
    private Label lbl;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSubtract;

    private int count = 0;

    @FXML
    private void initialize() {
        updateLabel();
    }

    @FXML
    private void handle(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            count++;
        } else if (event.getSource() == btnSubtract) {
            count--;
        }
        updateLabel();
    }

    private void updateLabel() {
        if (lbl != null) {
            lbl.setText(Integer.toString(count));
        }
    }
}
