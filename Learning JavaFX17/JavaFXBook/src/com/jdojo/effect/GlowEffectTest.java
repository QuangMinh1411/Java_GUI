package com.jdojo.effect;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GlowEffectTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Text t1 = createText(new Glow(0.1));
        Text t2 = createText(new Glow(0.3));
        Text t3 = createText(new Glow(0.7));
        Text t4 = createText(new Glow(1.0));

        HBox root = new HBox(
                wrap(t1, 0.1),
                wrap(t2, 0.3),
                wrap(t3, 0.7),
                wrap(t4, 1.0)
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
        stage.setTitle("Using the Glow Effect");
        stage.show();
    }

    private Text createText(Glow glow) {
        Text t = new Text("Glow");
        t.setFont(Font.font(null, FontWeight.BOLD, 36));
        t.setFill(Color.YELLOW);
        t.setEffect(glow);
        return t;
    }

    private VBox wrap(Text t, double level) {
        VBox box = new VBox(new Label("Level: " + level), t);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-background-color: green;" +
                "-fx-border-color: black;");
        return box;
    }
}
