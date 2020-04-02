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
    Pane gameRoot;
    Stage primaryStage;
    Scene gameScene;
    Scene mainMenuScene;

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
        }
    };

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        mainMenuScene = createMainMenu();
        gameScene = createGameScene();

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();

    }

    public Scene createMainMenu() {
        mainMenuRoot = new Pane();
        mainMenuScene = new Scene(mainMenuRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        // Create UI for Main Menu
        Label greetingsLabel = new Label("Welcome to Flower Power!");
        greetingsLabel.setLayoutX(300);
        greetingsLabel.setLayoutY(412);

        Button startGameButton = new Button("Start Game");
        startGameButton.setLayoutX(300);
        startGameButton.setLayoutY(512);

        mainMenuScene.setCursor(Cursor.DEFAULT);
        // Hook up scene transition
        startGameButton.setOnAction(e-> {
            // Transition to game scene
            primaryStage.setScene(gameScene);
            timer.start();
            primaryStage.show();
        });

        // Add Main Menu components to the Pane
        mainMenuRoot.getChildren().addAll(greetingsLabel, startGameButton);
        return mainMenuScene;
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
}
