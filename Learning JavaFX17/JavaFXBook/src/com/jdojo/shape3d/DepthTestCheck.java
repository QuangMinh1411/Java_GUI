/*
This JavaFX application demonstrates the concept of depth testing in a 3D scene.
Depth testing determines whether an object is in front of or behind another object from the camera's perspective.

The application sets up the following:
- Two Rectangle objects, one red and one green, are placed in a Group.
- They are positioned in 3D space using translate properties. The green rectangle (Z=300) is placed in front of the red one (Z=400).
- A CheckBox is provided to enable or disable the `DepthTest` property for both rectangles.
- The scene is created with a depth buffer enabled, which is a prerequisite for depth testing.
- A PerspectiveCamera is added to the scene to provide a 3D viewpoint.

Behavior:
- When the "DepthTest for Rectangles" checkbox is checked (default state), `DepthTest` is ENABLED. The rendering engine uses the Z-coordinates to determine which object is closer to the camera. As a result, the green rectangle correctly appears in front of the red rectangle.
- When the checkbox is unchecked, `DepthTest` is DISABLED. The rectangles are rendered in the order they were added to the Group. Since the red rectangle was added after the green one, it is drawn on top of the green one, ignoring their actual 3D positions.
*/
package com.jdojo.shape3d;

import javafx.application.Application;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DepthTestCheck extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        // Create two rectangles and add then to a Group
        Rectangle red = new Rectangle(100, 100);
        red.setFill(Color.RED);
        red.setTranslateX(100);
        red.setTranslateY(100);
        red.setTranslateZ(400);
        Rectangle green = new Rectangle(100, 100);
        green.setFill(Color.GREEN);
        green.setTranslateX(150);
        green.setTranslateY(150);
        green.setTranslateZ(300);
        Group center = new Group(green, red);
        CheckBox depthTestCbx =
                new CheckBox("DepthTest for Rectangles");
        depthTestCbx.setSelected(true);
        depthTestCbx.selectedProperty().addListener(
                (prop, oldValue, newValue) -> {
                    if (newValue) {
                        red.setDepthTest(DepthTest.ENABLE);
                        green.setDepthTest(DepthTest.ENABLE);
                    }
                    else {


                        red.setDepthTest(DepthTest.DISABLE);
                        green.setDepthTest(DepthTest.DISABLE);
                    }
                });
        // Create a BorderPane as the root node for the scene.
        // Need to set the background transparent, so the camera
        // can view the rectangles behind the surface of the
        // BorderPane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent;");
        root.setTop(depthTestCbx);
        root.setCenter(center);
        // Create a scene with depthBuffer enabled
        Scene scene = new Scene(root, 200, 200, true);
        // Need to set a camera to look into the 3D space of
        // the scene
        scene.setCamera(new PerspectiveCamera());
        stage.setScene(scene);
        stage.setTitle("Depth Test");
        stage.show();
    }
}
