package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Demo: Using custom cell factories in a TableView.
 *
 * Refactor summary:
 * - Replaced per-update DateTimeFormatter creation with a single static, reusable formatter (thread-safe in Java 8+).
 * - Simplified cell factory creation by returning the new TableCell directly, keeping the setup concise.
 * - Kept explicit cleanup (setText/setGraphic to null) in updateItem to avoid stale content when cells are reused.
 * - Simplified the boolean value factory for the "Baby?" column using a one-liner ReadOnlyBooleanWrapper.
 *
 * Behavior is identical to the original: the Birth Date column displays dates as MM/dd/yyyy
 * and the "Baby?" column shows a checkbox reflecting whether the person is categorized as BABY.
 */
public class TableViewCellFactoryTest extends Application {
    private static final DateTimeFormatter DOB_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());

        // Create the birth date column
        TableColumn<Person, LocalDate> birthDateCol = PersonTableUtil.getBirthDateColumn();

        // Set a custom cell factory for Birth Date column
        birthDateCol.setCellFactory(col -> new TableCell<Person, LocalDate>() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Cleanup the cell before populating it
                setText(null);
                setGraphic(null);

                if (!empty && item != null) {
                    setText(DOB_FMT.format(item));
                }
            }
        });

        // Create and configure the baby column
        TableColumn<Person, Boolean> babyCol = new TableColumn<>("Baby?");
        babyCol.setCellValueFactory(cellData ->
                new ReadOnlyBooleanWrapper(cellData.getValue().getAgeCategory() == Person.AgeCategory.BABY));

        // Set a custom cell factory for the baby column
        babyCol.setCellFactory(CheckBoxTableCell.forTableColumn(babyCol));

        // Add columns to the table
        table.getColumns().addAll(
                PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                birthDateCol,
                babyCol
        );

        HBox root = new HBox(table);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using a Custom Cell Factory for a TableColumn");
        stage.show();
    }
}

