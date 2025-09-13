/**
 * This JavaFX application demonstrates the use of the DisplacementMap effect.
 * It programmatically creates a FloatMap that defines displacement data.
 * The FloatMap is designed to create a horizontal shearing effect:
 * - The top half of the map contains negative displacement values, which shifts the corresponding pixels of the node to the left.
 * - The bottom half of the map contains positive displacement values, which shifts the corresponding pixels of the node to the right.
 * - The vertical displacement is set to zero, so the effect is purely horizontal.
 * This DisplacementMap is then applied to a Text node, visually splitting and shifting it horizontally.
 */
package com.jdojo.effect;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplacementmapTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
// Create a FloatMap
        int width = 250;
        int height = 50;
        FloatMap map = new FloatMap(width, height);

        double xDisplacement = 1.0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double u = xDisplacement;
                if (j < height / 2) {
                    // Move the top-half pixels to the left (a negative value)
                    u = -1.0 * (u * xDisplacement / width);
                }
                else {
                    // Move the bottom-half pixels to the right (a positive value)
                    u = u * xDisplacement / width;
                }

                // Set values for band 0 and 1 (x and y axes displaments factors).
                // Always use 0.0f for y-axis displacement factor.
                map.setSamples(i, j, (float)u, 0.0f);
            }
        }

        Text t1 = new Text("Displaced Text");
        t1.setFont(Font.font(36));

        DisplacementMap effect1 = new DisplacementMap();
        effect1.setMapData(map);
        t1.setEffect(effect1);

        HBox root = new HBox(t1);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Applying the DisplacementMap Effect");
        stage.show();
    }
}
