package com.jdojo.effect;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SepiaToneTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create a Text node with SepiaTone level 0.5
        Text t1 = createText(new SepiaTone(0.5));
        StackPane stack1 = createStackPane(t1);

        // Create a Text node with SepiaTone level 1.0
        Text t2 = createText(new SepiaTone(1.0));
        StackPane stack2 = createStackPane(t2);

        HBox root = new HBox(
            wrap(stack1, 0.5),
            wrap(stack2, 1.0)
        );
        root.setSpacing(20);
        root.setStyle("-fx-padding: 10;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: blue;");

        Scene scene = new Scene(root, 650, 250);
        stage.setScene(scene);
        stage.setTitle("Using the SepiaTone Effect");
        stage.show();
    }

    private Text createText(SepiaTone st) {
        Text t = new Text("Sepia Tone");
        t.setFont(Font.font(null, FontWeight.BOLD, 36));
        t.setFill(Color.WHITE);
        t.setEffect(st);
        return t;
    }

    private StackPane createStackPane(Text t) {
        Rectangle rect = new Rectangle(250, 120, Color.BLACK);
        StackPane stack = new StackPane(rect, t);
        return stack;
    }

    private VBox wrap(StackPane s, double level) {
        VBox box = new VBox(new Label("Level: " + level), s);
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
