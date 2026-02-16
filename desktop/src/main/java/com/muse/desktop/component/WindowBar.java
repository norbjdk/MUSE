package com.muse.desktop.component;

import com.muse.desktop.model.ui.Presentable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class WindowBar extends HBox implements Presentable {
    private Button  minimizeButton;
    private Button  maximizeButton;
    private Button  closeButton;
    private Label   titleLabel;
    private MenuBar menuBar;

    public WindowBar() {
        present();
        System.out.println("[MUSE] Initialized WindowBar");
    }

    @Override
    public void initComponents() {
        menuBar = new MenuBar();
        titleLabel = new Label();
        closeButton = new Button();
        maximizeButton = new Button();
        minimizeButton = new Button();
    }

    @Override
    public void setupComponents() {
        final Menu fileMenu = new Menu("File");
        final Menu editMenu = new Menu("Edit");

        menuBar.getMenus().addAll(
                fileMenu,
                editMenu
        );
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/window-bar.css")).toExternalForm());
        this.getStyleClass().add("window-bar");
    }

    @Override
    public void setupLayout() {

    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }
}
