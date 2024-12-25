package com.example.demo.view;

import com.example.demo.assets.ShieldImage;

import javafx.scene.Group;

/**
 * Represents the view for Level Three.
 * About the heart display and shield.
 */
public class LevelViewLevelThree extends LevelView {

    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final ShieldImage shieldImage;

     /**
     * Constructs a LevelViewLevelThree instance.
     *
     * @param root           The root group of the scene to which the components are added.
     * @param heartsToDisplay The number of hearts to display.
     */
    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay); // Call the superclass constructor
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        addImagesToRoot(root);
    }

    /**
     * Adds the shield image to the root.
     */
    private void addImagesToRoot(Group root) {
        root.getChildren().addAll(shieldImage);
    }

    /**
     * Show the shield to player.
     */
    public void showShield() {
        shieldImage.showShield();
    }

    /**
     * Hide the shield.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }
}
