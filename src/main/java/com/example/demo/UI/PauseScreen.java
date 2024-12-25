package com.example.demo.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.example.demo.controller.Controller;

/**
 * PauseScreen is for displaying the pause menu in the game.
 * It provides options to resume the game, access settings, or exit the game.
 */
public class PauseScreen {

    private final Stage stage;
    private final Scene mainScene;
    private final Runnable settings;
    private final Runnable resume;
    private final Controller controller;
    private static final String PAUSE_IMAGE = "/com/example/demo/images/pause.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";
    private static final String SETTING_IMAGE = "/com/example/demo/images/setting.png";
    private static final String TRIANGLE_MOUSE_ICON = "/com/example/demo/images/triangle_mouse.png";
    private static final String MOUSE_ICON = "/com/example/demo/images/mouse_icon.png";

    /**
     * Create PauseScreen.
     *
     * @param stage           The primary stage of the game.
     * @param controller      The controller managing game events.
     * @param resumeAction    The action to perform when resuming the game.
     * @param settingsAction  The action to perform when accessing settings.
     */
    public PauseScreen(Stage stage, Controller controller, Runnable resumeAction, Runnable settingsAction) {
        this.stage = stage;
        this.mainScene = stage.getScene();
        this.controller = controller;
        this.settings = settingsAction;
        this.resume = resumeAction;
    }

    /**
     * Displays the pause screen with options to resume, access settings, or exit.
     */
    public void show() {
        Stage pauseStage = new Stage();
        //Make the default box transparent
        pauseStage.initStyle(StageStyle.TRANSPARENT);

        //Root layout with background image
        BorderPane rootLayout = new BorderPane();
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(this.getClass().getResource(PAUSE_IMAGE).toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            // Ensure to fill the entire screen
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        rootLayout.setBackground(new Background(backgroundImage));

        //Center layout with buttons
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        //Load the custom pixel font
        Font titleFont = null;
        try {
            titleFont = Font.loadFont(this.getClass().getResource(PIXEL_FONT).toExternalForm(), 40);
            if (titleFont == null) {
                System.err.println("Error loading the font");
            }
        } catch (Exception e) {
            System.err.println("Error loading font: " + e.getMessage());
        }

        //Title Label
        Label titleLabel = new Label("Game Paused");
        if (titleFont != null) {
            titleLabel.setFont(titleFont);
        }
        titleLabel.setStyle("-fx-text-fill: #000000;");

        //Load the custom pixel font
        Font buttonFont = Font.loadFont(this.getClass().getResource(PIXEL_FONT).toExternalForm(), 20);
        // Load cursor images
        Image triangleCursorImage = new Image(this.getClass().getResource(TRIANGLE_MOUSE_ICON).toExternalForm());
        Image hoverCursorImage = new Image(this.getClass().getResource(MOUSE_ICON).toExternalForm());
        ImageCursor triangleCursor = new ImageCursor(triangleCursorImage);
        ImageCursor hoverCursor = new ImageCursor(hoverCursorImage);

        //Resume button
        Button resumeButton = createStyledButton("Resume", buttonFont, hoverCursor, triangleCursor);
        //Ensure other keys does not trigger the screen 
        resumeButton.setFocusTraversable(false);
        if (buttonFont != null) {
            resumeButton.setFont(buttonFont); 
        }
        resumeButton.setOnAction(event -> {
            if (resume != null) {
                resume.run();
            }
            pauseStage.close();
        });
    
        //Settings button
        Button settingsButton = createStyledButton("Settings", buttonFont, hoverCursor, triangleCursor);
        //Ensure other keys does not trigger the screen 
        settingsButton.setFocusTraversable(false);
        if (buttonFont != null) {
            settingsButton.setFont(buttonFont); 
        }
        settingsButton.setOnAction(event -> {
            if (settings != null) {
                settings.run();
                showSettings();
            }
        });

        //Exit button
        Button exitButton = createStyledButton("Exit", buttonFont, hoverCursor, triangleCursor);
        //Ensure other keys does not trigger the screen 
        exitButton.setFocusTraversable(false);
        if (buttonFont != null) {
            exitButton.setFont(buttonFont); 
        }
        exitButton.setOnAction(event -> {
            System.out.println("Exit game");
            pauseStage.close();
            stage.close();
        });

        //Add buttons and title label to the layout
        layout.getChildren().addAll(titleLabel, resumeButton, settingsButton, exitButton);

        //Set layout to the center of the root
        rootLayout.setCenter(layout);

        //Create scene with transparency
        Scene pauseScene = new Scene(rootLayout, 600, 400);
        // Set the custom cursor
        Image cursorImage = new Image(this.getClass().getResource(TRIANGLE_MOUSE_ICON).toExternalForm());
        pauseScene.setCursor(new ImageCursor(cursorImage));

        pauseScene.setFill(null);

        //Set scene to the stage
        pauseStage.setScene(pauseScene);

        //Show the pause screen until it is closed
        pauseStage.showAndWait();
    }

     /**
     * Creates a styled button with hover and default cursor effects.
     *
     * @param text           The text to display on the button.
     * @param font           The font for the button.
     * @param hoverCursor    The cursor displayed when hovering the button.
     * @param defaultCursor  The default cursor for the button.
     * @return The styled button.
     */
    private Button createStyledButton(String text, Font font, ImageCursor hoverCursor, ImageCursor defaultCursor) {
        Button button = new Button(text);
        button.setFont(font);
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setStyle(
            "-fx-background-image: url('" + getClass().getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: #000000;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"
        );

        // Set hover and default cursors
        button.setOnMouseEntered(event -> button.setCursor(hoverCursor));
        button.setOnMouseExited(event -> button.setCursor(defaultCursor));

        return button;
    }

    /**
     * Displays the settings screen where users can adjust the volume.
     */
    public void showSettings() {
        //Create new screen for settings
        Stage settingsStage = new Stage();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initStyle(StageStyle.TRANSPARENT);

        //Root layout with background image
        BorderPane rootLayout = new BorderPane();
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(this.getClass().getResource(SETTING_IMAGE).toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        rootLayout.setBackground(new Background(backgroundImage));

        //Top section with custom close button
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10));
        //Create a close button for instructions screen
        Button closeButton = new Button();
        closeButton.setStyle(
            "-fx-background-color: transparent; -fx-border-width: 0;"+
            "-fx-background-size: 50px 40px;"
        );
        //Set the custom image for button
        ImageView Closebutton = new ImageView(
        new Image(this.getClass().getResource("/com/example/demo/images/x_button.png").toExternalForm())
        );
        //Resize button X
        Closebutton.setFitWidth(30);
        Closebutton.setFitHeight(30);
        //Set resized image as graphic
        closeButton.setGraphic(Closebutton);
        
        closeButton.setOnAction(event -> settingsStage.close());
        topBar.getChildren().add(closeButton);
        //Add top bar to root layout
        rootLayout.setTop(topBar);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        //Load the custom pixel font
        Font buttonFont = null;
        try {
            buttonFont = Font.loadFont(this.getClass().getResource(PIXEL_FONT).toExternalForm(), 20);
            if (buttonFont == null) {
                System.err.println("Error loading font for settings.");
            }
        } catch (Exception e) {
            System.err.println("Error loading font: " + e.getMessage());
        }
    
        //Volume slider
        Label volumeLabel = new Label("Volume");
        if (buttonFont != null) {
            volumeLabel.setFont(buttonFont); 
        }
        volumeLabel.setStyle("-fx-text-fill: #000000;");

        Slider volumeSlider = new Slider(0, 1, controller.getMediaPlayer().getVolume());
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setBlockIncrement(0.1);
        //Adjust the size of slider
        volumeSlider.setMaxWidth(300); 
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) ->
            controller.setVolume(newValue.doubleValue())
        );

        layout.getChildren().addAll(volumeLabel, volumeSlider);

        rootLayout.setCenter(layout);

        //Create scene for settings
        Scene settingsScene = new Scene(rootLayout, 400, 300);
        settingsScene.setFill(null);
        settingsStage.setScene(settingsScene);
        settingsStage.showAndWait();
    }

}
