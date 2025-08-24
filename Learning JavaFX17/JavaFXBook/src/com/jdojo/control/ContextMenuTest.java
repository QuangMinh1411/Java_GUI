package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ContextMenuTest extends Application {
    // Use a single Canvas and a single ContextMenu instance.
    // FIX EXPLANATION:
    // - Previously, a new ContextMenu was created on every right click inside showContextMenu(MouseEvent).
    //   That leads to unnecessary object creation and does not handle platform-specific ways to open a context menu
    //   (e.g., keyboard key on Windows or Ctrl-click on macOS). It also ties the menu logic to mouse clicks only.
    // - We now create one ContextMenu, wire its menu items once, and show it using the standard
    //   setOnContextMenuRequested handler. This makes the behavior consistent across platforms and reduces allocations.
    // - Additionally, we use the Canvas's current width/height instead of hardcoded values when clearing/drawing.
    Canvas canvas = new Canvas(200, 200);
    private ContextMenu ctxMenu;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Initialize a reusable context menu and its items once
        MenuItem rectItem = new MenuItem("Rectangle");
        MenuItem circleItem = new MenuItem("Circle");
        MenuItem ellipseItem = new MenuItem("Ellipse");
        rectItem.setOnAction(e -> draw("Rectangle"));
        circleItem.setOnAction(e -> draw("Circle"));
        ellipseItem.setOnAction(e -> draw("Ellipse"));
        ctxMenu = new ContextMenu(rectItem, circleItem, ellipseItem);

        // Use the platform-standard context menu requested event rather than raw mouse clicks
        canvas.setOnContextMenuRequested(this::showContextMenu);

        BorderPane root = new BorderPane();
        root.setTop(new Label("Right click below (or use your OS shortcut) to display a context menu."));
        root.setCenter(canvas);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using Context Menus");
        stage.show();
    }

    // Show the reusable context menu at the requested screen coordinates
    public void showContextMenu(ContextMenuEvent e) {
        ctxMenu.show(canvas, e.getScreenX(), e.getScreenY());
    }

    public void draw(String shapeType) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc.clearRect(0, 0, w, h); // clear the canvas first using dynamic size
        gc.setFill(Color.TAN);

        switch (shapeType) {
            case "Rectangle":
                gc.fillRect(0, 0, w, h);
                break;
            case "Circle":
                // Draw a circle that fits inside the current canvas bounds
                double d = Math.min(w, h);
                gc.fillOval((w - d) / 2, (h - d) / 2, d, d);
                break;
            case "Ellipse":
                // Draw an ellipse with some padding relative to current size
                double padX = w * 0.05;
                double padY = h * 0.05;
                gc.fillOval(padX, padY, w - 2 * padX, h - 2 * padY);
                break;
            default:
                // no-op
        }
    }
}
