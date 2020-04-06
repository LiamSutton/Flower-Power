package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *  This class represents a Watering Can that will be used to heal flowers
 */
public class WateringCan extends GameObject implements IMoveable{

    /**
     *  The current position of the WateringCan on the grid
     */
    private int currentPosition;

    /**
     *  Initializes the WateringCan and sets is location to the 0th place on the grid
     * @param gc The context provided by the canvas (layer) it should be rendered on
     * @param x The X position of the WateringCan
     * @param y The Y position of the WateringCan
     */
    public WateringCan(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image(Constants.WATERING_CAN);
        super.setSizeProperties();
        currentPosition = 0;
        update();
    }


    /**
     * This function will move the WateingCan 1 place in the direction specified, if the move would take the watering
     * can off of the screen, it will loop to the opposite side of the grid
     * @param direction The value the users input maps to
     */
    @Override
    public void move(int direction) {
        if (direction == Constants.DIRECTION_RIGHT) {
            if (currentPosition == 7) {
                currentPosition = 0;
            } else {
                currentPosition += 1;
            }
        }
        if (direction == Constants.DIRECTION_LEFT){
            if (currentPosition == 0) {
                currentPosition = 7;
            } else {
                currentPosition -= 1;
            }

        }
        x = (currentPosition * 128) + 64;
    }

    /**
     *  Gets the current position of the WateringCan on the grid
     * @return The position of the watering can on the grid
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     *  This will heal the Object the delegate represents by a value specified in its implementation
     * @param target The Object delegating a Growable IFlower Object
     */
    public void water(FlowerDelegator target) {
        target.heal();
    }
    
}
