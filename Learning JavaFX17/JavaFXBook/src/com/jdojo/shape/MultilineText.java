/*
This JavaFX application demonstrates the use of multiline text nodes.
It creates a window displaying a poem in three different Text objects,
each with different properties, arranged horizontally in an HBox.

- The first Text object (`t1`) displays the poem with a line spacing of 5 pixels.
- The second Text object (`t2`) displays the same poem but with the text aligned to the right.
- The third Text object (`t3`) has a wrapping width of 100 pixels, which causes the
  lines to wrap if they exceed this width.

The HBox container for these Text objects has a spacing of 20 pixels between them
and is styled with a blue border, padding, and rounded corners.
*/
package com.jdojo.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MultilineText extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        String text = """
                        Strange fits of passion have I known:
                  And I will dare to tell,
                  But in the lover's ear alone,
                  What once to me befell.""".stripIndent();
        Text t1 = new Text(text);
        t1.setLineSpacing(5);


        Text t2 = new Text(text);
        t2.setTextAlignment(TextAlignment.RIGHT);
        Text t3 = new Text(text);
        t3.setWrappingWidth(100);
        HBox root = new HBox(t1, t2, t3);
        root.setSpacing(20);
        root.setStyle("""
                         -fx-padding: 10;
                   -fx-border-style: solid inside;
                   -fx-border-width: 2;
                   -fx-border-insets: 5;
                   -fx-border-radius: 5;
                    -fx-border-color: blue;""");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using Multiline Text Nodes");
        stage.show();
    }
}
