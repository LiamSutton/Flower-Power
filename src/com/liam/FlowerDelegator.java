package com.liam;

import javafx.scene.canvas.GraphicsContext;

/**
 *  This class is used to manage the lifecycle of an Object which extends Growable and implements the IFLower interface
 */
public class FlowerDelegator {
    /**
     * Reference to the delegate
     */
    IFlower delegate;

    /**
     * Represents the growth of the flower
     */
    protected int age = 0;

    /**
     * Represents the context provided by the canvas (layer) the Object should be rendered on
     */
    GraphicsContext gc;

    /**
     * Represents the X and Y position the FlowerDelegator will spawn Objects at
     */
    double x,y;

    /**
     * The Constructor will initialise the Object and instantiate the delegate as a Seed
     * @param gc Context provided by the Canvas
     * @param x The X position of the Object
     * @param y The Y position of the Object
     */
    public FlowerDelegator(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
        delegate = new Seed(gc, x, y);
    }


    /**
     * This function performs a check on whether the delegate needs to be moved to the next stage of growth
     * as well as applying tick damage and running the delegates update function
     */
    public void checkGrowth() {
        if (age == 100) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Sprout(gc, x, y, h);
        }
        if (age == 200) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Bud(gc, x, y, h);
        }
        if (age == 300) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Blossom(gc, x, y, h);
        }
        if (age == 400) {
            double h = ((Growable)delegate).currentHealth;
            delegate = new Bloom(gc, x, y, h);
        }
        if (age == 500) {
            delegate = new Seed(gc, x, y);
            age = 0;
            GameManager.score += 100;
        }

        if (getCurrentHealth() <= 0) {
            delegate = new Seed(gc, x, y);
            GameManager.lives--;
        }
        delegate.wilt();
        delegate.update();
    }


    // TODO: investigate potential solution to adding max health checks to every IFlower.

    /**
     * Heals the delegate for a value specified in its implementation
     */
    public void heal() {
        delegate.heal();
    }

    /**
     * Damages the delegate for a value specified in its implementation
     */
    public void wilt() {
        delegate.wilt();
    }

    /**
     * If the delegate is in a healthy state, increment its age
     */
    public void grow() {
        if (!getIsWilted()) {
            age++;
        }
    }


    /**
     * Gets the delegates current health
     * @return the current health of the delegate
     */
    public double getCurrentHealth() {
        return ((Growable)delegate).currentHealth;
    }

    /**
     * Gets whether the delegate is in a wilted state
     * @return The delegates wilted state
     */
    public boolean getIsWilted() {return ((Growable)delegate).isWilted;}
}
