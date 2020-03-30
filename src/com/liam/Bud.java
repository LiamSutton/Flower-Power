package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bud extends Growable implements IFlower {
    public Bud(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);
        healthy = Constants.BUD_HEALTHY;
        wilted = Constants.BUD_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 150;
        currentHealth = h;
        update();
    }
}
