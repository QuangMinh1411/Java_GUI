package com.jdojo.control;

import com.jdojo.util.ResourceUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
This JavaFX application demonstrates how to use a TitledPane to group related form fields
with a title and an icon.

What it does, step by step:
1) Create input controls:
   - Two TextFields (first/last name) with a preferred column count to suggest width.
   - A DatePicker for date of birth with a fixed preferred width for consistency.

2) Layout the form:
   - Use a GridPane and add each label-control pair per row via grid.addRow(rowIndex, nodes...).
     This implicitly places labels in column 0 and controls in column 1.

3) Wrap the form inside a TitledPane:
   - Set the pane's title via setText("Personal Info").
   - Set the content to the GridPane so the form appears inside the titled section.
   - Load an image (icon) from the application's resources, create an ImageView, and set it
     as the graphic of the TitledPane so the title area shows both text and an icon.

4) Place the TitledPane in an HBox:
   - Even though there is only one child, HBox is used so we can easily apply spacing and styling,
     and so the layout can be extended later if needed.
   - Inline CSS is applied to add padding and a simple blue border.

5) Show the UI:
   - Create a Scene with the HBox root, set it on the Stage, set a window title, and show the stage.

Notes:
- TitledPane is collapsible by default (user can expand/collapse the content).
- setPrefColumnCount() on TextField influences its displayed width based on character columns.
- grid.addRow() is a convenient way to add a full row of nodes without manual column indices.
*/
public class TitledPaneTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        TextField firstNameFld = new TextField();
        firstNameFld.setPrefColumnCount(8);

        TextField lastNameFld = new TextField();
        lastNameFld.setPrefColumnCount(8);

        DatePicker dob = new DatePicker();
        dob.setPrefWidth(150);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("First Name:"), firstNameFld);
        grid.addRow(1, new Label("Last Name:"), lastNameFld);
        grid.addRow(2, new Label("DOB:"), dob);

        TitledPane infoPane = new TitledPane();
        infoPane.setText("Personal Info");
        infoPane.setContent(grid);

        String imageStr = ResourceUtil.getResourceURLStr("picture/privacy_icon.png");
        Image img = new Image(imageStr);
        ImageView imgView = new ImageView(img);
        infoPane.setGraphic(imgView);

        HBox root = new HBox(infoPane);
        root.setSpacing(10);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using TitledPane Controls");
        stage.show();
    }
}
