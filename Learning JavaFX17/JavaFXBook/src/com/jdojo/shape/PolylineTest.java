package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class PolylineTest extends Application {
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
        Polyline triangle1 = polyline(Color.WHITE, Color.RED,
                50.0, 0.0,
                0.0, 50.0,
                100.0, 50.0,
                50.0, 0.0);
        // Create an open parallelogram
        Polyline parallelogram = polyline(Color.YELLOW, Color.BLACK,
                30.0, 0.0,
                130.0, 0.0,
                100.00, 50.0,
                0.0, 50.0);
        Polyline hexagon = polyline(Color.WHITE, Color.BLACK,
                100.0, 0.0,
                120.0, 20.0,
                120.0, 40.0,
                100.0, 60.0,
                80.0, 40.0,
                80.0, 20.0,
                100.0, 0.0);

        HBox root = new HBox(triangle1, parallelogram, hexagon);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using Polylines");
        stage.show();
    }

    private static Polyline polyline(Color fill, Color stroke, Double... points) {
        Polyline p = new Polyline();
        p.getPoints().addAll(points);
        p.setFill(fill);
        p.setStroke(stroke);
        return p;
    }
}
