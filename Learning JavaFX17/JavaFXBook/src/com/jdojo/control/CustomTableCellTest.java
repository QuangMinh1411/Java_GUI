package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

/**
 * Demo: Using a custom TableCell to edit LocalDate values with a DatePicker.
 *
 * Refactor summary:
 * - Added explicit import for LocalDateStringConverter and used proper generics: StringConverter<LocalDate>.
 * - Fixed typos in comments and clarified intent: TableView is editable and Birth Date uses DatePicker cells.
 * - Kept behavior identical: shows person data and allows editing birth dates with the specified format.
 */
public class CustomTableCellTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        TableView<Person> table = new TableView<>(PersonTableUtil.getPersonList());

        // Make sure the TableView is editable
        table.setEditable(true);

        // Set up the Birth Date column to use DatePickerTableCell
        TableColumn<Person, LocalDate> birthDateCol = PersonTableUtil.getBirthDateColumn();
        StringConverter<LocalDate> converter = new LocalDateStringConverter();
        birthDateCol.setCellFactory(DatePickerTableCell.forTableColumn(converter, false));

        table.getColumns().addAll(
                PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                birthDateCol
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
        stage.setTitle("Using a Custom TableCell");
        stage.show();
    }
}
