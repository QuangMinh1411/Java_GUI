package com.jdojo.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Demo: Overlaying nodes in a StackPane (z-order and transparency)
 *
 * What this example shows
 * -----------------------
 * - Several StackPane instances are created to demonstrate how child order and
 *   opacity affect which node appears on top when nodes overlap.
 * - Some panes place the Rectangle under the Text, others place it above; some
 *   Rectangles are semi-transparent so you can see content beneath.
 *
 * Key concepts explained
 * ----------------------
 * 1) Child order controls z-order
 *    - In StackPane, children are painted in the order they are added.
 *      The last added child is on top.
 *    - If you add(rect, text) then text overlays the rectangle; if you add(text, rect)
 *      then the rectangle overlays the text.
 *
 * 2) Transparency (opacity)
 *    - The Rectangle style sets -fx-opacity to either 1.0 (opaque) or 0.5 (semi-transparent).
 *      When semi-transparent, the rectangle on top still lets the underlying text be visible.
 *
 * 3) The createStackPane parameters
 *    - str: the text to display.
 *    - rectOpacity: 1.0 for opaque, 0.5 for translucent (or any value 0..1).
 *    - rectFirst: when true, the Rectangle is added before the Text (Text ends up on top);
 *                 when false, the Rectangle is added last and appears above the Text.
 *
 * Visual cues
 * -----------
 * - Each StackPane has padding and a blue border to make its bounds visible.
 * - Several panes are placed side-by-side in an HBox for easy comparison.
 *
 * Try it
 * ------
 * - Run the app and compare the five StackPanes:
 *   1) Text over opaque Rectangle
 *   2) Opaque Rectangle over Text
 *   3) Semi-transparent Rectangle over Text
 *   4) Opaque Rectangle over a longer Text (may clip/overlap depending on size)
 *   5) Semi-transparent Rectangle over a longer Text
 */
public class StackPaneOverlayTest extends Application {
    public static void main(String[] args) {
       Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        StackPane textOverRect = createStackPane("Hello", 1.0, true);
        StackPane rectOverText = createStackPane("Hello", 1.0, false);
        StackPane transparentRectOverText = createStackPane("Hello", 0.5, false);
        StackPane rectOverBigText = createStackPane("A bigger text", 1.0, false);
        StackPane transparentRectOverBigText =
                createStackPane("A bigger text", 0.5, false);

        // Add all StackPanes to an HBox
        HBox root = new HBox(textOverRect,
                rectOverText,
                transparentRectOverText,
                rectOverBigText,
                transparentRectOverBigText);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Overlaying Rules in StackPane");
        stage.show();
    }

    public StackPane createStackPane(String str, double rectOpacity, boolean rectFirst) {
        Rectangle rect = new Rectangle(60, 50);
        rect.setStyle("-fx-fill: lavender;" + "-fx-opacity: " + rectOpacity + ";");

        Text text = new Text(str);

        // Create a StackPane
        StackPane spane = new StackPane();

        // add the Rectangle before the Text if rectFirst is true.
        // Otherwise add the Text first
        if (rectFirst) {
            spane.getChildren().addAll(rect, text);
        } else {
            spane.getChildren().addAll(text, rect);
        }

        spane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        return spane;
    }
}
