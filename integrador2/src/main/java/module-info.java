module org.example.integrador2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.integrador2 to javafx.fxml;
    exports org.example.integrador2;
}