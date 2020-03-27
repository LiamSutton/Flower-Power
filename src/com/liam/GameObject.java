package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
    protected Image img;
    protected double x,y;
    protected int xSize, ySize;
    protected GraphicsContext gc;

    public GameObject(GraphicsContext gc, double x, double y, int xSize, int ySize) {
        this.gc = gc;
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    // If the GameObject has an Image, draw it to the screen at a given position
    public void update() {
        if (img != null) gc.drawImage(img, x, y, xSize, ySize);
    }
}
