package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Blossom extends Growable implements IFlower {
    public Blossom(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);
        healthy = Constants.BLOSSOM_HEALTHY;
        wilted = Constants.BLOSSOM_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 175;
        currentHealth = h;
        update();
    }

    @Override
    public void heal() {
        currentHealth += 4;
        currentHealth = currentHealth >= maxHealth ? maxHealth : currentHealth;
    }

    @Override
    public void grow() {

        age += 1.85F;
    }

    @Override
    public void wilt() {
        currentHealth -= Constants.BLOSSOM_WILT_RATE;
    }
}
