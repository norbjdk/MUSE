module com.muse.desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.muse.desktop to javafx.fxml;
    exports com.muse.desktop;
    opens com.muse.desktop.controller to javafx.fxml;
    exports com.muse.desktop.controller;
    exports com.muse.desktop.model.dto.internal to com.fasterxml.jackson.databind;
    opens com.muse.desktop.model.dto.internal to com.fasterxml.jackson.databind;
}