package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.UI.MainMenu;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class to launch the Sky Battle game.
 * Set up the game window and displays the main menu.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;

	/**
     * Starting point to run the JavaFX application.
     * Sets up the main game and main menu.
     *
     * @param stage The primary stage for the application.
     * @throws ClassNotFoundException If the Controller class is not found.
     * @throws NoSuchMethodException If a method in the Controller is not found.
     * @throws SecurityException If a security violation occurs.
     * @throws InstantiationException If the Controller cannot be instantiated.
     * @throws IllegalAccessException If there is an illegal access.
     * @throws IllegalArgumentException If an illegal argument.
     * @throws InvocationTargetException If method invocation fails.
     */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		myController = new Controller(stage);
		//myController.launchGame();
		MainMenu mainMenu = new MainMenu(stage, myController);
        mainMenu.show();
	}

	/**
     * The main method to run the JavaFX application.
     *
     * @param args Command-line arguments.
     */
	public static void main(String[] args) {
		launch();
	}
}


