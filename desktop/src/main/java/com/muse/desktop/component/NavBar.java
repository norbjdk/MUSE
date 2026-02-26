package com.muse.desktop.component;

import com.muse.desktop.model.dto.internal.ViewRequest;
import com.muse.desktop.model.event.ChangeViewRequestedEvent;
import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.model.ui.ViewName;
import com.muse.desktop.service.EventBus;
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

import javax.swing.text.View;
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
        ButtonFactory.addIcon(homeBtn, FontAwesomeSolid.HOME, 15, Color.rgb(5, 5, 5));
        ButtonFactory.addIcon(createProjectBtn, FontAwesomeSolid.PLUS_CIRCLE, 15, Color.rgb(5, 5, 5));
        ButtonFactory.addIcon(openProjectBtn, FontAwesomeSolid.FOLDER_OPEN, 15, Color.rgb(5, 5, 5));
        ButtonFactory.addIcon(collectionBtn, FontAwesomeSolid.LAYER_GROUP, 15, Color.rgb(5, 5, 5));
        ButtonFactory.addIcon(learnBtn, FontAwesomeSolid.GRADUATION_CAP, 15, Color.rgb(5, 5, 5));
        ButtonFactory.addIcon(settingsBtn, FontAwesomeSolid.COG, 15, Color.rgb(5, 5, 5));
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
        homeBtn.setOnAction(actionEvent -> handleHomeButtonClicked());
        createProjectBtn.setOnAction(actionEvent -> handleNewProjectButtonClicked());
        collectionBtn.setOnAction(actionEvent -> handleCollectionButtonClicked());
        settingsBtn.setOnAction(actionEvent -> handleSettingsButtonClicked());
    }

    private Region createSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        return region;
    }

    private void handleHomeButtonClicked() {
        EventBus.getInstance().publish(new ChangeViewRequestedEvent(new ViewRequest(ViewName.HOME)));
    }

    private void handleSettingsButtonClicked() {
        EventBus.getInstance().publish(new ChangeViewRequestedEvent(new ViewRequest(ViewName.SETTINGS)));
    }

    private void handleCollectionButtonClicked() {
        EventBus.getInstance().publish(new ChangeViewRequestedEvent(new ViewRequest(ViewName.COLLECTION)));
    }

    private void handleNewProjectButtonClicked() {
        EventBus.getInstance().publish(new ChangeViewRequestedEvent(new ViewRequest(ViewName.NEW_PROJECT)));
    }
}
