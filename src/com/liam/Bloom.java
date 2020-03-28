package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bloom extends Growable implements IFlower {
    public Bloom(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/Bloom.png");
        setSizeProperties();
        health = 200;
        update();
    }
}
