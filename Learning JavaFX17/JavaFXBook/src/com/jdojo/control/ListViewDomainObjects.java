package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Demonstrates how to display domain objects (Person) in a JavaFX ListView
 * using a custom cell factory.
 *
 * Key ideas:
 * - ListView<Person>: a list control holding Person items.
 * - setCellFactory(...): supplies a ListCell implementation that controls how each row is rendered.
 * - ListCell.updateItem(Person item, boolean empty): called by JavaFX to refresh cell content.
 *   We set the text to "<index>. Lastname, Firstname" when the cell has an item, or clear it when empty.
 * - getIndex(): the cell's current index within the ListView; used to create a 1-based sequence number.
 * - setGraphic(null): we render text only, with no graphic node.
 *
 * The example also demonstrates simple layout setup with an HBox and applies some border/padding styles.
 */
public class ListViewDomainObjects extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage){
        ListView<Person> persons = new ListView<>();
        persons.setPrefSize(250, 120);
        persons.getItems().addAll(new Person("John","Jacob",null),
                new Person("Donna", "Duncan", null),
                new Person("Layne", "Estes", null),
                new Person("Mason", "Boyd", null));
        
        // Set a cell factory to display a Person's full name with a sequence number.
        // Using a lambda for the Callback is more concise.
        persons.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Format the string to be displayed: "1. Lastname, Firstname"
                    setText((getIndex() + 1) + ". " + item.getLastName() + ", " + item.getFirstName());
                }
                // No graphic is used in this cell, so set it to null.
                setGraphic(null);
            }
        });

        // Create a root pane, scene, set it on the stage, and show it.
        HBox root = new HBox(new Label("Persons:"), persons);
        root.setSpacing(20);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using ListView Cell Factory");
        stage.show();
    }
}
