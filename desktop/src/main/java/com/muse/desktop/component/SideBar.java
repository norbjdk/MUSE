package com.muse.desktop.component;

import com.muse.desktop.model.ui.Presentable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.Objects;

public class SideBar extends GridPane implements Presentable {
    ProfileBar profileBar;
    FriendsList friendsList;

    public SideBar() {
        present();
        System.out.println("[MUSE] Initialized SideBar");
    }

    @Override
    public void initComponents() {
        profileBar = new ProfileBar();
        friendsList = new FriendsList();
    }

    @Override
    public void setupComponents() {

    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/side-bar.css")).toExternalForm());
        this.getStyleClass().add("side-bar");
    }

    @Override
    public void setupLayout() {
        RowConstraints firstRow = new RowConstraints();
        RowConstraints secondRow = new RowConstraints();

        firstRow.setPercentHeight(10);
        secondRow.setPercentHeight(90);
        secondRow.setVgrow(Priority.ALWAYS);

        getRowConstraints().addAll(firstRow, secondRow);

        add(profileBar, 0, 0);
        add(friendsList, 0, 1);
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }
}
