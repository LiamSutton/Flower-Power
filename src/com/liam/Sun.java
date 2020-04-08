package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * This class represents a Sun Object the player controls to grow the flowers
 */
public class Sun extends GameObject implements IMoveable {
    /**
     * The current position of the Sun on the grid
     */
    private int currentPosition;

    /**
     * A fixed array of Y co-ordinates representing the Suns position when on the relevant position of the grid
     */
    private final int[] yPositions = Constants.SUN_Y_POSITIONS; // Quicker than calculating on the fly

    /**
     * Instantiates the Object and sets its grid position to the 0th index
     * @param gc The context provided by the canvas (layer) it resides on
     * @param x The X position of the Object
     * @param y the Y position of the Object
     */
    public Sun(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image(Constants.SUN);
        super.setSizeProperties();
        currentPosition = 0;
        update();
    }
    /**
     * This function will move the Sun 1 place in the direction specified, if the move would take the Sun
     * off of the screen, it will loop to the opposite side of the grid
     * @param direction The value the users input maps to
     */
    @Override
    public void move(int direction) {
        if (direction == Constants.DIRECTION_RIGHT) {
            if (currentPosition == Constants.GRID_END) {
                currentPosition = Constants.GRID_START;
            } else {
                currentPosition++;
            }
        }
        if (direction == Constants.DIRECTION_LEFT) {
            if (currentPosition == Constants.GRID_START) {
                currentPosition = Constants.GRID_END;
            } else {
                currentPosition--;
            }
        }
        y = yPositions[currentPosition];
        x = (currentPosition * Constants.GRID_COL_WIDTH);

    }


    /**
     *  Draws the Sun and its Ray
     */
    @Override
    public void update() {
        super.update();
        castRay();
    }

    /**
     * Attempts to grow the Object represented by the delegate the Object represents
     * @param target The Object representing the delegate
     */
    protected void Shine(FlowerDelegator target) {
        target.grow();
    }

    /**
     * Casts a ray to indicate the flower that is currently getting grown by the Sun
     */
    private void castRay() {
        gc.setGlobalAlpha(0.2);
        gc.setFill(Color.DARKORANGE);
        gc.setStroke(Color.YELLOW);


        gc.fillPolygon(new double[]{x, x+64, x+128}, new double[]{700, y+128, 700}, 3);
        gc.setGlobalAlpha(1);
    }

    public int getCurrentPosition(){
        return currentPosition;
    }
}
