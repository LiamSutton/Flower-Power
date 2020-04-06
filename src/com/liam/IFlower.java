package com.liam;

/**
 *  This interface is implemented by all Growable objects to give them a common set of functions that
 *  can be modified
 */
public interface IFlower {
    /**
     * Does this need to be here lmao?
     */
    void update();

    /**
     *  This will heal the IFlower for a value specified in its implementation
     */
    void heal();

    /**
     *  This will damage the IFlower for a value specified in its implementation
     */
    void wilt();
}
