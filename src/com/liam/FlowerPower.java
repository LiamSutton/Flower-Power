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
    Text livesText;
    FlowerBed flowerBed;
    WateringCan wateringCan;
    Sun sun;
    public static void main(String[] args) {
        launch(args);
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (GameManager.gameOver()) {
                timer.stop();
                gameOverScene = createGameOverScene();
                primaryStage.setScene(gameOverScene);
                primaryStage.show();
            }
            backgroundContext.setFill(Color.SKYBLUE);
            backgroundContext.fillRect(0, 0, backgroundLayerCanvas.getWidth(), backgroundLayerCanvas.getHeight());
            sun.Shine(flowerBed.getFlower(sun.currentPosition));
            flowerBed.update();
            wateringCan.update();
            sun.update();
            scoreText.setText("SCORE: " + GameManager.score);
            livesText.setText("LIVES LEFT: " + GameManager.lives);
        }
    };

    EventHandler<KeyEvent> keyPressedEvent = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.A) wateringCan.move(Constants.DIRECTION_LEFT);
            if (keyEvent.getCode() == KeyCode.D) wateringCan.move(Constants.DIRECTION_RIGHT);
            if (keyEvent.getCode() == KeyCode.SPACE) {
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
        Pane mainMenuRoot = new Pane();
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
        Pane informationRoot = new Pane();
        informationScene = new Scene(informationRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        Label objectiveHeadingLabel = new Label("Objective");
        objectiveHeadingLabel.setFont(new Font("Verdana", 32));
        objectiveHeadingLabel.setLayoutX(400);
        objectiveHeadingLabel.setLayoutY(50);
        objectiveHeadingLabel.setPrefSize(1024, 100);

        Label objectiveLabel = new Label(Constants.OBJECTIVE_MESSAGE);
        objectiveLabel.setFont(new Font("Verdana", 22));
        objectiveLabel.setLayoutX(100);
        objectiveLabel.setLayoutY(150);
        objectiveLabel.setPrefSize(1024, 200);

        Label movementHeadingLabel = new Label("How to move");
        movementHeadingLabel.setFont(new Font("Verdana", 32));
        movementHeadingLabel.setLayoutX(375);
        movementHeadingLabel.setLayoutY(350);
        movementHeadingLabel.setPrefSize(1024, 100);

        Label wateringCanLabel = new Label(Constants.WATERING_CAN_INSTRUCTION);
        wateringCanLabel.setFont(new Font("Verdana", 22));
        wateringCanLabel.setLayoutX(250);
        wateringCanLabel.setLayoutY(400);
        wateringCanLabel.setPrefSize(1024, 100);

        Label sunLabel = new Label(Constants.SUN_INSTRUCTION);
        sunLabel.setFont(new Font("Verdana", 22));
        sunLabel.setLayoutX(250);
        sunLabel.setLayoutY(450);
        sunLabel.setPrefSize(1024, 100);

        Button backButton = new Button("Go Back");
        backButton.setFont(new Font("Verdana", 24));
        backButton.setLayoutX(300);
        backButton.setLayoutY(550);
        backButton.setPrefSize(350, 100);

        informationScene.setCursor(Cursor.DEFAULT);

        backButton.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.show();
        });

        informationRoot.getChildren().addAll(objectiveHeadingLabel, movementHeadingLabel, objectiveLabel, wateringCanLabel, sunLabel, backButton);

        return informationScene;
    }

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
        scoreText.setFont(new Font("Consolas", 24));
        scoreText.setFill(Color.WHITE);

        livesText = new Text(0, 50, "LIVES LEFT: " + GameManager.lives);
        livesText.setFont(new Font("Consolas", 24));
        livesText.setFill(Color.WHITE);

        gameRoot.getChildren().addAll(backgroundLayerCanvas, foregroundLayerCanvas, scoreText, livesText);

        gameScene.setOnKeyPressed(keyPressedEvent);
        System.out.println(javafx.scene.text.Font.getFamilies());

        return gameScene;
    }

    public Scene createGameOverScene() {
        Pane gameOverRoot = new Pane();
        gameOverScene = new Scene(gameOverRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Label scoreLabel = new Label("Your score was: " + GameManager.score);
        scoreLabel.setFont(new Font("Arial Black", 32));
        scoreLabel.setLayoutX(300);
        scoreLabel.setLayoutY(100);
        scoreLabel.setPrefSize(500, 100);

        Button restartButton = new Button("Play again");
        restartButton.setFont(new Font("Verdana", 24));
        restartButton.setLayoutX(300);
        restartButton.setLayoutY(300);
        restartButton.setPrefSize(350, 100);

        Button menuButton = new Button("Go to menu");
        menuButton.setFont(new Font("Verdana", 24));
        menuButton.setLayoutX(300);
        menuButton.setLayoutY(450);
        menuButton.setPrefSize(350, 100);

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
        GameManager.resetGameVariables();
        flowerBed = new FlowerBed(backgroundContext, foregroundContext, 0, backgroundLayerCanvas.getHeight() - 128);
        wateringCan = new WateringCan(foregroundContext, 64, 384);
        sun = new Sun(backgroundContext, 0, 224);
    }
}
