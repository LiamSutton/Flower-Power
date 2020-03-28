package com.liam;

import javafx.scene.canvas.GraphicsContext;

public class Growable extends GameObject{
    protected int age, health;
    public Growable(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
    }
}
