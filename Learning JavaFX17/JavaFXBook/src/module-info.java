module JavaFXBook {
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires javafx.swing;
    requires javafx.media;
    requires javafx.web;
    requires javafx.fxml;
    requires jdk.jsobject;
    opens com.jdojo.intro to javafx.graphics, javafx.base;
    opens com.jdojo.binding to javafx.base, javafx.graphics;
    opens com.jdojo.collections to javafx.graphics, javafx.base;
    opens com.jdojo.stage to javafx.graphics, javafx.base;
    opens com.jdojo.scene to javafx.graphics, javafx.base;
    opens com.jdojo.node to javafx.graphics, javafx.base;
    opens com.jdojo.color to javafx.graphics, javafx.base;
    opens com.jdojo.style to javafx.graphics, javafx.base;
    opens com.jdojo.event to javafx.graphics, javafx.base;
    opens com.jdojo.container to javafx.graphics, javafx.base;
    opens com.jdojo.mvc to javafx.graphics, javafx.base;
    opens com.jdojo.mvc.model to javafx.graphics, javafx.base;
    opens com.jdojo.mvc.view to javafx.graphics, javafx.base;
}