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
    public void onOk(ActionEvent event) {
        String character = txtCharacter.getText() == null ? "" : txtCharacter.getText().trim();
        String actor = txtActor.getText() == null ? "" : txtActor.getText().trim();

        StringBuilder errors = new StringBuilder();
        if (character.isBlank()) {
            errors.append("Character name is empty\n");
        }
        if (actor.isBlank()) {
            errors.append("Actor name is empty\n");
        }

        if (errors.length() == 0) {
            String message = String.format("The role of %s will be played by %s.", character, actor);
            MessageBox.show(message, "Cast");
        } else {
            MessageBox.show(errors.toString(), "Missing Data");
        }
    }
}
