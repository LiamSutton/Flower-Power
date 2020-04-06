package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a Blossom Object which is the 4th stage of flower growth
 */
public class Blossom extends Growable implements IFlower {
    /**
     * Instantiates the Blossom Object and sets its current health to the value of passedHealth
     * @param gc The context provided by the canvas (layer) the Object resides on
     * @param x The X position of the Blossom
     * @param y the Y position of the Blossom
     * @param passedHealth the health value passed from the ancestor Object
     */
    public Blossom(GraphicsContext gc, double x, double y, double passedHealth) {
        super(gc, x, y);
        healthy = Constants.BLOSSOM_HEALTHY;
        wilted = Constants.BLOSSOM_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 175;
        currentHealth = passedHealth;
        update();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void heal() {
        currentHealth += Constants.BLOSSOM_HEAL_VALUE;
        clampHealth();
    }


    /**
     * See IFlower interface
     */
    @Override
    public void wilt() {
        currentHealth -= Constants.BLOSSOM_WILT_RATE;
    }
}
