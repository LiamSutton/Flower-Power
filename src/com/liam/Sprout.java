package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprout extends Growable implements IFlower {
    public Sprout(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);

        healthy = Constants.SPROUT_HEALTHY;
        wilted = Constants.SPROUT_WILTED;

        maxHealth = 125;
        currentHealth = h;
        img = new Image(healthy);
        setSizeProperties();

        update();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void heal() {
        currentHealth += 2;
        currentHealth = currentHealth >= maxHealth ? maxHealth : currentHealth;
    }

    @Override
    public void grow() {
        age += 1.5F;
    }

    @Override
    public void wilt() {
        currentHealth -= 15;
    }
}
