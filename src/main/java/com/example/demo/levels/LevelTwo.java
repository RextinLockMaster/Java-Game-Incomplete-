package com.example.demo.levels;

import com.example.demo.GameState.GameState;
import com.example.demo.actors.Boss;
import com.example.demo.assets.ShieldImage;
import com.example.demo.view.LevelView;
import com.example.demo.view.LevelViewLevelTwo;

import javafx.stage.Stage;

/**
 * Represents Level Two.
 * Includes a boss with a shield and allows the player to go to next level.
 */
public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/river.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private static final int KILLS_TO_ADVANCE = 1;
    private LevelViewLevelTwo levelView;
    private ShieldImage shieldImage;
    public static final int SHIELD_SIZE = 200;

    /**
     * Constructs a LevelTwo object.
     *
     * @param screenHeight the height of the game screen.
     * @param screenWidth  the width of the game screen.
     * @param gameStage    the primary stage for the game.
     */
    public LevelTwo(double screenHeight, double screenWidth, Stage gameStage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth,  GameState.getInstance().getLevel2Hearts(), gameStage);

        //Initialize the image at (0,0)
        shieldImage = new ShieldImage(0, 0);
        boss = new Boss(shieldImage ,"LevelTwo");
        //Adjust the position of the image
        double xOffset = 100;  
        double yOffset = 100; 

        //Match the shield's X position with the boss plane's X position
        shieldImage.layoutXProperty().bind(
            boss.layoutXProperty()
            .add(boss.translateXProperty())
            .subtract(ShieldImage.SHIELD_SIZE / 2)
            .add(xOffset)
        );

        ///Match the shield's Y position with the boss plane's Y position
        shieldImage.layoutYProperty().bind(
            boss.layoutYProperty()
            .add(boss.translateYProperty())
            .subtract(ShieldImage.SHIELD_SIZE / 2)
            .add(yOffset)
        );
            
    }

    /**
     * Initializes the user's friendly units in the game.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
        getRoot().getChildren().add(shieldImage);
    }

    /**
     * Checks whether the game is over if the user died or the boss is destroyed.
     * Unlocks "Boss Defeated in Level Two" achievement if the boss is defeated.
     * Then proceed to next level.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {

        GameState.getInstance().addAchievement(" Boss Defeated in Level Two");
            winGame(NEXT_LEVEL); 
        }
    }

    /**
     * Spawns enemy units.
     * Ensures the boss is added if there is no other enemies.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    /**
     * Instantiates the level view for LevelTwo.
     * Update user's hearts from game state.
     *
     * @return the level view instance.
     */
    @Override
    protected LevelView instantiateLevelView() {
        int updatedHearts = GameState.getInstance().getLevel2Hearts();
        return new LevelView(getRoot(), updatedHearts);
    }

    /**
     * Start the game by resetting user hearts and updating the game state.
     */
    @Override
    public void startGame() {
        super.startGame();
        if (levelView != null) {
            levelView.resetHearts(PLAYER_INITIAL_HEALTH); // Reset hearts to initial value
        }
        GameState.getInstance().setLevel1Hearts(PLAYER_INITIAL_HEALTH);
        System.out.println("Game started with hearts: " + PLAYER_INITIAL_HEALTH);
    }
        /**
         * Activates the shield, to show the image.
         */
        public void activateShield() {
            shieldImage.showShield();
            
        }

        /**
         * Deactivates the shield, to hide the image.
         */
        public void deactivateShield() {
            shieldImage.hideShield();
        }

        /**
         * Checks if the user has reached the number of kills to advance.
         *
         * @return true if the user has reached the kill target; false otherwise.
         */
        boolean userHasReachedKillTarget() {

            return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE && !isChangedState();
        }
        
        /**
         * Returns the level view for LevelTwo.
         *
         * @return the level view.
         */
        @Override
        public LevelView getLevelView() {
            return levelView;
        }


    /**
     * Returns the boss object for LevelTwo.
     * Use in testing.
     *
     * @return The boss object.
     */
    public Boss getBoss() {
        return boss;
    }
}
