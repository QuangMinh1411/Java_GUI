package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleTest extends Application {
    private static final double ARC = 10.0;

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        // x=0, y=0, width=100, height=50, fill=LIGHTGRAY, stroke=null
        Rectangle rect1 = rectangle(0, 0, 100, 50, Color.LIGHTGRAY, null, 0, 0);
        // x=120, y=20, width=100, height=50, fill=WHITE, stroke=BLACK, rounded corners
        Rectangle rect2 = rectangle(120, 20, 100, 50, Color.WHITE, Color.BLACK, ARC, ARC);
        Pane root = new Pane();
        root.getChildren().addAll(rect1, rect2);
        stage.setScene(new Scene(root));
        stage.setTitle("Using Rectangles");
        stage.show();
    }

    private static Rectangle rectangle(double x, double y, double width, double height,
                                       Color fill, Color stroke, double arcWidth, double arcHeight) {
        Rectangle r = new Rectangle(x, y, width, height);
        r.setFill(fill);
        if (stroke != null) {
            r.setStroke(stroke);
        }
        if (arcWidth > 0 || arcHeight > 0) {
            r.setArcWidth(arcWidth);
            r.setArcHeight(arcHeight);
        }
        return r;
    }
}
