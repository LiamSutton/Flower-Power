package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Blossom extends Growable implements IFlower {
    public Blossom(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("/Blossom.png");
        setSizeProperties();
        health = 175;
        update();
    }
}
