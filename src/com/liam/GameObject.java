package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents a GameObject which all key on screen elements will derive from
 */
public class GameObject {
    /**
     * The image that will be rendered
     */
    protected Image img;
    /**
     * The X and Y position of the Object on screen
     */
    protected double x,y;

    /**
     * The width and height of the Objects image in pixels
     */
    protected double xSize, ySize;

    /**
     * Context provided by the canvas (layer) the Object resides on
     */
    protected GraphicsContext gc;

    /**
     * Constructor will instantiate a new GameObject
     * @param gc Context provided by the canvas (layer) the Object resides on
     * @param x The X position of the Object
     * @param y The Y position of the Object
     */
    public GameObject(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
    }

    /**
     * Automatically sets the size of the Object by getting the width and height of the image representing it
     */
    protected void setSizeProperties() {
        this.xSize = img.getWidth();
        this.ySize = img.getHeight();
    }

    /**
     * If the Object has a valid image associated with it, draw it to the screen at the given position
     */
    public void update() {
        if (img != null) gc.drawImage(img, x, y, xSize, ySize);
    }
}
