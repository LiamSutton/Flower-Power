package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bloom extends Growable implements IFlower {
    public Bloom(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);
        healthy = Constants.BLOOM_HEALTHY;
        wilted = Constants.BLOOM_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 200;
        currentHealth = h;
        update();
    }

    @Override
    public void heal() {
        currentHealth += 5;
    }

    @Override
    public void wilt() {
        currentHealth -= 30;
    }

    @Override
    public void grow() {
        age += 2F;
    }
}
