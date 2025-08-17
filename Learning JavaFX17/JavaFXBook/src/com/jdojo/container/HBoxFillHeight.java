package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Demo: Understanding HBox.setFillHeight(boolean)
 *
 * What this example shows
 * -----------------------
 * - An HBox with 10px spacing contains: a Label, a multi-line TextArea, a CheckBox,
 *   and two Buttons (OK, Cancel).
 * - The CheckBox toggles the HBox "fillHeight" property at runtime.
 * - The Cancel button is allowed to grow vertically (maxHeight = Double.MAX_VALUE).
 * - A styled border and padding help visualize the container.
 *
 * Key concepts explained
 * ----------------------
 * 1) HBox fillHeight
 *    - When fillHeight is true, HBox attempts to resize its children to match
 *      the HBox's own height (subject to each child’s maxHeight and vgrow).
 *    - When fillHeight is false (default), children keep their preferred heights.
 *
 * 2) Child maxHeight
 *    - Even if fillHeight is true, a child will not grow beyond its maxHeight.
 *      By setting cancelBtn.setMaxHeight(Double.MAX_VALUE), we explicitly
 *      allow the Cancel button to expand vertically if the HBox decides to fill height.
 *
 * 3) Live toggling via CheckBox
 *    - The CheckBox "Fill Height" is bound via an action handler that calls
 *      root.setFillHeight(fillHeightCbx.isSelected()). This lets you see the layout
 *      difference immediately when the box is checked/unchecked.
 *
 * 4) TextArea sizing
 *    - The TextArea has preferred column/row counts to give it an initial size,
 *      but its actual size in the HBox depends on layout rules and fillHeight.
 *
 * Visual cues
 * -----------
 * - CSS inline styles add padding and a blue border so you can better see the HBox bounds.
 *
 * Try it
 * ------
 * - Launch the app, toggle the "Fill Height" CheckBox, and observe how the Cancel
 *   button (which is allowed to grow) changes height relative to the HBox.
 */
public class HBoxFillHeight extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        HBox root = new HBox(10); // 10px spacing

        Label descLbl = new Label("Description:");
        TextArea desc = new TextArea();
        desc.setPrefColumnCount(10);
        desc.setPrefRowCount(3);

        Button okBtn = new Button("OK");
        Button cancelBtn = new Button("Cancel");

        // Let the Cancel button expand vertically
        cancelBtn.setMaxHeight(Double.MAX_VALUE);

        CheckBox fillHeightCbx = new CheckBox("Fill Height");
        fillHeightCbx.setSelected(true);

        // Add an event handler to the CheckBox, so the user can set the
        // fillHeight property using the CheckBox
        fillHeightCbx.setOnAction(e ->
                root.setFillHeight(fillHeightCbx.isSelected()));

        root.getChildren().addAll(
                descLbl, desc, fillHeightCbx, okBtn, cancelBtn);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using HBox fillHeight Property");
        stage.show();
    }

}
