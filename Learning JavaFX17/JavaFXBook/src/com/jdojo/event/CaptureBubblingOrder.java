package com.jdojo.event;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

/**
 * Demonstrates JavaFX event dispatch order for the capture (filter) and
 * bubbling (handler) phases when a mouse is clicked on different nodes.
 *
 * Summary
 * - JavaFX delivers an input event in two phases:
 *   1) Capture phase: travels from the top of the scene graph down to the target node.
 *      In this phase, EventFilters run (addEventFilter(...)).
 *   2) Bubbling phase: travels from the target node back up to the top.
 *      In this phase, EventHandlers run (addEventHandler(...)).
 * - This example registers filters and handlers on Stage, Scene, HBox (root), and Circle.
 *   It intentionally does not register any on the Rectangle to highlight the difference.
 * - The console output shows the phase, event type, target, source (where the
 *   listener is registered), and the mouse coordinates relative to the source.
 *
 * Try it
 * - Click the Circle:
 *   Capture (filters): Stage -> Scene -> HBox -> Circle
 *   Bubbling (handlers): Circle -> HBox -> Scene -> Stage
 * - Click the Rectangle:
 *   Capture (filters): Stage -> Scene -> HBox  (no filter on Rectangle)
 *   Bubbling (handlers): HBox -> Scene -> Stage (no handler on Rectangle)
 *
 * Notes
 * - e.getTarget() is the deepest node that actually received the event
 *   (e.g., Circle or Rectangle shape).
 * - e.getSource() is the object where the listener is registered (e.g., Stage,
 *   Scene, HBox, or Circle). These will differ for most of the outputs.
 * - e.getX()/e.getY() are the mouse coordinates in the coordinate space of the
 *   source (not the target). They will change as the event is delivered to
 *   different sources along the route.
 */
public class CaptureBubblingOrder extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create two shapes laid out horizontally inside an HBox
        Circle circle = new Circle(50, 50, 50); // centerX=50, centerY=50, radius=50
        circle.setFill(Color.CORAL);

        Rectangle rect = new Rectangle(100, 100); // width=100, height=100
        rect.setFill(Color.TAN);

        // HBox root container that holds the circle and rectangle
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(circle, rect);

        // Scene hosts the root; Stage will host the Scene
        Scene scene = new Scene(root);

        // Create two listeners: one for capture (filter), one for bubbling (handler)
        EventHandler<MouseEvent> filter = e -> handleEvent("Capture", e);
        EventHandler<MouseEvent> handler = e -> handleEvent("Bubbling", e);

        // Register filters (capture phase order: Stage -> Scene -> HBox -> target child)
        stage.addEventFilter(MOUSE_CLICKED, filter);
        scene.addEventFilter(MOUSE_CLICKED, filter);
        root.addEventFilter(MOUSE_CLICKED, filter);
        // Intentionally register on Circle but NOT on Rectangle to demonstrate differences
        circle.addEventFilter(MOUSE_CLICKED, filter);

        // Register handlers (bubbling phase order: target child -> HBox -> Scene -> Stage)
        stage.addEventHandler(MOUSE_CLICKED, handler);
        scene.addEventHandler(MOUSE_CLICKED, handler);
        root.addEventHandler(MOUSE_CLICKED, handler);
        // Intentionally register on Circle but NOT on Rectangle
        circle.addEventHandler(MOUSE_CLICKED, handler);

        stage.setScene(scene);
        stage.setTitle("Event Capture and Bubbling Execution Order");
        stage.show();
    }

    /**
     * Logs the current phase, event type, target node, source (listener owner),
     * and mouse coordinates relative to the source where this listener is registered.
     */
    public void handleEvent(String phase, MouseEvent e) {
        String type = e.getEventType().getName();
        String source = e.getSource().getClass().getSimpleName();
        String target = e.getTarget().getClass().getSimpleName();

        // Coordinates of the mouse cursor in the coordinate space of the source
        double x = e.getX();
        double y = e.getY();

        System.out.println(phase + ": Type=" + type +
                ", Target=" + target + ", Source=" + source +
                ", location(" + x + ", " + y + ")");
    }
}
