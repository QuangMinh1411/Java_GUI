package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShapeTest extends Application {
    // Demo app: shows two circles side-by-side to illustrate fill and stroke in JavaFX.
    // Constants to avoid magic numbers and improve readability
    private static final double RADIUS = 40.0;        // Circle radius in pixels
    private static final double CENTER = 40.0;        // Both circles centered at (40, 40)
    private static final double STROKE_WIDTH = 2.0;   // Width of the stroked circle's outline
    private static final double SPACING = 10.0;       // Horizontal gap between circles in the HBox

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Circle c1 = createCircle(Color.LIGHTGRAY, null, 0);
        Circle c2 = createCircle(Color.YELLOW, Color.BLACK, STROKE_WIDTH);

        HBox root = new HBox(c1, c2);
        root.setSpacing(SPACING);
        root.setStyle("""
               -fx-padding: 10;
               -fx-border-style: solid inside;
               -fx-border-width: 2;
               -fx-border-insets: 5;
               -fx-border-radius: 5;
               -fx-border-color: blue;""");

        stage.setScene(new Scene(root));
        stage.setTitle("Using Shapes");
        stage.show();
    }

    /**
     * Creates a Circle with common defaults and optional stroke.
     *
     * Explanation:
     * - The circle is centered at (CENTER, CENTER) with radius RADIUS.
     * - 'fill' sets the interior color of the circle.
     * - If 'stroke' is non-null and 'strokeWidth' > 0, an outline is applied.
     *   Otherwise, the circle has no stroke (outline).
     */
    private static Circle createCircle(Color fill, Color stroke, double strokeWidth) {
        Circle circle = new Circle(CENTER, CENTER, RADIUS);
        circle.setFill(fill);
        if (stroke != null && strokeWidth > 0) {
            circle.setStroke(stroke);
            circle.setStrokeWidth(strokeWidth);
        }
        return circle;
    }
}
