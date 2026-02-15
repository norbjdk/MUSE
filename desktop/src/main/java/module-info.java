module com.muse.desktop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.muse.desktop to javafx.fxml;
    exports com.muse.desktop;
}