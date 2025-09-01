package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class ArcTest extends Application {
    private static final double SPACING = 10.0;
    private static final String BORDER_STYLE = """
               -fx-padding: 10;
               -fx-border-style: solid inside;
               -fx-border-width: 2;
               -fx-border-insets: 5;
               -fx-border-radius: 5;
               -fx-border-color: blue;""";

    // Shared geometry for all arcs in this demo
    private static final double CENTER_X = 0;
    private static final double CENTER_Y = 0;
    private static final double RADIUS_X = 50;
    private static final double RADIUS_Y = 100;
    private static final double START_ANGLE = 0;
    private static final double LENGTH = 90;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // An OPEN arc with a fill
        Arc arc1 = arc(Color.LIGHTGRAY, null, null);
        // An OPEN arc with no fill and a stroke
        Arc arc2 = arc(Color.TRANSPARENT, Color.BLACK, null);
        // A CHORD arc with no fill and a stroke
        Arc arc3 = arc(Color.TRANSPARENT, Color.BLACK, ArcType.CHORD);
        // A ROUND arc with no fill and a stroke
        Arc arc4 = arc(Color.TRANSPARENT, Color.BLACK, ArcType.ROUND);
        // A ROUND arc with a gray fill and a stroke
        Arc arc5 = arc(Color.GRAY, Color.BLACK, ArcType.ROUND);

        HBox root = new HBox(arc1, arc2, arc3, arc4, arc5);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using Arcs");
        stage.show();
    }

    private static Arc arc(Color fill, Color stroke, ArcType type) {
        Arc a = new Arc(CENTER_X, CENTER_Y, RADIUS_X, RADIUS_Y, START_ANGLE, LENGTH);
        if (type != null) {
            a.setType(type);
        }
        a.setFill(fill);
        if (stroke != null) {
            a.setStroke(stroke);
        }
        return a;
    }
}
