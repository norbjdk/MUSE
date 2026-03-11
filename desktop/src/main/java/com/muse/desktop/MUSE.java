package com.muse.desktop;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;

public class MUSE extends Application {
    private static int x = 0;
    private static int y = 0;
    private static int WIDTH = 1600;
    private static int HEIGHT = 800;

    private static Stage stage;

    @Override public void init() {
        System.out.println("[MUSE] Initialized Logic");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MUSE.class.getResource("fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle("MUSE!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("assets/logo.png"))));
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

    public static int getY() {
        return y;
    }

    public static int getX() {
        return x;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        MUSE.HEIGHT = HEIGHT;
    }

    public static void setWIDTH(int WIDTH) {
        MUSE.WIDTH = WIDTH;
    }

    public static Stage getStage() {
        return stage;
    }
}
