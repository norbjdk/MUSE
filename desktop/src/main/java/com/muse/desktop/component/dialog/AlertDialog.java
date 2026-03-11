package com.muse.desktop.component.dialog;

import com.muse.desktop.MUSE;
import com.muse.desktop.model.ui.Presentable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AlertDialog extends Dialog<Boolean> implements Presentable {
    private final int WIDTH = 200;
    private final int HEIGHT = 100;

    private final String message;

    private DialogPane dialogPane;
    private VBox contentContainer;
    private Label messageLabel;
    private ProgressBar timeIndicator;

    public AlertDialog(String message) {
        this.message = message;

        initStyle(StageStyle.TRANSPARENT);

        present();

        autoClose();
    }

    @Override
    public void initComponents() {
        dialogPane = new DialogPane();
        contentContainer = new VBox();
        messageLabel = new Label(message);
        timeIndicator = new ProgressBar();
    }

    @Override
    public void setupComponents() {
        contentContainer.setAlignment(Pos.CENTER);
        timeIndicator.setProgress(1.0);
    }

    @Override
    public void setupStyle() {
        dialogPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/alert-dialog.css")).toExternalForm());
        dialogPane.getStyleClass().add("alert-dialog");

        timeIndicator.setMaxWidth(Double.MAX_VALUE);
        timeIndicator.getStyleClass().add("alert-progress");

        this.setOnShowing(ev -> {
            this.getDialogPane().getScene().setFill(Color.TRANSPARENT);
        });

        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
    }

    @Override
    public void setupLayout() {
        this.setDialogPane(dialogPane);

        contentContainer.getChildren().addAll(
            messageLabel,
            createSpacer(),
            timeIndicator
        );

        dialogPane.setContent(contentContainer);
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }

    public String getMessage() {
        return message;
    }

    private void autoClose() {
        if (getDialogPane().getButtonTypes().isEmpty()) {
            getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        }

        Timeline timeline = new Timeline();

        KeyValue kv = new KeyValue(timeIndicator.progressProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.seconds(3), kv);

        timeline.getKeyFrames().add(kf);

        timeline.setOnFinished(event -> Platform.runLater(this::close));

        timeline.play();
    }

    private Region createSpacer() {
        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);

        return region;
    }
}
