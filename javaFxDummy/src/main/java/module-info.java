module com.quangminh.javafxdummy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.quangminh.javafxdummy.userinput to javafx.fxml;
    exports com.quangminh.javafxdummy.userinput;
    opens com.quangminh.javafxdummy.pizza to javafx.fxml;
    exports com.quangminh.javafxdummy.pizza;
}