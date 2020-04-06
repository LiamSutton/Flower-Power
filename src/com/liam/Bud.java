package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a Bud Object which is the 3rd stage in the flower growth
 */
public class Bud extends Growable implements IFlower {
    /**
     * Instantiates the Bud and sets its current health to the value passed to it from its Ancestor
     * @param gc Context provided by the canvas (layer) the Bud resides on
     * @param x The X position of the Bud
     * @param y the Y position of the Bud
     * @param passedHealth The health of the ancestor of this Object
     */
    public Bud(GraphicsContext gc, double x, double y, double passedHealth) {
        super(gc, x, y);
        healthy = Constants.BUD_HEALTHY;
        wilted = Constants.BUD_WILTED;
        img = new Image(healthy);
        setSizeProperties();
        maxHealth = 150;
        currentHealth = passedHealth;
        update();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void heal() {
        currentHealth += Constants.BUD_HEAL_VALUE;
        clampHealth();
    }

    /**
     * See IFlower interface
     */
    @Override
    public void wilt() {
        currentHealth -= Constants.BUD_WILT_RATE;
    }

}
