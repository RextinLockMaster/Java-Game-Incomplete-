## Table of Contents

1. [Github](#github)
2. [Introduction](#introduction)
3. [Compilation Instructions](#compilation-instructions)
4. [Game Instructions](#game-instructions)
5. [Features](#features)
   - [5.1 Implemented and Working Properly](#51-implemented-and-working-properly)
   - [5.2 Implemented but Not Working Properly](#52-implemented-but-not-working-properly)
   - [5.3 Features Not Implemented](#53-features-not-implemented)
   - [5.4 New Java Classes](#54-new-java-classes)
   - [5.5 Modified Java Classes](#55-modified-java-classes)
   - [5.6 Deleted Java Classes](#56-deleted-java-classes)
   - [5.7 Unexpected Problems](#57-unexpected-problems)



# 1. Github
[https://github.com/Elinayap/CW2024.git](https://github.com/Elinayap/CW2024.git)

## 2. Introduction
#### My name is Yap Jia Hui and my student id is 20501037. 
#### Sky battle is a 2D arcade game where player can control the plane, and shooting down all the enemies while dodging the incoming projectiles. There are total of 3 levels and the game get harder with each level. The objective is to survive as long as possible to reach to the end of the game.
---
## 3. Compilation Instructions
- IDE: IntelliJ IDEA
- SDK: 19 Amazon Corretto 19.0.2 or 21 Oracle OpenJDK21- aarch64 (if cannot run)
- JavaFX version: 19.0.2
1. Clone the repository at [https://github.com/Elinayap/CW2024.git](https://github.com/Elinayap/CW2024.git)
2. Install and set up JavaFX.
3. Import project into IntelliJ or visual studio code.
4. Compile and run the program.
---
## 4. Game Instructions

### Controls
- **Move:**    Arrow keys
- **Shoot:**   Spacebar

### Score
- Destroy all the enemies in each level to score different points

### Goals
-  Destroy as many enemies as you can without being hit by enemy’s projectiles
-  Defeat the enemies to proceed to next level
---
## 5.Features
### 5.1 Implemented and Working Properly

- **Fonts of the texts:**
Changing the fonts of the title to matched the overall theme of the game.The new font enhance the consistency with the game style.

- **Colour of the texts:**
Changing the colour of texts to make it to match the game aesthetic.

- **Main menu page:**
Creating main menu page to provide users with structured starting point, rather than immediately entering the game. It allow users to choose options such as start game, viewing instructions and quit game.

- **UI of the buttons:**
The old button style does not match the overall game’s aesthetic. By updating the style to improve the consistency of the game.

- **Custom cursor:**
Changing the cursor style to enhances the user experience by providing a more interactive interface. A custom cursor style is added when users enter the game or hover over the buttons.

- **Play button:**
Users can click on the play button to start the game.

- **Instruction button:**
Users can read the instructions to get a brief of what is going on before playing the game.

- **Quit game button:**
Users can click on the quit game button to exit the game.

- **Change the image of hearts:**
Previous  heart images may have lacked visual appeal to match the game overall aesthetic. Replacing them makes the design more consistent.

- **Sound effect for shooting:**
Sound effect is added when the user shoots. It delivers instant audio feedback to enhance gameplay experience.

- **User plane controls:**
Users can control their plane to move forward and backward instead of just going upwards and downwards.

- **Adjust position of user projectile:**
Adjusting the position of user projectile to follow the movement of the user plane instead of staying at the same position without following the user plane.

- **Score label:**
The score label is added to the screen for LevelOne, LevelTwo, and LevelThree. It updates dynamically as users defeat enemies, reflecting the specific points earned for each level.

- **Pause screen:**
When users press on the ESCAPE key, they can pause and choose from the options such as resume, settings, or exit without disrupting the gameplay. This feature enhances the gaming experience by offering more control and flexibility. 

- **Resume button:**
Creating resume button allow users to take a break and then return to the game without losing the game process.

- **Settings button:**
Creating settings button allow users to adjust the background music as they play , giving them control over their gaming experience.

- **Volume slider:**
Adding volume slider in settings for users to adjust the volume of the background music.

- **Collision handling in LevelOne:**
The player loses hearts when enemies exit the screen. This feature add a strategic element, requiring users to efficiently handle enemies and stop them from leaving the screen.

- **Win screen:**
The win screen provides users with options such as proceeding to the next level, accessing the shop, or returning to the main menu. Additionally, it displays the user's final score, allowing them to track their performance.

- **Go to next level button:**
After defeating the enemies, players can take a short break instead of immediately proceeding to the next level.

- **Shop in LevelOne:**
Users can purchase hearts by clicking the shop button. However, users are limited to buying a maximum of 2 hearts during LevelOne only. Buying hearts is not allowed in LevelTwo, as it is the final level of the game.

- **Shop in LevelTwo:**
In Level 2, the shop is restricted to all users. A popup message notifies players that heart purchases are unavailable, ensuring clarity and maintaining the challenge of the level.

- **Created Game State:**
Implemented a game state management system using the GameState class to efficiently track player health (hearts), shop purchases, and level-specific data. This ensures the  smooth transitions between levels.

- **Return to menu button:**
Users can return to main menu if they choose not to proceed to the next level or wish to exit the game. This allows players to reset their game process.

- **Game lose screen:**
The game lose screen allows users to view their final score, providing a clear end to the gameplay session. After losing, users can choose to return to the main menu and restart the game.

- **Stop background music:**
The background music is stopped when users return to the main menu to prevent overlapping audio. To ensures users have smoother user experience and avoids any audio-related distractions when playing the game.

- **Created LevelThree:**
LevelThree introduces an additional feature with the inclusion of bombs. These bombs appear randomly and users need to avoid the collisions. Additionally, the projectile velocity increases. This added feature increase the difficulty of the level, making it more challenging.

- **Added background image:**
A captivating background image has been added to improve the game's visual appeal and create a more engaging gameplay experience.

- **Added bomb sound effect to LevelThree:**
A bomb sound effect has been added to LevelThree to enhance the audio experience and providing auditory feedback when a bomb collision occurs.

- **Added bombs to LevelThree:**
Bombs have been introduced in Level Three, spawning randomly to increase the challenge for players. These bombs must be avoided to prevent user from taking damage.

- **Achievement Added to LevelOne:**
Achievements have been integrated into LevelOne. When the user meets the kill target, they will be awarded the "All Enemies Defeated in Level One" achievement. To rewards the users for completing LevelOne. But if the user dies without completing the level, no achievements will be unlocked.

- **Achievement Added to LevelTwo:**
Achievement is now awarded in Level Two when the user successfully defeats the boss. They will be awarded the "All Enemies Defeated in Level Two" achievement. To rewards the users for completing LevelTwo. But if the user dies without completing the level, no achievements will be unlocked.

- **Achievements Added to LevelThree:**
An achievement will be added to LevelThree when the user successfully defeats the boss. Additionally, if the user completes LevelThree without colliding with any bombs, they will earn the "Bomb Dodger" achievement. But if the user dies without completing the level, no achievements will be unlocked.

- **Speed up projectile in LevelThree:**
In LevelThree, the projectile speed is increased to enhance the level's difficulty, providing a greater challenge for players and testing their reaction time.

- **Hearts and score reset:**
The user's hearts and score points are properly reset when returning to the main menu, ensuring a consistent gameplay experience.

- **Health and damage handling:**
Both user and enemy planes have health represented by hearts that decrease upon collisions. When the user's hearts reach zero, the game over screen is triggered, ending the gameplay session.

- **Enemy plane penetration:**
Deducted all the user’s hearts after enemy planes exit the screen in Levelone.
---
### **5.2 Implemented but not working properly**

### **Main menu screen:**
After the game win or lose screen is displayed, users can click on the "Return to Menu" button. This action bring them back to the main menu screen, where they can choose to start a new game, view  instructions, or quit game. However, the main menu screen sometimes does not display correctly, showing an incomplete interface which only showing partially of the play button.

#### Possible causes: 
This issue may be caused by incorrect  scene initialization, issues with rendering the background image, or incomplete loading of UI components. This could also result from improper resizing or misalignment of UI elements during screen transitions or a potential logic error in reinitializing the main menu layout.

  #### Possible solution: 
  1. **Correct scene initialization**
     Ensure the main menu scene is fully initialized with all components and layout correctly set before transitioning.
  2. **Ensure all the UI components are loaded**
      Ensure all UI components, including all other buttons are fully loaded and rendered before  displaying the main menu screen.
---
### **Stationary bullet issue:**
A single bullet fired by the plane remains fixed on the screen, failing to follow its expected trajectory. While other bullets move and synchronize properly with the plane.

#### **Possible causes:**
This issue could caused by improper detachment of the bullet after firing, causing it to stay stationary on the screen. There might also be a synchronization issue where the projectile is tied with the movement of the plane, preventing it from functioning independently. Additionally, the issue might be related to object state conflict as the activeProjectile state might not be properly managed.

#### **Possible solution:**
1. **Ensure proper detachment:**
Modify the projectile so it will be entirely separated from the plane once fired. Check the activeProjectile variable is cleared immediately after the bullet is fired.

2. **Fix synchronization problem:**
Modify the synchronization problem to apply only to projectiles still attached to the plane, ensure that it does not interfere with bullets that have already been fired.

3. **Fix object state conflict:**
Modify the activeProjectile lifecycle to ensure the smooth state transitions from initialization to firing and detachment to avoid the conflicts.
---
### **Hearts Display in LevelThree:**
In LevelThree, the hearts sometimes do not display correctly as 5, even though the user still takes 5 hits to die.

#### **Possible causes:**
The game state and the UI might not be in sync, so the hearts shown on the screen don't match the player's actual health. Or a bug in the code that updates the hearts on screen to display incorrectly.

#### **Possible solution:**
1. **Problem to update to screen:**
Debug all the lines that related to the heart display to find out the causes.
---
### 5.3 Features Not Implemented
### **Restart level button:**
I planned to include a restart level button in every level, allowing users to retry after losing the game. But, I replaced this button with a "Return to Menu" button, allowing users to go back to the main menu and restart the game from there.

#### Reason:
1.	**Time constraints:**
This feature was not implemented due to time constraints and the focus on ensuring other key features were working properly. Leaving it insufficient time to implement and test the feature across all the levels.
---
### **Additional boss in LevelThree:**
The addition of a second boss in Level 3 was initially planned but ultimately left out. 

#### Reason:
1.	**Too difficult for users:**
Having two bosses along with randomly bomb spawns and speed up projectiles created an overly chaotic environment, making it difficult for users to focus on both bosses and bombs at the same time.
---
### **5.4 New Java Classes**

### **MainMenu class:**
The MainMenu class serves as the entry point for the game. It provides the main menu where users can start the game, view instructions, or quit game. Additionally, it manages the display of custom elements, including background images, fonts, and cursors. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**startGame()**
Uses Controller class to launch the game.

2.	**showInstructions():**
Displays a screen to show the instructions to users.

3.	**createStyledButton():**
Create the style of the buttons including the hover effects and default cursors.

4.	**show():**
Displays the main menu screen with custom background, title, and buttons.
---
### **PauseScreen class:**
Displaying pause screen, allow users to pause the game and access options such as resuming the game, adjusting settings, or exiting the game. Additionally, it also provides a settings menu to adjust the volume. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**show():**
Displays the pause screen with custom background, title, and buttons.

2.	**createStyledButton():**
Create the style of the buttons including the hover effects and default cursors.

3.	**showSettings():**
Display the settings screen with a volume slider to adjust the volume.

4.	**resumeButton Action:**
Allows the users to resume the game by closing the pause screen and running the provided resume action.

5.	**settingsButton Action:**
Opens the settings menu to allow users to adjust the volume for the background music.

6.	**exitButton Action:**
Closes the pause screen and the game, ending the session.
---
### **GameWinScreen class**
The GameWinScreen class is responsible for displaying the “You Win” screen when a user successfully completes a level. It also displays the achievements earned by the user upon successfully completing the level. It provides option to go to the next level, shop, or return to main menu. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**showGameWinScreen(Stage displayStage, int score, LevelParent currentLevel, Runnable onNextLevel)**
Displays the “You Win” screen for Level 1 and Level 2, featuring a “You Win” message, the user’s score, and buttons for proceeding to the next level, accessing the shop, or returning to the main menu. Includes the functionality to prevent users from accessing shop in Level 2 by showing a pop-up message.

2.	**Showlvl3WinScreen(Stage displayStage, int score):**
Displays the “You Win” screen for Level 3, featuring a “You Win” message, the user’s score, achievements earned, and a button to return to main menu.

3.	**createStyledButton(String text, Font font):**
Create the style of the buttons including the hover effects and default cursors.

4.	**loadCustomFont(String fontPath, int size):**
Load custom fonts from the specified file path, and if the custom fonts fail to load, fall back to the default font.

5. **Achievements Display:**
Displays the achievements earned during the level. When the boss or all enemies are defeated, the "All Enemies Defeated" achievement is unlocked. If the user avoids colliding with any bombs in Level Three, the "Bomb Dodger" achievement is unlocked.
---
### **GameEndScreen class:**
The GameEndScreen class is responsible for displaying the “Game Over” screen when the user loses the game. It also displays there is no achievements earned by the user as they failed to complete the level and provides an option to return to main menu. It is located in the com.example.demo.UI package.

### Sub-components:
1.	**showGameEndScreen(Stage displayStage, int score)**
Displays the “Game Over” screen for all levels, showing the user’s score and a label indicating that no achievements were unlocked due to failing to complete the game. It also includes a button to return to the main menu.

2.	**loadCustomFont(String fontPath, int size):**
Load custom fonts from the specified file path, and if the custom fonts fail to load, fall back to the default font.
---
### **Shop class:**
Shop class allow users to purchase in-game items such as extra hearts. Users can purchase extra hearts at Level 1, and access a pop-up message if certain actions are unavailable. This ensures that the shop has limited access and can be locked once certain conditions are met. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**show():**
Display the shop interface. It includes components such as close button, which allow users to close shop and return to game. It also provides a buy item 1 button. When user purchase the item, this method updates the game’s heart display and increase the purchase count. If they purchase more than two times, the shop is locked to prevent users from purchasing more hearts. To ensure a good user experience, applying setFocusTraversable(false) to ensure other keys does not trigger the screen.

2.	**createCloseButton(Stage shopStage):**
Create a styled close button to close the shop.

3.	**loadFont(String fontPath, int size):**
Load custom fonts from the specified file path, and if the custom fonts fail to load, fall back to the default font.

4.	**showShopPopup(String title, String message):**
Display a pop-up message when certain conditions are met. It also provides a close button to close the pop-up. setFocusTraversable(false) is also applied to ensure other keys does not trigger the screen.
---
### **LevelThree class:**
The LevelThree class extends LevelParent and introduces a more challenging gameplay experience. In this level, players must defeat a shielded boss while dealing with randomly spawning bombs. Additionally, sound effects are integrated to enhance user immersion and provide auditory feedback during gameplay. It is located in the com.example.demo.levels package.

#### Sub-components:
1.	**updateScene():**
Update the level by checking the bomb collisions and randomly spawning bombs. Using bombCollision() method to check for collisions between bombs and users.

2.	**spawnBomb():**
Spawn bombs at random positions on the screen, one at a time. Existing bombs are cleared from the scene to avoid overlapping. A new bombImage object is then created and added to the root. Bombs are spawned in visible areas.

3.	**bombCollision():**
Manage collisions between the user and bombs. Check if any bomb collides with the user’s bounding box; if a collision is detected, reduce the user’s hearts and update the LevelView to reflect the reduced heart count. Finally, remove the bomb from the screen after the collision.

4.	**playBombSound():**
Playing a sound effect after user collides when a bomb. Ensures the game does not crash if the sound file not found or corrupted by catching exceptions.

5.	**startGame():**
Initializes game state for Level Three. Reset user’s hearts to initial health value and updates the GameState object to set the player’s initial health for Level One to initial health value. A debug message is printed to confirm the game starts with the correct health value.

6.	**activeShield():**
Activate the boss's shield to prevent it from taking damage. Calls showShield() method at the shieldImage object to make the shield visible.

7.	**deactivateShield():**
Deactivate the boss’s shield to allow it to take damage. Calls hideShield() method at shieldImage object to remove the shield.

8. **checkIfGameOver():**
Checks whether the user has lost all their hearts or if the boss has been defeated. If the boss is defeated, the "Boss Defeated in Level Three" achievement is unlocked. Additionally, if the user avoids all bomb collisions, the "Bomb Dodger in Level Three" achievement is unlocked.

9. **updateScene():**
Updates the level by randomly spawning bombs based on a mathematical probability and handling bomb collisions using the bombCollision() method.
---
### **LevelViewLevelThree class:**
#### This class is derived from the original LevelViewLevelTwo code, with no modifications made. It is located in the com.example.demo.view package.
---
### **GameState class:**
GameState manage the game’s state across different levels and interactions. It tracks the users health, shop purchases and other game variables. This class is located in the com.example.demo.GameState package.

#### Sub components:
1.	**updateScene():**
Ensure that a single instance of the GameState class is created and shared across all other classes, allowing them to access the same GameState instance.

2.	**Level health:**
Manages the player’s hearts across multiple levels, allowing updates and resets based on gameplay events.
The variables level1Hearts and level2Hearts are used to track the hearts in each level. Methods like setLevel1Hearts(int hearts) and addLevel2Hearts(int extraHearts) update the player’s health dynamically during gameplay.
Additionally, resetLevel1Hearts() and resetLevel2Hearts() ensure that each level starts with an initial health of 5.

3.	**Shop:**
The Shop class manages user interactions with the in-game shop and ensures it is locked under specific conditions. It tracks the number of times an item has been purchased using shopItem1PurchaseCount, preventing further access to the shop once certain conditions are met by utilizing methods like isShopLocked() and setShopLocked(Boolean locked). Additionally, the resetShop() method resets all purchase counts and lock statuses when the game is restarted, ensuring the shop is restored to its initial state.

4. **Achievements:**
Tracks the achievements unlocked by users during gameplay. The addAchievement(String achievement) method is used to add new achievements to the list.

5.	**resetAll():**
Resets both level1Hearts and level2Hearts to default value of 5 and clear the purchase count for the item, and unlock the shop when user restart the game.
---
### **bombImage class:**
This class handles the display of the image size, and positioning of the bomb to the screen. It is located at com.example.demo.assets.

#### Sub components:
1.	**Static constants:**
IMAGE_NAME stores the file path of the bomb image, while BOMB_SIZE is used to define the size of the bomb.

2.	**Public(bombImage(double xPosition, double yPosition)):**
this.setLayoutX(xPosition) and this.setLayoutY(yPosition) are used to position the bomb image based on the specified x and y coordinates in the game. this.setImage loads the image from the specified file path. this.setVisible(false) ensures that the bomb is initially not visible on the screen and is displayed only when necessary. this.setFitHeight(BOMB_SIZE) and this.setFitWidth(BOMB_SIZE) define the dimensions of the bomb image.
---
### **5.5 Modified Java Classes**

### **UserProjectile class:**

#### Changes made: 
1.	**Sound effect**
Added shooting.wav as the shooting sound. A static block was introduced to load the sound file path, ensuring the sound is ready to play when a projectile is fired. The playSound method is used to play the shooting sound when the projectile is created, providing auditory feedback to the user. To enhance the user experience by adding auditory feedback.

2.	**Projectile movement:**
The isFired flag determines whether the projectile is synchronized with the plane or has already been fired. The updatePosition method ensures the projectile moves horizontally only after it has been fired. This provides precise control over the projectile's movement, preventing unintended motion before it is fired.

3.	**Synchronize with plane:**
The syncWithPlane method is used to align the projectile with the plane. It calculates the x and y positions based on the plane's height and width.To ensure the projectile sync to the movement of the plane.

4.	**fire() method:**
Added this method to set the isFired flag to true, enable the projectile to move on its own once launched.
---
### **LevelViewLevelTwo class:**

#### Changes made: 
1.	**HeartDisplay:**
Added HeartDisplay to show the user’s remaining hearts on screen. Updated the constructor to set up HeartDisplay with the initial hearts and its position. addHeartDisplayToRoot() method is to add the heart display the game root. This feature is to clarify the hearts on the screen which can improve the overall gameplay experience.

3.	**updateHeartDisplay:**
This method is to update the heart display onto the screen. This ensures the heart display updated with the user’s current health, giving user instant feedback during the game.

4.	**private int hearts:**
Added this to store the current number of hearts. To allows the LevelViewLevelTwo class to update the HeartDisplay when the player’s health changes.
---
### **LevelTwo class:**
#### Changes made: 
1. **Shield Image**
ShieldImage object is added to boss like a shield. Shield image is initialized at (0,0) and match its position with the boss plane using layoutXProperty and layoutYProperty. To ensure the shield match the position of the boss plane.

2. **Boss with the shield:**
Updated the boss to add the function of the shield by passing ShieldImage object to its constructor. To ensure the boss can have interactive defensive abilities.

3. **Game State:**
GameState used to get the player’s health for Level 2 and set the player’s health when the level starts. The instantiateLevelView method has been updated to display the player’s health by using GameState. This is to ensure the consistency of health across other levels.

4. **Active and deactivate shield:**
Added activateShield() and deactivateShield() methods to show and hide the shield.

5. **checkIfGameOver():**
Edited checkIfGameOver() method to go to next level if the boss is defeated. Ensure after user defeated the boss then move to next level.

6. **Heart reset:**
Updated startGame() to reset the player initial health to initial value using GameState. To ensure each levels displayed the initial value of initial player health.

7. **Boss class:**
Edited Boss class to include the ShieldImage which allows it to show or hide the shield

8. **GameState:**
Enhanced to manage and access Level Two hearts, including functionality to lock and unlock the shop based on the purchase count. This ensures consistency in health and purchase counts across all levels.
---
### **Level Parent class:**

#### **Changes Made:**
1.	**isPaused:**
Track whether the game is paused.

2. **IsUpdated:**
Ensure the state transition occurs only once which helps to manage the game state of the game.

3. **isChangedState:**
Check if the game state is changed for smooth level transitions which helps to manage the game state of the game.

4. **isGameOver:**
Track if the game has ended which helps to manage the game state of the game.

5. **playerScore:**
Track the user’s score for the levels which helps to manage the game state of the game.

6. **Methods for pause and resume:**
Allow users to pause and resume the game to enhance the user experience.

7. **Game initialization:**
Updated instantiateLevelView() to ensure LevelView is initialized properly and throws an exception if null. initializeScene() to initialize LevelView with score and heart displays. To ensure it does not have any runtime errors and proper initialization.

8. **Added keys movement:**
Added Keycode.LEFT and Keycode.RIGHT to allow plane to move left and right. Allow the user plane to have more controls.

9. **Collisions:**
Added the handlePlaneCollisions() to deduct hearts when user collides with enemies and check if the user is destroyed. Added handleUserProjectileCollisions() to add scores when enemies are destroyed based on the current level. To enhance the game by taking damage for collisions and awarding score for defeating the enemies.

10. **Showing win screen and end screen:**
Updated winGame() to show the game win screen in Level 3 using GameWinScreen.showlvl3WinScreen() and show transition to next level for other levels. Updated loseGame() to show the game end screen. To enhance the gameplay experience and ensure a seamless transition between levels.

11. **Score:**
addScore(int points) method is to adds points when enemies are defeated. resetScore() is to reset the score at new level when the game restart. Updated updateLevelView() to update the score display. To allow users to view the final score for their performance.

12. **Enemy:**
handleEnemyPenetration() used to deduct all the hearts if an enemy exits the screen. Updated removeAllDestroyedActors() to clean up the destroyed actors. To fix the error when enemy exits the screen and ensure proper handling of the game objects.

13. **Level transition:**
Updated checkIfGameOver() to go to next level if all enemies are defeated and trigger game over screen if user is destroyed. To maintain smooth level transitions and gameplay. 

14. **Debugging message:**
Added debugging messages to track score updates, heart deductions, and level transitions to ensure the game logic functions correctly.
---
### **Level One class:**
#### **Changes Made:**
1. **Target score**
Check if player’s health (userIsDestroyed()) and whether the kill target has been reached (userHasReachedKillTarget()) and check if it reached the target score. To ensure the user reaches the score and kill target to proceed to next level.

2. **Achievement:**
The achievement "All Enemies Defeated in Level One" is unlocked when the user successfully destroys all the enemies in Level One. Once all enemies are defeated, this achievement will be displayed.
---
### **Level View class**
##### **Changes Made**:
1.	**Added score label:**
Display the score label and positioned at the top right. To update the score points to users.

2. **Style of score label:**
Display score label in default text.

3.	**Score:**
updateScore(int score) method to update the displayed score and add score label to game root.

4. **Heart display:**
Added updateHeartDisplay(int hearts) to update the hearts based on the remaining player’s health. resetHearts(int heartsToDisplay) method to reset the heart display to the default value.
---
### **Controller class**
#### **Changes Made:**
1.	**Added PlayMusic():**
Manage the playback of music and loop the music to enhance user experience by providing background music during gameplay.

2. **Added stopMusic():**
Stops and disposes of the current music, to stop the music when user click on return to menu.

3. **Added getMediaPlayer():**
Get the current MediaPlayer instance.

4. **setVolume(double volume):**
Adjust the volume of the music.

5. **handleKeyPress(KeyCode code):**
Method to detect the ESCAPE key to trigger the pause screen to provide users with ability to pause the game 

6. **showPauseScreen():**
Display the pause screen, allow user to pause game and and access options like settings or resume gameplay.

7. **resumeGame():**
Resume the gameplay and continuing with the current timeline.

8. **openSettings():**
Method to open the settings screen, to adjust the volume of music.

9. **Game state:**
Called GameState.getInstance().resetAll() to reset the game state. To maintain a consistent game state when restarting game.

10. **goToLevel():**
Modified goToLevel() to include a Stage parameter for creating levels and updated the level transition process to add key event handlers for the pause feature.

11. **Shop:**
Added openShop() for the current level.

12. **Return to menu:**
Added returnToMenu() to stop the current music, resets the game state and restarts the music when returning to menu.
---
### **HeartDisplay class**
#### **Changes Made:**
1.	**Added file path:**
Updated the HEART_IMAGE_NAME to a new image file. It is flexibility as can just change the file path of the image.

2.	**Final heartContainer:**
Apply final to the heartContainer to ensure it does not reassigned again. 

3.	**Update Heart Count:**
Add hearts based on the count, clear the current hearts, and throws an exception if heart count is less than 0 or the image is not found. Allows the number of hearts to adjust based on the player's health, clears old hearts to avoid duplication, and includes error handling to prevent crashes.

4.	**Remove heart:**
Remove the hearts in the last container. It is clear and easier for user to understand.

5. **addHeart():**
Add the player’s health when they gain hearts. It is easier to add the hearts without reset the display.

6.	**resetHearts(int heartsToDisplay):**
Calls updateHeartCount() to reset the number of hearts displayed. Ensure the health is updated correctly.
---
### **User plane class**
#### **Changes Made:**
1.	**Add horizontal movement:**
Added horizontalVelocityMultiplier to control the movement horizontally. Used X_LEFT_BOUND and X_RIGHT_BOUND to define the horizontal boundaries. Added moveLeft(), moveRight(), and stopHorizontalMove() to make it move along the x-axis. Update updatePosition() to control the horizontal movement. This allow the users to move both vertically and horizontally.

2.	**Sync projectile with plane:**
Using UserProjectile activeProjectile to synchronize the projectile with the plane. Added the assignProjectile(UserProjectile projectile) method to link with the projectile and user plane. Updated updatePosition() to sync with the position of the plane and fireProjectile() to launch new projectile and clear the current one after it is fired. Ensure the projectile follows the movement of the user plane, providing a more precise shooting.

3.	**Position projectile at tip of plane:**
Adjust fireProjectile() to shoot at the tip of plane and center the projectile vertically. Ensuring the projectiles are properly aligned with the plane.

4.	**Vertical and Horizontal movement:**
Separate the movement for moveVertical() and moveHorizontal() to make it easier to understand.

5.	**Moving inside the screen:**
Updated updatePosition() to move vertically and horizontally without moving out of the screen.

6.	**Track kill count:**
Added getKillCount() to align with getNumberOfKills() for consistency. To allow  the game to track the kill count.
---
### **Boss class**
#### **Changes Made:**
1.	**activiteShield() and deactivateShield():**
Updated to show and hide the shield image using shieldImage.showShield() and shieldImage.hideShield(). To show the shield to users to show that the boss will not take any damage.

2.	**takeDamage() class:**
Updated the class to only take damage if the shield is not visible. To ensure the boss does not take damage when there is shield.
---
### **5.6 Deleted Java Classes**
#### **WinImage class:**
#### The WinImage class was removed because its features were replaced by the GameWinScreen, which offers a more interactive way to handle winning scenarios. Unlike the simple **WinImage**, which likely just showed a basic image, the GameWinScreen provides more functionality and flexibility. 
---
#### **GameOverImage class:**
#### The GameOverImage class was removed because its features was replaced by the GameEndScreen, which handles game-over situations in a more interactive way. Instead of just showing a image like GameOverImage, the GameEndScreen provides more features to improve the user experience.
---
### **5.7 Unexpected Problems**
#### **Java JDK Issue:**
After downloading the Java JDK provided by my lecturer, I was unable to use it in IntelliJ as it displayed an error stating that the Java JDK was not found. To resolve this, I downloaded a JDK from Temurin Eclipse, which successfully allowed me to run my game. However, after adding the sound file, I encountered issues and was unable to run the game. I then decided to try Visual Studio Code to run my game, and it successfully built and ran without any problems. I believe this is due to the built-in functions available in Visual Studio Code, as I did not need to download any additional tools for it to work. But after completing half of the game, I can rerun the game using IntelliJ.


