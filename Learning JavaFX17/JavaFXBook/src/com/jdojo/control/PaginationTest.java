package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A minimal JavaFX demo showing how to use the Pagination control with
 * a page factory. The factory lazily creates content for a page index
 * on demand and may return null for invalid indexes.
 *
 * Key points:
 * - Pagination(int pageCount) configures how many pages the control has.
 * - setPageFactory(callback) supplies a function that builds a Node for a
 *   given page index; returning null tells Pagination that the page does not
 *   exist (it will show an empty placeholder).
 * - This example creates a simple Label per page and styles the container
 *   with some inline CSS so the control boundary is visible.
 */
public class PaginationTest extends Application {
    // Total number of pages shown by the Pagination control
    private static final int PAGE_COUNT = 5;

    public static void main(String[] args) {
        // Starts the JavaFX application lifecycle and calls start(Stage)
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create a Pagination with a fixed number of pages
        Pagination pagination = new Pagination(PAGE_COUNT);

        // Provide a page factory: for a given page index, return the Node to display
        // The factory is called lazily when the user navigates to a page
        pagination.setPageFactory(this::getPage);

        // Put the Pagination in a simple VBox and add a subtle border via CSS
        VBox root = new VBox(pagination);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Standard scene and stage setup
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using Pagination Controls");
        stage.show();
    }

    /**
     * Page factory method used by the Pagination control.
     *
     * @param pageIndex zero-based index of the page to build
     * @return a Label with simple text for valid indexes, or null if the index
     *         is outside [0, PAGE_COUNT). Returning null lets Pagination know
     *         the page is invalid, so it shows an empty placeholder.
     */
    public Label getPage(int pageIndex) {
        Label content = null;

        // Validate requested page and create content only for valid indexes
        if (pageIndex >= 0 && pageIndex < PAGE_COUNT) {
            content = new Label("Content for page " + (pageIndex + 1));
        }
        return content;
    }
}
