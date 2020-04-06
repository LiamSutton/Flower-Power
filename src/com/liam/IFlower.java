package com.liam;

/**
 *  This interface is implemented by all Growable objects to give them a common set of functions that
 *  can be modified
 */
public interface IFlower {
    /**
     *
     */
    void update();
    void heal();
    void wilt();
}
