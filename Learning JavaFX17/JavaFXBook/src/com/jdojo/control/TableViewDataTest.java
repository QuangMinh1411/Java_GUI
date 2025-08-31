package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class TableViewDataTest extends Application {
    /**
     * Refactoring note:
     * - The logic that computed the "Age" text inside the cellValueFactory
     *   lambda was extracted into a small, pure helper method (computeAgeLabel).
     * - This makes the cell factory easy to read and test, and avoids repeating
     *   the same age-formatting rules elsewhere.
     * - The method also centralizes edge-case handling (null dates, < 1 year,
     *   singular/plural) so future changes are in one place.
     */
    private static String computeAgeLabel(LocalDate dob, LocalDate today) {
        if (dob == null) {
            return "Unknown";
        }
        long years = YEARS.between(dob, today != null ? today : LocalDate.now());
        if (years == 0) {
            return "< 1 year";
        } else if (years == 1) {
            return "1 year";
        } else {
            return years + " years";
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create a TableView with data
        TableView<Person> table =
                new TableView<>(PersonTableUtil.getPersonList());

        // Create an "Age" computed column
        TableColumn<Person, String> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> {
            Person p = cellData.getValue();
            String label = computeAgeLabel(p.getBirthDate(), LocalDate.now());
            return new ReadOnlyStringWrapper(label);
        });

        // Create an "Age Cotegory" column
        TableColumn<Person, Person.AgeCategory> ageCategoryCol =
                new TableColumn<>("Age Category");
        ageCategoryCol.setCellValueFactory(
                new PropertyValueFactory<>("ageCategory"));

        // Add columns to the TableView
        table.getColumns().addAll(PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                PersonTableUtil.getBirthDateColumn(),
                ageCol,
                ageCategoryCol);

        HBox root = new HBox(table);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Populating TableViews");
        stage.show();
    }
}
