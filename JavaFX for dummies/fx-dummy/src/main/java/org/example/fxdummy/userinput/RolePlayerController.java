package org.example.fxdummy.userinput;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.fxdummy.message.MessageBox;

public class RolePlayerController {
    @FXML
    private TextField txtCharacter;
    @FXML
    private TextField txtActor;
    @FXML
    private Label lblCharacter;
    @FXML
    private Label lblActor;
    @FXML
    private Button btnOK;

    @FXML
    public void btnOK_Click(ActionEvent e) {
        String errorMessage = "";
        if (txtCharacter.getText().isEmpty()) {
            errorMessage += "Character name is empty\n";
        }
        if (txtActor.getText().isEmpty()) {
            errorMessage += "Actor name is empty\n";
        }
        if (errorMessage.isEmpty()) {
            String message = "The role of "+txtCharacter.getText()+" will be played by "+txtActor.getText();
            MessageBox.show(message, "Cast");
        }else {
            MessageBox.show(errorMessage, "Missing Data");
        }
    }
}
