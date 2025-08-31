package com.jdojo.control;

import com.jdojo.mvc.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Refactoring note:
 * - Extracted row-map creation into createRowMap(Person) to improve readability
 *   and reuse. Behavior is unchanged; keys and value mapping live in one place.
 * - This class demonstrates using a TableView backed by Map items and
 *   MapValueFactory to bind columns to map keys.
 */
public class TableViewMapDataTest extends Application {
    private final String idColumnKey = "id";
    private final String firstNameColumnKey = "firstName";
    private final String lastNameColumnKey = "lastName";
    private final String birthDateColumnKey = "birthDate";
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        TableView<Map> table = new TableView<>();
        ObservableList<Map<String, Object>> items = this.getMapData();
        table.getItems().addAll(items);
        this.addColumns(table);

        HBox root = new HBox(table);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using a Map as items in a TableView");
        stage.show();
    }

    public ObservableList<Map<String, Object>> getMapData() {
        ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

        // Extract the person data, convert each Person to a Map (one row),
        // and add it to the items list
        ObservableList<Person> persons = PersonTableUtil.getPersonList();
        for (Person p : persons) {
            items.add(createRowMap(p));
        }

        return items;
    }

    /**
     * Small refactoring:
     * - Encapsulates how a table row Map is built from a Person.
     * - Keeps keys and mapping logic in one place for reuse and maintenance.
     */
    private Map<String, Object> createRowMap(Person p) {
        Map<String, Object> map = new HashMap<>();
        map.put(idColumnKey, p.getPersonId());
        map.put(firstNameColumnKey, p.getFirstName());
        map.put(lastNameColumnKey, p.getLastName());
        map.put(birthDateColumnKey, p.getBirthDate());
        return map;
    }

    @SuppressWarnings("unchecked")
    public void addColumns(TableView table) {
        TableColumn<Map, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new MapValueFactory<>(idColumnKey));

        TableColumn<Map, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new MapValueFactory<>(firstNameColumnKey));

        TableColumn<Map, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new MapValueFactory<>(lastNameColumnKey));

        TableColumn<Map, LocalDate> birthDateCol = 	new TableColumn<>("Birth Date");
        birthDateCol.setCellValueFactory(new MapValueFactory<>(birthDateColumnKey));

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, birthDateCol);
//        table.setTableMenuButtonVisible(true);
    }
}
