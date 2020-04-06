package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WateringCan extends GameObject implements IMoveable{
    private int currentPosition;
    public WateringCan(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image(Constants.WATERING_CAN);
        super.setSizeProperties();
        currentPosition = 0;
        update();
    }

    @Override
    public void update() {
        super.update();
    }

    public void move(int direction) {
        if (direction == Constants.DIRECTION_RIGHT) {
            if (currentPosition == 7) {
                currentPosition = 0;
            } else {
                currentPosition += 1;
            }
        }
        if (direction == Constants.DIRECTION_LEFT){
            if (currentPosition == 0) {
                currentPosition = 7;
            } else {
                currentPosition -= 1;
            }

        }
        x = (currentPosition * 128) + 64;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void water(FlowerDelegator target) {
        target.heal();
    }

    public void wilt(FlowerDelegator target) {
        target.wilt();
    }
}
