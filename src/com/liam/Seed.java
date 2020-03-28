package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Seed extends Growable implements IFlower {
    IFlower delegate;
    public Seed(GraphicsContext gc, double x, double y, int xSize, int ySize) {
        super(gc, x, y, xSize, ySize);
        img = new Image("/Seed.png");
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
