package com.muse.desktop.component;

import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.util.ButtonFactory;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.Objects;

public class NavBar extends HBox implements Presentable {

    private Button homeBtn;
    private Button createProjectBtn;
    private Button openProjectBtn;
    private Button collectionBtn;
    private Button learnBtn;
    private Button settingsBtn;
    private Button currentProjectBtn;

    public NavBar() {
        present();
        System.out.println("[MUSE] Initialized NavBar");
    }

    @Override
    public void initComponents() {
        homeBtn = ButtonFactory.createButton("Home", "home-btn", "Home View", "nav-btn");
        createProjectBtn = ButtonFactory.createButton("New Project", "home-btn", "Home View", "nav-btn");
        openProjectBtn = ButtonFactory.createButton("Open", "home-btn", "Home View", "nav-btn");
        collectionBtn = ButtonFactory.createButton("Collection", "home-btn", "Home View", "nav-btn");
        learnBtn = ButtonFactory.createButton("Learn", "home-btn", "Home View", "nav-btn");
        settingsBtn = ButtonFactory.createButton("Settings", "home-btn", "Home View", "nav-btn");
        currentProjectBtn = ButtonFactory.createButton("Home", "home-btn", "Home View", "nav-btn");
    }

    @Override
    public void setupComponents() {
        ButtonFactory.addIcon(homeBtn, FontAwesomeSolid.HOME, 15, Color.rgb(255, 255, 255));
        ButtonFactory.addIcon(createProjectBtn, FontAwesomeSolid.PLUS_CIRCLE, 15, Color.rgb(255, 255, 255));
        ButtonFactory.addIcon(openProjectBtn, FontAwesomeSolid.FOLDER_OPEN, 15, Color.rgb(255, 255, 255));
        ButtonFactory.addIcon(collectionBtn, FontAwesomeSolid.LAYER_GROUP, 15, Color.rgb(255, 255, 255));
        ButtonFactory.addIcon(learnBtn, FontAwesomeSolid.GRADUATION_CAP, 15, Color.rgb(255, 255, 255));
        ButtonFactory.addIcon(settingsBtn, FontAwesomeSolid.COG, 15, Color.rgb(255, 255, 255));
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/nav-bar.css")).toExternalForm());
        this.getStyleClass().add("nav-bar");
    }

    @Override
    public void setupLayout() {
        getChildren().addAll(
                homeBtn,
                createProjectBtn,
                openProjectBtn,
                createSpacer(),
                collectionBtn,
                learnBtn,
                settingsBtn
        );
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
