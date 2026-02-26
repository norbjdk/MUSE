package com.muse.desktop.component;

import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.util.ButtonFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class ProfileBar extends GridPane implements Presentable {
    private Image profilePicture;
    private String username;
    private String status;
    private boolean isLoggedIn;

    private Circle pictureView;
    private Label usernameLabel;
    private Label statusLabel;
    private Button loginBtn;
    private Button logoutBtn;
    private VBox textContainer;
    private HBox buttonContainer;

    public ProfileBar() {
        setAlignment(Pos.TOP_RIGHT);
        loadData("norbjdk", "online", "/com/muse/desktop/assets/me.jpg", true);
        present();
        System.out.println("[MUSE] Initialized ProfileBar");
    }

    @Override
    public void initComponents() {
        loginBtn = ButtonFactory.createButton("Login", "login-btn", "Login", "profile-btn");
        logoutBtn = ButtonFactory.createButton("Logout", "logout-btn", "Logout", "logout-btn");

        usernameLabel = new Label();
        usernameLabel.getStyleClass().add("profile-username");

        statusLabel = new Label();
        statusLabel.getStyleClass().add("profile-status");

        pictureView = new Circle();
        pictureView.setRadius(25);

        textContainer = new VBox(5);
        textContainer.setAlignment(Pos.CENTER_LEFT);

        buttonContainer = new HBox(10);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
    }

    @Override
    public void setupComponents() {
        usernameLabel.setText(username);
        statusLabel.setText(status);

        if (profilePicture != null) {
            pictureView.setFill(new ImagePattern(profilePicture));
        }

        textContainer.getChildren().addAll(usernameLabel, statusLabel);

        updateButtonsVisibility();
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/style/side-bar.css")).toExternalForm());
        this.getStyleClass().add("profile-bar");

        textContainer.getStyleClass().add("profile-text-container");
        buttonContainer.getStyleClass().add("profile-button-container");

        logoutBtn.getStyleClass().add("profile-btn");
        loginBtn.getStyleClass().add("profile-btn");
        statusLabel.getStyleClass().add("profile-status");
        usernameLabel.getStyleClass().add("profile-username");

        DropShadow shadow = new DropShadow(8, Color.gray(0.5));
        pictureView.setEffect(shadow);
    }

    @Override
    public void setupLayout() {
        this.setHgap(15);
        this.setVgap(5);
        this.setPadding(new Insets(10));

        add(pictureView, 0, 0, 1, 2);

        add(textContainer, 1, 0, 1, 2);

        add(buttonContainer, 2, 0, 1, 2);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();

        this.getColumnConstraints().addAll(col1, col2, col3);
    }

    @Override
    public void setupEventListeners() {
        usernameLabel.textProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("[MUSE] Username changed from " + oldVal + " to " + newVal);
        });

        statusLabel.textProperty().addListener((obs, oldVal, newVal) -> {
            updateStatusStyle(newVal);
        });
    }

    @Override
    public void setupEventHandlers() {
        loginBtn.setOnAction(e -> {
            System.out.println("[MUSE] Login clicked");
            setLoggedIn(true);
            redraw();
        });

        logoutBtn.setOnAction(e -> {
            System.out.println("[MUSE] Logout clicked");
            setLoggedIn(false);
            redraw();
        });

        pictureView.setOnMouseClicked(e -> {
            System.out.println("[MUSE] Profile picture clicked");
        });
    }

    private void loadData(String username, String status, String picturePath, boolean loggedIn) {
        this.username = username;
        this.status = status;
        this.isLoggedIn = loggedIn;

        if (picturePath != null && !picturePath.isEmpty()) {
            try {
                this.profilePicture = new Image(
                        Objects.requireNonNull(getClass().getResource(picturePath)).toExternalForm(),
                        50, 50, true, true
                );
            } catch (Exception e) {
                System.err.println("[MUSE] Failed to load profile picture: " + e.getMessage());
            }
        }
    }

    private void updateButtonsVisibility() {
        buttonContainer.getChildren().clear();

        if (isLoggedIn) {
            buttonContainer.getChildren().add(logoutBtn);
        } else {
            buttonContainer.getChildren().add(loginBtn);
        }
    }

    private void updateStatusStyle(String status) {
        switch (status.toLowerCase()) {
            case "online":
                statusLabel.setStyle("-fx-text-fill: green");
                break;
            case "offline":
                statusLabel.setTextFill(Color.GRAY);
                break;
            case "busy":
                statusLabel.setTextFill(Color.RED);
                break;
            default:
                statusLabel.setTextFill(Color.BLACK);
        }
    }

    public void setUserData(String username, String status, String picturePath) {
        this.username = username;
        this.status = status;

        if (picturePath != null) {
            try {
                this.profilePicture = new Image(
                        Objects.requireNonNull(getClass().getResource(picturePath)).toExternalForm(),
                        50, 50, true, true
                );
            } catch (Exception e) {
                System.err.println("[MUSE] Failed to update profile picture: " + e.getMessage());
            }
        }

        redraw();
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        updateButtonsVisibility();
    }

    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText(username);
    }

    public void setStatus(String status) {
        this.status = status;
        statusLabel.setText(status);
        updateStatusStyle(status);
    }

    public void setProfilePicture(String picturePath) {
        if (picturePath != null) {
            try {
                this.profilePicture = new Image(
                        Objects.requireNonNull(getClass().getResource(picturePath)).toExternalForm(),
                        50, 50, true, true
                );
                pictureView.setFill(new ImagePattern(profilePicture));
            } catch (Exception e) {
                System.err.println("[MUSE] Failed to update profile picture: " + e.getMessage());
            }
        }
    }

    public void redraw() {
        setupComponents();

        updateStatusStyle(status);

        this.requestLayout();
    }

    public String getUsername() { return username; }
    public String getStatus() { return status; }
    public boolean isLoggedIn() { return isLoggedIn; }
}