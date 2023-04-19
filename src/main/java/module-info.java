module com.example.tp_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;
    requires java.prefs;

    opens com.example.tp_1 to javafx.fxml;
    exports com.example.tp_1;
    exports com.example.tp_1.root;
    opens com.example.tp_1.root to javafx.fxml;
}