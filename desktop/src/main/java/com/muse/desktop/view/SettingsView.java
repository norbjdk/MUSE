package com.muse.desktop.view;

import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.model.ui.ViewContainer;
import com.muse.desktop.util.ButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsView extends ScrollPane implements Presentable, ViewContainer {
    private final List<HBox> optionBoxes = new ArrayList<>();

    private ComboBox<String> languages;
    private ComboBox<String> autosave;
    private ToggleGroup starters;
    private ToggleGroup cloud;

    private VBox contentContainer;
    private Label headerLabel;
    private Button saveButton;
    private HBox headerBox;

    private HBox languageBox;
    private HBox defaultStartBox;
    private HBox autosaveBox;
    private HBox cloudSaveBox;
    private HBox directoriesBox;

    public SettingsView() {
        present();
    }

    @Override
    public void initComponents() {
        languages = new ComboBox<>();
        starters = new ToggleGroup();
        autosave = new ComboBox<>();
        cloud = new ToggleGroup();

        contentContainer = new VBox(5);
        headerLabel = new Label("Settings");
        saveButton = ButtonFactory.createButton("Save", "save-btn", "Save Settings", "settings-save-button");
        headerBox = new HBox();

        languageBox = new HBox();
        defaultStartBox = new HBox();
        autosaveBox = new HBox();
        cloudSaveBox = new HBox();
        directoriesBox = new HBox();

        optionBoxes.add(languageBox);
        optionBoxes.add(defaultStartBox);
        optionBoxes.add(autosaveBox);
        optionBoxes.add(cloudSaveBox);
    }

    @Override
    public void setupComponents() {
        contentContainer.setAlignment(Pos.TOP_LEFT);
        contentContainer.setMinHeight(1000);
        contentContainer.setMaxWidth(Double.MAX_VALUE);
        contentContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);

        headerBox.getChildren().addAll(
                headerLabel,
                createSpacer(),
                saveButton
        );

        final Label languageLabel = new Label("Language");
        final Label defaultStartLabel = new Label("Start with");
        final Label autoSaveLabel = new Label("Auto Save");
        final Label cloudSaveLabel = new Label("Projects in Cloud");
        final Label directoriesLabel = new Label("Directories");

        // Language

        languages.getItems().addAll(
                "English",
                "Polish",
                "Espanol"
        );
        languages.getSelectionModel().select(0);
        languageBox.getChildren().addAll(
                languageLabel,
                createSpacer(),
                languages
        );

        // Start with

        final RadioButton startEmptyButton = new RadioButton("Start Empty");
        final RadioButton startWithLatestProject = new RadioButton("Latest Project");

        startEmptyButton.setToggleGroup(starters);
        startWithLatestProject.setToggleGroup(starters);
        startEmptyButton.setSelected(true);

        defaultStartBox.getChildren().addAll(
                defaultStartLabel,
                createSpacer(),
                new VBox(10, startEmptyButton, startWithLatestProject)
        );

        // Autosave

        autosave.getItems().addAll(
                "Off",
                "1 min",
                "2 min",
                "5 min",
                "10 min",
                "15 min",
                "30 min",
                "45 min",
                "1 hour"
        );
        autosave.getSelectionModel().select(0);
        autosaveBox.getChildren().addAll(
                autoSaveLabel,
                createSpacer(),
                autosave
        );

        // Cloud save

        final RadioButton cloudEveryButton = new RadioButton("Every");
        final RadioButton cloudSelectedButton = new RadioButton("Only selected");
        final RadioButton cloudNoneButton = new RadioButton("None");

        cloudEveryButton.setToggleGroup(cloud);
        cloudSelectedButton.setToggleGroup(cloud);
        cloudNoneButton.setToggleGroup(cloud);
        cloudNoneButton.setSelected(false);

        cloudSaveBox.getChildren().addAll(
                cloudSaveLabel,
                createSpacer(),
                new VBox(10, cloudEveryButton, cloudSelectedButton, cloudNoneButton)
        );
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/MUSE/desktop/style/settings-view.css")).toExternalForm());
        this.getStyleClass().add("settings-view");

        contentContainer.getStyleClass().add("settings-content");
        headerBox.getStyleClass().add("header-box");
        headerLabel.getStyleClass().add("settings-header");
        saveButton.getStyleClass().add("settings-save-button");

        optionBoxes.forEach(box -> box.getStyleClass().add("option-box"));
        optionBoxes.forEach(box -> box.getChildren().getFirst().getStyleClass().add("option-header"));
    }

    @Override
    public void setupLayout() {
        this.setContent(contentContainer);

        contentContainer.getChildren().add(headerBox);
        contentContainer.getChildren().addAll(optionBoxes);
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }

    private Region createSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        return region;
    }
}
