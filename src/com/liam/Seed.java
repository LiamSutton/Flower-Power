package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public class Seed extends Growable implements IFlower {
    IFlower delegate;
    Random r;
    public Seed(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/Seed.png");
        setSizeProperties();
        maxHealth = 100;
        r = new Random();
        currentHealth = r.nextInt(100);
        age = 0;
        delegate = this;
        update();
    }



    public void grow() {
        age += 1;
        if (age == 100) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Sprout(gc, x, y, h);
        }
        if (age == 200) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Bud(gc, x, y, h);
        }
        if (age == 300) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Blossom(gc, x, y, h);
        }
        if (age == 400) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Bloom(gc, x, y, h);
        }
        if (age == 500) {
            delegate = new Seed(gc, x, y);
            age = 0;
        }
        delegate.update();
    }

    public void heal() {
        ((Growable)delegate).currentHealth += 50;
    }

    public double getCurrentHealth() {
        return ((Growable)delegate).currentHealth;
    }
}
