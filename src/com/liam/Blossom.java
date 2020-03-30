package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Blossom extends Growable implements IFlower {
    public Blossom(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);
        img = new Image("/Blossom.png");
        setSizeProperties();
        maxHealth = 175;
        currentHealth = h;
        update();
    }
}
