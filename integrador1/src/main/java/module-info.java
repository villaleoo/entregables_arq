module org.example.integrador1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.integrador1 to javafx.fxml;
    exports org.example.integrador1;
}