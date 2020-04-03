package com.liam;

import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

// TODO: Should FLowerDelegator implement IFLower????
public class FlowerDelegator {
    IFlower delegate;
    Random r;
    protected int age = 0;
    GraphicsContext gc;
    double x,y;
    public FlowerDelegator(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
        delegate = new Seed(gc, x, y);
    }



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
        delegate.update();
    }


    // TODO: investigate potential solution to adding max health checks to every IFlower.
    public void heal() {
        delegate.heal();
    }

    public void wilt() {
        delegate.wilt();
    }

    public void grow() {
        age++;
    }


    public double getCurrentHealth() {
        return ((Growable)delegate).currentHealth;
    }
}
