package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleTest extends Application {
    private static final double SPACING = 10.0;
    private static final String BORDER_STYLE = """
               -fx-padding: 10;
               -fx-border-style: solid inside;
               -fx-border-width: 2;
               -fx-border-insets: 5;
               -fx-border-radius: 5;
               -fx-border-color: blue;""";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Circle c1 = circle(Color.LIGHTGRAY, null, 0.0, 0, 0, 40);
        Circle c2 = circle(Color.YELLOW, Color.BLACK, 2.0, 10, 10, 40);
        HBox root = new HBox(c1, c2);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using Circle");
        stage.show();
    }

    private static Circle circle(Color fill, Color stroke, double strokeWidth, double centerX, double centerY, double radius) {
        Circle c = new Circle(centerX, centerY, radius);
        c.setFill(fill);
        if (stroke != null && strokeWidth > 0) {
            c.setStroke(stroke);
            c.setStrokeWidth(strokeWidth);
        }
        return c;
    }
}
