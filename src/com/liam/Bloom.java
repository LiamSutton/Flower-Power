package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bloom extends Growable implements IFlower {
    public Bloom(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);
        img = new Image("/Bloom.png");
        setSizeProperties();
        maxHealth = 200;
        currentHealth = h;
        update();
    }
}
