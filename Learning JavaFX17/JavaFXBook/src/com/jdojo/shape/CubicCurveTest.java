package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class CubicCurveTest extends Application {
    private static final double SPACING = 10.0;
    private static final String BORDER_STYLE = """
               -fx-padding: 10;
               -fx-border-style: solid inside;
               -fx-border-width: 2;
               -fx-border-insets: 5;
               -fx-border-radius: 5;
               -fx-border-color: blue;""";

    // Shared geometry for both cubic curves
    private static final double START_X = 0;
    private static final double START_Y = 50;
    private static final double CONTROL1_X = 20;
    private static final double CONTROL1_Y = 0;
    private static final double CONTROL2_X = 50;
    private static final double CONTROL2_Y = 80;
    private static final double END_X = 50;
    private static final double END_Y = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CubicCurve cc1 = cubicCurve(Color.TRANSPARENT, Color.BLACK);
        CubicCurve cc2 = cubicCurve(Color.LIGHTGRAY, null);
        HBox root = new HBox(cc1, cc2);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using CubicCurves");
        stage.show();
    }

    private static CubicCurve cubicCurve(Color fill, Color stroke) {
        CubicCurve c = new CubicCurve(START_X, START_Y, CONTROL1_X, CONTROL1_Y, CONTROL2_X, CONTROL2_Y, END_X, END_Y);
        c.setFill(fill);
        if (stroke != null) {
            c.setStroke(stroke);
        }
        return c;
    }
}
