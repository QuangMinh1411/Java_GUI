package com.jdojo.control;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

/**
 * A reusable TableCell that uses a DatePicker to edit LocalDate values.
 *
 * Refactor summary:
 * - Removed unused imports (Application, ObservableValue) to keep dependencies minimal.
 * - Replaced raw types with generics: StringConverter<LocalDate> for type safety.
 * - Imported and used LocalDateStringConverter explicitly; default constructor handles formatting/locales.
 * - Made listener generic (ChangeListener<LocalDate>) instead of using a raw ChangeListener cast.
 * - Simplified fully-qualified java.time.LocalDate usages where LocalDate is already imported.
 *
 * Behavior is unchanged: when a cell enters edit mode, it shows a DatePicker; committing updates the table item.
 */
public class DatePickerTableCell<S, T> extends TableCell<S, LocalDate> {
    private DatePicker datePicker;
    private StringConverter<LocalDate> converter = null;
    private boolean datePickerEditable = true;

    public DatePickerTableCell() {
        this.converter = new LocalDateStringConverter();
    }

    public DatePickerTableCell(boolean datePickerEditable) {
        this.converter = new LocalDateStringConverter();
        this.datePickerEditable = datePickerEditable;
    }

    public DatePickerTableCell(StringConverter<LocalDate> converter) {
        this.converter = converter;
    }

    public DatePickerTableCell(StringConverter<LocalDate> converter,
                               boolean datePickerEditable) {
        this.converter = converter;
        this.datePickerEditable = datePickerEditable;
    }

    @Override
    public void startEdit() {
        // Make sure the cell is editable
        if (!isEditable() ||
                !getTableView().isEditable() ||
                !getTableColumn().isEditable()) {
            return;
        }

        // Let the ancestor do the plumbing job
        super.startEdit();

        // Create a DatePicker, if needed, and set it as the graphic for the cell
        if (datePicker == null) {
            this.createDatePicker();
        }

        this.setGraphic(datePicker);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        this.setText(converter.toString(this.getItem()));
        this.setGraphic(null);
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);

        // Take actions based on whether the cell is being edited or not
        if (empty) {
            this.setText(null);
            this.setGraphic(null);
        }
        else {
            if (this.isEditing()) {
                if (datePicker != null) {
                    datePicker.setValue(item);
                }
                this.setText(null);
                this.setGraphic(datePicker);
            }
            else {
                this.setText(converter.toString(item));
                this.setGraphic(null);
            }
        }
    }

    private void createDatePicker() {
        datePicker = new DatePicker();
        datePicker.setConverter(converter);

        // Set the current value in the cell to the DatePicker
        datePicker.setValue(this.getItem());

        // Configure the DatePicker properties
        datePicker.setPrefWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        datePicker.setEditable(this.datePickerEditable);

        // Commit the new value when the user selects or enters a date
        datePicker.valueProperty().addListener((ChangeListener<LocalDate>) (prop, oldValue, newValue) -> {
            if (DatePickerTableCell.this.isEditing()) {
                DatePickerTableCell.this.commitEdit(newValue);
            }
        });
    }

    public static <S> Callback<TableColumn<S, LocalDate>,
                TableCell<S, LocalDate>> forTableColumn() {
        return forTableColumn(true);
    }

    public static <S> Callback<TableColumn<S, java.time.LocalDate>,
            TableCell<S, java.time.LocalDate>>
    forTableColumn(boolean datePickerEditable) {
        return (col -> new DatePickerTableCell<>(datePickerEditable));
    }

    public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> forTableColumn(StringConverter<java.time.LocalDate> converter) {
        return forTableColumn(converter, true);
    }

    public static <S> Callback<TableColumn<S, java.time.LocalDate>, TableCell<S, java.time.LocalDate>> forTableColumn(StringConverter<java.time.LocalDate> converter, boolean datePickerEditable) {
        return (col -> new DatePickerTableCell<>(converter, datePickerEditable));
    }


}
