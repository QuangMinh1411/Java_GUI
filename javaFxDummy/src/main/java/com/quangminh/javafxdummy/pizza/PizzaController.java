package com.quangminh.javafxdummy.pizza;

import com.quangminh.javafxdummy.message.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaController {
    @FXML
    private BorderPane painMain;
    @FXML
    private HBox paneTop;
    @FXML
    private Label lblHeading;
    @FXML
    private VBox paneCenter;
    @FXML
    private VBox paneCustomer;
    @FXML
    private HBox paneName;
    @FXML
    private Label lblName;
    @FXML
    private TextField txtName;
    @FXML
    private HBox panePhone;
    @FXML
    private Label lblPhone;
    @FXML
    private TextField txtPhone;
    @FXML
    private HBox paneAddress;
    @FXML
    private Label lblAddress;
    @FXML
    private TextField txtAddress;
    @FXML
    private HBox paneOrder;
    @FXML
    private VBox paneSize;
    @FXML
    private Label lblSize;
    @FXML
    private RadioButton rdoSmall;
    @FXML
    private ToggleGroup groupSize;
    @FXML
    private RadioButton rdoMedium;
    @FXML
    private RadioButton rdoLarge;
    @FXML
    private VBox paneCrust;
    @FXML
    private Label lblCrust;
    @FXML
    private RadioButton rdoThin;
    @FXML
    private ToggleGroup groupCrust;
    @FXML
    private RadioButton rdoThick;
    @FXML
    private VBox paneTopping;
    @FXML
    private Label lblToppings;
    @FXML
    private FlowPane paneToppings;
    @FXML
    private CheckBox chkPepperoni;
    @FXML
    private CheckBox chkSausage;
    @FXML
    private CheckBox chkLinguica;
    @FXML
    private CheckBox chkMushrooms;
    @FXML
    private CheckBox chkTomatoes;
    @FXML
    private CheckBox chkOlives;
    @FXML
    private CheckBox chkAnchovies;
    @FXML
    private HBox paneBottom;
    @FXML
    private Region spacer;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;


    @FXML
    public void btnOK_Click(ActionEvent e) {
        if (!isInputValid()) {
            return;
        }

        String orderSummary = buildOrderSummary();
        MessageBox.show(orderSummary, "Order Details");
    }

    @FXML
    public void btnCancel_Click(ActionEvent e) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private String buildOrderSummary() {
        StringBuilder summary = new StringBuilder();

        appendCustomerDetails(summary);
        appendOrderDetails(summary);

        return summary.toString();
    }

    private void appendCustomerDetails(StringBuilder summary) {
        summary.append("Customer:\n\n");
        summary.append("\t").append(txtName.getText()).append("\n");
        summary.append("\t").append(txtAddress.getText()).append("\n");
        summary.append("\t").append(txtPhone.getText()).append("\n\n");
    }

    private void appendOrderDetails(StringBuilder summary) {
        summary.append("You have ordered a ");
        summary.append(getSelectedRadioButtonText(groupSize)).append(" pizza with a ");
        summary.append(getSelectedRadioButtonText(groupCrust)).append(" crust");

        String toppings = getSelectedToppings();
        if (toppings.isEmpty()) {
            summary.append(" and no toppings.");
        } else {
            summary.append(" and the following toppings:\n").append(toppings);
        }
    }

    private String getSelectedToppings() {
        List<CheckBox> checkBoxes = Arrays.asList(
                chkPepperoni, chkSausage, chkLinguica, chkMushrooms,
                chkTomatoes, chkOlives, chkAnchovies
        );

        return checkBoxes.stream()
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.joining(", "));
    }

    private String getSelectedRadioButtonText(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected != null ? selected.getText().toLowerCase() : "";
    }

    private boolean isInputValid() {
        if (txtName.getText().trim().isEmpty()) {
            MessageBox.show("Customer name is required.", "Validation Error");
            txtName.requestFocus();
            return false;
        }
        return true;
    }
}
