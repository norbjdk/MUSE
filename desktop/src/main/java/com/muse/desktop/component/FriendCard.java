package com.muse.desktop.component;

import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.util.ButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.Objects;

public class FriendCard extends GridPane implements Presentable {
    private final String username;
    private final String status;
    private final String picturePath;

    private Label usernameLabel;
    private Label statusLabel;
    private Circle pictureView;
    private Button addFriend;

    public FriendCard(String username, String status, String picturePath) {
        this.username = username;
        this.status = status;
        this.picturePath = picturePath;

        present();
    }

    @Override
    public void initComponents() {
        usernameLabel = new Label(username);
        statusLabel = new Label(status);
        pictureView = new Circle(25);
        addFriend = ButtonFactory.createButton("", "add-friend", "Add User to Project", "add-btn");
    }

    @Override
    public void setupComponents() {
        if (!picturePath.isEmpty()) {
            Image picture = new Image(Objects.requireNonNull(getClass().getResource(picturePath)).toExternalForm());
            pictureView.setFill(new ImagePattern(picture));
        }

        ButtonFactory.addIcon(addFriend, FontAwesomeSolid.HANDSHAKE, 14, Color.rgb(15, 15, 15));
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/side-bar.css")).toExternalForm());
        this.getStyleClass().add("friend-card");

        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void setupLayout() {
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();

        this.getColumnConstraints().addAll(col1, col2, col3);

        add(pictureView, 0, 0);
        add(new VBox(5, usernameLabel, statusLabel), 1, 0);
        add(addFriend, 2, 0);
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
