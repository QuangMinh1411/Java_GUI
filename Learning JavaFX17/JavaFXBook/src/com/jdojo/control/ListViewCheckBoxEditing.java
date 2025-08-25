package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class ListViewCheckBoxEditing extends Application {
    private static final String[] BREAKFAST_ITEMS = {"Apple", "Banana", "Donut", "Hash Brown"};

    // Preserve insertion order for consistent ListView ordering
    private final Map<String, BooleanProperty> map = new LinkedHashMap<>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Populate the map with ListView items as keys and their selected state as values
        for (String item : BREAKFAST_ITEMS) {
            map.put(item, new SimpleBooleanProperty(false));
        }

        ListView<String> breakfasts = new ListView<>();
        breakfasts.setPrefSize(200, 120);
        breakfasts.setEditable(true);
        breakfasts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Add all keys from the map as items to the ListView (order preserved)
        breakfasts.getItems().setAll(map.keySet());

        // Set the cell factory using a lambda mapping items to their BooleanProperty
        breakfasts.setCellFactory(CheckBoxListCell.forListView(item -> map.get(item)));

        Button printBtn = new Button("Print Selection");
        printBtn.setOnAction(e -> printSelection());

        VBox root = new VBox(new Label("Breakfasts:"), breakfasts, printBtn);
        root.setSpacing(10);
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

    public void printSelection() {
        System.out.println("Selected items: ");
        for (Map.Entry<String, BooleanProperty> entry : map.entrySet()) {
            if (entry.getValue().get()) {
                System.out.println(entry.getKey());
            }
        }
        System.out.println();
    }
}
