package com.liam;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WateringCan extends GameObject implements IMoveable{
    private int currentPosition;
    public WateringCan(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/WateringCan.png");
        super.setSizeProperties();
        currentPosition = 0;
        update();
    }

    @Override
    public void update() {
        super.update();
    }

    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
