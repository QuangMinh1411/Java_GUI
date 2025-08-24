package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * A simple JavaFX demo that uses three Sliders to control the RGB components
 * of a Rectangle's fill color.
 *
 * How it works:
 * - Each Slider represents one color channel (Red, Green, Blue) with a range 0–255.
 * - When any slider value changes, a listener calls changeColor(), which reads
 *   the current R, G, B values, constructs a Color using Color.rgb(r, g, b),
 *   and updates the Rectangle's fill.
 * - The UI is laid out using a GridPane and given a visible border via inline CSS.
 */
public class SliderTest extends Application {
    // A rectangle whose color we will change using the sliders
    Rectangle rect = new Rectangle(0, 0, 200, 50);

    // Three sliders (R, G, B). getSlider() returns an identically configured slider
    // with range [0, 255], tick marks/labels, and snap-to-ticks enabled.
    Slider redSlider = getSlider();
    Slider greenSlider = getSlider();
    Slider blueSlider = getSlider();

    public static void main(String[] args) {
        // Launch the JavaFX application lifecycle
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Add one ChangeListener handler method to all three sliders
        redSlider.valueProperty().addListener(this::changed);
        greenSlider.valueProperty().addListener(this::changed);
        blueSlider.valueProperty().addListener(this::changed);

        // Build the UI: a GridPane with the rectangle, instruction label, and sliders
        GridPane root = new GridPane();
        root.setVgap(10); // vertical spacing between rows
        root.add(rect, 0, 0, 2, 1); // rectangle spans two columns
        root.add(new Label("Use sliders to change the fill color"), 0, 1, 2, 1);
        root.addRow(2, new Label("Red:"), redSlider);
        root.addRow(3, new Label("Green:"), greenSlider);
        root.addRow(4, new Label("Blue:"), blueSlider);

        // Simple inline CSS so the container padding and border are visible
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Standard scene/stage setup
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using Slider Controls");
        stage.show();

        // Initialize the rectangle color based on current slider positions
        changeColor();
    }

    /**
     * Creates a slider configured for 0–255 values with ticks and snapping.
     *
     * Details:
     * - Major tick unit 85 divides the range roughly into three sections.
     * - Minor tick count 10 shows smaller ticks between major ones.
     * - blockIncrement controls keyboard/page increment.
     * - snapToTicks makes the thumb snap to the nearest tick.
     */
    public Slider getSlider() {
        Slider slider = new Slider(0, 255, 125);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(85);
        slider.setMinorTickCount(10);
        slider.setBlockIncrement(20);
        slider.setSnapToTicks(true);
        return slider;
    }

    // Change listener invoked when any slider value changes
    public void changed(ObservableValue<? extends Number> prop,
                        Number oldValue,
                        Number newValue) {
        changeColor();
    }

    /**
     * Reads the current R, G, B values from the sliders, converts them to ints,
     * creates a Color, and applies it to the rectangle's fill.
     */
    public void changeColor() {
        int r = (int) redSlider.getValue();
        int g = (int) greenSlider.getValue();
        int b = (int) blueSlider.getValue();
        Color fillColor = Color.rgb(r, g, b);
        rect.setFill(fillColor);
    }
}
