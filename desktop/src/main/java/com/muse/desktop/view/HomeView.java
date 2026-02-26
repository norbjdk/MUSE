package com.muse.desktop.view;

import com.muse.desktop.model.ui.Presentable;
import com.muse.desktop.model.ui.ViewContainer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;

public class HomeView extends ScrollPane implements Presentable, ViewContainer {
    private VBox contentContainer;
    private Label headerLabel;
    private HBox heroContainer;

    public HomeView() {
        present();
    }

    @Override
    public void initComponents() {
        contentContainer = new VBox();
        headerLabel = new Label("Welcome to MUSE");
        heroContainer = new HBox();
        heroContainer = new AnchorPane();
        heroImageContainer = new StackPane();
        encourageContainer = new VBox();
    }

    @Override
    public void setupComponents() {
        contentContainer.setAlignment(Pos.TOP_CENTER);
        contentContainer.setMinHeight(1000);

        headerLabel.setMaxWidth(Double.MAX_VALUE);
        headerLabel.setAlignment(Pos.CENTER_LEFT);

        // Hero Section
        final StackPane heroImageContainer = new StackPane();
        // Hero Section

        final Image heroImage = new Image(Objects.requireNonNull(getClass().getResource("/com/muse/desktop/assets/hero.jpg")).toExternalForm());
        final Rectangle heroImageView = new Rectangle();

        heroImageView.setWidth(550);
        heroImageView.setHeight(320);
        heroImageView.setArcWidth(30);
        heroImageView.setArcHeight(20);
        heroImageView.setFill(new ImagePattern(heroImage));

        heroImageContainer.getChildren().add(heroImageView);
        heroImageContainer.setEffect(createShadow(BlurType.THREE_PASS_BOX, Color.gray(0.5), 8));

        final VBox encourageContainer = new VBox();
        final Label heroHeader = new Label("Compose without limits");
        final Label heroParagraph = new Label("If you want to create new cool music sheets alone or with your band, this is the perfect place for you!");

        heroHeader.getStyleClass().add("hero-header");
        heroParagraph.getStyleClass().add("hero-paragraph");

        encourageContainer.getChildren().addAll(
                heroHeader,
                heroParagraph
        );

        heroContainer.getChildren().addAll(
                heroImageContainer,
                createSpacer(),
                encourageContainer
        );
    }

    @Override
    public void setupStyle() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/MUSE/desktop/style/home-view.css")).toExternalForm());
        this.getStyleClass().add("home-view");

        headerLabel.getStyleClass().add("home-header");
        heroContainer.getStyleClass().add("hero-content");
    }

    @Override
    public void setupLayout() {
        this.setContent(contentContainer);

        contentContainer.getChildren().add(headerLabel);
        contentContainer.getChildren().add(heroContainer);
    }

    @Override
    public void setupEventListeners() {

    }

    @Override
    public void setupEventHandlers() {

    }

    private DropShadow createShadow(BlurType blurType, Color color, double radius) {
        DropShadow shadow = new DropShadow();

        shadow.setBlurType(blurType);
        shadow.setColor(color);
        shadow.setRadius(radius);

        return shadow;
    }

    private Region createSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        return region;
    }
}
