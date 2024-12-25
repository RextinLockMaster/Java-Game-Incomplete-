package com.example.demo.actors;

import javafx.scene.image.*;

/**
 * Represents the abstract class of an active actor.
 * Extends the ImageView class to allow the use of images.
 */
public abstract class ActiveActor extends ImageView {
	
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
     * Constructs ActiveActor with image, position, and size.
     *
     * @param imageName    The name of the image file.
     * @param imageHeight  The height of the image.
     * @param initialXPos  The initial X position of the actor.
     * @param initialYPos  The initial Y position of the actor.
     */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		//this.setImage(new Image(IMAGE_LOCATION + imageName));
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
     * Abstract method to update the position of the actor.
     */
	public abstract void updatePosition();

	/**
     * Moves the actor horizontally.
     *
     * @param horizontalMove The distance to move the actor along the X-axis.
     */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
     * Moves the actor vertically.
     *
     * @param verticalMove The distance to move the actor along the Y-axis.
     */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
