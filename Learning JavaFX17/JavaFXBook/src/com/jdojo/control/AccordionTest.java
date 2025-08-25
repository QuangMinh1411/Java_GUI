package com.jdojo.control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A JavaFX application demonstrating the use of the Accordion control.
 * An Accordion is a container that shows a stack of TitledPanes,
 * where only one pane can be expanded at a time.
 *
 * This refactored version improves on the original by:
 * 1.  Enhancing encapsulation by making helper methods private.
 * 2.  Improving code clarity by separating content creation from UI assembly.
 * 3.  Adding proper layout (padding and gaps) to the forms for better aesthetics.
 * 4.  Including detailed comments and Javadoc for better maintainability.
 */
public class AccordionTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create the content for each TitledPane using private helper methods.
        // This separates the content creation from the main layout assembly.
        GridPane generalContent = createGeneralContent();
        GridPane addressContent = createAddressContent();
        GridPane phoneContent = createPhoneContent();

        // Create TitledPanes, each with a title and its content Node.
        TitledPane generalPane = new TitledPane("General", generalContent);
        TitledPane addressPane = new TitledPane("Address", addressContent);
        TitledPane phonePane = new TitledPane("Phone", phoneContent);

        // Create an Accordion control.
        Accordion root = new Accordion();
        // Add all the TitledPanes to the Accordion.
        root.getPanes().addAll(generalPane, addressPane, phonePane);

        // Set which TitledPane should be expanded by default when the application starts.
        root.setExpandedPane(generalPane);

        // Apply CSS styling directly to the Accordion.
        // For more complex applications, it's better to use an external CSS file.
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the main scene with the Accordion as the root node.
        Scene scene = new Scene(root);
        // Set the scene for the stage.
        stage.setScene(scene);
        // Set the title of the application window.
        stage.setTitle("Using Accordion Controls");
        // Show the stage.
        stage.show();
    }

    /**
     * Creates and configures a GridPane with general information fields.
     * This method encapsulates the creation of the "General" form content.
     *
     * @return A configured GridPane for the "General" section.
     */
    private GridPane createGeneralContent() {
        GridPane grid = new GridPane();
        // Set padding and gaps for better layout.
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(5);

        // Add rows with labels and input controls.
        grid.addRow(0, new Label("First Name:"), new TextField());
        grid.addRow(1, new Label("Last Name:"), new TextField());
        grid.addRow(2, new Label("DOB:"), new DatePicker());

        return grid;
    }

    /**
     * Creates and configures a GridPane with address fields.
     * This method encapsulates the creation of the "Address" form content.
     *
     * @return A configured GridPane for the "Address" section.
     */
    private GridPane createAddressContent() {
        GridPane grid = new GridPane();
        // Set padding and gaps for better layout.
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(5);

        // Add rows with labels and input controls.
        grid.addRow(0, new Label("Street:"), new TextField());
        grid.addRow(1, new Label("City:"), new TextField());
        grid.addRow(2, new Label("State:"), new TextField());
        grid.addRow(3, new Label("ZIP:"), new TextField());

        return grid;
    }

    /**
     * Creates and configures a GridPane with phone number fields.
     * This method encapsulates the creation of the "Phone" form content.
     *
     * @return A configured GridPane for the "Phone" section.
     */
    private GridPane createPhoneContent() {
        GridPane grid = new GridPane();
        // Set padding and gaps for better layout.
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(5);

        // Add rows with labels and input controls.
        grid.addRow(0, new Label("Home:"), new TextField());
        grid.addRow(1, new Label("Work:"), new TextField());
        grid.addRow(2, new Label("Cell:"), new TextField());

        return grid;
    }
}
