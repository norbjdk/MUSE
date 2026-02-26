package com.muse.desktop.view;

import com.muse.desktop.model.dto.internal.DocumentMetadata;
import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.model.ui.ViewContainer;
import com.muse.desktop.service.DocumentListPersistence;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CollectionView extends VBox implements Presentable, ViewContainer {

    private ObservableList<DocumentMetadata> documentList;
    private DocumentListPersistence persistence;

    private Label header;
    private ScrollPane scrollPane;
    private FlowPane contentContainer;

    public CollectionView() {
        String appDataDir = getAppDataDirectory();
        persistence = new DocumentListPersistence(appDataDir);

        List<DocumentMetadata> loadedList = persistence.load();
        documentList = FXCollections.observableArrayList(loadedList);

        present();

        addSampleDocuments();
    }

    @Override
    public void initComponents() {
        header = new Label("Your Collection");
        scrollPane = new ScrollPane();
        contentContainer = new FlowPane();
    }

    @Override
    public void setupComponents() {
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(contentContainer);
        contentContainer.setAlignment(Pos.TOP_CENTER);
        contentContainer.setHgap(25);
        contentContainer.setVgap(25);
        contentContainer.setPadding(new Insets(20));
        contentContainer.setOrientation(Orientation.HORIZONTAL);
        refreshView();
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/collection-view.css")).toExternalForm());
        this.getStyleClass().add("collection-view");

        header.getStyleClass().add("collection-header");
    }

    @Override
    public void setupLayout() {
        getChildren().addAll(header, scrollPane);
    }

    @Override
    public void setupEventListeners() {
        documentList.addListener((ListChangeListener<DocumentMetadata>) c -> {
            refreshView();
        });
    }

    @Override
    public void setupEventHandlers() {

    }

    private void refreshView() {
        contentContainer.getChildren().clear();
        for (DocumentMetadata data : documentList) {
            contentContainer.getChildren().add(createProjectCard(data));
        }
    }

    private VBox createProjectCard(DocumentMetadata data) {
        final VBox container = new VBox(10);
        container.getStyleClass().add("project-card");

        final DropShadow shadow = new DropShadow(8, Color.gray(0.5));
        container.setEffect(shadow);

        final ImageView scoreView = new ImageView(new Image(Objects.requireNonNull(
                getClass().getResource("/com/muse/desktop/assets/score.png")).toExternalForm()));
        scoreView.setSmooth(true);
        scoreView.setFitHeight(200);
        scoreView.setFitWidth(150);

        final Label titleLabel = new Label(data.getTitle());
        final Label modifiedLabel = new Label("Last modified: " + data.getLastModified().toLocalDate().toString());

        titleLabel.getStyleClass().add("project-title");
        modifiedLabel.getStyleClass().add("project-modification");

        container.getChildren().addAll(
                scoreView,
                titleLabel,
                modifiedLabel
        );

        return container;
    }

    private String getAppDataDirectory() {
        String userHome = System.getProperty("user.home");
        String appDataDir;
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            appDataDir = userHome + "\\AppData\\Roaming\\MUSE\\";
        } else if (osName.contains("mac")) {
            appDataDir = userHome + "/Library/Application Support/MUSE/";
        } else {
            appDataDir = userHome + "/.local/share/MUSE/";
        }

        try {
            Files.createDirectories(Paths.get(appDataDir));
        } catch (IOException e) {
            throw new RuntimeException("Nie można utworzyć katalogu aplikacji: " + appDataDir, e);
        }

        return appDataDir;
    }

    public void addDocument(DocumentMetadata data) {
        documentList.add(data);
        persistence.save(documentList);
    }

    private void addSampleDocuments() {
        for (int i = 0; i < 20; i++) {
            DocumentMetadata data = new DocumentMetadata(
                    "C:\\nowy\\plik" + i + ".xml",
                    "score " + i,
                    LocalDateTime.now().minusDays(i)
            );
            documentList.add(data);
        }
        refreshView();
    }
}