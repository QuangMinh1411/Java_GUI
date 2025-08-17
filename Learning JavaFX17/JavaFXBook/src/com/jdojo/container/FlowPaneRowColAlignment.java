package com.jdojo.container;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.geometry.Orientation.HORIZONTAL;
import static javafx.geometry.Orientation.VERTICAL;

/**
 * Demo: FlowPane row/column alignment with different orientations
 *
 * What this example shows
 * -----------------------
 * - Three FlowPane instances are created to compare how row and column alignment work.
 * - Two panes use HORIZONTAL orientation and one uses VERTICAL orientation.
 * - For HORIZONTAL orientation, setRowValignment(VPos) controls vertical alignment of nodes within each row.
 * - For VERTICAL orientation, setColumnHalignment(HPos) controls horizontal alignment of nodes within each column.
 * - Each pane displays a Text indicating which alignment value is being demonstrated and a TextArea showing the orientation.
 * - All panes are given gaps, preferred sizes, and a visible border to make effects easy to see; they are placed side-by-side in an HBox.
 *
 * Key concepts explained
 * ----------------------
 * 1) Orientation matters
 *    - HORIZONTAL: content flows left-to-right, wrapping into multiple rows; rowValignment sets how nodes in a row line up vertically (TOP, CENTER, BASELINE, BOTTOM).
 *    - VERTICAL: content flows top-to-bottom, wrapping into multiple columns; columnHalignment sets how nodes in a column line up horizontally (LEFT, CENTER, RIGHT).
 *
 * 2) Visual helpers
 *    - A Text node shows the active alignment value (row or column depending on orientation).
 *    - A TextArea shows the pane's orientation and provides a multi-line control to better visualize alignment.
 *    - Inline CSS adds padding and a blue border so the container bounds are obvious.
 *
 * Try it
 * ------
 * - Run the app and compare the three panes: notice how TOP vs CENTER row VPos affects horizontal panes, while RIGHT column HPos affects the vertical pane.
 */
public class FlowPaneRowColAlignment extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        FlowPane fp1 = createFlowPane(HORIZONTAL, VPos.TOP, HPos.LEFT);
        FlowPane fp2 = createFlowPane(HORIZONTAL, VPos.CENTER, HPos.LEFT);
        FlowPane fp3 = createFlowPane(VERTICAL, VPos.CENTER, HPos.RIGHT);

        HBox root = new HBox(fp1, fp2, fp3);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FlowPane Row and Column Alignment");
        stage.show();
    }

    private FlowPane createFlowPane(Orientation orientation,
                                    VPos rowAlign,
                                    HPos colAlign) {
        // Show the row or column alignment value in a Text
        Text t = new Text();
        if (orientation == Orientation.HORIZONTAL) {
            t.setText(rowAlign.toString());
        } else {
            t.setText(colAlign.toString());
        }

        // Show the orientation of the FlowPane in a TextArea
        TextArea ta = new TextArea(orientation.toString());
        ta.setPrefColumnCount(5);
        ta.setPrefRowCount(3);

        FlowPane fp = new FlowPane(orientation, 5, 5);
        fp.setRowValignment(rowAlign);
        fp.setColumnHalignment(colAlign);
        fp.setPrefSize(175, 130);
        fp.getChildren().addAll(t, ta);
        fp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        return fp;
    }
}
