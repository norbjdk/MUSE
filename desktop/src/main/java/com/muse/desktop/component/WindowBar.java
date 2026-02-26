package com.muse.desktop.component;

import com.muse.desktop.MUSE;
import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.util.ButtonFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.Objects;

public class WindowBar extends HBox implements Presentable {
    private Button  minimizeButton;
    private Button  maximizeButton;
    private Button  closeButton;
    private Label   titleLabel;
    private MenuBar menuBar;

    public WindowBar() {
        present();
    }

    @Override
    public void initComponents() {
        menuBar = new MenuBar();
        titleLabel = new Label("MUSE");
        closeButton = ButtonFactory.createButton("", "closeBtn", "Close App", "window-btn");
        maximizeButton = ButtonFactory.createButton("", "maximizeBtn", "Maximize App", "window-btn");
        minimizeButton = ButtonFactory.createButton("", "minimizeBtn", "Minimize App", "window-btn");
    }

    @Override
    public void setupComponents() {
        final Menu fileMenu = new Menu("File");
        final Menu editMenu = new Menu("Edit");
        final Menu viewMenu = new Menu("View");
        final Menu addMenu = new Menu("Add");
        final Menu formatMenu = new Menu("Format");
        final Menu toolsMenu = new Menu("Tools");
        final Menu helpMenu = new Menu("Help");

        menuBar.getMenus().addAll(
                fileMenu,
                editMenu,
                viewMenu,
                addMenu,
                formatMenu,
                toolsMenu,
                helpMenu
        );

        ButtonFactory.addIcon(minimizeButton, FontAwesomeSolid.MINUS, 14, Color.rgb(41, 41, 41));
        ButtonFactory.addIcon(maximizeButton, FontAwesomeSolid.WINDOW_RESTORE, 14, Color.rgb(41, 41, 41));
        ButtonFactory.addIcon(closeButton, FontAwesomeSolid.TIMES, 14, Color.rgb(41, 41, 41));
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/window-bar.css")).toExternalForm());
        this.getStyleClass().add("window-bar");

        titleLabel.getStyleClass().add("window-title");
    }

    @Override
    public void setupLayout() {
        final HBox windowButtons = new HBox(1, minimizeButton, maximizeButton, closeButton);
        windowButtons.setStyle("-fx-padding: 4px 4px 0px 0px");

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(5);
        this.getChildren().addAll(
                menuBar,
                createSpacer(),
                titleLabel,
                createSpacer(),
                windowButtons
        );
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {
        MUSE.applyApplicationMovement(this);
        closeButton.setOnAction(actionEvent -> MUSE.close());
    }

    private Region createSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        return region;
    }
}
