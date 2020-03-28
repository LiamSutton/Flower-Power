package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Seed extends Growable implements IFlower {
    IFlower delegate;
    public Seed(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/Seed.png");
        setSizeProperties();
        health = 100;
        age = 0;
        delegate = this;
        update();
    }

    public void grow() {
        age += 1;

        delegate.update();
    }
}
