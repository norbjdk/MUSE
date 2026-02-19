package com.muse.desktop.controller;

import com.muse.desktop.component.NavBar;
import com.muse.desktop.component.WindowBar;
import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.util.FXMLUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, Presentable {
    @FXML
    private BorderPane rootContainer;
    private VBox headerContainer;
    private WindowBar windowBar;
    private NavBar navBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        present();
        System.out.println("[MUSE] Initialized MainController");
    }

    @Override
    public void initComponents() {
        headerContainer = new VBox();
        windowBar = new WindowBar();
        navBar = new NavBar();
    }

    @Override
    public void setupComponents() {
        try {
            headerContainer.getChildren().addAll(
                    windowBar,
                    navBar
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setupStyle() {

    }

    @Override
    public void setupLayout() {
        rootContainer.setTop(headerContainer);
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }
}
