package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class Sun extends GameObject implements IMoveable {
    int currentPosition = 0;
    final int[] yPositions = {160, 64, 32, 32, 32, 32, 64, 160};
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
        x = (currentPosition * 128) + 16;



        System.out.println("Stats");
        System.out.println("X:" + x + ", Y: " + y);
        System.out.println("Current Position: " + currentPosition);
    }

    @Override
    public void update() {
        super.update();
    }
}
