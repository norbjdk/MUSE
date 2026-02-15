package com.muse.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MUSE extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MUSE.class.getResource("fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setTitle("MUSE!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String [] args) {
        launch();
    }
}
