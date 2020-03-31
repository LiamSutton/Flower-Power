package com.liam;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FlowerPower extends Application {
    Pane root;
    Scene scene;
    Canvas backgroundLayerCanvas;
    Canvas foregroundLayerCanvas;
    GraphicsContext backgroundContext;
    GraphicsContext foregroundContext;

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
        }
    };

    @Override
    public void start(Stage stage) throws Exception {
        root = new Pane();

        scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        backgroundLayerCanvas = new Canvas(scene.getWidth(), scene.getHeight());
        backgroundContext = backgroundLayerCanvas.getGraphicsContext2D();

        foregroundLayerCanvas = new Canvas(scene.getWidth(), scene.getHeight());
        foregroundContext = foregroundLayerCanvas.getGraphicsContext2D();

        backgroundContext.setFill(Color.SKYBLUE);
        backgroundContext.fillRect(0, 0, backgroundLayerCanvas.getWidth(), backgroundLayerCanvas.getHeight());

        flowerBed = new FlowerBed(backgroundContext, foregroundContext, 0, backgroundLayerCanvas.getHeight() - 128);
        wateringCan = new WateringCan(foregroundContext, 64, 384);
        sun = new Sun(foregroundContext, 16, 128);
        root.getChildren().addAll(backgroundLayerCanvas, foregroundLayerCanvas);

        scene.setOnKeyPressed(keyPressedEvent);

        scene.setCursor(Cursor.NONE);
        stage.setScene(scene);
        stage.setTitle(Constants.GAME_TITLE);
        stage.show();

        timer.start();
    }
}
