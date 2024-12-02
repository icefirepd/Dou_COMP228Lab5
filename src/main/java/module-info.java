module com.lab.fangdou_comp228lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.lab.fangdou_comp228lab5 to javafx.fxml;
    exports com.lab.fangdou_comp228lab5;
}