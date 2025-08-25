package com.jdojo.control;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Demo: Managing closable tabs in a TabPane.
 *
 * What this program shows:
 * - A TabPane with two tabs (General and Address), each representing a custom Tab subclass.
 * - A CheckBox that enables or disables the ability to close tabs.
 * - A Button to restore the two tabs if they were closed.
 * - A ChoiceBox bound to the TabPane's tabClosingPolicy to control where/when close buttons appear
 *   (ALL_TABS, SELECTED_TAB, UNAVAILABLE).
 *
 * How it works:
 * 1) Tabs and close handling
 *    - The two tabs are added to the TabPane at startup.
 *    - Each tab has an onCloseRequest handler (tabClosingRequested). If the "Are Tabs closable?" checkbox
 *      is not selected, the event is consumed to prevent the tab from closing.
 *    - Each tab also has an onClosed handler (tabClosed) that prints which tab was closed.
 *
 * 2) Restoring tabs
 *    - Clicking the "Restore Tabs" button calls restoreTabs(). It checks whether the General and Address tabs
 *      are already present; if not, it inserts them back at indices 0 and 1 respectively.
 *
 * 3) Controlling the close button policy
 *    - The ChoiceBox is populated with the three TabClosingPolicy values. The TabPane's current policy is set
 *      as the initial value.
 *    - The TabPane's tabClosingPolicyProperty is bound to the ChoiceBox's valueProperty, so changes in the
 *      ChoiceBox immediately update the TabPane. (The closingPolicyChanged(...) method is provided as an
 *      alternative imperative approach but is unused because binding is already in place.)
 *
 * Notes:
 * - Consuming the close request event is the standard way to veto a tab close in JavaFX.
 * - The checkbox simply toggles whether close attempts are allowed; it does not hide the close buttons by itself.
 *   The visibility/availability of close buttons is controlled by the TabClosingPolicy.
 */
public class TabClosingTest extends Application {
    GeneralTab generalTab = new GeneralTab("General", null);
    AddressTab addressTab = new AddressTab("Address", null);
    TabPane tabPane = new TabPane();

    CheckBox allowClosingTabsFlag = new CheckBox("Are Tabs closable?");
    Button restoreTabsBtn = new Button("Restore Tabs");
    ChoiceBox<TabPane.TabClosingPolicy> tabClosingPolicyChoices = new ChoiceBox<>();
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        // Add Tabs to the TabPane
        tabPane.getTabs().addAll(generalTab, addressTab);

        // Set a tab close request event handler for tabs
        generalTab.setOnCloseRequest(this::tabClosingRequested);
        addressTab.setOnCloseRequest(this::tabClosingRequested);

        // Set a closed event handler for the tabs
        generalTab.setOnClosed(e -> tabClosed(e));
        addressTab.setOnClosed(e -> tabClosed(e));

        // Set an action event handler for the restore button
        restoreTabsBtn.setOnAction(e -> restoreTabs());

        // Add choices to the choice box
        tabClosingPolicyChoices.getItems()
                .addAll(TabPane.TabClosingPolicy.ALL_TABS,
                        TabPane.TabClosingPolicy.SELECTED_TAB,
                        TabPane.TabClosingPolicy.UNAVAILABLE);

        // Set the default value for the tab closing policy
        tabClosingPolicyChoices.setValue(tabPane.getTabClosingPolicy());


        // Bind the tabClosingPolicy of the tabPane to the value property of the 
        // of the ChoiceBoxx
        tabPane.tabClosingPolicyProperty().bind(
                tabClosingPolicyChoices.valueProperty());

        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-padding: 10;");
        grid.addRow(0, allowClosingTabsFlag, restoreTabsBtn);
        grid.addRow(1, new Label("Tab Closing Policy:"),
                tabClosingPolicyChoices);
        root.setTop(grid);
        root.setCenter(tabPane);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Closing Tabs");
        stage.show();
    }

    public void tabClosingRequested(Event e) {
        if (!allowClosingTabsFlag.isSelected()) {
            e.consume(); // Closing tabs is not allowed
        }
    }

    public void tabClosed(Event e) {
        Tab tab = (Tab)e.getSource();
        String text = tab.getText();
        System.out.println(text + " tab has been closed.");
    }

    public void restoreTabs() {
        ObservableList<Tab> list = tabPane.getTabs();
        if (!list.contains(generalTab)) {
            list.add(0, generalTab);
        }

        if (!list.contains(addressTab)) {
            list.add(1, addressTab);
        }
    }

    public void closingPolicyChanged(
            ObservableValue<? extends TabPane.TabClosingPolicy> prop,
            TabPane.TabClosingPolicy oldPolicy,
            TabPane.TabClosingPolicy newPolicy) {
        tabPane.setTabClosingPolicy(newPolicy);
    }
}
