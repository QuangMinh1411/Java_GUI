package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demo: Using BorderPane regions and grow/margin behaviors
 *
 * What this example shows
 * -----------------------
 * - A BorderPane with content in three regions: CENTER, RIGHT, and BOTTOM.
 *   TOP and LEFT regions are intentionally set to null.
 * - CENTER contains a small form: an HBox with a Name label and TextField,
 *   followed by a Description label and a multi-line TextArea stacked in a VBox.
 * - RIGHT contains two vertically stacked buttons (Ok, Cancel) in a VBox.
 * - BOTTOM shows a status bar in an HBox with padding, small font, and background.
 *
 * Key concepts explained
 * ----------------------
 * 1) Region placement in BorderPane
 *    - new BorderPane(center, top, right, bottom, left) sets child nodes for
 *      CENTER, TOP, RIGHT, BOTTOM, and LEFT respectively. Here, top and left are null.
 *
 * 2) Grow priorities (hgrow and vgrow)
 *    - HBox.setHgrow(nameFld, Priority.ALWAYS) lets the TextField grow to use
 *      extra horizontal space within the HBox.
 *    - VBox.setVgrow(descText, Priority.ALWAYS) lets the TextArea grow vertically
 *      when the center VBox has extra height.
 *
 * 3) Making buttons the same width in the RIGHT region
 *    - okBtn.setMaxWidth(Double.MAX_VALUE) allows the OK button to expand so both
 *      buttons can align to the same maximum width inside the VBox.
 *
 * 4) Margins and styling
 *    - BorderPane.setMargin(bottom, new Insets(10, 0, 0, 0)) adds space above the
 *      bottom status bar.
 *    - Inline CSS is used to visualize layout (padding, background, small font).
 *
 * Try it
 * ------
 * - Resize the window: the Name TextField grows horizontally, the Description
 *   TextArea grows vertically, and the bottom status bar remains at the bottom.
 */
public class BorderPaneTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        // Set the top and left child nodes to null
        Node top = null;
        Node left = null;

        // Build the content nodes for the center region
        VBox center = getCenter();

        // Create the right child node
        Button okBtn = new Button("Ok");
        Button cancelBtn = new Button("Cancel");

        // Make the OK and cancel buttons the same size
        okBtn.setMaxWidth(Double.MAX_VALUE);
        VBox right = new VBox(okBtn, cancelBtn);
        right.setStyle("-fx-padding: 10;");

        // Create the bottom child node
        Label statusLbl = new Label("Status: Ready");
        HBox bottom = new HBox(statusLbl);
        BorderPane.setMargin(bottom, new Insets(10, 0, 0, 0));
        bottom.setStyle("-fx-background-color: lavender;" +
                "-fx-font-size: 7pt;" +
                "-fx-padding: 10 0 0 0;" );

        BorderPane root = new BorderPane(center, top, right, bottom, left);
        root.setStyle("-fx-background-color: lightgray;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using a BorderPane");
        stage.show();
    }

    private VBox getCenter() {
        // A Label and a TextField in an HBox
        Label nameLbl = new Label("Name:");
        TextField nameFld = new TextField();
        HBox.setHgrow(nameFld, Priority.ALWAYS);
        HBox nameFields = new HBox(nameLbl, nameFld);

        // A Label and a TextArea
        Label descLbl = new Label("Description:");
        TextArea descText = new TextArea();
        descText.setPrefColumnCount(20);
        descText.setPrefRowCount(5);
        VBox.setVgrow(descText, Priority.ALWAYS);

        // Box all controls in a VBox
        VBox center = new VBox(nameFields, descLbl, descText);

        return center;
    }
}
