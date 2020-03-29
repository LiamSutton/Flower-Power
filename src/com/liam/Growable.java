package com.liam;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Growable extends GameObject{
    protected int age, maxHealth;
    protected double currentHealth;
    protected Text healthUI;
    public Growable(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
    }

    @Override
    public void update() {
        super.update();
        tickDamage();

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
}
