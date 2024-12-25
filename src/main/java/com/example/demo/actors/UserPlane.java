package com.example.demo.actors;

import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.projectiles.UserProjectile;

/**
 * Represents the user's plane. 
 * Handles movement, firing projectiles,and track the number of kills.
 */
public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = -40;
    private static final double Y_LOWER_BOUND = 600.0;
    private static final double X_LEFT_BOUND = 0.0;
    private static final double X_RIGHT_BOUND = 600.0;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 150;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 8;

    private int verticalVelocityMultiplier;
    private int horizontalVelocityMultiplier;
    private int numberOfKills;

    private UserProjectile activeProjectile; 

    /**
     * Constructs a UserPlane object with initial health.
     * 
     * @param initialHealth The initial health of the user's plane.
     */
    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
    }

     /**
     * Updates the position of the user's plane, ensure it does not go out of boundaries.
     * Get postision of user plane and Sync with the projectile.
     */
    @Override
    public void updatePosition() {
        //Control vertical movement
        if (isMovingVertical()) {
            double initialTranslateY = getTranslateY();
            moveVertical(VERTICAL_VELOCITY * verticalVelocityMultiplier);
            double newPositionY = getLayoutY() + getTranslateY();
            if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
                setTranslateY(initialTranslateY);
            }
        }

        //Control horizontal movement
        if (isMovingHorizontal()) {
            double initialTranslateX = getTranslateX();
            moveHorizontal(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newPositionX = getLayoutX() + getTranslateX();
            if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
                setTranslateX(initialTranslateX);
            }
        }

        //Sync with the user projectile
        if (activeProjectile != null) {
            //Get the position of the user plane
            activeProjectile.syncWithPlane(
                getBoundsInParent().getMinX(),
                getBoundsInParent().getMinY(),
                getBoundsInParent().getWidth(),
                getBoundsInParent().getHeight()
            );
        }
    }

    /**
     * Assigns a projectile to the user's plane.
     * 
     * @param projectile The projectile to be assigned.
     */
    public void assignProjectile(UserProjectile projectile) {
        this.activeProjectile = projectile;
    }

     /**
     * Updates the actor by synchronizing with the position .
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the user's plane.
     * 
     * @return The fired projectile.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        //Check if there is active projectile
        if (activeProjectile != null) {
            //Make the projectile to fire so it moves on its own
            activeProjectile.fire(); 
            //Remove it from being tracked so new one can be fired
            activeProjectile = null; 
        }

        
        UserProjectile projectile = new UserProjectile(
            //Start to shoot at the tip of user plane
            getBoundsInParent().getMaxX(), 
            //Set the position at center vertically
            getBoundsInParent().getMinY() + getBoundsInParent().getHeight() / 2 - UserProjectile.IMAGE_HEIGHT / 2 
        );
        //Assign projectile to active
        assignProjectile(projectile); 
        return projectile;
    }

    /**
     * Checks if the user's plane is moving vertically.
     * 
     * @return True if the plane is moving vertically, otherwise false.
     */
    private boolean isMovingVertical() {
        return verticalVelocityMultiplier != 0;
    }

    /**
     * Checks if the user's plane is moving horizontally.
     * 
     * @return True if the plane is moving horizontally, otherwise false.
     */
    private boolean isMovingHorizontal() {
        return horizontalVelocityMultiplier != 0;
    }

    /**
     * Moves the user's plane upwards.
     */
    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    /**
     * Moves the user's plane downwards.
     */
    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }

    /**
     * Moves the user's plane to left.
     */
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    /**
     * Moves the user's plane to right.
     */
    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    /**
     * Stop moving the user's plane vertically.
     */
    public void stopVerticalMove() {
        verticalVelocityMultiplier = 0;
    }

    /**
     * Stop moving the user's plane horizontally.
     */
    public void stopHorizontalMove() {
        horizontalVelocityMultiplier = 0;
    }

    /**
     * Gets the total number of kills.
     * 
     * @return The number of kills.
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
        numberOfKills++;
    }


    /**
     * Increments the kill count.
     */
    public int getKillCount() {
        return numberOfKills;
    }

    /**
     * Moves the plane vertically by the specified amount.
     * 
     * @param Yaxis The distance to move along the Y-axis (positive values move down, negative values move up).
     */
    private void moveVertical(double Yaxis) {
        setTranslateY(getTranslateY() + Yaxis);
    }

    /**
     * Moves the plane horizontally by the specified amount.
     * 
     * @param Xaxis The distance to move along the X-axis (positive values move right, negative values move left).
     */
    private void moveHorizontal(double Xaxis) {
        setTranslateX(getTranslateX() + Xaxis);
    }
    

    
}
