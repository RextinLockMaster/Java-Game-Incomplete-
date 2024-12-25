package com.example.demo.actors;

import com.example.demo.destructible.ActiveActorDestructible;

/**
 * Represents a abstract class FighterPlane.
 * Handles health, firing projectiles, and projectile positions.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	
	/**
     * Constructs a FighterPlane with the specified image, dimensions, position, and health.
     * 
     * @param imageName    The name of the image.
     * @param imageHeight  The height of the image.
     * @param initialXPos  The initial X position of the fighter plane.
     * @param initialYPos  The initial Y position of the fighter plane.
     * @param health       The initial health of the fighter plane.
     */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
     * Fires a projectile from fighter plane.
     * 
     * @return The projectile fired by the fighter plane.
     */
	public abstract ActiveActorDestructible fireProjectile();
	
	/**
     * Deducts the health of the fighter plane. If the health reaches zero, the plane is destroyed.
     */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
     * Calculates the X position of a projectile.
     * 
     * @param xPositionOffset The X offset of the current position.
     * @return The calculated X position of the projectile.
     */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
     * Calculates the Y position of a projectile.
     * 
     * @param yPositionOffset The Y offset of the current position.
     * @return The calculated Y position of the projectile.
     */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
     * Checks if the health of the fighter plane is 0.
     * 
     * @return True if the health is 0, false otherwise.
     */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
     * Gets the current health of the fighter plane.
     * 
     * @return The current health of the fighter plane.
     */
	public int getHealth() {
		return health;
	}
		
}
