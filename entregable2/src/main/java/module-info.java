module com.example.entregable2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.entregable2 to javafx.fxml;
    exports com.example.entregable2;
}