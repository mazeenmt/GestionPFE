module org.example.gestionpfe {
    exports org.example.gestionpfe;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires java.desktop;


    opens org.example.gestionpfe to javafx.fxml;
}