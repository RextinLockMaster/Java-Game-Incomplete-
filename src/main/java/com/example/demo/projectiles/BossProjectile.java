package com.example.demo.projectiles;

/**
 * Represents a projectile fired by the boss.
 * The projectile moves horizontally across the screen and interacts with the game environment.
 */
public class BossProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private int horizontalVelocity;
	private static final int INITIAL_X_POSITION = 950;

	 /**
     * Constructs a BossProjectile with the initial y-coordinate.
     *
     * @param initialYPos The initial y-coordinate position of the projectile.
     */
	public BossProjectile(double initialYPos, int velocity) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
		this.horizontalVelocity = velocity;
	}

	/**
     * Updates the position of the projectile to move horizontally.
     * Determined by the horizontal velocity.
     */
	@Override
	public void updatePosition() {
		moveHorizontally(horizontalVelocity);
	}
	
	/**
     * Updates the state of the projectile.
     * And also update the position.
     */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
     * Returns the horizontal velocity of the projectile for testing.
     * 
     * @return The horizontal velocity.
     */
    public int getHorizontalVelocity() {
        return horizontalVelocity; 
    }
	
}
