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
        currentHealth += Constants.BLOOM_HEAL_VALUE;
        currentHealth = currentHealth >= maxHealth ? maxHealth : currentHealth;
    }

    @Override
    public void wilt() {
        currentHealth -= Constants.BLOOM_WILT_RATE;
    }

}
