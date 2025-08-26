package com.jdojo.control;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * An application demonstrating the JavaFX ToolBar control.
 *
 * This refactored version improves upon the original by:
 * 1. Using a type-safe enum (ShapeType) instead of Strings for drawing commands.
 * 2. Encapsulating the ToolBar creation logic into a private helper method.
 * 3. Making the drawing logic dynamic by using the canvas's current width and height,
 *    which allows shapes to scale correctly if the window is resized.
 * 4. Improving code clarity with a switch statement and better documentation.
 */
public class ToolBarTest extends Application {
    // A private enum to represent the shapes that can be drawn.
    // This is more type-safe and readable than using simple strings.
    private enum ShapeType {
        RECTANGLE, CIRCLE, ELLIPSE
    }

    // The canvas where shapes will be drawn.
    private final Canvas canvas = new Canvas(200, 200);

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create the toolbar using a helper method to keep the start() method clean.
        ToolBar toolBar = createToolBar();

        // A label to provide instructions to the user.
        Label instructions = new Label("Click a shape to draw.");

        // Use a VBox to stack the instructions label and the toolbar vertically.
        VBox topContainer = new VBox(instructions, toolBar);

        // The main layout is a BorderPane.
        BorderPane root = new BorderPane();
        root.setTop(topContainer);
        root.setCenter(canvas);

        // It's generally better to use an external CSS file for styling,
        // but inline styles are used here for simplicity.
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the scene and set it on the stage.
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using ToolBar Controls");
        stage.show();
    }

    /**
     * Creates and configures the ToolBar with shape buttons and an exit button.
     * This method encapsulates the creation of the toolbar and its controls.
     *
     * @return A fully configured ToolBar.
     */
    private ToolBar createToolBar() {
        // Create buttons with icons representing the shapes.
        Button rectBtn = new Button("", new Rectangle(0, 0, 16, 16));
        Button circleBtn = new Button("", new Circle(0, 0, 8));
        Button ellipseBtn = new Button("", new Ellipse(8, 8, 8, 6));
        Button exitBtn = new Button("Exit");

        // Set tooltips to provide hints to the user.
        rectBtn.setTooltip(new Tooltip("Draws a rectangle"));
        circleBtn.setTooltip(new Tooltip("Draws a circle"));
        ellipseBtn.setTooltip(new Tooltip("Draws an ellipse"));
        exitBtn.setTooltip(new Tooltip("Exits the application"));

        // Set action handlers for each button.
        // This now calls the draw() method with a type-safe enum constant.
        rectBtn.setOnAction(e -> draw(ShapeType.RECTANGLE));
        circleBtn.setOnAction(e -> draw(ShapeType.CIRCLE));
        ellipseBtn.setOnAction(e -> draw(ShapeType.ELLIPSE));
        exitBtn.setOnAction(e -> Platform.exit());

        // Create the ToolBar and add the buttons, separated by a Separator.
        return new ToolBar(rectBtn, circleBtn, ellipseBtn, new Separator(), exitBtn);
    }

    /**
     * Clears the canvas and draws the specified shape.
     * This method uses the canvas's current dimensions to draw, making it responsive
     * to window resizing.
     *
     * @param shapeType The enum constant representing the shape to draw.
     */
    private void draw(ShapeType shapeType) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        // Clear the entire canvas before drawing a new shape.
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.TAN);

        // Use a switch statement on the enum for clarity and type-safety.
        switch (shapeType) {
            case RECTANGLE:
                // Fill the entire canvas.
                gc.fillRect(0, 0, width, height);
                break;
            case CIRCLE:
                // Draw a circle that fits within the smaller of the canvas's width or height.
                double radius = Math.min(width, height);
                gc.fillOval(0, 0, radius, radius);
                break;
            case ELLIPSE:
                // Draw an ellipse that is proportional to the canvas size.
                gc.fillOval(width * 0.1, height * 0.2, width * 0.8, height * 0.6);
                break;
        }
    }
}
