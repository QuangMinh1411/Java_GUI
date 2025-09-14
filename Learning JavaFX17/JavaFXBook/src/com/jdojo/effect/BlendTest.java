/**
 * This JavaFX application provides an interactive demonstration of the {@code Blend} effect.
 *
 * The application sets up two overlapping colored rectangles using {@code ColorInput} effects:
 * a light green one as the "top" input and a purple one as the "bottom" input.
 *
 * A {@code Blend} effect is created to combine these two inputs. The result of the blend
 * is then applied to a {@code Rectangle} node, making the visual outcome visible.
 *
 * The application includes interactive controls to explore the {@code Blend} effect's properties:
 * - A {@code ComboBox} allows the user to select any of the available {@code BlendMode} values
 *   (e.g., ADD, MULTIPLY, SCREEN), dynamically changing how the two colors are combined.
 * - A {@code Slider} controls the {@code opacity} of the top input, adjusting its influence
 *   on the final blended result.
 *
 * This setup provides a clear, hands-on way to understand how different blend modes and
 * opacity levels affect the combination of two graphical inputs.
 */
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlendTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ColorInput topInput = new ColorInput(0, 0, 100, 50, Color.LIGHTGREEN);
        ColorInput bottomInput = new ColorInput(50, 25, 100, 50, Color.PURPLE);

        Blend effect = new Blend();
        effect.setTopInput(topInput);
        effect.setBottomInput(bottomInput);

        stage.setScene(new Scene(createRoot(effect)));
        stage.setTitle("Applying the Blend Effect");
        stage.show();
    }

    private Parent createRoot(Blend effect) {
        Rectangle rect = new Rectangle(150, 75);
        rect.setEffect(effect);

        GridPane controller = this.getController(effect);

        HBox root = new HBox(30, rect, controller);
        root.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: blue;""");
        return root;
    }

    private GridPane getController(Blend effect) {
        ComboBox<BlendMode> blendModeList = new ComboBox<>();
        blendModeList.setValue(effect.getMode());
        blendModeList.getItems().addAll(BlendMode.values());
        effect.modeProperty().bind(blendModeList.valueProperty());

        Slider opacitySlider = new Slider(0, 1.0, 1.0);
        opacitySlider.setMajorTickUnit(0.10);
        opacitySlider.setShowTickMarks(true);
        opacitySlider.setShowTickLabels(true);
        effect.opacityProperty().bind(opacitySlider.valueProperty());

        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(10);
        pane.addRow(0, new Label("Blend Mode:"), blendModeList);
        pane.addRow(1, new Label("Opacity:"), opacitySlider);

        return pane;
    }
}
