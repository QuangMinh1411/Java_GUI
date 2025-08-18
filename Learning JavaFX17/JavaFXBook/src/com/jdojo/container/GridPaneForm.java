package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Demo: Building a simple form using GridPane (cells, spans, and grow constraints)
 *
 * What this example shows
 * -----------------------
 * - A simple form laid out in a GridPane with these controls:
 *   Name label + TextField, Description label + multi-line TextArea,
 *   two Buttons (OK, Cancel), and a bottom status bar Label.
 * - Children are added with explicit column/row coordinates and with
 *   column/row spans where needed (for example, the Description label spans 3 columns).
 * - The last row uses GridPane.REMAINING for the column span so the status bar fills
 *   the remaining columns in that row.
 * - Inline CSS is used to color the background and style the status bar.
 *
 * Key concepts explained
 * ----------------------
 * 1) Grid coordinates and spans
 *    - root.add(node, colIndex, rowIndex, colSpan, rowSpan) places a child in a specific
 *      cell and can span across multiple columns/rows.
 *    - Example: root.add(descLbl, 0, 1, 3, 1) places the Description label in row 1
 *      starting at column 0 and spanning 3 columns.
 *
 * 2) Resizing behavior via constraints
 *    - GridPane.setHgrow(nameFld, Priority.ALWAYS) allows the TextField to grow horizontally
 *      to consume extra space in its column.
 *    - GridPane.setVgrow(descText, Priority.ALWAYS) lets the TextArea grow vertically
 *      when the GridPane gets extra height.
 *    - okBtn.setMaxWidth(Double.MAX_VALUE) and statusBar.setMaxWidth(Double.MAX_VALUE)
 *      permit those nodes to stretch to fill the width of their cells when space is available.
 *
 * 3) Using GridPane.REMAINING
 *    - The status bar is added with a column span of GridPane.REMAINING, which means it will
 *      extend through the remaining columns in that row, making it behave like a full-width footer.
 *
 * Try it
 * ------
 * - Run the app and resize the window:
 *   • The Name TextField grows horizontally within its row.
 *   • The Description TextArea grows vertically.
 *   • The bottom status bar fills the remaining columns in the last row.
 */
public class GridPaneForm extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        // A Label and a TextField 
        Label nameLbl = new Label("Name:");
        TextField nameFld = new TextField();

        // A Label and a TextArea
        Label descLbl = new Label("Description:");
        TextArea descText = new TextArea();
        descText.setPrefColumnCount(20);
        descText.setPrefRowCount(5);

        // Two buttons
        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");

        // A Label used as a status bar
        Label statusBar = new Label("Status: Ready");
        statusBar.setStyle("-fx-background-color: lavender;" +
                "-fx-font-size: 7pt;" +
                "-fx-padding: 10 0 0 0;");

        // Create a GridPane and set its background color to lightgray
        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: lightgray;");

        // Add children to the GridPane
        root.add(nameLbl, 0, 0, 1, 1);   // (c0, r0, colspan=1, rowspan=1)
        root.add(nameFld, 1, 0, 1, 1);   // (c1, r0, colspan=1, rowspan=1)
        root.add(descLbl, 0, 1, 3, 1);   // (c0, r1, colspan=3, rowspan=1)
        root.add(descText, 0, 2, 2, 1);  // (c0, r2, colspan=2, rowspan=1)
        root.add(okBtn, 2, 0, 1, 1);     // (c2, r0, colspan=1, rowspan=1)
        root.add(cancelBtn, 2, 1, 1, 1); // (c2, r1, colspan=1, rowspan=1)
        root.add(statusBar, 0, 3, GridPane.REMAINING, 1);

        /* Set constraints for children to customize their resizing behavior */

        // The max width of the OK button should be big enough, 
        // so it can fill the width of its cell
        okBtn.setMaxWidth(Double.MAX_VALUE);

        // The name field in the first row should grow horizontally
        GridPane.setHgrow(nameFld, Priority.ALWAYS);

        // The description field in the third row should grow vertically
        GridPane.setVgrow(descText, Priority.ALWAYS);

        // The status bar in the last should fill its cell 
        statusBar.setMaxWidth(Double.MAX_VALUE);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Creating Forms Using a GridPane");
        stage.show();
    }
}
