package com.example.demo.projectiles;

import com.example.demo.destructible.ActiveActorDestructible;

/**
 * Represents the projectile. 
 * This class is about the movement and handling damage of projectile.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
     * Constructs a new projectile with attributes.
     *
     * @param imageName   The name of the image file used to represent the appearance of the projectile.
     * @param imageHeight The height of the projectile image.
     * @param initialXPos The initial X-coordinate of the projectile.
     * @param initialYPos The initial Y-coordinate of the projectile.
     */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
     * Handles when the projectile takes damage. 
     * The projectile is destroyed when taking damage.
     */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
     * Updates the position of the projectile. 
     */
	@Override
	public abstract void updatePosition();

}
