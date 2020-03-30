package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bud extends Growable implements IFlower {
    public Bud(GraphicsContext gc, double x, double y, double h) {
        super(gc, x, y);
        img = new Image("/Bud.png");
        setSizeProperties();
        maxHealth = 150;
        currentHealth = h;
        update();
    }
}
