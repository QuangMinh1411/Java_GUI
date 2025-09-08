/*
This JavaFX application demonstrates the use of a camera in a 3D scene and how to animate it.

The application sets up the following:
- A `Box` shape is created and positioned in the 3D space. Its `cullFace` property is set to `NONE`,
  which means even the back faces of the box will be rendered.
- A `PerspectiveCamera` is created and positioned in the scene. This camera provides a 3D perspective view.
- A `RotateTransition` is applied to the camera, causing it to animate. The camera rotates around the X-axis
  from 0 to 90 degrees and back, indefinitely. This animation gives the viewer a changing perspective of the scene.
- Two `PointLight` sources (one red, one green) are added to illuminate the box from different positions.
- The box and the lights are added to a `Group`. The entire group is also rotated by 30 degrees around the X-axis.
- The scene is created with a depth buffer, and the animated camera is set as the scene's camera.

The result is a 3D box that appears to be viewed from a moving camera, with colored lighting highlighting its faces.
*/
package com.jdojo.shape3d;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CameraTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        Box box = new Box(100, 100, 100);
        box.setCullFace(CullFace.NONE);
        box.setTranslateX(250);
        box.setTranslateY(100);
        box.setTranslateZ(400);

        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(100);
        camera.setTranslateY(-50);
        camera.setTranslateZ(300);

        // Add a Rotation animation to the camera
        RotateTransition rt = new RotateTransition(Duration.seconds(2), camera);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setFromAngle(0);
        rt.setToAngle(90);
        rt.setAutoReverse(true);
        rt.setAxis(Rotate.X_AXIS);
        rt.play();

        PointLight redLight = new PointLight();
        redLight.setColor(Color.RED);
        redLight.setTranslateX(250);
        redLight.setTranslateY(-100);
        redLight.setTranslateZ(250);

        PointLight greenLight = new PointLight();
        greenLight.setColor(Color.GREEN);
        greenLight.setTranslateX(250);
        greenLight.setTranslateY(300);
        greenLight.setTranslateZ(300);

        Group root = new Group(box, redLight, greenLight);
        root.setRotationAxis(Rotate.X_AXIS);
        root.setRotate(30);

        Scene scene = new Scene(root, 500, 300, true);
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.setTitle("Using camaras");
        stage.show();
    }
}
