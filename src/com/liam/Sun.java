package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.Flow;

public class Sun extends GameObject implements IMoveable {
    int currentPosition = 0;
    final int[] yPositions = {224, 128, 32, 0, 0, 32, 128, 224};
    public Sun(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image(Constants.SUN);
        super.setSizeProperties();
        update();
    }

    @Override
    public void move(int direction) {
        if (direction == Constants.DIRECTION_RIGHT) {
            if (currentPosition == 7) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
        }
        if (direction == Constants.DIRECTION_LEFT) {
            if (currentPosition == 0) {
                currentPosition = 7;
            } else {
                currentPosition--;
            }
        }
        y = yPositions[currentPosition];
        x = (currentPosition * 128);

    }

    @Override
    public void update() {
        super.update();
        gc.setGlobalAlpha(0.2);
        gc.setFill(Color.DARKORANGE);
        gc.setStroke(Color.YELLOW);


        gc.fillPolygon(new double[]{x, x+64, x+128}, new double[]{700, y+128, 700}, 3);
        gc.setGlobalAlpha(1);
    }

    public void Shine(FlowerDelegator target) {

        target.grow();

    }
}
