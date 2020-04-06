package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Seed extends Growable implements IFlower {
    public Seed(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        healthy = Constants.SEED_HEALTHY;
        wilted = Constants.SEED_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 100;
        currentHealth = maxHealth;
        update();
    }


    @Override
    public void heal() {
        currentHealth += Constants.SEED_HEAL_VALUE;
        currentHealth = currentHealth >= maxHealth ? maxHealth : currentHealth;
    }

    @Override
    public void wilt() {
        currentHealth -= Constants.SEED_WILT_RATE;
    }

}
