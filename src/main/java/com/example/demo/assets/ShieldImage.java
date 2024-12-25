package com.example.demo.assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a shield image.
 *  Extends {@link ImageView} to allow image to be shown or hidden on the screen, and adjust image properties.
 */
public class ShieldImage extends ImageView {
	
	private static final String IMAGE_NAME = "/images/shield.png";
	public static final int SHIELD_SIZE = 200;
	
	/**
     * Constructs a ShieldImage with the initial position.
     *
     * @param xPosition The initial x-coordinate of the shield.
     * @param yPosition The initial y-coordinate of the shield.
     */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
     * Allows the shield visible on the screen.
     */
	public void showShield() {
		this.setVisible(true);
	}
	
	/**
     * Hides the shield from the screen.
     */
	public void hideShield() {
		this.setVisible(false);
	}

}
