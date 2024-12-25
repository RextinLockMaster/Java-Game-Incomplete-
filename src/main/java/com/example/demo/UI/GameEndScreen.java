package com.example.demo.UI;

import com.example.demo.controller.Controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Represents the game end screen that appears when the game is over.
 * It displays the final score and allows the user to return to the main menu.
 */
public class GameEndScreen {

    private static boolean isGameEndScreenVisible = false;
    private static Stage gameEndStage = null;

    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/pause.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";

    private GameEndScreen() {
        
    }

    /**
     * Displays the game end screen with the final score, achievement earned and options to return to the main menu.
     *
     * @param displayStage The primary stage on which the game is displayed.
     * @param score        The final score win by the player.
     */
    public static void showGameEndScreen(Stage displayStage, int score) {
        if (isGameEndScreenVisible) {
            return;
        }

        isGameEndScreenVisible = true;

        Platform.runLater(() -> {
            try {
                gameEndStage = new Stage();
                gameEndStage.initModality(Modality.APPLICATION_MODAL);
                gameEndStage.initStyle(StageStyle.TRANSPARENT);
                gameEndStage.initOwner(displayStage);

                BorderPane rootLayout = new BorderPane();

                // Set background image
                try {
                    BackgroundImage backgroundImage = new BackgroundImage(
                        new Image(GameEndScreen.class.getResource(BACKGROUND_IMAGE).toExternalForm()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)
                    );
                    rootLayout.setBackground(new Background(backgroundImage));
                } catch (Exception e) {
                    System.out.println("Error loading background image: " + e.getMessage());
                }

                VBox layout = new VBox(20);
                layout.setAlignment(Pos.CENTER);
                layout.setPadding(Insets.EMPTY);
                rootLayout.setPadding(Insets.EMPTY);

               // Load fonts
               Font titleFont = loadCustomFont(PIXEL_FONT, 40);
               Font buttonFont = loadCustomFont(PIXEL_FONT, 20);

                Label titleLabel = new Label("Game Over");
                titleLabel.setFont(titleFont);
                titleLabel.setStyle("-fx-text-fill: #000000;");

                Label scoreLabel = new Label("Your Score: " + score);
                scoreLabel.setFont(buttonFont);
                scoreLabel.setStyle("-fx-text-fill: #000000;");

                // Achievement label
                Label achievementLabel = new Label("No achievement earned");
                achievementLabel.setFont(buttonFont);
                achievementLabel.setStyle("-fx-text-fill: grey;"); 

                Button mainMenuButton = new Button("Return to Menu");
                mainMenuButton.setFont(buttonFont);
                mainMenuButton.setPrefWidth(200);
                mainMenuButton.setPrefHeight(50);
                mainMenuButton.setStyle(
                    "-fx-background-image: url('" + GameEndScreen.class.getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
                    "-fx-background-size: 100% 100%;" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-text-fill: #000000;" +
                    "-fx-alignment: center;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-width: 0;"
                );
                //Ensure other keys does not trigger the screen 
                mainMenuButton.setFocusTraversable(false);
                mainMenuButton.setOnAction(event -> {
                    gameEndStage.close();
                    isGameEndScreenVisible = false;
                    displayStage.setWidth(1300);  
                    displayStage.setHeight(730);
                    Controller controller = new Controller(displayStage); 
                    new MainMenu(displayStage, controller).show();
                });
                

                layout.getChildren().addAll(titleLabel, scoreLabel,achievementLabel, mainMenuButton);
                rootLayout.setCenter(layout);

                Scene gameEndScene = new Scene(rootLayout, 500, 500);
                //Make the scene transparent
                gameEndScene.setFill(null);
                gameEndStage.setScene(gameEndScene);

                gameEndStage.setOnHidden(event -> isGameEndScreenVisible = false);

                gameEndStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Loads a custom font from the file path.
     *
     * @param fontPath The path to the custom font.
     * @param size     The size of the font.
     * @return The loaded Font object, or a default font if cannot load.
     */
     private static Font loadCustomFont(String fontPath, int size) {
        try {
            return Font.loadFont(GameWinScreen.class.getResource(fontPath).toExternalForm(), size);
        } catch (Exception e) {
            System.err.println("Error loading font: " + fontPath);
            e.printStackTrace();
            return Font.font("Arial", size); // Default font fallback
        }
    }


    
}
