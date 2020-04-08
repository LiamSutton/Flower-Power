package com.liam;

/**
 * This interface is implemented by all Objects which are controlled by the player
 */
public interface IMoveable {

    /**
     * This function will move an Object that implements the Interface in a given direction
     * @param direction The direction the Players input maps to
     */
    void move(int direction);

    int getCurrentPosition();
}
