package com.example.demo.levels;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;
import com.example.demo.destructible.ActiveActorDestructible;
import com.example.demo.view.LevelView;
import com.example.demo.GameState.GameState;
import com.example.demo.UI.GameEndScreen;
import com.example.demo.UI.GameWinScreen;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Abstract class representing the parent for all levels in the game.
 * Include user actions, spawning enemy, collisions, score, and transition to different levels.
 */
public abstract class LevelParent extends Observable {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private static final int MILLISECOND_DELAY = 50;

    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private boolean isUpdated = false;
    private boolean isChangedState = false;
    protected boolean isGameOver = false;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    private final Group root;
    private final Timeline timeline;
    private final UserPlane user;
    private final Scene scene;
    private final ImageView background;
    private boolean isPaused = false;
    private int playerScore;
    

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    private LevelView levelView;
    private final Stage gameStage;

    /**
     * Construct LevelParent with specific parameters.
     *
     * @param backgroundImageName Name of the background image.
     * @param screenHeight        Height of the game screen.
     * @param screenWidth         Width of the game screen.
     * @param playerInitialHealth Initial health of the user plane.
     * @param gameStage           The main game stage.
     */
    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Stage gameStage) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.isUpdated = false;
        this.isChangedState = false;
        this.gameStage = gameStage;
        this.playerScore = 0;
        
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        if (this.levelView == null) {
            throw new IllegalStateException("instantiateLevelView() must not return null!");
        }
        this.currentNumberOfEnemies = 0;
        initializeTimeline();
        friendlyUnits.add(user);
    }

    /**
     * Abstract method to initialize the user's friendly units in the game.
     */
    protected abstract void initializeFriendlyUnits();

    /**
     * Abstract method to spawns enemy units.
     */
    protected abstract void spawnEnemyUnits();

    /**
     * Returns the level view for current level.
     *
     * @return the level view for current level.
     */
    public abstract LevelView getLevelView();
   
    /**
     * Instantiates the LevelView for the level.
     *
     * @return The instantiated LevelView.
     */
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initializes the scene for the level.
     * Set up background and friendly units
     *
     * @return The initialized scene.
     */
    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
         if (levelView != null) { 
        levelView.showHeartDisplay();
        levelView.showScoreDisplay();
    } else {
        throw new IllegalStateException("LevelView is not initialized!");
    }
        levelView.updateHeartDisplay(GameState.getInstance().getLevel2Hearts());
        return scene;
    }

    /**
     * Starts the game by playing the timeline.
     */
    public void startGame() {
        background.requestFocus();
        timeline.play();
    }

    /**
     * Pauses the game by stopping the timeline.
     */
    public void pauseGame() {
        if (!isPaused) {
            timeline.pause();
            isPaused = true;
        }
    }

    /**
     * Resumes the game by playing the timeline.
     */
    public void resumeGame() {
        if (isPaused) {
            timeline.play();
            isPaused = false;
        }
    }

     /**
     * Get the current scene.
     *
     * @return The current Scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Go to the next level.
     *
     * @param levelName The name of the next level.
     */
    public void goToNextLevel(String levelName) {
        if (!isUpdated) {
            setChanged();
            notifyObservers(levelName);
            isUpdated = true;
            isChangedState = true;
            resetScore();
            //GameState holds heart count
             GameState.getInstance().setLevel2Hearts(user.getHealth()); 
            resetScore();
        }
    }

    /**
     * Checks if the game state changed.
     *
     * @return True if the game state has changed, false otherwise.
     */
    public boolean isChangedState() {
        return isChangedState;
    }

    /**
     * Resets the player's score to zero.
     */
    private void resetScore() {
        playerScore = 0;
        // Update the score display 
        levelView.updateScore(playerScore); 
        System.out.println("Score reset for the next level.");
    }

    /**
     * Updates the scene by processing game events.
     */
    protected void updateScene() {
        if (!isPaused && !isTransitioning) {
            spawnEnemyUnits();
            updateActors();
            generateEnemyFire();
            updateNumberOfEnemies();
            handleEnemyPenetration();
            handleUserProjectileCollisions();
            handleEnemyProjectileCollisions();
            handlePlaneCollisions();
            removeAllDestroyedActors();
            updateKillCount();
            updateLevelView();
            checkIfGameOver();
        }
    }

    /**
     * Initializes the timeline for the game loop.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

     /**
     * Sets up the background.
     */
    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
    
        background.setOnKeyPressed(event -> {
            if (isGameOver && event.getCode() != KeyCode.TAB) {
                return;
            }
            if (!isGameOver) {
                KeyCode kc = event.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.LEFT) user.moveLeft();
                if (kc == KeyCode.RIGHT) user.moveRight();
                if (kc == KeyCode.SPACE) fireProjectile();
            }
        });

        background.setOnKeyReleased(event -> {
            if (isGameOver || isTransitioning) {
                return;
            }
            if (!isPaused) {
                KeyCode kc = event.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
                    user.stopVerticalMove();
                }
                if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) {
                    user.stopHorizontalMove();
                }
            }
        });
        
    
        root.getChildren().add(background);
    }
    
    /**
     * Fires the projectile from the user's plane.
     * Add projectile to game root, and track in userProjectiles
     */
    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }

    /**
     * Generate enemy projectile.
     * Add enemy projectile to game root, and track in enemyProjectiles.
     */
    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Spawns enemy projectile.
     * 
     * @param projectile The projectile to be added to the game.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

    /**
     * Updates all active actors in the game.
     */
    private void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor());
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor());
    }

    /**
     * Removes all destroyed actors from the game.
     */
    void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from the list and game root.
     * 
     * @param actors The list of actors to check if they are destroyed.
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between the user's plane and enemy plane.
     * Deducts health from the user's plane and removes collided enemies.
     */
    private void handlePlaneCollisions() {
        List<ActiveActorDestructible> collidedEnemies = new ArrayList<>();
    
        for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
            if (getUser().getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                collidedEnemies.add(enemy); // Add enemy to the list of collided enemies
                getUser().takeDamage();    // Deduct one heart for each collision
            }
        }
    
        // Remove all collided enemies after processing
        for (ActiveActorDestructible enemy : collidedEnemies) {
            root.getChildren().remove(enemy);
            enemyUnits.remove(enemy);
        }
    
        // Check if the user is destroyed
        if (userIsDestroyed()) {
            loseGame(); 
        }
    }
    
    
    /**
     * Handles collisions between the user's projectiles and enemy planes.
     * Updates score and remove the destroyed enemies.
     */
    void handleUserProjectileCollisions() {
        List<ActiveActorDestructible> destroyedProjectiles = new ArrayList<>();
        List<ActiveActorDestructible> destroyedEnemies = new ArrayList<>();
    
        for (ActiveActorDestructible projectile : userProjectiles) {
            boolean projectileHit = false;
    
            for (ActiveActorDestructible enemy : enemyUnits) {
                
                if (projectile.getBoundsInParent().intersects(enemy.getBoundsInParent()) && !projectileHit) {
                    projectile.takeDamage();
                    enemy.takeDamage();
    
                    // Add score only if the enemy is destroyed
                    if (enemy.isDestroyed()) {
                        destroyedEnemies.add(enemy); 
                        if (this instanceof LevelOne) {
                            addScore(5);
                        }
                        if (this instanceof LevelTwo) {
                            addScore(60);
                        }
                        if (this instanceof LevelThree) {
                            addScore(100); 
                        }
                        
                    }
    
                    destroyedProjectiles.add(projectile); 
                    projectileHit = true; 
                }
            }
        }
    
        // Cleanup: Remove destroyed projectiles and enemies
        root.getChildren().removeAll(destroyedProjectiles);
        userProjectiles.removeAll(destroyedProjectiles);
        root.getChildren().removeAll(destroyedEnemies);
        enemyUnits.removeAll(destroyedEnemies);
    }
    
    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Generic method to handle collisions between two lists of actors.
     * 
     * @param actors1 First list of actors.
     * @param actors2 Second list of actors.
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles cases where enemies move out of screen.
     * Deducts all hearts from the user and triggers game over.
     */
    void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
            if (enemyHasPenetratedDefenses(enemy)) {
                System.out.println("Enemy exited the screen! Deducting all hearts.");
    
                // Deduct all hearts from the user
                while (user.getHealth() > 0) {
                    user.takeDamage();
                }
    
                // Remove the enemy and trigger game over
                enemy.destroy();
                root.getChildren().remove(enemy);
                enemyUnits.remove(enemy);
    
                // If user destroyed trigger game over
                if (userIsDestroyed()) {
                    loseGame();
                }
            }
        }
    }
    
    /**
     * Updates the level view to show the user's current health and score.
     */
    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
        levelView.updateScore(playerScore);
    }

    /**
     * Updates the kill count.
     * By comparing the current and previous number of enemies.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks if an enemy move out of screen boundaries.
     * 
     * @param enemy The enemy actor to check.
     * @return True if the enemy is out of the screen, false otherwise.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return enemy.getBoundsInParent().getMaxX() < 0 || 
               enemy.getBoundsInParent().getMinX() > screenWidth || 
               enemy.getBoundsInParent().getMaxY() < 0 || 
               enemy.getBoundsInParent().getMinY() > screenHeight;
    }
    
    /**
     * Adds points to the player's score.
     * 
     * @param points The number of points to add.
     */
    private void addScore(int points) {
        playerScore += points;
        System.out.println("Score: " + playerScore);
    }

    /**
     * If win the game, displays the win screen.
     * 
     * @param nextLevel The name of the next level, or null if is LevelThree.
     */
    protected void winGame(String nextLevel) {
        if (isGameOver) return;
        timeline.stop();
        isGameOver = true;
    
        if (nextLevel == null) {
            //Show final win screen in level 3
            GameWinScreen.showlvl3WinScreen(gameStage, playerScore);
        } else {
            GameWinScreen.showGameWinScreen(gameStage, playerScore, this, () -> goToNextLevel(nextLevel));
        }
    }
    protected boolean isTransitioning = false;

    /**
     * EIf game over and displays the game end screen.
     */
    protected void loseGame() {
        if (isGameOver) return;
        timeline.stop();
        isGameOver = true;
        GameEndScreen.showGameEndScreen(gameStage, playerScore);
    }
    
    /**
     * Checks if the game is over or if all enemies are defeated.
     */
    protected void checkIfGameOver() {
        if (isGameOver || isTransitioning) {
            return;
        }
    
        if (userIsDestroyed()) {
            loseGame(); 
        } else if (allEnemiesDefeated()) { 
            //Go to next level
            winGame("com.example.demo.levels.LevelTwo"); 
        }
    }
    
    /**
     * Checks if all enemies have been defeated.
     * 
     * @return True if all enemies are defeated, false otherwise.
     */
    private boolean allEnemiesDefeated() {
        return enemyUnits.isEmpty();
    }
    
    /**
     * Get the user's plane object.
     * 
     * @return The user's plane.
     */
    protected UserPlane getUser() {
        return user;
    }

    /**
     * Get the root group.
     * 
     * @return The root group.
     */
    protected Group getRoot() {
        return root;
    }

    /**
     * Get the current number of enemy units.
     * 
     * @return The current number of enemy units.
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds an enemy unit to game.
     * Enemy is added to the list of enemies and displayed in the root.
     * 
     * @param enemy The enemy unit to add.
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Get the maximum Y position of enemy.
     * 
     * @return The maximum Y position for enemies.
     */
    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    /**
     * Get the width of the game screen.
     * 
     * @return The width of the game screen.
     */
    protected double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Checks if the user's plane is destroyed.
     * 
     * @return True if the user's plane is destroyed, false otherwise.
     */
    protected boolean userIsDestroyed() {
        return getUser().getHealth() <= 0;
    }

    /**
     * Updates the number of enemies.
     * Call when the number of enemies change.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    /**
     * Get the player's current score.
     * 
     * @return The player's score.
     */
    public int getPlayerScore() {
        return playerScore;
    }

    
}

