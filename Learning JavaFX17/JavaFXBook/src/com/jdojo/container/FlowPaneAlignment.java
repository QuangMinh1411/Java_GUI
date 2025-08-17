package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Demo: FlowPane alignment basics (Pos)
 *
 * What this example shows
 * -----------------------
 * - Three FlowPane instances are created with different alignment values:
 *   BOTTOM_RIGHT, BOTTOM_LEFT, and CENTER.
 * - Each FlowPane uses 5px horizontal and vertical gaps and a 200x100 preferred size.
 * - A small set of child nodes (a Text header plus three Buttons) are added.
 * - Each FlowPane has a visible border and padding to make its bounds obvious.
 * - The three panes are placed side-by-side inside an HBox to compare alignments.
 *
 * Key concepts explained
 * ----------------------
 * 1) FlowPane alignment
 *    - alignment controls where the content block is positioned within the pane’s
 *      available area when there is extra space.
 *    - For example, BOTTOM_RIGHT pushes nodes toward the bottom-right corner of
 *      the FlowPane; CENTER centers the whole content.
 *
 * 2) Preferred size and gaps
 *    - setPrefSize(200, 100) gives the FlowPane a target size, so extra space is
 *      available to visualize how alignment shifts content within that area.
 *    - The constructor FlowPane(5, 5) sets hgap and vgap to 5px between children.
 *
 * 3) Styling for visualization
 *    - Inline CSS adds padding and a blue border so you can clearly see the pane
 *      boundaries and how children are positioned inside.
 *
 * Try it
 * ------
 * - Run the app and compare the three panes: observe how the same set of nodes
 *   sit in different positions depending on the specified Pos alignment.
 */
public class FlowPaneAlignment extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        FlowPane fp1 = createFlowPane(Pos.BOTTOM_RIGHT);
        FlowPane fp2 = createFlowPane(Pos.BOTTOM_LEFT);
        FlowPane fp3 = createFlowPane(Pos.CENTER);

        HBox root = new HBox(fp1, fp2, fp3);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FlowPane Alignment");
        stage.show();
    }

    private FlowPane createFlowPane(Pos alignment) {
        FlowPane fp = new FlowPane(5, 5);
        fp.setPrefSize(200, 100);
        fp.setAlignment(alignment);
        fp.getChildren().addAll(new Text(alignment.toString()),
                new Button("Button 1"),
                new Button("Button 2"),
                new Button("Button 3"));

        fp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        return fp;
    }
}
