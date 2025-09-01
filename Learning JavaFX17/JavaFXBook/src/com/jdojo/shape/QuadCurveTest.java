package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

public class QuadCurveTest extends Application {
    private static final double SPACING = 10.0;
    private static final String BORDER_STYLE = """
               -fx-padding: 10;
               -fx-border-style: solid inside;
               -fx-border-width: 2;
               -fx-border-insets: 5;
               -fx-border-radius: 5;
               -fx-border-color: blue;""";

    // Shared geometry for the two curves
    private static final double START_X = 0;
    private static final double START_Y = 100;
    private static final double CONTROL_X = 20;
    private static final double CONTROL_Y = 0;
    private static final double END_X = 150;
    private static final double END_Y = 100;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        QuadCurve qc1 = quadCurve(Color.TRANSPARENT, Color.BLACK);
        QuadCurve qc2 = quadCurve(Color.LIGHTGRAY, null);
        HBox root = new HBox(qc1, qc2);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using QuadCurves");
        stage.show();
    }

    private static QuadCurve quadCurve(Color fill, Color stroke) {
        QuadCurve q = new QuadCurve(START_X, START_Y, CONTROL_X, CONTROL_Y, END_X, END_Y);
        q.setFill(fill);
        if (stroke != null) {
            q.setStroke(stroke);
        }
        return q;
    }
}
