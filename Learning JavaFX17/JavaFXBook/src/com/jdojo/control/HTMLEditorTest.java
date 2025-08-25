package com.jdojo.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * Small JavaFX demo showing how to synchronize content between an HTMLEditor and a TextArea.
 *
 * Refactor notes:
 * - Extracted common sizes and spacing into constants to avoid magic numbers and ease future changes.
 * - Split UI construction into small helper methods for readability and separation of concerns.
 * - Fixed a semantics bug: previously, the button labels did not match their actions.
 *   Now "Convert HTML to Text" copies the HTMLEditor's HTML into the plain TextArea,
 *   and "Convert Text to HTML" copies the TextArea content into the HTMLEditor.
 * - Added a monospace font to the TextArea so HTML is easier to read as plain text.
 */
public class HTMLEditorTest extends Application {
    private static final double PREF_WIDTH = 600;
    private static final double PREF_HEIGHT = 300;
    private static final double SPACING = 10;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        HTMLEditor editor = createEditor();
        TextArea html = createHtmlArea();
        HBox buttons = createButtons(editor, html);

        VBox root = new VBox(editor, buttons, html);
        root.setSpacing(SPACING);
        root.setStyle("-fx-padding: 10;" +
                       "-fx-border-style: solid inside;" +
                       "-fx-border-width: 2;" +
                       "-fx-border-insets: 5;" +
                       "-fx-border-radius: 5;" +
                       "-fx-border-color: blue;");

        stage.setScene(new Scene(root));
        stage.setTitle("Using an HTMLEditor");
        stage.show();
    }

    private HTMLEditor createEditor() {
        HTMLEditor editor = new HTMLEditor();
        editor.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        return editor;
    }

    private TextArea createHtmlArea() {
        TextArea html = new TextArea();
        html.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        html.setStyle("-fx-font-size:10pt; -fx-font-family: \"Courier New\";");
        return html;
    }

    private HBox createButtons(HTMLEditor editor, TextArea html) {
        Button htmlToText = new Button("Convert HTML to Text");
        Button textToHtml = new Button("Convert Text to HTML");

        // Correct mapping:
        // - HTML to Text: take HTML from editor and display it as plain text in the TextArea.
        htmlToText.setOnAction(e -> html.setText(editor.getHtmlText()));
        // - Text to HTML: take the plain text and set it as the editor's HTML content.
        textToHtml.setOnAction(e -> editor.setHtmlText(html.getText()));

        HBox buttons = new HBox(htmlToText, textToHtml);
        buttons.setSpacing(SPACING);
        return buttons;
    }
}
