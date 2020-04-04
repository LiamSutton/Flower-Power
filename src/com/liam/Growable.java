package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Growable extends GameObject{
    protected int age, maxHealth;
    protected double currentHealth;
    protected String healthy;
    protected String wilted;
    protected boolean isWilted;

    public Growable(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        isWilted = false;
    }

    @Override
    public void update() {
        super.update();
        tickDamage();
        isWilted = checkIsWilted();

        double healthVal = (currentHealth / maxHealth) * 100;
        if (healthVal >= 75F) {
            gc.setFill(Color.GREEN);
            gc.setStroke(Color.GREEN);
        } else if (healthVal >= 50F) {
            gc.setFill(Color.YELLOWGREEN);
            gc.setStroke(Color.YELLOWGREEN);
        } else if (healthVal >= 25F) {
            gc.setFill(Color.ORANGE);
            gc.setStroke(Color.ORANGE);
        } else {
            gc.setFill(Color.RED);
            gc.setStroke(Color.RED);
        }

        gc.setFont(Font.font("Verdana", 20));
        gc.strokeText(Integer.toString((int)this.currentHealth), x + 48, y + 196);
        gc.fillText(Integer.toString((int)this.currentHealth), x + 48, y + 196);
    }

    public void tickDamage() {
//        currentHealth -= 1F;
    }

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
}
