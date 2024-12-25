package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.levels.LevelParent;
import com.example.demo.levels.LevelTwo;
import com.example.demo.GameState.GameState;
import com.example.demo.UI.PauseScreen;
import com.example.demo.UI.Shop;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Controller class manages gameplay, transition to levels, 
 * background music, pause screen, and manages different UI screens.
 */
public class Controller implements Observer { 

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private Scene mainScene;
	private LevelParent currentLevel;
	private boolean isGamePaused = false;
	private static MediaPlayer mediaPlayer; 
	private double currentVolume = 0.5;

	/**
     * Constructor for the Controller.
     * Set up the game stage and play background music.
     * 
     * @param stage The primary stage of the application.
     */
	public Controller(Stage stage) {
		this.stage = stage;
		PlayMusic();
	}

	/**
     * Plays the background music.
	 * Loop the music.
     */
	public void PlayMusic() {

		stopMusic();
		//Create media object from the audio file
        Media media = new Media(this.getClass().getResource("/com/example/demo/audios/movement.mp3").toExternalForm());
		 //Manage playback of media
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(currentVolume);
		//Loop the music
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		//Start playing music
        mediaPlayer.play();
    }

	/**
     * Stops the current background music and disposes the music.
     */
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null; // Ensure the reference is cleared
        }
    }
	
	/**
     * Get the current MediaPlayer instance.
     * 
     * @return The current MediaPlayer instance.
     */
	public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

	/**
     * Adjusts the volume of the background music.
     * 
     * @param volume The desired volume level.
     */
	public void setVolume(double volume) {
        currentVolume = volume;
		if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

	/**
     * Launches the game.
	 * Reset the game state and start LevelOne.
     * 
     * @throws ClassNotFoundException If the class for the level not found.
     * @throws NoSuchMethodException If there is no level constructor.
     * @throws InvocationTargetException If method invocation fails.
     * @throws InstantiationException If the level instance cannot be created.
     * @throws IllegalAccessException If there is an illegal access.
     */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			GameState.getInstance().resetAll();
			goToLevel(LEVEL_ONE_CLASS_NAME);


	}

	 /**
     * Go to another level.
     * 
     * @param className The class name of the level.
     * @throws ClassNotFoundException If the level class cannot be found.
     * @throws NoSuchMethodException If there is no level constructor.
     * @throws InvocationTargetException If the constructor invocation fails.
     * @throws InstantiationException If the level instance cannot be created.
     * @throws IllegalAccessException If there is an illegal access.
     */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
        InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> levelClass = Class.forName(className);
        Constructor<?> constructor = levelClass.getConstructor(double.class, double.class, Stage.class);
        currentLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), stage);
        currentLevel.addObserver(this);
        Scene scene = currentLevel.initializeScene();

        // Key handling event for pause screen
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        stage.setScene(scene);
        currentLevel.startGame();
}

	/**
     * Handles key press events for ESCAPE key to pause the game.
     * 
     * @param code The KeyCode of the pressed key.
     */
	private void handleKeyPress(KeyCode code){
		if (code == KeyCode.ESCAPE){
			//Pause the game if it is not pause
			if (!isGamePaused){
				showPauseScreen();
			}
			else{
				resumeGame();
			}
		}
	}

	/**
     * Displays the pause screen and pauses the game.
     */
	void showPauseScreen() {
		//Set pause game flag
		isGamePaused = true;
		//Pause timeline in current level
		currentLevel.pauseGame();
		//Create pause screen and show the resume and settings options
        PauseScreen pauseScreen = new PauseScreen(stage, this, this::resumeGame, this::openSettings);
        pauseScreen.show();  //Show the pause screen
    }

	/**
     * Resumes the game.
     */
	void resumeGame() {
		//Clear the game flag
		isGamePaused = false;
		//Resume timeline in current level
		currentLevel.resumeGame();
		stage.setScene(currentLevel.getScene());
		
    }
	
	/**
     * Opens the settings screen.
     */
	private void openSettings() {
    
        System.out.println("Settings screen");
    }

	/**
     * Updates the game observer, go to the next level.
     * 
     * @param arg0 The observable object.
     * @param arg1 The argument passed by the observable.
     */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	/**
     * Opens the shop for the current level.
     */
	 public void openShop() {
        // Ensure the shop is initialized with the current level
        if (currentLevel instanceof LevelTwo) {
            Shop shop = new Shop(stage, (LevelTwo) currentLevel); // Pass the currentLevel
            shop.show();
        } else {
            System.out.println("Shop is not available for this level.");
        }

		
    }
	/**
     * Returns to the main menu.
	 * Reset the game state and music.
     */
	 public void returnToMenu() {
        stopMusic(); // Stop the current music
        PlayMusic(); // Restart the music
		GameState.getInstance().resetAll();
        System.out.println("Returned to Menu");
    }

	

}