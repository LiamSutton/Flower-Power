package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WateringCan extends GameObject {
    public WateringCan(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/WateringCan.png");
        super.setSizeProperties();
        update();
    }
}
