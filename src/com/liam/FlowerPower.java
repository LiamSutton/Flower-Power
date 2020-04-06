package com.liam;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the Class that holds the main loop of the Program
 */
public class FlowerPower extends Application {

    /**
     * The stage which will be currently shown
     */
    Stage primaryStage;

    /**
     * The Scene for the game loop
     */
    Scene gameScene;

    /**
     * The Scene for the main menu
     */
    Scene mainMenuScene;

    /**
     * The Scene for the information screen
     */
    Scene informationScene;

    /**
     * The Scene for the game over screen
     */
    Scene gameOverScene;

    /**
     * The layer on which all background Objects will be drawn
     */
    Canvas backgroundLayerCanvas;

    /**
     * The layer on which all foreground Objects will be drawn
     */
    Canvas foregroundLayerCanvas;

    /**
     * Context for the background layer
     */
    GraphicsContext backgroundContext;

    /**
     * Context for the foreground layer
     */
    GraphicsContext foregroundContext;

    /**
     * Displays the players current score
     */
    Text scoreText;

    /**
     * Displays the players remaining lives
     */
    Text livesText;

    /**
     * The flowerbed which holds all the flowers
     */
    FlowerBed flowerBed;

    /**
     * The watering can used to heal flowers in the flowerbed
     */
    WateringCan wateringCan;

    /**
     * The sun used to grow flowers in the flowerbed
     */
    Sun sun;

    /**
     * Run the main loop of the application
     * @param args arguments provided to the program
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is where all the updates in the main game loop occur and aims to run at a consistent 60FPS
     */
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {

            // If the player has ran out of lives, transition to the game over scene
            if (GameManager.gameOver()) {
                timer.stop();
                gameOverScene = createGameOverScene();
                primaryStage.setScene(gameOverScene);
                primaryStage.setTitle(Constants.GAME_OVER_TITLE);
                primaryStage.show();
            }

            // Render the background
            backgroundContext.setFill(Color.SKYBLUE);
            backgroundContext.fillRect(0, 0, backgroundLayerCanvas.getWidth(), backgroundLayerCanvas.getHeight());

            // Attempt to grow the flower at the suns current position
            sun.Shine(flowerBed.getFlower(sun.currentPosition));

            // update all flowers in the flowerbed
            flowerBed.update();

            // redraw the watering can at its current position
            wateringCan.update();

            // redraw the sun at its current position
            sun.update();
            scoreText.setText("SCORE: " + GameManager.score);
            livesText.setText("LIVES LEFT: " + GameManager.lives);
        }
    };

    /**
     * Any keyboard input will trigger this event to be raised
     */
    EventHandler<KeyEvent> keyPressedEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            // Move the watering can left
            if (keyEvent.getCode() == KeyCode.A) wateringCan.move(Constants.DIRECTION_LEFT);

            // Move the watering can right
            if (keyEvent.getCode() == KeyCode.D) wateringCan.move(Constants.DIRECTION_RIGHT);

            // Water the flower at the watering cans current position
            if (keyEvent.getCode() == KeyCode.SPACE) {
                int position = wateringCan.getCurrentPosition();
                FlowerDelegator target = flowerBed.getFlower(position);
                wateringCan.water(target);
            }

            // Move the sun left
            if (keyEvent.getCode() == KeyCode.LEFT) sun.move(Constants.DIRECTION_LEFT);

            // Move the sun right
            if (keyEvent.getCode() == KeyCode.RIGHT) sun.move(Constants.DIRECTION_RIGHT);
        }
    };

    /**
     *  Initialize all of the Scenes and load the main menu
     * @param stage the Stage to be launched
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        mainMenuScene = createMainMenuScene();
        informationScene = createInformationScene();
        gameScene = createGameScene();
        gameOverScene = createGameOverScene();

        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle(Constants.MENU_TITLE);
        primaryStage.show();

    }

    /**
     * Create the main menu scene
     * @return the created Scene Object
     */
    public Scene createMainMenuScene() {
        Pane mainMenuRoot = new Pane();
        mainMenuScene = new Scene(mainMenuRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Create UI for Main Menu
        Label greetingsLabel = new Label(Constants.GREETING_MESSAGE);
        greetingsLabel.setFont(Constants.HEADING_FONT);
        greetingsLabel.setLayoutX(300);
        greetingsLabel.setLayoutY(100);
        greetingsLabel.setPrefSize(512, 100);

        Button startGameButton = new Button("Start Game");
        startGameButton.setFont(Constants.MENU_FONT);
        startGameButton.setLayoutX(300);
        startGameButton.setLayoutY(300);
        startGameButton.setPrefSize(350, 100);

        Button informationButton = new Button("Information");
        informationButton.setFont(Constants.MENU_FONT);
        informationButton.setLayoutX(300);
        informationButton.setLayoutY(450);
        informationButton.setPrefSize(350, 100);


        mainMenuScene.setCursor(Cursor.DEFAULT);

        // Hook up scene transition
        startGameButton.setOnAction(e-> {
            // Transition to game scene
            resetGameState();
            primaryStage.setScene(gameScene);
            primaryStage.setTitle(Constants.GAME_TITLE);
            timer.start();
            primaryStage.show();
        });

        // Transition to the information scene
        informationButton.setOnAction(e -> {
            primaryStage.setScene(informationScene);
            primaryStage.setTitle(Constants.INFORMATION_TITLE);
            primaryStage.show();
        });

        // Add Main Menu components to the Pane
        mainMenuRoot.getChildren().addAll(greetingsLabel, startGameButton, informationButton);
        return mainMenuScene;
    }

    /**
     * Create the information scene
     * @return the created Scene Object
     */
    public Scene createInformationScene() {
        Pane informationRoot = new Pane();
        informationScene = new Scene(informationRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        Label objectiveHeadingLabel = new Label("Objective");
        objectiveHeadingLabel.setFont(Constants.HEADING_FONT);
        objectiveHeadingLabel.setLayoutX(400);
        objectiveHeadingLabel.setLayoutY(50);
        objectiveHeadingLabel.setPrefSize(1024, 100);

        Label objectiveLabel = new Label(Constants.OBJECTIVE_MESSAGE);
        objectiveLabel.setFont(Constants.INSTRUCTIONS_FONT);
        objectiveLabel.setLayoutX(100);
        objectiveLabel.setLayoutY(150);
        objectiveLabel.setPrefSize(1024, 200);

        Label movementHeadingLabel = new Label("How to move");
        movementHeadingLabel.setFont(Constants.HEADING_FONT);
        movementHeadingLabel.setLayoutX(375);
        movementHeadingLabel.setLayoutY(350);
        movementHeadingLabel.setPrefSize(1024, 100);

        Label wateringCanLabel = new Label(Constants.WATERING_CAN_INSTRUCTION);
        wateringCanLabel.setFont(Constants.INSTRUCTIONS_FONT);
        wateringCanLabel.setLayoutX(250);
        wateringCanLabel.setLayoutY(400);
        wateringCanLabel.setPrefSize(1024, 100);

        Label sunLabel = new Label(Constants.SUN_INSTRUCTION);
        sunLabel.setFont(Constants.INSTRUCTIONS_FONT);
        sunLabel.setLayoutX(250);
        sunLabel.setLayoutY(450);
        sunLabel.setPrefSize(1024, 100);

        Button backButton = new Button("Go Back");
        backButton.setFont(Constants.MENU_FONT);
        backButton.setLayoutX(300);
        backButton.setLayoutY(550);
        backButton.setPrefSize(350, 100);

        informationScene.setCursor(Cursor.DEFAULT);

        backButton.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle(Constants.MENU_TITLE);
            primaryStage.show();
        });

        informationRoot.getChildren().addAll(objectiveHeadingLabel, movementHeadingLabel, objectiveLabel, wateringCanLabel, sunLabel, backButton);

        return informationScene;
    }

    /**
     * Create the game scene
     * @return the created Scene Object
     */
    public Scene createGameScene() {
        Pane gameRoot = new Pane();
        gameScene = new Scene(gameRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        gameScene.setCursor(Cursor.NONE);

        backgroundLayerCanvas = new Canvas(gameScene.getWidth(), gameScene.getHeight());
        foregroundLayerCanvas = new Canvas(gameScene.getWidth(), gameScene.getHeight());

        backgroundContext = backgroundLayerCanvas.getGraphicsContext2D();
        foregroundContext = foregroundLayerCanvas.getGraphicsContext2D();

        backgroundContext.setFill(Color.SKYBLUE);
        backgroundContext.fillRect(0, 0, backgroundLayerCanvas.getWidth(), backgroundLayerCanvas.getHeight());

        flowerBed = new FlowerBed(backgroundContext, foregroundContext, 0, backgroundLayerCanvas.getHeight() - 128);
        wateringCan = new WateringCan(foregroundContext, 64, 384);
        sun = new Sun(foregroundContext, 0, 224);
        scoreText = new Text(0, 20, "SCORE: " + GameManager.score);
        scoreText.setFont(Constants.HEALTH_FONT);
        scoreText.setFill(Color.WHITE);

        livesText = new Text(0, 50, "LIVES LEFT: " + GameManager.lives);
        livesText.setFont(Constants.HEALTH_FONT);
        livesText.setFill(Color.WHITE);

        gameRoot.getChildren().addAll(backgroundLayerCanvas, foregroundLayerCanvas, scoreText, livesText);

        gameScene.setOnKeyPressed(keyPressedEvent);

        return gameScene;
    }

    /**
     * Create the game over scene
     * @return The created Scene
     */
    public Scene createGameOverScene() {
        Pane gameOverRoot = new Pane();
        gameOverScene = new Scene(gameOverRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Label scoreLabel = new Label("Your score was: " + GameManager.score);
        scoreLabel.setFont(Constants.SCORE_FONT);
        scoreLabel.setLayoutX(300);
        scoreLabel.setLayoutY(100);
        scoreLabel.setPrefSize(500, 100);

        Button restartButton = new Button("Play again");
        restartButton.setFont(Constants.MENU_FONT);
        restartButton.setLayoutX(300);
        restartButton.setLayoutY(300);
        restartButton.setPrefSize(350, 100);

        Button menuButton = new Button("Go to menu");
        menuButton.setFont(Constants.MENU_FONT);
        menuButton.setLayoutX(300);
        menuButton.setLayoutY(450);
        menuButton.setPrefSize(350, 100);

        gameOverScene.setCursor(Cursor.DEFAULT);

        restartButton.setOnAction(e -> {
            resetGameState();
            primaryStage.setScene(gameScene);
            primaryStage.setTitle(Constants.GAME_TITLE);
            timer.start();
            primaryStage.show();
        });

        menuButton.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle(Constants.MENU_TITLE);
            primaryStage.show();
        });

        gameOverRoot.getChildren().addAll(scoreLabel, restartButton, menuButton);

        return gameOverScene;
    }

    /**
     * Return the game to its initial state to allow replay
     */
    public void resetGameState() {
        GameManager.resetGameVariables();
        flowerBed = new FlowerBed(backgroundContext, foregroundContext, 0, backgroundLayerCanvas.getHeight() - 128);
        wateringCan = new WateringCan(foregroundContext, 64, 384);
        sun = new Sun(backgroundContext, 0, 224);
    }
}
