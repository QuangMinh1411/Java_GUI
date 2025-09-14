package com.jdojo.effect;

import com.jdojo.util.ResourceUtil;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

/**
 * This JavaFX application demonstrates the use of the {@code ImageInput} effect.
 *
 * The application loads an image from a resource file and uses it to create an
 * {@code ImageInput} effect. This effect essentially makes the image available
 * as an input for other effects.
 *
 * The {@code ImageInput} is then set as the input for a {@code GaussianBlur} effect,
 * creating a chain of effects. The final blurred image effect is applied to a
 * {@code Rectangle} node.
 *
 * If the image file cannot be found, a placeholder with an error message is
 * displayed instead. This example illustrates how to use an image as a graphical
 * input for another effect, which can then be applied to any scene graph node.
 */
public class ImageInputTest extends Application {
    private static final double IMAGE_WIDTH = 100;
    private static final double IMAGE_HEIGHT = 50;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        URL url = ResourceUtil.getResourceURL("picture/randomness.jpg"); //getClass().getClassLoader().getResource(path);

        Node node = null;
        ImageInput imageInputEffect = new ImageInput();
        double requestedWidth = 100;
        double requestedHeight = 50;
        boolean preserveRation = false;
        boolean smooth = true;
        Image image = new Image(url.toExternalForm(),
                requestedWidth,
                requestedHeight,
                preserveRation,
                smooth);
        imageInputEffect.setSource(image);

        node = new Rectangle(100, 50);
        GaussianBlur dsEffect = new GaussianBlur();
        dsEffect.setInput(imageInputEffect);
        node.setEffect(dsEffect);


        HBox root = new HBox(node);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Applying the ImageInput Effect");
        stage.show();
    }
}
