package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a Bloom Object which is the 5th and final stage of flower growth
 */
public class Bloom extends Growable implements IFlower {
    /**
     * Instantiates the Object and sets its current health to the value of passedHealth
     * @param gc The context provided by the canvas (layer) the Object resides on
     * @param x The X position of the Bloom
     * @param y the Y position of the Bloom
     * @param passedHealth the heath value of the ancestor Object
     */
    public Bloom(GraphicsContext gc, double x, double y, double passedHealth) {
        super(gc, x, y);
        healthy = Constants.BLOOM_HEALTHY;
        wilted = Constants.BLOOM_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 200;
        currentHealth = passedHealth;
        update();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void heal() {
        currentHealth += Constants.BLOOM_HEAL_VALUE;
        clampHealth();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void wilt() {
        currentHealth -= Constants.BLOOM_WILT_RATE;
    }

}
