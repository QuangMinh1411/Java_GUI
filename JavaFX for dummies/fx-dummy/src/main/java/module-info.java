module org.example.fxdummy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    opens org.example.fxdummy to javafx.fxml;
    exports org.example.fxdummy.hello;
    opens org.example.fxdummy.hello to javafx.fxml;
    exports org.example.fxdummy.addsubtract;
    opens org.example.fxdummy.addsubtract to javafx.fxml;
    exports org.example.fxdummy.message;
    opens org.example.fxdummy.userinput to javafx.fxml;
    exports org.example.fxdummy.userinput;
}