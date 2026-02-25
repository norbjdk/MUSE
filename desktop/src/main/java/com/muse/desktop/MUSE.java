package com.muse.desktop;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MUSE extends Application {
    private static int x = 0;
    private static int y = 0;

    private static Stage stage;

    @Override public void init() {
        System.out.println("[MUSE] Initialized Logic");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MUSE.class.getResource("fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
        stage.setTitle("MUSE!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
        System.out.println("[MUSE] Initialized Graphics");
    }

    public static void main(String [] args) {
        launch();
    }

    public static void applyApplicationMovement(Node target) {
        target.setOnMousePressed((MouseEvent event) -> {
            x = (int) (event.getScreenX() - stage.getX());
            y = (int) (event.getScreenY() - stage.getY());
        });

        target.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    public static void close() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(300),
                new KeyValue(stage.getScene().getRoot().opacityProperty(), 0)
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(actionEvent -> System.exit(0));
        timeline.play();
    }
}
