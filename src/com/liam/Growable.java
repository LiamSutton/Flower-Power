package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents an extension of GameObject for Objects that grow (flowers)
 */
public class Growable extends GameObject{
    /**
     * The maxHealth possible for the Object
     */
    protected int maxHealth;

    /**
     * The current health of the Object
     */
    protected double currentHealth;

    /**
     * A path to the image for the Objects healthy state
     */
    protected String healthy;

    /**
     * A path to the image for the Objects wilted state
     */
    protected String wilted;

    /**
     * Whether the Object is currently in its wilted state
     */
    protected boolean isWilted;

    /**
     * Instantiates the Object and sets it to be not wilted by default
     * @param gc The context provided by the canvas (layer) the Object resides on
     * @param x The X position of the Object
     * @param y the Y position of the Object
     */
    public Growable(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        isWilted = false;
    }

    /**
     * Checks if the Object is in its wilted state and changes its health colour / image accordingly then displays
     * the health of the Object
     */
    @Override
    public void update() {
        super.update();
        isWilted = checkIsWilted();
        setHealthColour();
        displayHealth();
    }


    /**
     * Converts the Objects health into a percentage and if it is below 50%, changes it to its wilted state
     * @return whether the Object is in its wilted state
     */
    public boolean checkIsWilted() {
        double h = (currentHealth / maxHealth) * 100;
        if (h > 50F) {
            img = new Image(healthy);
            return false;
        } else {
            img = new Image(wilted);
            return true;
        }
    }

    /**
     *  ensures that a Growable Objects health never exceeds its defined limit
     */
    public void clampHealth() {
        currentHealth = currentHealth > maxHealth ? maxHealth : currentHealth;
    }

    /**
     * Changes the colour of the Objects health UI depending on its current health value
     */
    public void setHealthColour() {
        double healthVal = (currentHealth / maxHealth) * 100;
        if (healthVal >= 50F) {
            gc.setFill(Color.FORESTGREEN);
            gc.setStroke(Color.FORESTGREEN);
        } else if (healthVal >= 25F) {
            gc.setFill(Color.YELLOW);
            gc.setStroke(Color.YELLOW);
        } else {
            gc.setFill(Color.RED);
            gc.setStroke(Color.RED);
        }
    }

    /**
     * Displays the current health of the Object
     */
    public void displayHealth() {
        gc.setFont(Constants.HEALTH_FONT);
        gc.strokeText(Integer.toString((int)this.currentHealth), x + 48, y + 196);
        gc.fillText(Integer.toString((int)this.currentHealth), x + 48, y + 196);
    }
}
