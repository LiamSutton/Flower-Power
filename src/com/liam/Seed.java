package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Seed extends Growable implements IFlower {
    IFlower delegate;
    public Seed(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/Seed.png");
        setSizeProperties();
        maxHealth = 100;
        currentHealth = maxHealth;
        age = 0;
        delegate = this;
        update();
    }



    public void grow() {
        age += 1;
        if (age == 100) {
            delegate = new Sprout(gc, x, y);
        }
        if (age == 200) {
            delegate = new Bud(gc, x, y);
        }
        if (age == 300) {
            delegate = new Blossom(gc, x, y);
        }
        if (age == 400) {
            delegate = new Bloom(gc, x, y);
        }
        if (age == 500) {
            delegate = new Seed(gc, x, y);
            age = 0;
        }
        delegate.update();
    }
}
