package com.example.demo.view;

import com.example.demo.assets.HeartDisplay;
import com.example.demo.assets.ShieldImage;

import javafx.scene.Group;

/**
 * Represents the view for Level Two.
 * About the heart display and shield.
 */
public class LevelViewLevelTwo extends LevelView {

    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final Group root;
    private final ShieldImage shieldImage;
    private final HeartDisplay heartDisplay;
    private int hearts;

    /**
     * Constructs a LevelViewLevelTwo instance.
     *
     * @param root           The root group of the scene to which the components are added.
     * @param heartsToDisplay The number of hearts to display.
     */
    public LevelViewLevelTwo(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        this.heartDisplay = new HeartDisplay(20, 20, heartsToDisplay); 
        addImagesToRoot();
        addHeartDisplayToRoot();
    }

    /**
     * Adds the shield image to the root.
     */
    private void addImagesToRoot() {
        root.getChildren().addAll(shieldImage);
    }

    /**
     * Adds the heart display container to the root.
     */
    private void addHeartDisplayToRoot() {
        root.getChildren().add(heartDisplay.getContainer());
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

    /**
     * Updates the heart display to show the current number of hearts.
     *
     * @param hearts  Current number of hearts to display.
     */
    @Override
    public void updateHeartDisplay(int hearts) {
        this.hearts = hearts;
        System.out.println("Heart display updated: " + hearts);
    }

    
}


