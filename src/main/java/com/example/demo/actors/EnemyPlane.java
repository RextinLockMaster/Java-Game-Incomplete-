package com.example.demo.actors;

import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.projectiles.EnemyProjectile;

/**
 * Represents an enemy plane.
 * The plane moves horizontally across the screen and can fire projectiles..
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	/**
     * Constructs enemy plane with the initial positions.
     * 
     * @param initialXPos The initial X-coordinate of the enemy plane.
     * @param initialYPos The initial Y-coordinate of the enemy plane.
     */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
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
     * Fires a projectile from enemy plane.
     * The projectile is fired randomly.
     * 
     * @return An {@link ActiveActorDestructible} represents the projectile, or null if there is no projectile.
     */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
     * Updates posisiton of enemy plane.
     */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
