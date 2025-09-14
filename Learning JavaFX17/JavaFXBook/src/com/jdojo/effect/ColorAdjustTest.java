/**
 * This JavaFX application demonstrates the `ColorAdjust` effect.
 * It displays an image and provides sliders to control the hue, saturation,
 * brightness, and contrast of the image.
 *
 * The application loads an image from a file. If the image cannot be found,
 * it displays a placeholder shape with text.
 *
 * A `ColorAdjust` effect is created and applied to the image view.
 *
 * Four sliders are created to control the `hue`, `saturation`, `brightness`,
 * and `contrast` properties of the `ColorAdjust` effect. The sliders are
 * bound to their respective properties on the `ColorAdjust` object, so any
 * change in a slider's value is immediately reflected in the image's appearance.
 *
 * The image is placed in the center of a `BorderPane`, and the sliders are
 * placed at the bottom, creating an interactive tool to explore the `ColorAdjust` effect.
 */
package com.jdojo.effect;

import com.jdojo.util.ResourceUtil;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class ColorAdjustTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ColorAdjust effect = new ColorAdjust();

        Node node = getImageNode();
        node.setEffect(effect);

        GridPane controller = getController(effect);

        BorderPane root = new BorderPane();
        root.setCenter(node);
        root.setBottom(controller);
        root.setStyle("""
                -fx-padding: 10;
                -fx-border-style: solid inside;
                -fx-border-width: 2;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: blue;""");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Applying the ColorAdjust Effect");
        stage.show();
    }

    private Node getImageNode() {
        URL url = ResourceUtil.getResourceURL("picture/randomness.jpg");

        if (url != null) {
            return new ImageView(url.toExternalForm());
        } else {
            System.out.println("Missing image file " + "picture/randomness.jpg");
            return new StackPane(new Rectangle(100, 50, Color.LIGHTGRAY),
                    new Text("Color Adjust"));
        }
    }

    private GridPane getController(ColorAdjust effect) {
        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(10);

        addSlider(pane, "Hue:", effect.hueProperty(), 0);
        addSlider(pane, "Saturation:", effect.saturationProperty(), 1);
        addSlider(pane, "Brightness:", effect.brightnessProperty(), 2);
        addSlider(pane, "Contrast:", effect.contrastProperty(), 3);

        return pane;
    }

    private void addSlider(GridPane pane, String label, DoubleProperty property, int rowIndex) {
        Slider slider = new Slider(-1.0, 1.0, 0.0);
        slider.setPrefWidth(300);
        slider.setMajorTickUnit(0.10);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        property.bind(slider.valueProperty());
        pane.addRow(rowIndex, new Label(label), slider);
    }
}