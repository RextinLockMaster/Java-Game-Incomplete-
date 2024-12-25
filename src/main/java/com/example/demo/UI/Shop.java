package com.example.demo.UI;

import com.example.demo.GameState.GameState;
import com.example.demo.levels.LevelParent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The Shop class is the shop interface in the game,
 * allowing players to purchase item.
 */
public class Shop {

    private final Stage stage; 
    private final LevelParent currentLevel;
    
    private static final String SHOP_BACKGROUND_IMAGE = "/com/example/demo/images/pause.png";
    private static final String PIXEL_FONT = "/com/example/demo/fonts/pixelFont.ttf";
    private static final String CLOSE_BUTTON_IMAGE = "/com/example/demo/images/x_button.png";
    private static final String HEARTS_IMAGE = "/com/example/demo/images/hearts.png";
    private static final String GRASS_BUTTON_IMAGE = "/com/example/demo/images/grass_button.png";
      /**
     * Constructor for the Shop class.
     *
     * @param stage The stage which the shop will be displayed.
     * @param currentLevel The current game level for which the shop is active.
     */
    public Shop(Stage stage, LevelParent currentLevel) {
        this.stage = stage;
        this.currentLevel = currentLevel;
    }
     /**
     * Displays the shop, allow players to purchase item.
     */
    public void show() {

        if (GameState.getInstance().isShopLocked()) {
            showShopPopup("Shop ", "The shop is no longer available.");
            return; // Prevent shop from opening
        }


        Stage shopStage = new Stage();
        shopStage.initStyle(StageStyle.TRANSPARENT);
        shopStage.initOwner(stage);

        BorderPane rootLayout = new BorderPane();
        try {
            BackgroundImage backgroundImage = new BackgroundImage(
                new Image(this.getClass().getResource(SHOP_BACKGROUND_IMAGE).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
            );
            rootLayout.setBackground(new Background(backgroundImage));
        } catch (NullPointerException e) {
            System.err.println("Background image not found: " + SHOP_BACKGROUND_IMAGE);
        }

       //Place button at top right 
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10));

        //Create close button
        Button closeButton = createCloseButton(shopStage);
        closeButton.setFocusTraversable(false);
        topBar.getChildren().add(closeButton);

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Font buttonFont = loadFont(PIXEL_FONT, 20);

        //Item1 
        HBox item1 = new HBox(10);
        item1.setAlignment(Pos.CENTER);
        //Hearts image
        ImageView heartsImageView = new ImageView();
        try {
            Image heartsImage = new Image(this.getClass().getResource(HEARTS_IMAGE).toExternalForm());
            heartsImageView.setImage(heartsImage);
            heartsImageView.setFitWidth(50); 
            heartsImageView.setFitHeight(50); 
        } catch (NullPointerException e) {
            System.err.println("Hearts image not found: " + HEARTS_IMAGE);
        }

        //Item 1 Button
        Button item1Button = new Button("Buy Item 1");
        item1Button.setFont(buttonFont);
        item1Button.setPrefWidth(200);
        item1Button.setPrefHeight(50);
        item1Button.setStyle(
            "-fx-background-image: url('" + this.getClass().getResource(GRASS_BUTTON_IMAGE).toExternalForm() + "');" +
            "-fx-background-size: 100% 100%;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-text-fill: #000000;" +
            "-fx-alignment: center;" +
            "-fx-background-color: transparent;" +
            "-fx-border-width: 0;"
        );
        item1Button.setFocusTraversable(false);
        item1Button.setOnAction(event -> {
            if (GameState.getInstance().getShopItem1PurchaseCount() < 2) {
                GameState.getInstance().incrementShopItem1PurchaseCount();
                GameState.getInstance().addLevel2Hearts(1);
                System.out.println("Purchased an extra heart! Total Level 2 hearts: " + GameState.getInstance().getLevel2Hearts());

                currentLevel.getLevelView().updateHeartDisplay(GameState.getInstance().getLevel2Hearts());

                if (GameState.getInstance().getShopItem1PurchaseCount() == 2) {
                    GameState.getInstance().setShopLocked(true); // Lock the shop
                }
            } else {
                showShopPopup("Item Not Available", "You cannot purchase this item more than 2 times.");
            }
        });

        item1.getChildren().addAll(item1Button, heartsImageView);

        layout.getChildren().addAll(item1);
        rootLayout.setTop(topBar);
        rootLayout.setCenter(layout);

        Scene shopScene = new Scene(rootLayout, 600, 400);
        shopScene.setFill(null);
        shopStage.setScene(shopScene);
        shopStage.show();
    }

    /**
     * Creates the close button for the shop.
     *
     * @param shopStage The stage of the shop to be closed.
     * @return A custom button to close the shop.
     */
    private Button createCloseButton(Stage shopStage) {
        Button closeButton = new Button();
        closeButton.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-background-size: 50px 40px;");
        try {
            ImageView closeButtonImage = new ImageView(
                new Image(this.getClass().getResource(CLOSE_BUTTON_IMAGE).toExternalForm())
            );
            closeButtonImage.setFitWidth(30);
            closeButtonImage.setFitHeight(30);
            closeButton.setGraphic(closeButtonImage);
        } catch (NullPointerException e) {
            System.err.println("Close button image not found: " + CLOSE_BUTTON_IMAGE);
        }

        closeButton.setOnAction(event -> shopStage.close());
        return closeButton;
    }

    /**
     * Loads a custom font.
     *
     * @param fontPath The path to the font file.
     * @param size The size of the font.
     * @return The loaded Font, or a default font if not found.
     */
    private Font loadFont(String fontPath, int size) {
        try {
            return Font.loadFont(this.getClass().getResource(fontPath).toExternalForm(), size);
        } catch (NullPointerException e) {
            System.err.println("Font not found: " + fontPath);
            return Font.font("Arial", size); 
        }
    }

     /**
     * Displays a popup message for the shop.
     *
     * @param title The title of the popup message.
     * @param message The message to display.
     */

    public static void showShopPopup(String title, String message) {
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.TRANSPARENT);
    
        // Root layout with background image
        BorderPane rootLayout = new BorderPane();
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image(Shop.class.getResource(SHOP_BACKGROUND_IMAGE).toExternalForm()),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        rootLayout.setBackground(new Background(backgroundImage));
    
        // Top section with close button
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10));
    
        // Create close button
        Button closeButton = new Button();
        closeButton.setStyle(
            "-fx-background-color: transparent; -fx-border-width: 0; " +
            "-fx-background-size: 50px 40px;"
        );
        try {
            ImageView closeButtonImage = new ImageView(
                new Image(Shop.class.getResource(CLOSE_BUTTON_IMAGE).toExternalForm())
            );
            closeButtonImage.setFitWidth(30);
            closeButtonImage.setFitHeight(30);
            closeButton.setGraphic(closeButtonImage);
        } catch (NullPointerException e) {
            System.err.println("Close button image not found: " + CLOSE_BUTTON_IMAGE);
        }
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> popupStage.close());
        topBar.getChildren().add(closeButton);
        rootLayout.setTop(topBar);
    
        // Center layout with content
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    
        // Load the custom pixel font
        Font titleFont = null;
        Font buttonFont = null;
        try {
            titleFont = Font.loadFont(Shop.class.getResource(PIXEL_FONT).toExternalForm(), 40);
            buttonFont = Font.loadFont(Shop.class.getResource(PIXEL_FONT).toExternalForm(), 20);
        } catch (Exception e) {
            System.err.println("Error loading font: " + e.getMessage());
            titleFont = Font.font("Arial", 40); 
            buttonFont = Font.font("Arial", 20); 
        }
    
        // Title label
        Label titleLabel = new Label(title);
        if (titleFont != null) {
            titleLabel.setFont(titleFont);
        }
        titleLabel.setStyle("-fx-text-fill: #000000;");
    
        // Message label
        Label messageLabel = new Label(message);
        if (buttonFont != null) {
            messageLabel.setFont(buttonFont);
        }
        messageLabel.setStyle("-fx-text-fill: #555555;");
    
        // Add content to layout
        layout.getChildren().addAll(titleLabel, messageLabel);
    
        // Set layout to the center of the root
        rootLayout.setCenter(layout);
    
        // Create scene with transparency
        Scene popupScene = new Scene(rootLayout, 600, 400);
        popupScene.setFill(null);
    
        // Set scene to the stage and show
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
   


}
