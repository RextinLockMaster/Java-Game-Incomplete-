package com.example.demo.GameState;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is to manage game state.
 * Track the player's health and other game variables.
 */
public class GameState {

    private int level1Hearts; // Player health for Level 1
    private int level2Hearts; 
    private int shopItem1PurchaseCount; 
    private boolean shopLocked; 
    private static GameState instance = null; 
    private Set<String> achievements; // Store achievements

    /**
     * Private constructor ensure only one instance is created.
     * Reset the game to default values.
     */
    private GameState() {
        resetAll();
    }

     /**
     * Gets the single instance of the GameState.
     * 
     * @return The single instance of GameState.
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    /**
     * Gets the player's health in LevelTwo,
     * 
     * @return The player's health for LevelTwo.
     */
    public int getLevel2Hearts() {
        return level2Hearts;
    }

    /**
     * Adds extra hearts to the player's health for LevelTwo.
     * 
     * @param extraHearts Number of extra hearts add.
     */
    public void addLevel2Hearts(int extraHearts) {
        this.level2Hearts += extraHearts;
        System.out.println("Level 2 hearts updated to: " + level2Hearts);
    }

     /**
     * Gets the player's health for LevelOne.
     * 
     * @return The player's health in LevelOne.
     */
    public int getLevel1Hearts() {
        return level1Hearts;
    }

    /**
     * Sets the player's health for LevelOne.
     * 
     * @param hearts New LevelOne health.
     */
    public void setLevel1Hearts(int hearts) {
        this.level1Hearts = hearts;
        System.out.println("Level 1 hearts updated to: " + hearts);
    }
    
     /**
     * Resets the player's health in LevelOne to default value.
     */
    public void resetLevel1Hearts() {
        this.level1Hearts = 5;
        System.out.println("Level 1 hearts reset to default: " + level1Hearts);
    }

    /**
     * Resets the player's health in LevelTwo to default value.
     */
    public void resetLevel2Hearts() {
        this.level2Hearts = 5;
        System.out.println("Level 2 hearts reset to default: " + level2Hearts);
    }

    /**
     * Sets the player's health for LevelTwo.
     * 
     * @param hearts New LevelTwo health.
     */
    public void setLevel2Hearts(int hearts) {
        level2Hearts = hearts;
        System.out.println("Level 2 hearts updated to: " + hearts);
    }

    /**
     * Gets the number of times an item has been purchased in the shop.
     * 
     * @return The number of item purchases.
     */
    public int getShopItem1PurchaseCount() {
        return shopItem1PurchaseCount;
    }

    /**
     * Increments the number of times an item has been purchased in the shop.
     * Ensure the number of purchases does not exceed the limit.
     */
    public void incrementShopItem1PurchaseCount() {
        if (shopItem1PurchaseCount < 2) {
            shopItem1PurchaseCount++;
            System.out.println("Shop Item 1 purchased. Total purchases: " + shopItem1PurchaseCount);
        } else {
            System.out.println("Shop Item 1 purchase limit reached.");
        }
    }

    /**
     * Checks if shop is locked.
     * 
     * @return True if the shop is locked, otherwise false.
     */
    public boolean isShopLocked() {
        return shopLocked;
    }

    /**
     * Resets the shop's purchase count and unlocks the shop.
     */
    public void resetShop() {
        this.shopItem1PurchaseCount = 0; 
        this.shopLocked = false; 
        System.out.println("Shop reset to initial values.");
    }

     /**
     * Shop locked status set to lock.
     * 
     * @param locked True to lock the shop, false to unlock it.
     */
    public void setShopLocked(boolean locked) {
        this.shopLocked = locked;
        System.out.println("Shop locked status set to: " + locked);
    }

    /**
     * Adds an achievement to the set of achievements if have not unlock it.
     *
     * @param achievement The name of the achievement to add.
     */
     public void addAchievement(String achievement) {
        if (achievements.add(achievement)) {
            System.out.println("Achievement unlocked: " + achievement);
        }
    }

    /**
     * Gets the set of all achievements earned.
     *
     * @return  new {@link HashSet} containing all earned achievements.
     */
    public Set<String> getAchievements() {
        return new HashSet<>(achievements);
    }


    /**
     * Resets all game state to their default values.
     * Includes player's health, shop status, purchase counts, and  all achievements earned.
     */
    public void resetAll() {
        this.level1Hearts = 5;  // Default hearts for Level 1
        this.level2Hearts = 5;  // Default hearts for Level 2
        this.shopItem1PurchaseCount = 0; // Reset shop purchases
        this.shopLocked = false; // Unlock shop
        this.achievements = new HashSet<>();
        System.out.println("GameState reset to initial values.");
    }
}
