package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListViewEditEvents extends Application {
    private static final String[] BREAKFAST_ITEMS = {"Apple", "Banana", "Donut", "Hash Brown"};

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        final ListView<String> breakfasts = new ListView<>();
        breakfasts.setPrefSize(200, 120);
        breakfasts.getItems().setAll(BREAKFAST_ITEMS);
        breakfasts.setEditable(true);
        breakfasts.setCellFactory(TextFieldListCell.forListView());

        // Add Edit-related event handlers
        breakfasts.setOnEditStart(this::editStart);
        breakfasts.setOnEditCommit(this::editCommit);
        breakfasts.setOnEditCancel(this::editCancel);

        final HBox root = new HBox(new Label("Breakfast:"), breakfasts);
        root.setSpacing(20);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using ListView Edit Events");
        stage.show();
    }

    private static String getItemAt(ListView<String> lv, int index) {
        return (index >= 0 && index < lv.getItems().size()) ? lv.getItems().get(index) : null;
    }

    private void editStart(ListView.EditEvent<String> e) {
        ListView<String> lv = e.getSource();
        String current = getItemAt(lv, e.getIndex());
        System.out.println("Edit Start: index=" + e.getIndex() + ", currentItem=" + current);
    }

    private void editCommit(ListView.EditEvent<String> e) {
        // Persist the edited value back into the ListView's items
        ListView<String> lv = e.getSource();
        int index = e.getIndex();
        if (index >= 0 && index < lv.getItems().size()) {
            lv.getItems().set(index, e.getNewValue());
        }
        System.out.println("Edit Commit: index=" + index + ", newItem=" + e.getNewValue());
    }

    private void editCancel(ListView.EditEvent<String> e) {
        ListView<String> lv = e.getSource();
        String current = getItemAt(lv, e.getIndex());
        System.out.println("Edit Cancel: index=" + e.getIndex() + ", currentItem=" + current);
    }
}
