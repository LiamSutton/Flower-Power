package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
    protected Image img;
    protected double x,y;
    protected double xSize, ySize;
    protected GraphicsContext gc;

    public GameObject(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
    }

    // TODO: Possibly check for null?
    public void setSizeProperties() {
        this.xSize = img.getWidth();
        this.ySize = img.getHeight();
    }

    // If the GameObject has an Image, draw it to the screen at a given position
    public void update() {
        if (img != null) gc.drawImage(img, x, y, xSize, ySize);
    }
}
