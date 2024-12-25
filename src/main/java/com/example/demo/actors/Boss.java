package com.example.demo.actors;

import java.util.*;

import com.example.demo.assets.ShieldImage;
import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.projectiles.BossProjectile;

/**
 * Represents the boss.
 * Extends the FighterPlane class.
 * Includes the shield, firirng projectile and movement pattern.
 */
public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = .002;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 10;   //100
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private final ShieldImage shieldImage;
	private final String currentLevel;
	/**
     * Construct the Boss object with a shield image.
     *
     * @param shieldImage Link the ShieldImage object with the boss.
     */
	public Boss(ShieldImage shieldImage, String currentLevel) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		this.shieldImage = shieldImage;
		this.currentLevel = currentLevel;
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
     * Updates the boss's position based on its movement pattern.
     */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}
	
	/**
     * Updates the boss's state like the position and shield.
     */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
     * Fires a projectile.
     *
     * @return A new BossProjectile object if the boss fires in the current frame, otherwise null.
     */
	@Override
	public ActiveActorDestructible fireProjectile() {
		int velocity = speedProjectileVelocity();
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition(),velocity) : null;
	}

	/**
 	 * Determines the velocity of the projectile in the current level.
 	 *
 	 * @return The velocity of the projectile.
 	 *         Returns -24 for LevelThree which provide a faster speed than LevelTwo.
 	 *         Returns -15 as the default velocity for other levels.
 	 */
	private int speedProjectileVelocity() {
		if ("LevelThree".equals(currentLevel)) {
			return -24; 
		}
		return -15; 
	}
	
	/**
     * Deduct boss's health if shield is not visible.
     */
	@Override
	public void takeDamage() {
		// Only take damage if the shield is not visible
		if (!shieldImage.isVisible()) {
			super.takeDamage();
		}
	}

	/**
     * Initializes the movement pattern for the boss.
     */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
     * Updates the boss's shield state to either active or inactive.
     */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		}else if (shieldShouldBeActivated()) {
			activateShield();	
		}
		if (shieldExhausted()) {
			deactivateShield();
		}
	}

	/**
     * Gets the next move for the boss.
     *
     * @return The next movement value.
     */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
     * Check if the boss fires a projectile in the current frame.
     *
     * @return True if the boss fires, false otherwise.
     */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
     * Gets the initial position of the projectile.
     *
     * @return The Y-coordinate of the projectile's initial position.
     */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
     * Check if the shield should be activated.
     *
     * @return True if the shield should be activated, false otherwise.
     */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
     * Checks if the shield is exhausted.
     *
     * @return True if the shield is exhausted, false otherwise.
     */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
     * Activates the shield and show the shield image.
     */
	public void activateShield() {
		isShielded = true;
		//Show the shield image
		shieldImage.showShield(); 
	}

	/**
     * Deactivates the shield and hide the shield image.
     */
	public void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		shieldImage.hideShield(); // Hide the shield image
	}

}
