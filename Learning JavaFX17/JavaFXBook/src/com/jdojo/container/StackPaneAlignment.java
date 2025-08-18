package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Demo: Aligning children in a StackPane (Pos)
 *
 * What this example shows
 * -----------------------
 * - Five StackPane instances are created, each configured with a different
 *   alignment value: TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, and CENTER.
 * - Each StackPane contains two children: a lavender Rectangle (80x50) and a
 *   Text node showing the alignment value.
 * - The panes are placed side-by-side inside an HBox with 10px spacing so you
 *   can visually compare where the children end up within each StackPane.
 *
 * Key concepts explained
 * ----------------------
 * 1) StackPane alignment
 *    - setAlignment(Pos) controls where the StackPane positions its children
 *      within its own content area when extra space is available.
 *    - For example, Pos.TOP_LEFT places children at the top-left corner; CENTER
 *      centers them. Since both children are stacked on top of each other, they
 *      share the same alignment reference point.
 *
 * 2) Child order and stacking
 *    - By passing (rect, text) to the StackPane constructor, the rectangle is
 *      added first and the text is added last; the last added child is painted
 *      on top, so the Text appears above the Rectangle.
 *
 * 3) Visual cues
 *    - Inline CSS adds padding and a blue border to each StackPane, making the
 *      bounds and alignment effects easy to see.
 *
 * Try it
 * ------
 * - Run the app and compare the five StackPanes in the HBox.
 *   Notice how the same rectangle/text pair is positioned differently depending
 *   on the Pos alignment set on each StackPane.
 */
public class StackPaneAlignment extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        StackPane topLeft = createStackPane(Pos.TOP_LEFT);
        StackPane topRight = createStackPane(Pos.TOP_RIGHT);
        StackPane bottomLeft = createStackPane(Pos.BOTTOM_LEFT);
        StackPane bottomRight = createStackPane(Pos.BOTTOM_RIGHT);
        StackPane center = createStackPane(Pos.CENTER);

        double spacing = 10.0;
        HBox root = new HBox(spacing,
                topLeft,
                topRight,
                bottomLeft,
                bottomRight,
                center);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using StackPane");
        stage.show();
    }

    public StackPane createStackPane(Pos alignment) {
        Rectangle rect = new Rectangle(80, 50);
        rect.setFill(Color.LAVENDER);

        Text text = new Text(alignment.toString());
        text.setStyle("-fx-font-size: 7pt;");

        StackPane spane = new StackPane(rect, text);
        spane.setAlignment(alignment);
        spane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        return spane;
    }
}
