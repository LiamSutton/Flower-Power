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
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        gameRoot = new Pane();
        mainMenuRoot = new Pane();

        gameScene = new Scene(gameRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        mainMenuScene = new Scene(mainMenuRoot, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        Label greetingsLabel = new Label("Welcome to Flower Power!");
        Button startGameButton = new Button("Start game");
        greetingsLabel.setLayoutY(284);
        greetingsLabel.setLayoutX(350);

        startGameButton.setLayoutY(384);
        startGameButton.setLayoutX(350);

        startGameButton.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            primaryStage.show();
            timer.start();
        });

        mainMenuRoot.setCursor(Cursor.DEFAULT);
        mainMenuRoot.getChildren().addAll(greetingsLabel, startGameButton);

        backgroundLayerCanvas = new Canvas(gameScene.getWidth(), gameScene.getHeight());
        backgroundContext = backgroundLayerCanvas.getGraphicsContext2D();

        foregroundLayerCanvas = new Canvas(gameScene.getWidth(), gameScene.getHeight());
        foregroundContext = foregroundLayerCanvas.getGraphicsContext2D();

        backgroundContext.setFill(Color.SKYBLUE);
        backgroundContext.fillRect(0, 0, backgroundLayerCanvas.getWidth(), backgroundLayerCanvas.getHeight());

        flowerBed = new FlowerBed(backgroundContext, foregroundContext, 0, backgroundLayerCanvas.getHeight() - 128);
        wateringCan = new WateringCan(foregroundContext, 64, 384);
        sun = new Sun(foregroundContext, 0, 224);
        scoreText = new Text(0, 20, "SCORE: " + GameManager.score);
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(new Font("verdana", 20));
        gameRoot.getChildren().addAll(backgroundLayerCanvas, foregroundLayerCanvas, scoreText);

        gameScene.setOnKeyPressed(keyPressedEvent);

        gameScene.setCursor(Cursor.NONE);
        stage.setScene(mainMenuScene);
        stage.setTitle(Constants.GAME_TITLE);
        stage.show();
    }
}
