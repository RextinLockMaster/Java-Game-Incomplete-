package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.UI.MainMenu;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainTest {

    /**
     * Sets up the JavaFX environment needed for testing.
     * Runs before all tests to start the JavaFX application thread.
     */
    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    /**
     * Tests the launch of the Main method.
     * Ensures user can launch game without errors.
     */
    @Test
    void testMainLaunch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Main mainStart = new Main();
                Stage stage = new Stage();

                assertDoesNotThrow(() -> {
                    mainStart.start(stage);
                });
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the display of the main menu.
     * Ensures the main menu can be displayed without errors.
     */
    @Test
    void testMainMenuDisplay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Controller mockController = new Controller(stage);
                MainMenu mainMenu = new MainMenu(stage, mockController);

                assertDoesNotThrow(() -> mainMenu.show());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
