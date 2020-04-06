package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *  Represents a Sprout Object which is the 2nd stage of flower growth
 */
public class Sprout extends Growable implements IFlower {
    /**
     * Instantiates a Sprout Object and sets its current health to the value of the Object from the previous
     * Growth stage
     * @param gc Context provided by the canvas (layer) the Sprout resides on
     * @param x The X position of the Sprout
     * @param y The Y position of the Sprout
     * @param passedHealth The health of the ancestor of this Object
     */
    public Sprout(GraphicsContext gc, double x, double y, double passedHealth) {
        super(gc, x, y);

        healthy = Constants.SPROUT_HEALTHY;
        wilted = Constants.SPROUT_WILTED;

        maxHealth = 125;
        currentHealth = passedHealth;
        img = new Image(healthy);
        setSizeProperties();

        update();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void heal() {
        currentHealth += Constants.SPROUT_HEAL_VALUE;
        clampHealth();
    }


    /**
     * See IFlower interface
     */
    @Override
    public void wilt() {
        currentHealth -= Constants.SPROUT_WILT_RATE;
    }
}
