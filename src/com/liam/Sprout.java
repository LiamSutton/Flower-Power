package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprout extends Growable implements IFlower {
    public Sprout(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/Sprout.png");
        setSizeProperties();
        maxHealth = 125;
        currentHealth = maxHealth;
        update();
    }

    @Override
    public void update() {
        super.update();
    }
}
