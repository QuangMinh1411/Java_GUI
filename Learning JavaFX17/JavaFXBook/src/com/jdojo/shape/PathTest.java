package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class PathTest extends Application {
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
        Path triangle = path(
                new MoveTo(0, 0),
                new LineTo(0, 50),
                new LineTo(50, 50),
                new ClosePath());
        Path star = new Path();
        star.getElements().addAll(
                new MoveTo(30, 0),
                new LineTo(0, 30),
                new LineTo(60, 30),
                new ClosePath(),/* new LineTo(30, 0), */
                new MoveTo(0, 10),
                new LineTo(60, 10),
                new LineTo(30, 40),
                new ClosePath() /*new LineTo(0, 10)*/);
        HBox root = new HBox(triangle, star);
        root.setSpacing(SPACING);
        root.setStyle(BORDER_STYLE);
        stage.setScene(new Scene(root));
        stage.setTitle("Using Paths");
        stage.show();
    }

    private static Path path(javafx.scene.shape.PathElement... elements) {
        Path p = new Path();
        p.getElements().addAll(elements);
        return p;
    }
}
