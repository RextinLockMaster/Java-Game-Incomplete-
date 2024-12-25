package com.example.demo.projectiles;

import java.net.URL;
import javafx.scene.media.AudioClip;

/**
 * Represents a projectile fired by the user.
 * This class handles the appearance, sound effects, and movement of the user's projectile.
 */
public class UserProjectile extends Projectile {

    private static final String IMAGE_NAME = "userfire.png";
    public static final int IMAGE_HEIGHT = 100; 
    private static final int HORIZONTAL_VELOCITY = 15;
    private static final String SHOOTING_SOUND = "/com/example/demo/audios/shooting.wav";
    private static AudioClip shootingSound;

    boolean isFired = false; 

    static {
        try {
            URL soundURL = UserProjectile.class.getResource(SHOOTING_SOUND);
            if (soundURL == null) {
                System.err.println("Failed to load sound");
            } else {
                shootingSound = new AudioClip(soundURL.toExternalForm());
                System.out.println("Sound loaded successfully");
            }
        } catch (Exception e) {
            System.err.println("Failed to load sound: " + e.getMessage());
            shootingSound = null;
        }
    }

    /**
     * Constructs a UserProjectile at the initial position.
     * Plays the shooting sound after projectile fired.
     * 
     * @param initialX The initial X-coordinate of the projectile.
     * @param initialY The initial Y-coordinate of the projectile.
     */
    public UserProjectile(double initialX, double initialY) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialX, initialY);
        playSound();
    }

    /**
     * Updates the position of the projectile.
     * Moves the projectile horizontally if it is fired.
     */
    @Override
    public void updatePosition() {
        if (isFired) {
            moveHorizontally(HORIZONTAL_VELOCITY); // Move independently after firing
        }
    }

     /**
     * Updates the state of the projectile.
     * Updates its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

     /**
     * Synchronizes the projectile's position with the user's plane.
     * Ensure the projectile aligns with the plane.
     * 
     * @param planeX The X-coordinate of the user's plane.
     * @param planeY The Y-coordinate of the user's plane.
     * @param planeWidth The width of the user's plane.
     * @param planeHeight The height of the user's plane.
     */
    public void syncWithPlane(double planeX, double planeY, double planeWidth, double planeHeight) {
		//If the projectile has bot fired then update its position
        if (!isFired) {
			//Align x-coordinate with right edge of plane
            setLayoutX(planeX + planeWidth); 
			//Center the y-coordinate to match the middle of the plane
            setLayoutY(planeY + planeHeight / 2 - IMAGE_HEIGHT / 2); 
        }
    }

    /**
     * Fires the projectile, allows it to move independently.
     */
    public void fire() {
        isFired = true;
    }

    /**
     * Plays the shooting sound effect if its not null.
     */
    private void playSound() {
        if (shootingSound != null) {
            shootingSound.play();
        }
    }
}
