package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A simple JavaFX example that demonstrates the SplitPane control.
 *
 * The UI shows two labeled text areas side-by-side separated by a draggable
 * divider. Users can drag the divider to resize the left and right panes.
 *
 * Key points:
 * - Two TextAreas are wrapped in VBoxes with labels to create two logical panes.
 * - A SplitPane manages these panes horizontally (default orientation) and
 *   lets the user resize them via the divider.
 * - The SplitPane is placed inside an HBox, which here simply hosts the
 *   SplitPane and provides some spacing and border styling.
 */
public class SplitPaneTest extends Application {
    public static void main(String[] args) {
        // Launch the JavaFX application (calls start(Stage) below)
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create first text area and set preferred size hints
        TextArea desc1 = new TextArea();
        desc1.setPrefColumnCount(10); // approx. width in characters
        desc1.setPrefRowCount(4);     // approx. height in rows

        // Create second text area and set preferred size hints
        TextArea desc2 = new TextArea();
        desc2.setPrefColumnCount(10);
        desc2.setPrefRowCount(4);

        // Wrap each TextArea with a label in a vertical box
        VBox vb1 = new VBox(new Label("Description1"), desc1);
        VBox vb2 = new VBox(new Label("Description2"), desc2);

        // Create a SplitPane and add the two panes; users can drag the divider
        SplitPane sp = new SplitPane();
        sp.getItems().addAll(vb1, vb2);

        // Put the SplitPane in an HBox (single child here) to allow spacing and styling
        HBox root = new HBox(sp);
        root.setSpacing(10);
        // Simple inline CSS to make the container padding and border visible
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Standard scene and stage setup
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using SplitPane Controls");
        stage.show();
    }
}
