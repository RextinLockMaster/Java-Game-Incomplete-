package com.example.demo.UI;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.example.demo.GameState.GameState;
import com.example.demo.controller.Controller;
import com.example.demo.levels.LevelParent;
import com.example.demo.levels.LevelTwo;

/**
 * Represents the Game Win Screen, shown to the user upon successfully completing a level.
 * Provides options to proceed to the next level, return to the main menu, or access the shop.
 */
public class GameWinScreen {

    private static boolean isGameWinScreenVisible = false;
    private static Stage gameWinStage = null;

    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/pause.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";
    private static final String BUTTON_IMAGE = "/com/example/demo/images/grass_button.png";

    private GameWinScreen() {
        
    }

    /**
     * Displays the Game Win Screen for the level.
     *
     * @param displayStage   The stage where the Game Win Screen will be displayed.
     * @param score          The player's score.
     * @param currentLevel   The current level completed by the player.
     * @param onNextLevel    A runnable action to proceed to the next level.
     */
    public static void showGameWinScreen(Stage displayStage, int score, LevelParent currentLevel, Runnable onNextLevel) {
        if (isGameWinScreenVisible) {
            return;
        }

        isGameWinScreenVisible = true;

        Platform.runLater(() -> {
            try {
                gameWinStage = new Stage();
                gameWinStage.initModality(Modality.APPLICATION_MODAL);
                gameWinStage.initStyle(StageStyle.TRANSPARENT);
                gameWinStage.initOwner(displayStage);

                BorderPane rootLayout = new BorderPane();

                // Set background image
                try {
                    BackgroundImage backgroundImage = new BackgroundImage(
                        new Image(GameWinScreen.class.getResource(BACKGROUND_IMAGE).toExternalForm()),
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

                Label titleLabel = new Label("You Win!");
                titleLabel.setFont(titleFont);
                titleLabel.setStyle("-fx-text-fill: #000000;");

                Label scoreLabel = new Label("Your Score: " + score);
                scoreLabel.setFont(buttonFont);
                scoreLabel.setStyle("-fx-text-fill: #000000;");

               // Achievement label
                Label achievementLabel = null;
                if (GameState.getInstance().getAchievements().contains("All Enemies Defeated in Level One")) {
                    achievementLabel = new Label("Achievement: All Enemies Defeated");
                    achievementLabel.setFont(buttonFont);
                    achievementLabel.setStyle("-fx-text-fill: green;"); 
                }

                // Shop button
                Button shopButton = createStyledButton("Shop", buttonFont);
                //Ensure other keys does not trigger the screen 
                shopButton.setFocusTraversable(false);
                shopButton.setOnAction(event -> {
                    if (currentLevel instanceof LevelTwo) {
                        // Display a popup message when clicking the shop button in Level 2
                        Shop.showShopPopup("Shop", "Sorry, you can't purchase extra hearts for this level");
                    } else {
                        Shop shop = new Shop(displayStage, currentLevel); 
                        shop.show();
                    }
                });
                // Go to next level button
                Button nextLevelButton = createStyledButton("Go to Next Level", buttonFont);
                //Ensure other keys does not trigger the screen 
                nextLevelButton.setFocusTraversable(false);
                nextLevelButton.setFont(buttonFont);
                nextLevelButton.setOnAction(event -> {
                    gameWinStage.close();
                    isGameWinScreenVisible = false;
                    //Go to next level
                    onNextLevel.run();
                });
                // Return to main menu button
                Button mainMenuButton = createStyledButton("Return to Menu", buttonFont);
                 //Ensure other keys does not trigger the screen 
                 mainMenuButton.setFocusTraversable(false);
                mainMenuButton.setOnAction(event -> {
                    GameState.getInstance().resetAll(); 
                    gameWinStage.close();
                    isGameWinScreenVisible = false;
                    displayStage.setWidth(1300);  
                    displayStage.setHeight(730);
                    Controller controller = new Controller(displayStage); 
                    new MainMenu(displayStage, controller).show();
                });

                layout.getChildren().addAll(titleLabel, scoreLabel,achievementLabel, nextLevelButton, shopButton, mainMenuButton);
                rootLayout.setCenter(layout);

                Scene gameWinScene = new Scene(rootLayout, 500, 500);
                //Make the scene transparent
                gameWinScene.setFill(null); 
                gameWinStage.setScene(gameWinScene);

                gameWinStage.setOnHidden(event -> isGameWinScreenVisible = false);

                gameWinStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * Creates a styled button with a custom font and background image.
     *
     * @param text The text displayed on the button.
     * @param font The font used for the button text.
     * @return The styled button.
     */
    private static Button createStyledButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setStyle(
            "-fx-background-image: url('" + GameWinScreen.class.getResource(BUTTON_IMAGE).toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: #000000;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"
        );
        return button;
    }
    
    /**
     * Loads a custom font from the file path.
     *
     * @param fontPath The path to the font file.
     * @param size     The font size.
     * @return The loaded font, or the font if the specified font cannot be loaded.
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

    /**
     * Displays the Game Win Screen in Level 3.
     *
     * @param displayStage The stage where the Game Win Screen will be displayed.
     * @param score        The player's score.
     */
    public static void showlvl3WinScreen(Stage displayStage, int score) {
        if (isGameWinScreenVisible) {
            return;
        }
    
        isGameWinScreenVisible = true;
    
        Platform.runLater(() -> {
            try {
                gameWinStage = new Stage();
                gameWinStage.initModality(Modality.APPLICATION_MODAL);
                gameWinStage.initStyle(StageStyle.TRANSPARENT);
                gameWinStage.initOwner(displayStage);
    
                BorderPane rootLayout = new BorderPane();
    
                //Set background image
                try {
                    BackgroundImage backgroundImage = new BackgroundImage(
                        new Image(GameWinScreen.class.getResource(BACKGROUND_IMAGE).toExternalForm()),
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
    
                Font titleFont;
                Font buttonFont;
                try {
                    titleFont = Font.loadFont(GameWinScreen.class.getResource(PIXEL_FONT).toExternalForm(), 40);
                    buttonFont = Font.loadFont(GameWinScreen.class.getResource(PIXEL_FONT).toExternalForm(), 20);
                } catch (Exception e) {
                    System.out.println("Error loading font: " + e.getMessage());
                    titleFont = Font.font("Arial", 40);
                    buttonFont = Font.font("Arial", 20);
                }
    
                Label titleLabel = new Label("You Win!");
                titleLabel.setFont(titleFont);
                titleLabel.setStyle("-fx-text-fill: #000000;");
    
                Label scoreLabel = new Label("Your Score: " + score);
                scoreLabel.setFont(buttonFont);
                scoreLabel.setStyle("-fx-text-fill: #000000;");

                // Achievement label
                Label achievementLabel = null;
                if (GameState.getInstance().getAchievements().contains("All Enemies Defeated in Level One"))
                {
                    achievementLabel = new Label("Achievement: All Enemies Defeated");
                    achievementLabel.setFont(buttonFont);
                    achievementLabel.setStyle("-fx-text-fill: green;"); 
                }
                // Achievement label (Bomb Dodger)
                Label bombDodgerLabel = null;
                if (GameState.getInstance().getAchievements().contains("Achievement: Bomb Dodger in Level Three")) 
                {
                    bombDodgerLabel = new Label("Achievement: Bomb Dodger");
                    bombDodgerLabel.setFont(buttonFont);
                    bombDodgerLabel.setStyle("-fx-text-fill: green;"); 
                }
    
                Button mainMenuButton = new Button("Return to Menu");
                mainMenuButton.setFont(buttonFont);
                mainMenuButton.setPrefWidth(200);
                mainMenuButton.setPrefHeight(50);
                mainMenuButton.setStyle(
                    "-fx-background-image: url('" + GameWinScreen.class.getResource("/com/example/demo/images/grass_button.png").toExternalForm() + "');" +
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
                    gameWinStage.close();
                    isGameWinScreenVisible = false;
                    displayStage.setWidth(1300);  
                    displayStage.setHeight(730);
                    Controller controller = new Controller(displayStage); 
                    new MainMenu(displayStage, controller).show();
                });
    
                // Add elements to layout
                layout.getChildren().addAll(titleLabel, scoreLabel,achievementLabel);
                if (bombDodgerLabel != null) 
                { // Add Bomb Dodger label if it exists
                    layout.getChildren().add(bombDodgerLabel);
                }
                layout.getChildren().addAll( mainMenuButton);
                rootLayout.setCenter(layout);
    
                Scene gameWinScene = new Scene(rootLayout, 500, 500);
                //Make the scene transparent
                gameWinScene.setFill(null); 
                gameWinStage.setScene(gameWinScene);
    
                gameWinStage.setOnHidden(event -> isGameWinScreenVisible = false);
    
                gameWinStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        
    }
    

    
}

