module com.example.preparcialventas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens objects to javafx.base;


    opens com.example.preparcialventas to javafx.fxml;
    opens  controllers;
    exports com.example.preparcialventas;
    exports objects;
}