package com.example.demo.levels;

import com.example.demo.GameState.GameState;
import com.example.demo.actors.EnemyPlane;
import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.view.LevelView;

import javafx.stage.Stage;

/**
 * Represents Level One.
 * Hnadles initialization, gameplay, and allows player go to next level.
 */
public class LevelOne extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/river.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int SCORE_TO_ADVANCE = 5;

    private final LevelView levelView;

    /**
     * Constructs a LevelOne object.
     *
     * @param screenHeight the height of the game screen.
     * @param screenWidth  the width of the game screen.
     * @param gameStage    the primary stage for the game.
     */
    public LevelOne(double screenHeight, double screenWidth, Stage gameStage) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, gameStage);
        this.levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks whether the game is over if the user died or meets the kill target.
     * If user  meets the kill target, user will unlock "All Enemies Defeated in Level One" achievement.
     * Then proceed to next level.
     */
    @Override
    protected void checkIfGameOver() {
        if (isGameOver) {
            return;
        }
        if (userIsDestroyed()) {
            loseGame(); 
        } else if (userHasReachedKillTarget() && !isTransitioning) {
            
        GameState.getInstance().addAchievement("All Enemies Defeated in Level One");
        
        winGame(NEXT_LEVEL);
        }
    }

    /**
     * Initializes the user's friendly units in the game.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units.
     * Limits the total number of enemies to TOTAL_ENEMIES.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
            }
        }
    }

    /**
     * Instantiates the level view for LevelOne.
     *
     * @return the level view instance.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Returns the level view for LevelOne.
     *
     * @return the level view.
     */
    @Override
    public LevelView getLevelView() {
        return levelView;
    }

    /**
     * Checks if the user has met the kill target before going to the next level.
     * 
     * @return True if the user meets the kill target and score , false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE 
            && getPlayerScore() >= SCORE_TO_ADVANCE 
            && !isGameOver;
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
}







