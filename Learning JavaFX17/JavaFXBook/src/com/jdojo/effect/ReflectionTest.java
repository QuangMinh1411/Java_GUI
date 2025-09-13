package com.jdojo.effect;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReflectionTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // A default reflection
        Reflection r1 = new Reflection();
        Text t1 = createText(r1);

        // A reflection with a top offset
        Reflection r2 = new Reflection();
        r2.setTopOffset(10);
        Text t2 = createText(r2);

        // A reflection with a smaller fraction
        Reflection r3 = new Reflection();
        r3.setFraction(0.5);
        Text t3 = createText(r3);
        
        // A reflection with different top and bottom opacities
        Reflection r4 = new Reflection();
        r4.setTopOpacity(0.8);
        r4.setBottomOpacity(0.1);
        Text t4 = createText(r4);

        HBox root = new HBox(
            wrap(t1, "Default"),
            wrap(t2, "topOffset: 10"),
            wrap(t3, "fraction: 0.5"),
            wrap(t4, "topOpacity: 0.8\nbottomOpacity: 0.1")
        );
        root.setSpacing(20);
        root.setStyle("-fx-padding: 10;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using the Reflection Effect");
        stage.show();
    }

    private Text createText(Reflection r) {
        Text t = new Text("Reflection");
        t.setFont(Font.font(null, FontWeight.BOLD, 36));
        t.setEffect(r);
        return t;
    }

    private VBox wrap(Text t, String description) {
        VBox box = new VBox(new Label(description), t);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setStyle("-fx-padding: 10;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 1;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: black;");
        return box;
    }
}
