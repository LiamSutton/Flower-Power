package com.liam;

import javafx.scene.canvas.GraphicsContext;

public class Growable extends GameObject{
    protected int age, health;
    public Growable(GraphicsContext gc, double x, double y, int xSize, int ySize) {
        super(gc, x, y, xSize, ySize);
    }
}
