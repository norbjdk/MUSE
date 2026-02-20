package com.muse.desktop.component;

import com.muse.desktop.model.ui.Presentable;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendsList extends VBox implements Presentable {
    private final List<FriendCard> friendCards = new ArrayList<>();

    public FriendsList() {
        present();
        System.out.println("[MUSE] Initialized FriendsList");
    }

    @Override
    public void initComponents() {
        friendCards.add(new FriendCard("Frederic Chopin", "online", "/com/muse/desktop/assets/user.png"));
        friendCards.add(new FriendCard("Lang Lang", "online", "/com/muse/desktop/assets/user.png"));
        friendCards.add(new FriendCard("Thomas Kruger", "busy", "/com/muse/desktop/assets/user.png"));
        friendCards.add(new FriendCard("Peter Buka", "online", "/com/muse/desktop/assets/user.png"));
        friendCards.add(new FriendCard("Alan Roberts", "online", "/com/muse/desktop/assets/user.png"));
        friendCards.add(new FriendCard("Constantino Carrara", "busy", "/com/muse/desktop/assets/user.png"));
    }

    @Override
    public void setupComponents() {

    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/side-bar.css")).toExternalForm());
        this.getStyleClass().add("friends-list");
    }

    @Override
    public void setupLayout() {
        friendCards.forEach(friend -> getChildren().add(friend));
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }
}
