module com.muse.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;

    opens com.muse.desktop to javafx.fxml;
    exports com.muse.desktop;
    opens com.muse.desktop.controller to javafx.fxml;
    exports com.muse.desktop.controller;
}