package com.muse.desktop.view;

import com.muse.desktop.model.entity.ProjectEntity;
import com.muse.desktop.model.event.ChangeSheetPreviewEvent;
import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.model.ui.ViewContainer;
import com.muse.desktop.service.EventBus;
import com.muse.desktop.util.ButtonFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.muse.desktop.model.entity.ProjectEntity.Template.*;

public class NewProjectView extends FlowPane implements Presentable, ViewContainer {
    private final List<TextField> inputs = new ArrayList<>();
    private final List<VBox> fieldBoxes = new ArrayList<>();

    private ProjectEntity.Template template;

    private ListView<String> templatesList;

    private TextField titleInput;
    private TextField authorInput;
    private TextField albumInput;

    private Button createProjectButton;
    private Canvas sheetPreview;
    private GridPane dataForm;
    private GridPane templateForm;

    public NewProjectView() {
        present();
    }

    @Override
    public void initComponents() {
        titleInput = new TextField();
        authorInput = new TextField();
        albumInput = new TextField();

        dataForm = new GridPane();
        templateForm = new GridPane();

        createProjectButton = ButtonFactory.createButton("Create", "create-btn", "Create New Project", "create-project-button");
        sheetPreview = new Canvas();

        templatesList = new ListView<>();

        inputs.add(titleInput);
        inputs.add(authorInput);
        inputs.add(albumInput);
    }

    @Override
    public void setupComponents() {
        ButtonFactory.addIcon(createProjectButton, FontAwesomeSolid.CHECK_CIRCLE, 16, Color.rgb(10, 10, 10));

        titleInput.setPromptText("Title");
        authorInput.setPromptText("Author");
        albumInput.setPromptText("Album");

        dataForm.setEffect(createReflection());
        templateForm.setEffect(createReflection());
        sheetPreview.setEffect(createReflection());

        fieldBoxes.addAll(List.of(
                new VBox(10, new Label("Title:"), titleInput),
                new VBox(10, new Label("Author:"), authorInput),
                new VBox(10, new Label("Album:"), albumInput)
        ));

        for (int box = 0; box < fieldBoxes.size(); box++) {
            dataForm.add(fieldBoxes.get(box), 0 , box);
        }

        final Label templateLabel = new Label("Template");

        templatesList.getItems().addAll("Treble Clef", "Bass Clef", "Grand Staff");

        templatesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Treble Clef" -> template = ProjectEntity.Template.TREBLE;
                    case "Bass Clef" -> template = ProjectEntity.Template.BASS;
                    case "Grand Staff" -> template = ProjectEntity.Template.GRAND;
                }
            }
            EventBus.getInstance().publish(new ChangeSheetPreviewEvent());
        });

        templateForm.add(templateLabel, 0, 0);
        templateForm.add(templatesList, 0, 1);

        sheetPreview.setWidth(300);
        sheetPreview.setHeight(450);
        final GraphicsContext gc = sheetPreview.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillRect(0, 0, 300, 450);
        gc.setFill(Color.BLACK);
        gc.strokeRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/MUSE/desktop/style/new-project-view.css")).toExternalForm());
        this.getStyleClass().add("new-project-view");

        dataForm.getStyleClass().add("data-form");
        templateForm.getStyleClass().add("template-form");

        fieldBoxes.forEach(box -> {
            box.getChildren().getFirst().getStyleClass().add("data-label");
            box.getChildren().getLast().getStyleClass().add("data-input");
        });
    }

    @Override
    public void setupLayout() {
        getChildren().addAll(
                dataForm,
                templateForm,
                sheetPreview,
                createProjectButton
        );
    }

    @Override
    public void setupEventListeners() {
        EventBus.getInstance().subscribe(ChangeSheetPreviewEvent.class, event -> redrawPreview());
    }

    @Override
    public void setupEventHandlers() {
        titleInput.setOnKeyTyped(keyEvent -> handleTitleInputInsert());
        authorInput.setOnKeyTyped(keyEvent -> handleAuthorInputInsert());
        albumInput.setOnKeyTyped(keyEvent -> handleAlbumInputInsert());
    }

    private void handleTitleInputInsert() {
        EventBus.getInstance().publish(new ChangeSheetPreviewEvent());
    }

    private void handleAuthorInputInsert() {
        EventBus.getInstance().publish(new ChangeSheetPreviewEvent());
    }

    private void handleAlbumInputInsert() {
        EventBus.getInstance().publish(new ChangeSheetPreviewEvent());
    }

    private void redrawPreview() {
        GraphicsContext gc = sheetPreview.getGraphicsContext2D();
        gc.clearRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
        gc.setFill(Color.BLACK);
        gc.strokeRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
        gc.setTextAlign(TextAlignment.CENTER);

        final String title = titleInput.getText();
        final String album = albumInput.getText();
        final String author = authorInput.getText();

        if (!title.isEmpty()) {
            gc.setFont(Font.font("Arial Black", 16));
            gc.fillText(title, sheetPreview.getWidth() / 2, 60);
        }

        if (!album.isEmpty()) {
            gc.setFont(Font.font("Roboto Light", 13));
            gc.fillText(album, sheetPreview.getWidth() / 2, 80);
        }

        if (!author.isEmpty()) {
            gc.setFont(Font.font("Times New Roman", 14));
            gc.fillText(author, sheetPreview.getWidth() - 28, 100);
        }

        if (template != null) {
            Image image;
            switch (template) {
                case TREBLE -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/MUSE/desktop/assets/treble.png")).toExternalForm());
                case BASS -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/MUSE/desktop/assets/bass.png")).toExternalForm());
                case GRAND -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/MUSE/desktop/assets/grand.png")).toExternalForm());
                case null, default -> throw new RuntimeException("Error with template");
            }
            gc.drawImage(image, 10, 120, sheetPreview.getWidth() - 30, sheetPreview.getHeight() / 2);
        }
    }

    private Reflection createReflection() {
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        reflection.setTopOffset(0.0);
        reflection.setTopOpacity(0.3);
        reflection.setBottomOpacity(0.0);

        return reflection;
    }
}
