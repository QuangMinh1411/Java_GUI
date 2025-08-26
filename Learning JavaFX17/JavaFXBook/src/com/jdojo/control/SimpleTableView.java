package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Refactor note:
 * - Extracted small factory methods (buildPersonTable and buildRoot) to keep the start method
 *   focused on high-level flow (create UI -> set scene -> show stage). This improves readability
 *   and single-responsibility, and makes table/root creation reusable if the class grows.
 * - Moved the inline CSS text block into a named constant (ROOT_STYLE) to avoid duplication,
 *   ease maintenance, and clarify intent without changing behavior.
 *
 * Behavior remains the same: a TableView with Person columns inside a styled VBox is shown.
 */
public class SimpleTableView extends Application {
    // Centralize the style so it's easy to tweak or reuse without hunting through the code
    private static final String ROOT_STYLE = """
            -fx-padding: 10;
            -fx-border-style: solid inside;
            -fx-border-width: 2;
            -fx-border-insets: 5;
            -fx-border-radius: 5;
            -fx-border-color: blue;""";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = buildRoot();
        stage.setScene(new Scene(root));
        stage.setTitle("Simplest TableView");
        stage.show();
    }

    // Creates the table and configures its columns without altering original behavior
    private TableView<Person> buildPersonTable() {
        TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());
        table.getColumns().addAll(
                PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                PersonTableUtil.getBirthDateColumn()
        );
        return table;
    }

    // Builds the root container and applies the centralized style
    private VBox buildRoot() {
        VBox root = new VBox(buildPersonTable());
        root.setStyle(ROOT_STYLE);
        return root;
    }
}
