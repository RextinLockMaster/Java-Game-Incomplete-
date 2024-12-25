package com.example.demo.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the bomb image.
 * Extends the {@link ImageView} class to create and manage the bomb.
 */
public class bombImage extends ImageView {
    
    private static final String IMAGE_NAME = "/images/bomb.png";
    public static final int BOMB_SIZE = 150;
    
    /**
     * Constructs a bomb image at the specific position.
     *
     * @param xPosition The x-coordinate where the bomb image is displayed.
     * @param yPosition The y-coordinate where the bomb image is displayed.
     */
    public bombImage(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setImage(new Image(getClass().getResource("/com/example/demo/images/bomb.png").toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(BOMB_SIZE);
        this.setFitWidth(BOMB_SIZE);
    }
}
