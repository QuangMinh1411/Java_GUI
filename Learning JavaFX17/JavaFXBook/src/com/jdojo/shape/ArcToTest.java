package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * Demo: Using a Path with an ArcTo segment in JavaFX.
 *
 * What this sample does:
 * - Builds a Path using MoveTo/VLineTo/HLineTo to form three sides of a rectangle-like path.
 * - Adds an ArcTo segment to connect from the current point (100, 50) to an end point (0, 0)
 *   using elliptical arc parameters you can change with UI controls.
 * - Exposes ArcTo properties (largeArcFlag, sweepFlag, x-axis rotation, radiusX, radiusY)
 *   and binds them to checkboxes/sliders so you can see the arc update live.
 *
 * Notes on ArcTo:
 * - ArcTo requires an end point (x, y). Without it, the arc cannot be computed.
 * - The arc is part of a Path (it is not a standalone Arc shape). The visual result updates
 *   automatically when bound properties change.
 */
public class ArcToTest extends Application {
    private ArcTo arcTo;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create the ArcTo path element that will close the path with an elliptical arc
        arcTo = new ArcTo();
        // ArcTo needs an end point (relative to the Path's coordinate system):
        // The path below moves to (0,0), goes down to (0,100), right to (100,100), and up to (100,50).
        // From that current point (100,50), this ArcTo will draw an arc to the end point (0,0).
        // Setting X/Y here ensures the ArcTo is valid even before the UI controls change anything.
        arcTo.setX(0);
        arcTo.setY(0);
        // Use the arcTo element to build a Path
        // Path segments in order:
        // - MoveTo(0,0): set the starting point.
        // - VLineTo(100): draw a vertical line down to y=100 (at x=0).
        // - HLineTo(100): draw a horizontal line right to x=100 (at y=100).
        // - VLineTo(50): draw a vertical line up to y=50 (at x=100). Current point is now (100,50).
        // - ArcTo(...): draw an elliptical arc from (100,50) to (0,0) with properties controlled by the UI.
        Path path = new Path(
                new MoveTo(0, 0),
                new VLineTo(100),
                new HLineTo(100),
                new VLineTo(50),
                arcTo);
        BorderPane root = new BorderPane(); // Root layout: controls on top, drawing in the center
        root.setTop(this.getTopPane());
        root.setCenter(path);
        // Some simple styling so the demo has visible padding and border
        root.setStyle("""
               -fx-padding: 10;
               -fx-border-style: solid inside;
               -fx-border-width: 2;
               -fx-border-insets: 5;
               -fx-border-radius: 5;
               -fx-border-color: blue;""");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using ArcTo Path Elements");
        stage.show();
    }
    // Build the top control panel with checkboxes and sliders bound to ArcTo properties
    private GridPane getTopPane() {
        CheckBox largeArcFlagCbx = new CheckBox("largeArcFlag");
        CheckBox sweepFlagCbx = new CheckBox("sweepFlag");
        // X-axis rotation in degrees (0°..360°)
        Slider xRotationSlider = new Slider(0, 360, 0);
        xRotationSlider.setPrefWidth(300);
        xRotationSlider.setBlockIncrement(30);
        xRotationSlider.setShowTickMarks(true);
        xRotationSlider.setShowTickLabels(true);
        // Ellipse radius along X (try 100..300)
        Slider radiusXSlider = new Slider(100, 300, 100);
        radiusXSlider.setBlockIncrement(10);
        radiusXSlider.setShowTickMarks(true);
        radiusXSlider.setShowTickLabels(true);
        // Ellipse radius along Y (try 100..300)
                Slider radiusYSlider = new Slider(100, 300, 100);
        radiusYSlider.setBlockIncrement(10);
        radiusYSlider.setShowTickMarks(true);
        radiusYSlider.setShowTickLabels(true);
        // Bind ArcTo properties to the control data
        // largeArcFlag: choose the larger (true) or smaller (false) arc between the two possible solutions.
        // sweepFlag: direction of drawing the arc (true = clockwise, false = counter-clockwise).
        // XAxisRotation: rotation of the ellipse’s x-axis in degrees, affecting the arc’s tilt.
        // radiusX / radiusY: radii of the ellipse used to compute the arc.
        arcTo.largeArcFlagProperty().bind(largeArcFlagCbx.selectedProperty());
        arcTo.sweepFlagProperty().bind(sweepFlagCbx.selectedProperty());
        arcTo.XAxisRotationProperty().bind(xRotationSlider.valueProperty());
        arcTo.radiusXProperty().bind(radiusXSlider.valueProperty());
        arcTo.radiusYProperty().bind(radiusYSlider.valueProperty());
        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(10);
        pane.addRow(0, largeArcFlagCbx, sweepFlagCbx);
        pane.addRow(1, new Label("XAxisRotation"), xRotationSlider);
        pane.addRow(2, new Label("radiusX"), radiusXSlider);
        pane.addRow(3, new Label("radiusY"), radiusYSlider);
        return pane;
    }
}
