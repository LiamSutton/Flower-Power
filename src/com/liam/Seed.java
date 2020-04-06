package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *  This class represents a Seed Object which is the 1st growth stage of the flower
 */
public class Seed extends Growable implements IFlower {

    /**
     * Initialises the Seed Object and provides a path to both its healthy and wilted images
     * @param gc The context provided by the canvas (layer) it resides on
     * @param x The X position of the Seed
     * @param y the Y position of the Seed
     */
    public Seed(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        healthy = Constants.SEED_HEALTHY;
        wilted = Constants.SEED_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 100;
        currentHealth = maxHealth;
        update();
    }


    /**
     * See IFlower interface
     */
    @Override
    public void heal() {
        currentHealth += Constants.SEED_HEAL_VALUE;
        clampHealth();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void wilt() {
        currentHealth -= Constants.SEED_WILT_RATE;
    }

}
