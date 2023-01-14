module com.example.tresenrayaivanmm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.tresenrayaivanmm to javafx.fxml;
    exports com.example.tresenrayaivanmm;
}