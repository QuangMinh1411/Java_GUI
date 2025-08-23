package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A small JavaFX demo that shows how to use ProgressIndicator and ProgressBar
 * in both indeterminate and determinate modes, and how to update their progress.
 *
 * Key points:
 * - Indeterminate: created with the no-arg constructor, it animates continuously
 *   to indicate "work is in progress" without a known completion percentage.
 * - Determinate: created with an initial progress value (e.g., 0), it shows a
 *   specific fraction of completion between 0.0 and 1.0.
 * - Buttons demonstrate directly completing indeterminate controls and stepping
 *   progress on determinate controls.
 */
public class ProgressTest extends Application {
    public static void main(String[] args) {
        // Launches the JavaFX application lifecycle, which will call start(Stage)
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // ProgressIndicator with no-arg constructor -> indeterminate mode
        ProgressIndicator indeterminateInd = new ProgressIndicator();
        // ProgressIndicator with explicit initial value -> determinate mode starting at 0%
        ProgressIndicator determinateInd = new ProgressIndicator(0);

        // ProgressBar with no-arg constructor -> indeterminate mode
        ProgressBar indeterminateBar = new ProgressBar();
        // ProgressBar with explicit initial value -> determinate mode starting at 0%
        ProgressBar determinateBar = new ProgressBar(0);

        // Button to instantly complete the indeterminate ProgressIndicator
        Button completeIndBtn = new Button("Complete Task");
        // Setting progress to 1.0 tells the control the task is complete
        completeIndBtn.setOnAction(e -> indeterminateInd.setProgress(1.0));

        // Button to instantly complete the indeterminate ProgressBar
        Button completeBarBtn = new Button("Complete Task");
        completeBarBtn.setOnAction(e -> indeterminateBar.setProgress(1.0));

        // Button that increments the determinate ProgressIndicator by 10% per click
        Button makeProgresstIndBtn = new Button("Make Progress");
        makeProgresstIndBtn.setOnAction(e -> makeProgress(determinateInd));

        // Button that increments the determinate ProgressBar by 10% per click
        Button makeProgresstBarBtn = new Button("Make Progress");
        makeProgresstBarBtn.setOnAction(e -> makeProgress(determinateBar));

        // Layout the controls in a simple grid with spacing and a visible border
        GridPane root = new GridPane();
        root.setHgap(10); // horizontal gap between columns
        root.setVgap(5);  // vertical gap between rows

        // Row 0: label + indeterminate ProgressIndicator + Complete button
        root.addRow(0, new Label("Indeterminate Progress:"),
                indeterminateInd, completeIndBtn);
        // Row 1: label + determinate ProgressIndicator + Make Progress button
        root.addRow(1, new Label("Determinate Progress:"),
                determinateInd, makeProgresstIndBtn);
        // Row 2: label + indeterminate ProgressBar + Complete button
        root.addRow(2, new Label("Indeterminate Progress:"),
                indeterminateBar, completeBarBtn);
        // Row 3: label + determinate ProgressBar + Make Progress button
        root.addRow(3, new Label("Determinate Progress:"),
                determinateBar, makeProgresstBarBtn);

        // Some simple inline CSS to make the demo visually distinct
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Set up the scene and stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using ProgressIndicator and ProgressBar Controls");
        stage.show();
    }

    /**
     * Increments the progress of a ProgressIndicator (or ProgressBar, since it
     * is a subclass) by 10% per call, clamping the value between 0.0 and 1.0.
     *
     * Notes:
     * - If current progress is negative (indeterminate) or exactly 0, we start at 0.1 (10%).
     * - Otherwise, we add 0.1 until reaching 1.0 (100%), at which point it is clamped.
     */
    public void makeProgress(ProgressIndicator p) {
        double progress = p.getProgress(); // current progress in [0.0, 1.0], or negative for indeterminate
        if (progress <= 0) {
            // Move from indeterminate/zero to the first visible chunk (10%)
            progress = 0.1;
        } else {
            // Increment by 10%
            progress = progress + 0.1;
            // Clamp at 100% so it doesn't exceed the maximum
            if (progress >= 1.0) {
                progress = 1.0;
            }
        }
        p.setProgress(progress);
    }
}
