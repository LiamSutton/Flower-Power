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

public class FlowerPower extends Application {
    Pane mainMenuRoot;
    Pane informationRoot;
    Pane gameRoot;
    Pane gameOverRoot;
    Stage primaryStage;
    Scene gameScene;
    Scene mainMenuScene;
    Scene informationScene;
    Scene gameOverScene;
    Canvas backgroundLayerCanvas;
    Canvas foregroundLayerCanvas;
    GraphicsContext backgroundContext;
    GraphicsContext foregroundContext;
    Text scoreText;
    FlowerBed flowerBed;
    WateringCan wateringCan;
    Sun sun;
    public static void main(String[] args) {
        launch(args);
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            // TODO: is there a better way of updating the scenes value of score?
            if (GameManager.lives <= 0) {
                timer.stop();
                gameOverScene = createGameOverScene();
                primaryStage.setScene(gameOverScene);
                System.out.println(GameManager.score);
                primaryStage.show();
            }
            flowerBed.update();
            wateringCan.update();
            sun.update();
            sun.Shine(flowerBed.getFlower(sun.currentPosition));
            scoreText.setText("SCORE: " + GameManager.score);
        }
    };

    EventHandler<KeyEvent> keyPressedEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.A) wateringCan.move(Constants.DIRECTION_LEFT);
            if (keyEvent.getCode() == KeyCode.D) wateringCan.move(Constants.DIRECTION_RIGHT);
            if (keyEvent.getCode() == KeyCode.Q) {
                int position = wateringCan.getCurrentPosition();
                FlowerDelegator target = flowerBed.getFlower(position);
                wateringCan.water(target);
            }

            if (keyEvent.getCode() == KeyCode.SPACE) {
                int position = wateringCan.getCurrentPosition();
                FlowerDelegator target = flowerBed.getFlower(position);
                wateringCan.wilt(target);
            }
            if (keyEvent.getCode() == KeyCode.LEFT) sun.move(Constants.DIRECTION_LEFT);
            if (keyEvent.getCode() == KeyCode.RIGHT) sun.move(Constants.DIRECTION_RIGHT);
            if (keyEvent.getCode() == KeyCode.W) GameManager.score+=100;
            if (keyEvent.getCode() == KeyCode.P) GameManager.lives--;
        }
    };

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        mainMenuScene = createMainMenuScene();
        informationScene = createInformationScene();
        gameScene = createGameScene();
        gameOverScene = createGameOverScene();

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();

    }

    public Scene createMainMenuScene() {
        mainMenuRoot = new Pane();
        mainMenuScene = new Scene(mainMenuRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Create UI for Main Menu
        Label greetingsLabel = new Label(Constants.GREETING_MESSAGE);
        greetingsLabel.setFont(new Font("Verdana", 32));
        greetingsLabel.setLayoutX(300);
        greetingsLabel.setLayoutY(100);
        greetingsLabel.setPrefSize(512, 100);

        Button startGameButton = new Button("Start Game");
        startGameButton.setFont(new Font("Verdana", 24));
        startGameButton.setLayoutX(300);
        startGameButton.setLayoutY(300);
        startGameButton.setPrefSize(350, 100);

        Button informationButton = new Button("Information");
        informationButton.setFont(new Font("Verdana", 24));
        informationButton.setLayoutX(300);
        informationButton.setLayoutY(450);
        informationButton.setPrefSize(350, 100);


        mainMenuScene.setCursor(Cursor.DEFAULT);
        // Hook up scene transition
        startGameButton.setOnAction(e-> {
            // Transition to game scene
            resetGameState();
            primaryStage.setScene(gameScene);
            timer.start();
            primaryStage.show();
        });

        informationButton.setOnAction(e -> {
            primaryStage.setScene(informationScene);
            primaryStage.show();
        });

        // Add Main Menu components to the Pane
        mainMenuRoot.getChildren().addAll(greetingsLabel, startGameButton, informationButton);
        return mainMenuScene;
    }

    public Scene createInformationScene() {
        informationRoot = new Pane();
        informationScene = new Scene(informationRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        Label objectiveLabel = new Label(Constants.OBJECTIVE_MESSAGE);
        objectiveLabel.setLayoutX(350);
        objectiveLabel.setLayoutY(200);

        Label wateringCanLabel = new Label(Constants.WATERING_CAN_INSTRUCTION);
        wateringCanLabel.setLayoutX(350);
        wateringCanLabel.setLayoutY(300);

        Label sunLabel = new Label(Constants.SUN_INSTRUCTION);
        sunLabel.setLayoutX(350);
        sunLabel.setLayoutY(400);

        Button backButton = new Button("Go Back");
        backButton.setLayoutX(350);
        backButton.setLayoutY(500);

        informationScene.setCursor(Cursor.DEFAULT);

        backButton.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.show();
        });

        informationRoot.getChildren().addAll(objectiveLabel, wateringCanLabel, sunLabel, backButton);

        return informationScene;
    }

    public Scene createGameScene() {
        gameRoot = new Pane();
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
        scoreText.setFont(new Font("verdana", 20));
        scoreText.setFill(Color.WHITE);

        gameRoot.getChildren().addAll(backgroundLayerCanvas, foregroundLayerCanvas, scoreText);

        gameScene.setOnKeyPressed(keyPressedEvent);

        return gameScene;
    }

    public Scene createGameOverScene() {
        gameOverRoot = new Pane();
        gameOverScene = new Scene(gameOverRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Label scoreLabel = new Label("Your score was: " + GameManager.score);
        scoreLabel.setLayoutX(450);
        scoreLabel.setLayoutY(300);

        Button restartButton = new Button("Play again");
        restartButton.setLayoutX(450);
        restartButton.setLayoutY(400);

        Button menuButton = new Button("Go to menu");
        menuButton.setLayoutX(450);
        menuButton.setLayoutY(500);

        gameOverScene.setCursor(Cursor.DEFAULT);

        restartButton.setOnAction(e -> {
            resetGameState();
            primaryStage.setScene(gameScene);
            timer.start();
            primaryStage.show();
        });

        menuButton.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.show();
        });

        gameOverRoot.getChildren().addAll(scoreLabel, restartButton, menuButton);

        return gameOverScene;
    }

    public void resetGameState() {
        GameManager.score = 0;
        GameManager.lives = 3;

        flowerBed = new FlowerBed(backgroundContext, foregroundContext, 0, backgroundLayerCanvas.getHeight() - 128);
        wateringCan = new WateringCan(foregroundContext, 64, 384);
        sun = new Sun(foregroundContext, 0, 224);
    }
}
