package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ComboBoxWithConverter extends Application {
    private final Label userSelectionMsgLbl = new Label("Your selection:");
    private final Label userSelectionDataLbl = new Label("");

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label personLbl = new Label("Select/Enter Person:");
        ComboBox<Person> persons = new ComboBox<>();
        persons.setEditable(true);
        persons.setConverter(new PersonStringConverter());
        persons.getItems().addAll(new Person("John", "Jacobs", null),
                new Person("Donna", "Duncan", null),
                new Person("Layne", "Estes", null),
                new Person("Mason", "Boyd", null));

        // Add ChangeListeners to the selectedItem and selectedIndex
        // properties of the selection model
        persons.getSelectionModel().selectedItemProperty()
                .addListener(this::personChanged);
        persons.getSelectionModel().selectedIndexProperty()
                .addListener(this::indexChanged);

        // Update the message Label when the value changes
        persons.setOnAction(e -> valueChanged(persons));

        GridPane root = new GridPane();
        root.addRow(0, personLbl, persons);
        root.addRow(1, userSelectionMsgLbl, userSelectionDataLbl);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using StringConverter in ComboBox");
        stage.show();
    }

    // Formats a Person's name as "Last, First", handling nulls gracefully
    private String formatPerson(Person p) {
        if (p == null) {
            return "";
        }
        String first = p.getFirstName() == null ? "" : p.getFirstName();
        String last = p.getLastName() == null ? "" : p.getLastName();
        if (!last.isEmpty() && !first.isEmpty()) {
            return last + ", " + first;
        }
        return last.isEmpty() ? first : last;
    }

    public void valueChanged(ComboBox<Person> list) {
        Person p = list.getValue();
        userSelectionDataLbl.setText(formatPerson(p));
    }

    // A change listener to track the change in item selection
    public void personChanged(ObservableValue<? extends Person> observable,
                              Person oldValue,
                              Person newValue) {
        System.out.println("Item changed: old = " + formatPerson(oldValue) +
                ", new = " + formatPerson(newValue));
    }

    // A change listener to track the change in index selection
    public void indexChanged(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue) {
        System.out.println("Index changed: old = " + oldValue + ", new = " + newValue);
    }
}
