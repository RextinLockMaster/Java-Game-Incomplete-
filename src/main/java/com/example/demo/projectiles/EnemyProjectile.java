package com.example.demo.projectiles;

/**
 * Represents a projectile fired by an enemy.
 * This projectile moves horizontally towards the player's position.
 */
public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	static final int HORIZONTAL_VELOCITY = -10;

	/**
     * Constructs an EnemyProjectile with the initial position.
     *
     * @param initialXPos The initial x-coordinate of the projectile.
     * @param initialYPos The initial y-coordinate of the projectile.
     */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
     * Updates the position of the projectile to move horizontally.
     * Determined by the horizontal velocity.
     */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
     * Updates the state of the projectile.
     * And also update the position.
     */
	@Override
	public void updateActor() {
		updatePosition();
	}


}
