package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class EllipseTest extends Application {
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
        Ellipse e1 = ellipse(Color.LIGHTGRAY, null, 0, 50, 30);
        Ellipse e2 = ellipse(Color.YELLOW, Color.BLACK, 2.0, 60, 30);
        // Draw a circle using the Ellipse class (radiusX=radiusY=30)
        Ellipse e3 = ellipse(Color.YELLOW, Color.BLACK, 2.0, 30, 30);

        HBox root = new HBox(e1, e2, e3);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using Ellipses");
        stage.show();
    }

    private static Ellipse ellipse(Color fill, Color stroke, double strokeWidth, double radiusX, double radiusY) {
        Ellipse e = new Ellipse(radiusX, radiusY);
        e.setFill(fill);
        if (stroke != null && strokeWidth > 0) {
            e.setStroke(stroke);
            e.setStrokeWidth(strokeWidth);
        }
        return e;
    }
}
